package com.tm.j10.web.rest.vm;

import java.util.Set;

public class OderVM {
    private Long userId;

    private String subAddress;

    private Set<ProductOrderVM> productOrderVMSet;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSubAddress() {
        return subAddress;
    }

    public void setSubAddress(String subAddress) {
        this.subAddress = subAddress;
    }

    public Set<ProductOrderVM> getProductVMSet() {
        return productOrderVMSet;
    }

    public void setProductVMSet(Set<ProductOrderVM> productOrderVMSet) {
        this.productOrderVMSet = productOrderVMSet;
    }
}
