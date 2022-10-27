package com.tm.j10.service;

import com.tm.j10.domain.*;
import com.tm.j10.repository.*;
import com.tm.j10.web.rest.vm.StorageVM;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StorageService {
    private StorageRepository storageRepository;
    private StoreRepository storeRepository;
    private ProductRepository productRepository;
    private ProductSizeRepository productSizeRepository;
    private ColorRepository colorRepository;

    public StorageService(StorageRepository storageRepository,
                          StoreRepository storeRepository,
                          ProductRepository productRepository,
                          ProductSizeRepository productSizeRepository,
                          ColorRepository colorRepository) {
        this.storageRepository = storageRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
        this.productSizeRepository = productSizeRepository;
        this.colorRepository = colorRepository;
    }

    public void addCapacity(StorageVM storageVM) {
        Long storeId = storageVM.getStoreId();
        if (storeId == null || storeId <= 0) {
            throw new RuntimeException("Invalid store id");
        }

        Long additionalCapacity = storageVM.getAdditionalCapacity();
        if (additionalCapacity == null || additionalCapacity <= 0) {
            throw new RuntimeException("Invalid additional capacity");
        }

        Long productId = storageVM.getProductId();
        if (productId == null || productId <= 0) {
            throw new RuntimeException("Invalid product id");
        }

        Long productSizeId = storageVM.getProductSizeId();
        if (productSizeId == null || productSizeId <= 0) {
            throw new RuntimeException("Invalid product size id");
        }

        Long colorId = storageVM.getColorId();
        if (colorId == null || colorId <= 0) {
            throw new RuntimeException("Invalid color id");
        }
        Optional<Storage> optionalStorage = this.storageRepository.getByStoreAndProductAndProductSizeAndColor(
            storeId, productId, productSizeId, colorId);
        if (optionalStorage.isPresent()) {
            Storage currentStorage = optionalStorage.get();
            var currentCapacity = currentStorage.getCapacity();
            var newCapacity = Long.sum(currentCapacity, additionalCapacity);
            currentStorage.setCapacity((Long) newCapacity);

            storageRepository.save(currentStorage);
        } else {
            Optional<Store> optionalStore = this.storeRepository.findById(storeId);
            if (optionalStore.isEmpty()){
                throw new RuntimeException("Not found store");
            }

            Optional<Product> optionalProduct = this.productRepository.findById(productId);
            if (optionalProduct.isEmpty()){
                throw new RuntimeException("Not found product");
            }

            Optional<ProductSize> optionalProductSize = this.productSizeRepository.findById(productSizeId);
            if (optionalProductSize.isEmpty()){
                throw new RuntimeException("Not found product size");
            }

            Optional<Color> optionalColor = this.colorRepository.findById(colorId);
            if (optionalColor.isEmpty()){
                throw new RuntimeException("Not found color");
            }

            Storage newStorage = new Storage();
            newStorage.setCapacity(additionalCapacity);
            newStorage.setStore(optionalStore.get());
            newStorage.setColor(optionalColor.get());
            newStorage.setProduct(optionalProduct.get());
            newStorage.setProductSize(optionalProductSize.get());

            this.storageRepository.save(newStorage);
        }
    }
}
