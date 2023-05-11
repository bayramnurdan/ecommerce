package nurdanemin.ecommerce.business.abstracts;


import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.shipping.UpdateShippingRequest;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetAllShippingsResponse;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetShippingResponse;
import nurdanemin.ecommerce.business.dto.response.update.shipping.UpdateShippingResponse;
import nurdanemin.ecommerce.entities.Shipping;

import java.util.List;

public interface ShippingService {
    List<GetAllShippingsResponse> getAll();
    GetShippingResponse getById(Long id);

    Shipping createShipping(CreateShippingRequest request);
    UpdateShippingResponse updateShipping(Long id, UpdateShippingRequest request);
    void delete(Long id);

}
