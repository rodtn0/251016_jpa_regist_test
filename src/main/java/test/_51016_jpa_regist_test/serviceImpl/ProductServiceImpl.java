package test._51016_jpa_regist_test.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test._51016_jpa_regist_test.dto.ProductDto;
import test._51016_jpa_regist_test.entity.Product;
import test._51016_jpa_regist_test.entity.ProductDetail;
import test._51016_jpa_regist_test.repository.ProductRepository;
import test._51016_jpa_regist_test.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> getActiveProducts() {
        List<Product> products = productRepository.findByDeleteYn('N');
        return ResponseEntity.ok(products);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Product> getProductWithDetails(String productName) {
        return productRepository.findById(productName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @Transactional
    public ResponseEntity<String> saveProduct(ProductDto productDto) {
        try {
            // 기존 상품이 있으면 찾고, 없으면 새로 생성
            Product product = productRepository.findById(productDto.getProductName())
                    .orElse(new Product());

            product.setProductName(productDto.getProductName());
            product.setDeleteYn('N'); // 저장 시 항상 'N'으로 설정

            // 기존 상세 정보는 모두 삭제 (Cascade 옵션으로 DB에서 자동 삭제됨)
            product.getDetails().clear();

            // DTO에서 받은 새로운 상세 정보 추가
            if (productDto.getDetails() != null) {
                productDto.getDetails().forEach(detailDto -> {
                    ProductDetail newDetail = new ProductDetail();
                    newDetail.setYear(detailDto.getYear());
                    newDetail.setPrice(detailDto.getPrice());
                    product.addDetail(newDetail);
                });
            }

            productRepository.save(product);
            return ResponseEntity.ok("상품이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            // 년도 중복 등 DB 제약조건 위반 시 예외 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("저장 실패: " + e.getMessage());
        }
    }
}