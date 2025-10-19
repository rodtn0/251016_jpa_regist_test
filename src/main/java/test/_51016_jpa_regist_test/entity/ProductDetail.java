package test._51016_jpa_regist_test.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import test._51016_jpa_regist_test.dto.ProductDetailId;

@Entity
@Getter
@Setter
@IdClass(ProductDetailId.class) // 복합키 클래스 지정
public class ProductDetail {

    @Id
    @ManyToOne
    @JoinColumn(name = "product_name")
    @JsonIgnore // 순환 참조 방지
    private Product product;

    @Id
    @Column(name = "\"year\"") // "year"가 H2 DB의 예약어이므로 Column 어노테이션으로 직접 매핑
    private int year;

    private int price;
}
