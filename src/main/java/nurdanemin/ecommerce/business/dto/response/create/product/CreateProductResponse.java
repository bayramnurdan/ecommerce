package nurdanemin.ecommerce.business.dto.response.create.product;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductResponse {
    private String name;
    private Long id;
    private String brandName;
    private List<String> categoryNames;
    private Long quantity;
    private double price;
    private double discount;
    private String description;
    private String imageUrl;
}