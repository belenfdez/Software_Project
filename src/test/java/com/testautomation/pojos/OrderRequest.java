package com.testautomation.pojos;

public class OrderRequest {
    private String userId;
    private String productId;
    private Integer quantity;

    public String getUserId() { return userId; }
    public String getProductId() { return productId; }
    public Integer getQuantity() { return quantity; }

    public OrderRequest setUserId(String userId) { this.userId = userId; return this; }
    public OrderRequest setProductId(String productId) { this.productId = productId; return this; }
    public OrderRequest setQuantity(Integer quantity) { this.quantity = quantity; return this; }
}
