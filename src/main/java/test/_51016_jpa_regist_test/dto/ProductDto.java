package test._51016_jpa_regist_test.dto;

import lombok.Getter;
import lombok.Setter;
import test._51016_jpa_regist_test.entity.ProductDetail;

import java.util.List;

// 프론트엔드와 데이터를 주고받기 위한 DTO
@Getter
@Setter
public class ProductDto {

    private String productName;
    private List<ProductDetailDto> details;

    @Getter
    @Setter
    public static class ProductDetailDto {
        private int year;
        private int price;
    }
}

