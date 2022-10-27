package com.tm.j10.web.rest.vm;

import java.util.Set;

public class NewProductVM {
    private Long userId;
    private String name;
    private Long price;
    private Long finalPrice;
    private String designer;
    private String description;
    private String modelHeight;
    private String modelWeight;
    private Long categoryId;
    private Set<ColorVM> colorVMSet;
    private Set<ProductSizeVM> productSizeVMSet;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModelHeight() {
        return modelHeight;
    }

    public void setModelHeight(String modelHeight) {
        this.modelHeight = modelHeight;
    }

    public String getModelWeight() {
        return modelWeight;
    }

    public void setModelWeight(String modelWeight) {
        this.modelWeight = modelWeight;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<ColorVM> getColorVMSet() {
        return colorVMSet;
    }

    public void setColorVMSet(Set<ColorVM> colorVMSet) {
        this.colorVMSet = colorVMSet;
    }

    public Set<ProductSizeVM> getProductSizeVMSet() {
        return productSizeVMSet;
    }

    public void setProductSizeVMSet(Set<ProductSizeVM> productSizeVMSet) {
        this.productSizeVMSet = productSizeVMSet;
    }
}
