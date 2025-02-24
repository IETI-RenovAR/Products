package org.adaschool.project.dto;

public class ProductDTO {

    private String name;
    private String category;
    private double price;
    private String dimensions;
    private String storeId;

    public ProductDTO() {
    }

    public ProductDTO(String name, String category, double price, String dimensions, String storeId) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.dimensions = dimensions;
        this.storeId = storeId;
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
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}