package nurdanemin.ecommerce.business.dto.request.create.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductRequest {
    @NotBlank
    private String name;
    @Min(1)
    private Long brandId;

    private Set<String> categoryNames;
    @Min(1)
    private Long quantity;
    @Min(1)
    private double price;
    private double discount;
    @Min(10)
    private String description;
    @NotBlank
    private String imageUrl;

}