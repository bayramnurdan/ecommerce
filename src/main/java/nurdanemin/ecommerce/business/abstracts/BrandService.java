package nurdanemin.ecommerce.business.abstracts;



import nurdanemin.ecommerce.business.dto.request.create.brand.CreateBrandRequest;
import nurdanemin.ecommerce.business.dto.request.update.brand.UpdateBrandRequest;
import nurdanemin.ecommerce.business.dto.response.create.brand.CreateBrandResponse;

import nurdanemin.ecommerce.business.dto.response.get.brand.GetAllBrandsResponse;
import nurdanemin.ecommerce.business.dto.response.get.brand.GetBrandResponse;
import nurdanemin.ecommerce.business.dto.response.update.brand.UpdateBrandResponse;
import nurdanemin.ecommerce.entities.Brand;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(Long id);
    Brand getBrandById(Long Id);

    CreateBrandResponse createBrand(CreateBrandRequest request);
    UpdateBrandResponse updateBrand(Long id, UpdateBrandRequest request);
    void delete(Long id);


}
