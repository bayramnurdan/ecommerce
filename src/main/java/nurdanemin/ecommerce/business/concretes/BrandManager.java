package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.BrandService;
import nurdanemin.ecommerce.business.dto.request.create.brand.CreateBrandRequest;
import nurdanemin.ecommerce.business.dto.request.update.brand.UpdateBrandRequest;
import nurdanemin.ecommerce.business.dto.response.create.brand.CreateBrandResponse;
import nurdanemin.ecommerce.business.dto.response.get.brand.GetAllBrandsResponse;
import nurdanemin.ecommerce.business.dto.response.get.brand.GetBrandResponse;
import nurdanemin.ecommerce.business.dto.response.update.brand.UpdateBrandResponse;
import nurdanemin.ecommerce.business.rules.BrandRules;
import nurdanemin.ecommerce.entities.Brand;
import nurdanemin.ecommerce.repositories.BrandRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private ModelMapper mapper;
    private BrandRules rules;
    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands  = repository.findAll();
        List<GetAllBrandsResponse> response = brands
                .stream()
                .map(brand->mapper.map(brand, GetAllBrandsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetBrandResponse getById(Long id) {
        rules.checkIfBrandExistsById(id);
        GetBrandResponse response = mapper.map(repository.findById(id), GetBrandResponse.class);
        return response;
    }

    @Override
    public CreateBrandResponse createBrand(CreateBrandRequest request) {
        rules.checkIfBrandAlreadyExists(request.getName());
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(0L);
        Brand createdBrand =  repository.save(brand);
        CreateBrandResponse response = mapper.map(createdBrand, CreateBrandResponse.class);
        return response;
    }


    @Override
    public UpdateBrandResponse updateBrand(Long id, UpdateBrandRequest request) {
        rules.checkIfBrandExistsById(id);
        Brand brand = mapper.map(request, Brand.class);
        brand.setId(id);
        Brand updatedBrand =  repository.save(brand);
        UpdateBrandResponse response = mapper.map(updatedBrand, UpdateBrandResponse.class);
        return response;
    }


    @Override
    public void delete(Long id) {
        rules.checkIfBrandExistsById(id);
        repository.deleteById(id);

    }
}
