package com.testautomation.pojos;

public class DiscountRequest {
    private String productId;
    private Integer percentage;

    public String getProductId() { return productId; }
    public Integer getPercentage() { return percentage; }

    public DiscountRequest setProductId(String productId) { this.productId = productId; return this; }
    public DiscountRequest setPercentage(Integer percentage) { this.percentage = percentage; return this; }
}
