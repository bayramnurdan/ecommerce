package nurdanemin.ecommerce.business.dto.response.update.product;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nurdanemin.ecommerce.entities.Brand;
import nurdanemin.ecommerce.entities.Category;

import java.util.Set;

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
