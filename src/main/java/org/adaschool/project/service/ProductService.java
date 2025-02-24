package org.adaschool.project.service;

import org.adaschool.project.dto.ProductDTO;
import org.adaschool.project.exception.ProductNotFoundException;
import org.adaschool.project.model.Product;
import org.adaschool.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Map<String, Comparator<Product>> sortMethods = new HashMap<>();

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        sortMethods.put("descPrice", Comparator.comparingDouble(Product::getPrice).reversed());
        sortMethods.put("ascPrice", Comparator.comparingDouble(Product::getPrice));
        sortMethods.put("score", Comparator.comparingDouble(Product::getScore));
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        return product.get();
    }

    public Product saveProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        return productRepository.save(product);
    }

    public Product updateProduct(String id, ProductDTO productDTO) throws Exception {
        Product product = getProductById(id);
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setDimensions(productDTO.getDimensions());
        product.setStoreId(productDTO.getStoreId());
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public double getPrice(String id) throws Exception {
        Product product = getProductById(id);
        return product.getPrice();
    }

    public String getDimensions(String id) throws Exception {
        Product product = getProductById(id);
        return product.getDimensions();
    }


    public String getStore(String id) throws Exception {
        Product product = getProductById(id);
        return product.getStore();
    }

    public List<Product> getByCategory(String category){
        return productRepository.findByCategory(category);
    }

    public List<Product> sort(String criteria, String category) throws Exception {
        List<Product> products = getByCategory(category);
        Comparator<Product> comparator = sortMethods.get(criteria);
        if (comparator != null) {
            products.sort(comparator);
        } else {
            throw new Exception("Invalid sorting criteria: " + criteria);
        }
        return products;
    }

}