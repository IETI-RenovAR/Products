package org.adaschool.project.service;

import org.adaschool.project.dto.ProductDTO;
import org.adaschool.project.exception.ProductNotFoundException;
import org.adaschool.project.model.Product;
import org.adaschool.project.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {

        Product product1 = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        Product product2 = new Product("2", "Product2", "Category2", 200.0, "20x20", "Store2");
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(products, result);
    }

    @Test
    void testGetProductById() throws Exception {

        Product product = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        Product result = productService.getProductById("1");

        assertEquals(product, result);
    }

    @Test
    void testGetProductByIdNotFound() {

        when(productRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById("1"));
    }

    @Test
    void testSaveProduct() {

        ProductDTO productDTO = new ProductDTO("Product1", "Category1", 100.0, "10x10", "Store1");
        Product product = new Product(productDTO);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.saveProduct(productDTO);

        assertEquals(product, result);
    }

    @Test
    void testUpdateProduct() throws Exception {

        ProductDTO productDTO = new ProductDTO("UpdatedProduct", "Category1", 150.0, "15x15", "Store1");
        Product existingProduct = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        when(productRepository.findById("1")).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        Product result = productService.updateProduct("1", productDTO);

        assertEquals("UpdatedProduct", result.getName());
        assertEquals(150.0, result.getPrice());
    }

    @Test
    void testDeleteProduct() {

        doNothing().when(productRepository).deleteById("1");

        productService.deleteProduct("1");

        verify(productRepository, times(1)).deleteById("1");
    }

    @Test
    void testGetPrice() throws Exception {

        Product product = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        double result = productService.getPrice("1");

        assertEquals(100.0, result);
    }

    @Test
    void testGetDimensions() throws Exception {

        Product product = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        String result = productService.getDimensions("1");

        assertEquals("10x10", result);
    }

    @Test
    void testSortProducts() throws Exception {

        Product product1 = new Product("1", "Product1", "Category1", 100.0, "10x10", "Store1");
        Product product2 = new Product("2", "Product2", "Category1", 200.0, "20x20", "Store2");
        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findByCategory("Category1")).thenReturn(products);

        List<Product> result = productService.sort("ascPrice", "Category1");

        assertEquals(products, result);
    }
}
