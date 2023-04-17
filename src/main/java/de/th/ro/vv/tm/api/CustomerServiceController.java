package de.th.ro.vv.tm.api;

import de.th.ro.vv.tm.models.dtos.CustomerDto;
import de.th.ro.vv.tm.models.requestModels.CustomerRequestModel;
import de.th.ro.vv.tm.services.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller("/v1/customer")
public class CustomerServiceController {

    private final CustomerService customerService;

    public CustomerServiceController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Get(uri = "/", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all Customers", description = "Returns all registered Customers")
    @ApiResponse(responseCode = "200", description = "List of Customers")
    @ApiResponse(responseCode = "404", description = "No Customers found")
    @Tag(name = "Customer")
    public List<?> getCustomers(@Parameter(description = "Max Customers to query from Service - Limit is 100") @Max(100) int maxCustomers) {
        return customerService.getCustomers(maxCustomers);
    }


    @Get(uri = "/{customerId}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Get specific Customer from Database", description = "Returns registered Customer if available - else null")
    @ApiResponse(responseCode = "200", description = "Found Customer")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    @Tag(name = "Customer")
    public Optional<CustomerDto> getCustomer(@Parameter(description = "customerId to search for") @NotEmpty UUID customerId) {
        return customerService.getCustomer(customerId);
    }

    @Post(uri = "/", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Save Customer in Database", description = "Validates and save customer in Database")
    @ApiResponse(responseCode = "201", description = "Customer saved")
    @ApiResponse(responseCode = "400", description = "Bad Request - Customer not saved")
    @Tag(name = "Customer")
    public MutableHttpResponse<CustomerDto> saveCustomer(@NotNull CustomerRequestModel customer) {
        var saveResult = customerService.saveCustomer(customer);
        if (saveResult.getKey())
            return HttpResponse.created(saveResult.getValue());
        return HttpResponse.badRequest();
    }
}