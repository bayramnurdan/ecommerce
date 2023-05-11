package nurdanemin.ecommerce.business.dto.request.update.product;

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
}
