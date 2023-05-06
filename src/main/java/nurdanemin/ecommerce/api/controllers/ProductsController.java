package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.ProductService;

import nurdanemin.ecommerce.business.dto.request.create.product.CreateProductRequest;
import nurdanemin.ecommerce.business.dto.request.update.product.UpdateProductRequest;
import nurdanemin.ecommerce.business.dto.response.create.product.CreateProductResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetAllProductsResponse;
import nurdanemin.ecommerce.business.dto.response.get.product.GetProductResponse;
import nurdanemin.ecommerce.business.dto.response.update.product.UpdateProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductsController {
    private final ProductService service;


    @GetMapping
    public List<GetAllProductsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable Long id){
        return service.getById(id);

    }
    @GetMapping("/get-by-category/{categoryName}")
    public List<GetAllProductsResponse> getAllByCategoryName(@PathVariable String categoryName) {
        return service.getAllByCategoryName(categoryName);

    }
    @GetMapping("/get-by-brand/{brandName}")
    public List<GetAllProductsResponse> getAllByBrandName(@PathVariable String brandName) {
        return service.getAllByBrandName(brandName);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse add(@RequestBody CreateProductRequest request){
        return service.createProduct(request);
    }

    @PutMapping("{id}")
    public UpdateProductResponse update(@PathVariable  Long id, @RequestBody UpdateProductRequest request){
        return  service.updateProduct(id, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id){
        service.delete(id);
    }
}
