package org.adaschool.project.controller.product;

import org.adaschool.project.dto.ProductDTO;
import org.adaschool.project.model.Product;
import org.adaschool.project.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        Product product2 = new Product("2", "Product2", "Category2", 200.0, "20x20", "Store2");
        List<Product> products = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void testGetProductById() throws Exception {

        Product product = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        when(productService.getProductById("1")).thenReturn(product);

        ResponseEntity<Product> response = productController.getProductById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testGetProductByIdNotFound() throws Exception {

        when(productService.getProductById("1")).thenThrow(new Exception("Product not found"));

        ResponseEntity<Product> response = productController.getProductById("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateProduct() {

        ProductDTO productDTO = new ProductDTO("Product1", "Category1", 100.0, "10x10", "Store1");
        Product product = new Product(productDTO);
        when(productService.saveProduct(productDTO)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(productDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void testUpdateProduct() throws Exception {

        ProductDTO productDTO = new ProductDTO("UpdatedProduct", "Category1", 150.0, "15x15", "Store1");
        Product updatedProduct = new Product("1", "UpdatedProduct", "Category1", 150.0, "15x15", "Store1");
        when(productService.updateProduct("1", productDTO)).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.updateProduct("1", productDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    void testUpdateProductNotFound() throws Exception {

        ProductDTO productDTO = new ProductDTO("UpdatedProduct", "Category1", 150.0, "15x15", "Store1");
        when(productService.updateProduct("1", productDTO)).thenThrow(new Exception("Product not found"));

        ResponseEntity<Product> response = productController.updateProduct("1", productDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteProduct() {

        doNothing().when(productService).deleteProduct("1");

        ResponseEntity<Void> response = productController.deleteProduct("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetPrice() throws Exception {

        when(productService.getPrice("1")).thenReturn(100.0);

        ResponseEntity<Double> response = productController.getPrice("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(100.0, response.getBody());
    }

    @Test
    void testGetDimensions() throws Exception {

        when(productService.getDimensions("1")).thenReturn("10x10");

        ResponseEntity<String> response = productController.getDimensions("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("10x10", response.getBody());
    }

    @Test
    void testGetStore() throws Exception {

        when(productService.getStore("1")).thenReturn("Store1");

        ResponseEntity<String> response = productController.getStore("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Store1", response.getBody());
    }

    @Test
    void testSortProducts() throws Exception {

        Product product1 = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        Product product2 = new Product("2", "Product2", "Category1", 200.0, "20x20", "Store2");
        List<Product> products = Arrays.asList(product1, product2);
        when(productService.sort("ascPrice", "Category1")).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.sortProducts("ascPrice", "Category1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }
}