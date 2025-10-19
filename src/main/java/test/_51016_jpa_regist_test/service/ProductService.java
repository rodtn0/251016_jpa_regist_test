package test._51016_jpa_regist_test.service;

import org.springframework.http.ResponseEntity;
import test._51016_jpa_regist_test.dto.ProductDto;
import test._51016_jpa_regist_test.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ResponseEntity<List<Product>> getActiveProducts();
    ResponseEntity<Product> getProductWithDetails(String productName);
    ResponseEntity<String> saveProduct(ProductDto productDto);
}