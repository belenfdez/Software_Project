package com.testautomation.pojos;

public class ProductRequest {
    private String name;
    private String category;
    private Double price;
    private Integer stockQuantity;

    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; }
    public Integer getStockQuantity() { return stockQuantity; }

    public ProductRequest setName(String name) { this.name = name; return this; }
    public ProductRequest setCategory(String category) { this.category = category; return this; }
    public ProductRequest setPrice(Double price) { this.price = price; return this; }
    public ProductRequest setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; return this; }
}
