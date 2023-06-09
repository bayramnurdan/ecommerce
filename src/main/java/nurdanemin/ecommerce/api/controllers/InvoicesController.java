package nurdanemin.ecommerce.api.controllers;


import lombok.AllArgsConstructor;
import nurdanemin.ecommerce.business.abstracts.InvoiceService;
import nurdanemin.ecommerce.business.dto.request.create.invoice.CreateInvoiceRequest;
import nurdanemin.ecommerce.business.dto.request.update.invoice.UpdateInvoiceRequest;
import nurdanemin.ecommerce.business.dto.response.create.invoice.CreateInvoiceResponse;
import nurdanemin.ecommerce.business.dto.response.get.GetAllInvoicesResponse;
import nurdanemin.ecommerce.business.dto.response.get.GetInvoiceResponse;
import nurdanemin.ecommerce.business.dto.response.update.invoice.UpdateInvoiceResponse;
import nurdanemin.ecommerce.entities.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private final InvoiceService service;


    @GetMapping
    public List<GetAllInvoicesResponse> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable Long id){
        return service.getById(id);

    }



    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Long id){
        service.delete(id);
    }


}