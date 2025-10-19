package test._51016_jpa_regist_test.dto;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// ProductDetail의 복합키를 위한 클래스
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDetailId implements Serializable {
    private String product; // Product 엔티티의 productName 필드명과 일치해야 함
    private int year;
}
