package com.testautomation.pojos;

public class OrderStatusRequest {
    private String orderId;
    private String status;

    public String getOrderId() { return orderId; }
    public String getStatus() { return status; }

    public OrderStatusRequest setOrderId(String orderId) { this.orderId = orderId; return this; }
    public OrderStatusRequest setStatus(String status) { this.status = status; return this; }
}
