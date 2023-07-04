package nurdanemin.ecommerce.business.dto.response.get.product;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllProductsResponse {
    private Long id;
    private String name;
    private String brandName;
    private Long quantity;
    private double price;
    private double discount;
    private String description;
    private String imageUrl;

}