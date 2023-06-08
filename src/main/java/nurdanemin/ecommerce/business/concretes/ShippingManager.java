package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.AddressService;
import nurdanemin.ecommerce.business.abstracts.ShippingService;
import nurdanemin.ecommerce.business.dto.request.create.shipping.CreateShippingRequest;
import nurdanemin.ecommerce.business.dto.request.update.shipping.UpdateShippingRequest;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetAllShippingsResponse;
import nurdanemin.ecommerce.business.dto.response.get.shipping.GetShippingResponse;
import nurdanemin.ecommerce.business.dto.response.update.shipping.UpdateShippingResponse;
import nurdanemin.ecommerce.common.constants.Messages;
import nurdanemin.ecommerce.entities.Shipping;
import nurdanemin.ecommerce.entities.enums.ShippingStatus;
import nurdanemin.ecommerce.repositories.ShippingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ShippingManager  implements ShippingService {
    private final ShippingRepository repository;
    private final ModelMapper mapper;
    private final AddressService addressService;
    @Override
    public List<GetAllShippingsResponse> getAll() {
        List<Shipping> shippings = repository.findAll();
        return shippings
                .stream()
                .map(shipping -> mapper.map(shipping, GetAllShippingsResponse.class))
                .toList();
    }

    @Override
    public GetShippingResponse getById(Long id) {
        Shipping shipping = repository.findById(id).orElseThrow();
        return mapper.map(shipping, GetShippingResponse.class);
    }

    @Override
    public Shipping createShipping(CreateShippingRequest request) {
        Shipping shipping = new Shipping();
        shipping.setReceiversFirstName(request.getReceiversFirstName());
        shipping.setReceiversLastName(request.getReceiversLastName());
        shipping.setAddress(addressService.getAddressById(request.getAddressId()));
        shipping.setStatus(ShippingStatus.BEINGPREPARED);
        return repository.save(shipping);
    }

    @Override
    public UpdateShippingResponse updateShipping(Long id, UpdateShippingRequest request) {
        Shipping shipping = repository.findById(id).orElseThrow();
        if (!shipping.getStatus().equals(ShippingStatus.BEINGPREPARED)){
            throw new RuntimeException(Messages.Shipping.TOO_LATE);

        }
        shipping.setReceiversFirstName(request.getReceiversfirstName());
        shipping.setReceiversLastName(request.getReceiverslastName());
        shipping.setAddress(addressService.getAddressById(request.getAddressId()));
        Shipping savedShipping = repository.save(shipping);
        UpdateShippingResponse response = mapper.map(savedShipping, UpdateShippingResponse.class);
        response.setAddressId(response.getAddressId());
        response.setOrderId(shipping.getOrder().getId());
        return response;
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}