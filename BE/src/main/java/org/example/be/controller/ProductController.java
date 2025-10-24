package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.entity.Product;
import org.example.be.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ---------------- CREATE ----------------
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        ApiResponse<Product> response = new ApiResponse<>();
        try {
            Product created = productService.createProduct(product);

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("timestamp", LocalDateTime.now());
            response.ok(created, (HashMap<String, Object>) metadata);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ---------------- GET ALL ----------------
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        ApiResponse<List<Product>> response = new ApiResponse<>();
        try {
            List<Product> products = productService.getAllProducts();

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("count", products.size());
            metadata.put("timestamp", LocalDateTime.now());

            response.ok(products, (HashMap<String, Object>) metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ---------------- GET BY ID ----------------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Integer id) {
        ApiResponse<Product> response = new ApiResponse<>();
        try {
            Product product = productService.getProductById(id);

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("timestamp", LocalDateTime.now());

            response.ok(product, (HashMap<String, Object>) metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ---------------- UPDATE ----------------
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        ApiResponse<Product> response = new ApiResponse<>();
        try {
            Product updated = productService.updateProduct(id, product);

            Map<String, Object> metadata = new HashMap<>();
            metadata.put("updatedAt", LocalDateTime.now());

            response.ok(updated, (HashMap<String, Object>) metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ---------------- DELETE ----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteProduct(@PathVariable Integer id) {
        ApiResponse<Boolean> response = new ApiResponse<>();
        try {
            productService.deleteProduct(id);

            response.ok();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
