package org.adaschool.project.model;

import org.adaschool.project.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String name;
    private String category;
    private double price;
    private String dimensions;
    private String store;

    public void setScore(float score) {
        this.score = score;
    }


    public float getScore() {
        return score;
    }

    private float score;

    public Product() {
    }

    // Constructor con todos los campos
    public Product(String id, String name, String category, double price, String dimensions, String storeId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.dimensions = dimensions;
        this.store = storeId;
    }

    // Constructor a partir de un ProductDTO
    public Product(ProductDTO productDTO) {
        this.id = null;
        this.name = productDTO.getName();
        this.category = productDTO.getCategory();
        this.price = productDTO.getPrice();
        this.dimensions = productDTO.getDimensions();
        this.store = productDTO.getStoreId();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getStoreId() {
        return store;
    }

    public void setStoreId(String storeId) {
        this.store = storeId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}