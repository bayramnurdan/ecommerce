package nurdanemin.ecommerce.business.dto.response.update.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductResponse {
    private Long id;
    private String name;
    private Long brandId;
    private Long quantity;
    private double price;
    private double discount;
    private double specialPrice;
}