package com.tm.j10.web.rest.vm;

public class ProductOrderVM {
    private Long productId;
    private Long productSizeId;
    private Long colorId;
    private Long quantity;

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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
