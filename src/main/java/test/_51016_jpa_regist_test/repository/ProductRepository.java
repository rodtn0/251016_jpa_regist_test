package test._51016_jpa_regist_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test._51016_jpa_regist_test.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    // 삭제되지 않은 상품만 조회
    List<Product> findByDeleteYn(char deleteYn);
}
