package org.example.be.service;

import org.example.be.entity.Product;
import org.example.be.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product updateProduct(Integer id, Product productDetails) {
        Product product = getProductById(id);
        if (product == null) {
            return null;
        }
        product.setMember(productDetails.getMember());
        product.setVehicle(productDetails.getVehicle());
        product.setBattery(productDetails.getBattery());
        product.setProductType(productDetails.getProductType());
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setStatus(productDetails.getStatus());
        product.setCreatedAt(productDetails.getCreatedAt());
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
