package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CategoryService;
import nurdanemin.ecommerce.business.dto.request.create.category.CreateCategoryRequest;
import nurdanemin.ecommerce.business.dto.request.update.category.UpdateCategoryRequest;
import nurdanemin.ecommerce.business.dto.response.create.category.CreateCategoryResponse;
import nurdanemin.ecommerce.business.dto.response.get.brand.GetAllBrandsResponse;
import nurdanemin.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import nurdanemin.ecommerce.business.dto.response.get.category.GetCategoryResponse;
import nurdanemin.ecommerce.business.dto.response.update.category.UpdateCategoryResponse;
import nurdanemin.ecommerce.business.rules.CategoryRules;
import nurdanemin.ecommerce.entities.Category;
import nurdanemin.ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryManager  implements CategoryService {
    private final CategoryRepository repository;
    private final ModelMapper mapper;
    private final CategoryRules rules;
    @Override
    public List<GetAllCategoriesResponse> getAll() {
        List<Category>  categories = repository.findAll();
        List<GetAllCategoriesResponse> response = categories
                .stream()
                .map(category -> mapper.map(category, GetAllCategoriesResponse.class))
                .toList();
        return  response;
    }

    @Override
    public GetCategoryResponse getById(Long id) {
        rules.checkIfCategoryExistsById(id);
        GetCategoryResponse response = mapper.map(repository.findById(id), GetCategoryResponse.class);
        return response;
    }

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        rules.checkIfCategoryAlreadyExists(request.getName());
       Category category = mapper.map(request, Category.class);
       Category createdCategory = repository.save(category);
       CreateCategoryResponse response = mapper.map(createdCategory, CreateCategoryResponse.class);
       return response;
    }

    @Override
    public UpdateCategoryResponse updateCategory(Long id, UpdateCategoryRequest request) {
        rules.checkIfCategoryExistsById(id);
        Category category = mapper.map(request, Category.class);
        Category updatedCategory = repository.save(category);
        UpdateCategoryResponse response = mapper.map(updatedCategory, UpdateCategoryResponse.class);
        return response;
    }


    @Override
    public void delete(Long id) {
        rules.checkIfCategoryExistsById(id);
        repository.deleteById(id);

    }
}
