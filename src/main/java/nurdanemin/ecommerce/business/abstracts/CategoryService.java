package nurdanemin.ecommerce.business.abstracts;





import nurdanemin.ecommerce.business.dto.request.create.category.CreateCategoryRequest;
import nurdanemin.ecommerce.business.dto.request.update.category.UpdateCategoryRequest;
import nurdanemin.ecommerce.business.dto.response.create.category.CreateCategoryResponse;
import nurdanemin.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import nurdanemin.ecommerce.business.dto.response.get.category.GetCategoryResponse;
import nurdanemin.ecommerce.business.dto.response.update.category.UpdateCategoryResponse;

import java.util.List;

public interface CategoryService {
    List<GetAllCategoriesResponse> getAll();
    GetCategoryResponse getById(Long id);

    CreateCategoryResponse createCategory(CreateCategoryRequest request);
    UpdateCategoryResponse updateCategory(Long id, UpdateCategoryRequest request);
    void delete(Long id);
}
