package org.adaschool.project.controller.product;

import org.adaschool.project.dto.ProductDTO;
import org.adaschool.project.model.Product;
import org.adaschool.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        Product createdProduct = productService.saveProduct(productDTO);
        URI createdProductUri = URI.create("/v1/products/" + createdProduct.getId());
        return ResponseEntity.created(createdProductUri).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = null;
        try {
            updatedProduct = productService.updateProduct(id, productDTO);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/price/{id}")
    public ResponseEntity<Double> getPrice(@PathVariable String id){
        try {
            return ResponseEntity.ok(productService.getPrice(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dimensions/{id}")
    public ResponseEntity<String> getDimensions(@PathVariable String id){
        try {
            return ResponseEntity.ok(productService.getDimensions(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping ("/seller/{id}")
    public ResponseEntity<String> getStore (@PathVariable String id){
        try {
            return ResponseEntity.ok(productService.getStore(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sort/{criteria}/{category}")
    public ResponseEntity<List<Product>> sortProducts(@PathVariable("criteria") String criteria, @PathVariable("category") String category){
        try {
            return ResponseEntity.ok(productService.sort(criteria, category));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}