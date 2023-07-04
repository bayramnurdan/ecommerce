package nurdanemin.ecommerce.api.controllers;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.BrandService;
import nurdanemin.ecommerce.business.dto.request.create.brand.CreateBrandRequest;
import nurdanemin.ecommerce.business.dto.request.update.brand.UpdateBrandRequest;
import nurdanemin.ecommerce.business.dto.response.create.brand.CreateBrandResponse;
import nurdanemin.ecommerce.business.dto.response.get.brand.GetAllBrandsResponse;
import nurdanemin.ecommerce.business.dto.response.get.brand.GetBrandResponse;
import nurdanemin.ecommerce.business.dto.response.update.brand.UpdateBrandResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("api/brands")
public class BrandsController {
    private final BrandService service;


    @GetMapping
    public List<GetAllBrandsResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable Long id){
        return service.getById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest request){
        return service.createBrand(request);
    }

    @PutMapping("{id}")
    public UpdateBrandResponse update(@PathVariable  Long id, @RequestBody UpdateBrandRequest request){
        return  service.updateBrand(id, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id){
        service.delete(id);
    }


}