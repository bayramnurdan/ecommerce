package nurdanemin.ecommerce.business.abstracts;



import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.shipping.UpdateShippingRequest;
import nurdanemin.ecommerce.business.dto.response.create.shipping.CreateShippingResponse;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetAllShippingsResponse;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetShippingResponse;
import nurdanemin.ecommerce.business.dto.response.update.shipping.UpdateShippingResponse;

import java.util.List;

public interface ShippingService {
    List<GetAllShippingsResponse> getAll();
    GetShippingResponse getById(Long id);

    CreateShippingResponse createShipping(CreateShippingRequest request, Long orderId);
    UpdateShippingResponse updateShipping(Long id, UpdateShippingRequest request);
    void delete(Long id);
}
