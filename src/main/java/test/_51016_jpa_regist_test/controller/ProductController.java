package test._51016_jpa_regist_test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test._51016_jpa_regist_test.dto.ProductDto;
import test._51016_jpa_regist_test.entity.Product;
import test._51016_jpa_regist_test.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 활성화된 상품 목록 조회 (Select box용)
    @GetMapping
    public ResponseEntity<List<Product>> getActiveProducts() {
        return productService.getActiveProducts();
    }

    // 특정 상품의 상세 정보 조회 (팝업 데이터 로딩용)
    @GetMapping("/{productName}")
    public ResponseEntity<Product> getProductDetails(@PathVariable String productName) {
        return productService.getProductWithDetails(productName);
    }

    // 상품 정보 저장/수정
    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody ProductDto productDto) {
        return productService.saveProduct(productDto);
    }
}