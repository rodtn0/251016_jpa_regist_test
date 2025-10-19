package test._51016_jpa_regist_test.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @Column(name = "product_name")
    private String productName;

    private char deleteYn = 'N';

    // Product 저장 시 ProductDetail도 함께 저장/삭제 되도록 Cascade 설정
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductDetail> details = new ArrayList<>();

    // 연관관계 편의 메소드
    public void addDetail(ProductDetail detail) {
        this.details.add(detail);
        detail.setProduct(this);
    }
}
