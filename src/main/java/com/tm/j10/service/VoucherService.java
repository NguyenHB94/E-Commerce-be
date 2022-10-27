package com.tm.j10.service;

import com.tm.j10.domain.Customer;
import com.tm.j10.domain.Voucher;
import com.tm.j10.domain.enumeration.VoucherStatus;
import com.tm.j10.domain.enumeration.VoucherValueType;
import com.tm.j10.repository.CustomerRepository;
import com.tm.j10.repository.VoucherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherService {
    private VoucherRepository voucherRepository;
    private CustomerRepository customerRepository;
    private EntityManager em;

    public VoucherService(VoucherRepository voucherRepository, CustomerRepository customerRepository, EntityManager em) {
        this.voucherRepository = voucherRepository;
        this.customerRepository = customerRepository;
        this.em = em;
    }

    public void createNewVoucher(Voucher newVoucher) {
        //validate
        Long validTo = newVoucher.getValidTo();
        Long validFrom = newVoucher.getValidFrom();
        if (validTo == null ||
            validFrom == null ||
            validTo <= validFrom ||
            validTo < System.currentTimeMillis() / 1000L ||
            validFrom < System.currentTimeMillis() / 1000L ||
            validTo <= 0 ||
            validFrom <= 0) {
            throw new RuntimeException("Invalid valid time");
        }

        Double maxValue = newVoucher.getMaxValue();
        Double minValue = newVoucher.getMinValue();
        if (maxValue == null ||
            minValue == null ||
            maxValue <= minValue ||
            maxValue <= 0 ||
            minValue <= 0) {
            throw new RuntimeException("Invalid limited value of voucher");
        }

        Long voucherQuantity = newVoucher.getVoucherQuantity();
        if (voucherQuantity == null ||
            voucherQuantity <= 0) {
            throw new RuntimeException("Invalid voucher quantity");
        }

        if (newVoucher.getVoucherValue() == null) {
            throw new RuntimeException("Invalid voucher value: Not null");
        }

        if (newVoucher.getValueType() == VoucherValueType.PLAIN && newVoucher.getVoucherValue() <= 0) {
            throw new RuntimeException("Invalid voucher value: value can not be negative or 0");
        } else if (newVoucher.getValueType() == VoucherValueType.PERCENTAGE) {
            if (newVoucher.getVoucherValue() >= 1 ||
                newVoucher.getVoucherValue() <= 0) {
                throw new RuntimeException("Invalid voucher value: Percentage can not be higher than 1, lower than 0 or negative");
            }
        }

        String voucherId = generateVoucherId();
        newVoucher.setVoucherId(voucherId);
        newVoucher.setVoucherStatus(VoucherStatus.ACTIVE);

        this.voucherRepository.save(newVoucher);

    }

    private String generateVoucherId() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder();
        int voucherIdLength = 10;
        for (int i = 0; i < voucherIdLength; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    @Transactional
    public void addCustomers(Long voucherId, List<Long> ListCustomerId) {
        if (voucherId == null ||
            voucherId <= 0) {
            throw new RuntimeException("Invalid voucherId");
        }
        var voucherOtp = this.voucherRepository.findById(voucherId);
        if (voucherOtp.isEmpty()) {
            throw new RuntimeException("Not found voucher");
        }

        Voucher currentVoucher = voucherOtp.get();

        if (ListCustomerId == null ||
            ListCustomerId.size() == 0) {
            throw new RuntimeException("Invalid customer list");
        }

        for (Long customerId : ListCustomerId) {
            if (customerId == null ||
                customerId <= 0) {
                throw new RuntimeException("Invalid customerId");
            }
            var customerOtp = this.customerRepository.findById(customerId);
            if (customerOtp.isEmpty()) {
                throw new RuntimeException("Not found customer with id" + customerId);
            }
            Customer currentCustomer = customerOtp.get();

            currentVoucher.getCustomers().add(currentCustomer);
            currentCustomer.getVouchers().add(currentVoucher);

            this.customerRepository.save(currentCustomer);
        }
        this.voucherRepository.save(currentVoucher);
    }

    public void editVoucher(Long voucherId, Voucher newVoucher) {
        if (voucherId == null ||
            voucherId <= 0) {
            throw new RuntimeException("Invalid voucherId");
        }
        var voucherOtp = this.voucherRepository.findById(voucherId);
        if (voucherOtp.isEmpty()) {
            throw new RuntimeException("Not found voucher");
        }

        Voucher currentVoucher = voucherOtp.get();

        //valid time
        Long validTo = newVoucher.getValidTo();
        Long validFrom = newVoucher.getValidFrom();
        if (validTo != null && validFrom != null) {
            if (validTo <= validFrom ||
                validTo < System.currentTimeMillis() / 1000L ||
                validFrom < System.currentTimeMillis() / 1000L ||
                validTo <= 0 ||
                validFrom <= 0) {
                throw new RuntimeException("Invalid valid time");
            } else {
                currentVoucher.setValidTo(validTo);
                currentVoucher.setValidFrom(validFrom);
            }
        } else if (validTo == null && validFrom != null) {
            if (currentVoucher.getValidTo() <= validFrom ||
                validFrom < System.currentTimeMillis() / 1000L ||
                validFrom <= 0) {
                throw new RuntimeException("Invalid valid from");
            } else {
                currentVoucher.setValidFrom(validFrom);
            }
        } else if (validTo != null && validFrom == null) {
            if (validTo <= currentVoucher.getValidFrom() ||
                validTo < System.currentTimeMillis() / 1000L ||
                validTo <= 0) {
                throw new RuntimeException("Invalid valid to");
            } else {
                currentVoucher.setValidTo(validTo);
            }
        }

        //min value && max value
        Double maxValue = newVoucher.getMaxValue();
        Double minValue = newVoucher.getMinValue();
        if (maxValue != null && minValue != null) {
            if (maxValue <= minValue ||
                maxValue <= 0 ||
                minValue <= 0) {
                throw new RuntimeException("Invalid limited value of voucher");
            } else {
                currentVoucher.setMaxValue(maxValue);
                currentVoucher.setMinValue(minValue);
            }
        } else if (maxValue == null && minValue != null) {
            if (currentVoucher.getMaxValue() <= minValue ||
                minValue <= 0) {
                throw new RuntimeException("Invalid min value of voucher");
            } else {
                currentVoucher.setMinValue(minValue);
            }
        } else if (maxValue != null && minValue == null) {
            if (maxValue <= currentVoucher.getMinValue() ||
                maxValue <= 0) {
                throw new RuntimeException("Invalid max value of voucher");
            } else {
                currentVoucher.setMaxValue(maxValue);
            }
        }

        //Voucher quantity
        Long voucherQuantity = newVoucher.getVoucherQuantity();
        if (voucherQuantity != null) {
            if (voucherQuantity <= 0) {
                throw new RuntimeException("Invalid voucher quantity");
            } else currentVoucher.setVoucherQuantity(voucherQuantity);
        }

        //voucher value && voucher value type
        Double newVoucherValue = newVoucher.getVoucherValue();
        VoucherValueType newVoucherValueType = newVoucher.getValueType();
        if (newVoucherValue != null && newVoucherValueType != null) {
            if (newVoucherValueType == VoucherValueType.PLAIN && newVoucherValue <= 0) {
                throw new RuntimeException("Invalid voucher value: value can not be negative or 0");
            } else if (newVoucherValueType == VoucherValueType.PERCENTAGE) {
                if (newVoucherValue >= 1 ||
                    newVoucherValue <= 0) {
                    throw new RuntimeException("Invalid voucher value: Percentage can not be higher than 1, lower than 0 or negative");
                }
            }
            currentVoucher.setVoucherValue(newVoucherValue);
            currentVoucher.setValueType(newVoucherValueType);

        } else if (newVoucherValue == null && newVoucherValueType != null) {
            if (newVoucherValueType == VoucherValueType.PLAIN && currentVoucher.getVoucherValue() < 1) {
                throw new RuntimeException("Invalid voucher value type");
            } else if (newVoucherValueType == VoucherValueType.PERCENTAGE) {
                if (currentVoucher.getVoucherValue() >= 1) {
                    throw new RuntimeException("Invalid voucher value type");
                }
            }
            currentVoucher.setValueType(newVoucherValueType);

        } else if (newVoucherValue != null && newVoucherValueType == null) {
            if (currentVoucher.getValueType() == VoucherValueType.PLAIN && newVoucherValue <= 0) {
                throw new RuntimeException("Invalid voucher value: value can not be negative or 0");
            } else if (currentVoucher.getValueType() == VoucherValueType.PERCENTAGE) {
                if (newVoucherValue >= 1 ||
                    newVoucherValue <= 0) {
                    throw new RuntimeException("Invalid voucher value: Percentage can not be higher than 1, lower than 0 or negative");
                }
            }
            currentVoucher.setVoucherValue(newVoucherValue);
        }

        this.voucherRepository.save(currentVoucher);
    }

    public void deleteVoucher(Long voucherId) {
        if (voucherId == null ||
            voucherId <= 0) {
            throw new RuntimeException("Invalid voucherId");
        }
        var voucherOtp = this.voucherRepository.findById(voucherId);
        if (voucherOtp.isEmpty()) {
            throw new RuntimeException("Not found voucher");
        }

        this.voucherRepository.deleteById(voucherId);
    }

    public Voucher getById(Long voucherId) {
        if (voucherId == null ||
            voucherId <= 0) {
            throw new RuntimeException("Invalid voucherId");
        }
        var voucherOtp = this.voucherRepository.findById(voucherId);
        if (voucherOtp.isEmpty()) {
            throw new RuntimeException("Not found voucher");
        }

        return voucherOtp.get();
    }

    public Page<Voucher> searchVouchers(VoucherStatus voucherStatus, VoucherValueType valueType, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Voucher> cq = cb.createQuery(Voucher.class);
        Root<Voucher> root = cq.from(Voucher.class);

        List<Predicate> searchCriteria = new ArrayList<>();
        if (voucherStatus != null) {
            searchCriteria.add(cb.equal(root.get("voucherStatus"), voucherStatus));
        }
        if (valueType != null) {
            searchCriteria.add(cb.equal(root.get("valueType"), valueType));
        }
        cq.where(searchCriteria.toArray(new Predicate[]{}));

        TypedQuery<Voucher> query = em.createQuery(cq);
        return new PageImpl<Voucher>(query.getResultList(), pageable, getTotalCount(cb, searchCriteria));
    }

    private Long getTotalCount(CriteriaBuilder criteriaBuilder, List<Predicate> searchCriteria) {
        CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
        Root<Voucher> root = cq.from(Voucher.class);

        cq.select(criteriaBuilder.count(root));
        cq.where(searchCriteria.toArray(new Predicate[]{}));

        return em.createQuery(cq).getSingleResult();
    }

}
