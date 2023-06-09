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
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductsController {
    private final ProductService service;


    @GetMapping
    public List<GetAllProductsResponse> getAll(@RequestParam Integer page, @RequestParam Integer size){
        return service.getAll(page, size);
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable Long id){
        return service.getById(id);

    }
    @GetMapping("/category/{categoryName}")
    public List<GetAllProductsResponse> getAllByCategoryName(@PathVariable String categoryName, @RequestParam  Integer pageNo, @RequestParam Integer pageSize) {
        return service.getAllByCategoryName(categoryName, pageNo, pageSize);

    }

    @GetMapping("/name/{prodName}")
    public List<GetAllProductsResponse> getAllByName(@PathVariable String prodName) {
        return service.getAllByName(prodName);

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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable  Long id){
        service.delete(id);
    }
}