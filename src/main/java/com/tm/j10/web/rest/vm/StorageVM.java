package com.tm.j10.web.rest.vm;

public class StorageVM {
    private Long storeId;
    private Long additionalCapacity;
    private Long productId;
    private Long productSizeId;
    private Long colorId;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getAdditionalCapacity() {
        return additionalCapacity;
    }

    public void setAdditionalCapacity(Long additionalCapacity) {
        this.additionalCapacity = additionalCapacity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductSizeId() {
        return productSizeId;
    }

    public void setProductSizeId(Long productSizeId) {
        this.productSizeId = productSizeId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }
}
