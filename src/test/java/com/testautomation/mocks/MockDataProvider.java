package com.testautomation.mocks;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MockDataProvider {

    public static JsonArray getMockProducts() {
        JsonArray products = new JsonArray();
        
        JsonObject product1 = new JsonObject();
        product1.addProperty("productId", "laptop1");
        product1.addProperty("name", "Dell Laptop");
        product1.addProperty("category", "Electronics");
        product1.addProperty("price", 999.99);
        product1.addProperty("stockQuantity", 50);
        product1.addProperty("availableQuantity", 50);
        products.add(product1);
        
        JsonObject product2 = new JsonObject();
        product2.addProperty("productId", "phone1");
        product2.addProperty("name", "Samsung Phone");
        product2.addProperty("category", "Electronics");
        product2.addProperty("price", 599.99);
        product2.addProperty("stockQuantity", 100);
        product2.addProperty("availableQuantity", 100);
        products.add(product2);
        
        return products;
    }
}
