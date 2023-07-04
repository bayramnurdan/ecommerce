package nurdanemin.ecommerce.business.dto.request.update.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Category;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateProductRequest {
    private Long quantity;
    private double price;
    private double discount;
    @Min(10)
    private String description;
    @NotBlank
    private String imageUrl;
}