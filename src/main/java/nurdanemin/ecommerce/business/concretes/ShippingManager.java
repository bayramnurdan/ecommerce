package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.ShippingService;
import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.shipping.UpdateShippingRequest;
import nurdanemin.ecommerce.business.dto.response.create.shipping.CreateShippingResponse;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetAllShippingsResponse;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetShippingResponse;
import nurdanemin.ecommerce.business.dto.response.update.shipping.UpdateShippingResponse;
import nurdanemin.ecommerce.entities.Shipping;
import nurdanemin.ecommerce.entities.enums.ShippingStatus;
import nurdanemin.ecommerce.repositories.ShippingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ShippingManager  implements ShippingService {
    private final ShippingRepository repository;
    private final ModelMapper mapper;
    @Override
    public List<GetAllShippingsResponse> getAll() {
        return null;
    }

    @Override
    public GetShippingResponse getById(Long id) {
       Shipping shipping = repository.findById(id).orElseThrow();
       return null;
    }

    @Override
    public CreateShippingResponse createShipping(CreateShippingRequest request, Long orderId) {
        Shipping shipping = new Shipping();
        shipping.setAddressId(request.getAddressId());
        shipping.setReceiversfirstName(request.getReceiversfirstName());
        shipping.setReceiverslastName(request.getReceiverslastName());
        shipping.setOrderId(orderId);
        shipping.setStatus(ShippingStatus.BEINGPREPARED);
        repository.save(shipping);
        return null;

    }

    @Override
    public UpdateShippingResponse updateShipping(Long id, UpdateShippingRequest request) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }
}
