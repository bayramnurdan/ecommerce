package nurdanemin.ecommerce.business.dto.response.get.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllCategoriesResponse {
    private Long id;
    private String name;
}
