package com.testautomation.pojos;

public class QuantityRequest {
    private String productId;
    private Integer quantity;

    public String getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }

    public QuantityRequest setProductId(String productId) { this.productId = productId; return this; }
    public QuantityRequest setQuantity(Integer quantity) { this.quantity = quantity; return this; }
}
