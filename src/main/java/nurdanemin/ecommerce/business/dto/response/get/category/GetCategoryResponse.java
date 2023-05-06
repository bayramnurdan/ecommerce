package nurdanemin.ecommerce.business.dto.response.get.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetCategoryResponse {
    private Long id;
    private String name;
}
