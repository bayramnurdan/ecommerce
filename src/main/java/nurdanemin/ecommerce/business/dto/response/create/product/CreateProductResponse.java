package nurdanemin.ecommerce.business.dto.response.create.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Category;

import java.util.List;
import java.util.Set;

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
}
