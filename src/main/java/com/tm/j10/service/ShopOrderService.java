package com.tm.j10.service;

import com.tm.j10.domain.*;
import com.tm.j10.domain.enumeration.OrderStatus;
import com.tm.j10.repository.*;
import com.tm.j10.web.rest.vm.OderVM;
import com.tm.j10.web.rest.vm.ProductOrderVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopOrderService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final StorageRepository storageRepository;
    private final OrderDescRepository orderDescRepository;
    private final ShopOrderRepository shopOrderRepository;

    public ShopOrderService(CustomerRepository customerRepository, ProductRepository productRepository, StorageRepository storageRepository, OrderDescRepository orderDescRepository, ShopOrderRepository shopOrderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.storageRepository = storageRepository;
        this.orderDescRepository = orderDescRepository;
        this.shopOrderRepository = shopOrderRepository;
    }

    private void validateNumber(Long number) {
        if (number == 0) {
            throw new RuntimeException("Invalid number: 0 is invalid");
        }
        if (number < 0) {
            throw new RuntimeException("Invalid number: can not be negative");
        }
    }

    public void closeOrder(Long id){
            this.validateNumber(id);
            Optional<ShopOrder> opOrder = shopOrderRepository.findById(id);
            if (opOrder.isPresent()) {
                ShopOrder currentShopOrder = opOrder.get();
                OrderStatus currentStatus = currentShopOrder.getOrderStatus();
                if (currentStatus.equals(OrderStatus.CANCEL_BY_SHOP)||
                    currentStatus.equals(OrderStatus.COMPLETE)||
                    currentStatus.equals(OrderStatus.CANCEL_BY_USER))
                {
                    throw new RuntimeException(currentStatus + " :invalid status");
                }

            currentShopOrder.setOrderStatus(OrderStatus.CANCEL_BY_USER);
            shopOrderRepository.save(currentShopOrder);
        } else {
            throw new RuntimeException("Not found Order");
        }
    }

    public void processOrder(Long orderId) {
        this.validateNumber(orderId);
        var optOrder = this.shopOrderRepository.findById(orderId);
        if (optOrder.isPresent()) {
            ShopOrder currentShopOrder = optOrder.get();
            if (currentShopOrder.getOrderStatus().equals(OrderStatus.CREATED)) {
                currentShopOrder.setOrderStatus(OrderStatus.PROCESS);
                this.shopOrderRepository.save(currentShopOrder);
            } else {
                throw new RuntimeException("Invalid order status");
            }
        } else {
            throw new RuntimeException("Not found order");
        }
    }

    public void closeOrderByAdmin(Long id) {
        this.validateNumber(id);
        Optional<ShopOrder> shopOrder = shopOrderRepository.findById(id);
        if (shopOrder.isPresent()) {
            ShopOrder currentShopOder = shopOrder.get();
            OrderStatus currentStatus = currentShopOder.getOrderStatus();
            if (currentStatus.equals(OrderStatus.CANCEL_BY_SHOP) ||
                currentStatus.equals(OrderStatus.COMPLETE) ||
                currentStatus.equals(OrderStatus.CANCEL_BY_USER)) {
                throw new RuntimeException(currentStatus + " :invalid status");
            }
            currentShopOder.setOrderStatus(OrderStatus.CANCEL_BY_SHOP);
            shopOrderRepository.save(currentShopOder);
        } else {
            throw new RuntimeException("Not found Order");
        }
    }

    public Page<ShopOrder> getAllByCustomerId(Long customerId, Pageable pageable) {
        this.validateNumber(customerId);
        var customerOtp = this.customerRepository.findById(customerId);
        if (customerOtp.isPresent()) {
            return this.shopOrderRepository.findByCustomerId(customerId, pageable);
        } else {
            throw new RuntimeException("Not found customer");
        }
    }

    public Page<ShopOrder> getAllByCustomerIdAndOrderStatus(Long customerId, OrderStatus orderStatus, Pageable pageable) {
        this.validateNumber(customerId);
        var customerOtp = this.customerRepository.findById(customerId);
        if (customerOtp.isPresent()) {
            return this.shopOrderRepository.findByCustomerIdAndOrderStatus(customerId, orderStatus, pageable);
        } else {
            throw new RuntimeException("Not found customer");
        }
    }


    public void createOrderProduct(OderVM oderVM) {
        // Validation
        if (oderVM.getUserId() == null) {
            throw new RuntimeException("Id is invalid");
        }
        for (ProductOrderVM vm : oderVM.getProductVMSet()) {
            if (vm.getQuantity() < 0) {
                throw new RuntimeException("Quantity is invalid");
            }

            if (vm.getProductSizeId() == null || vm.getProductId() == null || vm.getColorId() == null) {
                throw new RuntimeException("Data is invalid");
            }
        }


        Optional<Customer> optionalCustomer = this.customerRepository.findCustomerByUserId(oderVM.getUserId());
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException("Customer not found");
        }
        var customer = optionalCustomer.get();
        String deliveryAddress = oderVM.getSubAddress() + ", "
            + customer.getWard().getWardName() + ", "
            + customer.getDistrict().getDistrictName() + ", "
            + customer.getProvince().getProvinceName();

        Set<ProductOrderVM> productInPutList = oderVM.getProductVMSet();
        Set<OrderDesc> orderDescSet = new HashSet<>();


        ShopOrder newShopOrder = new ShopOrder();
        newShopOrder.setCustomer(customer);
        newShopOrder.setOrderStatus(OrderStatus.CREATED);
        newShopOrder.setDeliveryAddress(deliveryAddress);
        newShopOrder.setWard(customer.getWard());
        newShopOrder.setDistrict(customer.getDistrict());
        newShopOrder.setProvince(customer.getProvince());
        newShopOrder.setCreatedDate(1L);

        var totalPrice = Double.valueOf(0);

        for (ProductOrderVM p : productInPutList) {

            Optional<Product> product = this.productRepository.findByIdAndIsValidAndIsEnable(p.getProductId(), true, true);

            if (product.isEmpty()) {
                throw new RuntimeException("Product is invalid");
            }

            List<Storage> storagesList = this.storageRepository.findByProductIdAndProductSizeIdAndColorId(
                p.getProductId(), p.getProductSizeId(), p.getColorId()
            );
            if (storagesList.isEmpty()) {
                throw new RuntimeException("Storage is invalid");
            }

            Optional<Storage> storage = this.checkAndChooseStorage(storagesList, p.getQuantity());
            if (storage.isEmpty()) {
                throw new RuntimeException("Storage is invalid");
            }
            var finalPrice = p.getQuantity() * Double.valueOf(product.get().getFinalPrice());

            OrderDesc newOrderDesc = new OrderDesc();
            newOrderDesc.setCount(p.getQuantity());
            newOrderDesc.setOrderPrice(Double.valueOf(product.get().getFinalPrice()));
            newOrderDesc.setFinalPrice(finalPrice);
            newOrderDesc.setStorage(storage.get());
            newOrderDesc.setShopOrder(newShopOrder);

            orderDescSet.add(newOrderDesc);

            totalPrice += finalPrice;
        }

        newShopOrder.setTotalPrice(totalPrice);
        newShopOrder.setOrderDescs(orderDescSet);

        this.shopOrderRepository.save(newShopOrder);
        this.orderDescRepository.saveAll(orderDescSet);
    }

    private Optional<Storage> checkAndChooseStorage(List<Storage> storageList, Long orderQuantity) {
        return storageList.stream()
            .filter(s -> s.getCapacity() > (this.orderDescRepository.getQuantityProductWaitComplete(s.getId()).orElse(0L) + orderQuantity))
            .min(Comparator.comparing(Storage::getCapacity));

    }

    public Page<ShopOrder> getAllShopOrderByAdmin(Pageable pageable) {
        return this.shopOrderRepository.findAll(pageable);
    }

    public Page<ShopOrder> getAllShopOrderByOrderStatus(OrderStatus orderStatus, Pageable pageable) {
        return this.shopOrderRepository.findAllByOrderStatus(orderStatus, pageable);
    }
}
