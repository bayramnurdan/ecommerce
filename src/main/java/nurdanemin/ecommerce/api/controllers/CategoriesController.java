package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.CategoryService;

import nurdanemin.ecommerce.business.dto.request.create.category.CreateCategoryRequest;
import nurdanemin.ecommerce.business.dto.request.update.category.UpdateCategoryRequest;
import nurdanemin.ecommerce.business.dto.response.create.category.CreateCategoryResponse;
import nurdanemin.ecommerce.business.dto.response.get.category.GetAllCategoriesResponse;
import nurdanemin.ecommerce.business.dto.response.get.category.GetCategoryResponse;
import nurdanemin.ecommerce.business.dto.response.update.category.UpdateCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoriesController {
    private final CategoryService service;


    @GetMapping
    public List<GetAllCategoriesResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCategoryResponse getById(@PathVariable Long id){
        return service.getById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCategoryResponse add(@RequestBody CreateCategoryRequest request){
        return service.createCategory(request);
    }

    @PutMapping("{id}")
    public UpdateCategoryResponse update(@PathVariable  Long id, @RequestBody UpdateCategoryRequest request){
        return  service.updateCategory(id, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id){
        service.delete(id);
    }
}
