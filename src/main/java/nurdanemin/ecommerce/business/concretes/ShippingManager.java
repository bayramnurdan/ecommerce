package nurdanemin.ecommerce.business.concretes;

import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.AddressService;
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
    private final AddressService addressService;
    @Override
    public List<GetAllShippingsResponse> getAll() {
        List<Shipping> shippings = repository.findAll();
        List<GetAllShippingsResponse> response= shippings
                .stream()
                .map(shipping -> mapper.map(shipping, GetAllShippingsResponse.class))
                .toList();
        return response;
    }

    @Override
    public GetShippingResponse getById(Long id) {
       Shipping shipping = repository.findById(id).orElseThrow();
       GetShippingResponse response =mapper.map(shipping, GetShippingResponse.class);
       return  response;
    }

    @Override
    public Shipping createShipping(CreateShippingRequest request) {
        Shipping shipping = new Shipping();
        shipping.setReceiversfirstName(request.getReceiversfirstName());
        shipping.setReceiverslastName(request.getReceiverslastName());
        shipping.setAddress(addressService.getAddressById(request.getAddressId()));
        shipping.setStatus(ShippingStatus.BEINGPREPARED);
        Shipping shippingSaved = repository.save(shipping);
        return shippingSaved;


    }

    @Override
    public UpdateShippingResponse updateShipping(Long id, UpdateShippingRequest request) {
        return null;
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
