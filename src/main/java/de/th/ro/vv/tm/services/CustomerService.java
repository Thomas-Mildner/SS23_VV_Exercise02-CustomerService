package de.th.ro.vv.tm.services;

import de.th.ro.vv.tm.models.CustomerValidationModel;
import de.th.ro.vv.tm.models.builder.CustomerDtoBuilder;
import de.th.ro.vv.tm.models.dtos.CustomerDto;
import de.th.ro.vv.tm.models.requestModels.CustomerRequestModel;
import de.th.ro.vv.tm.validators.CustomerValidator;
import io.micronaut.context.annotation.Bean;
import net.datafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Bean
public class CustomerService {

    public static Map<UUID, CustomerDto> CustomerDataStore = new HashMap<>();
    private final CustomerValidator customerValidator;


    public CustomerService(CustomerValidator customerValidator) {
        this.customerValidator = customerValidator;
    }

    public List<?> getCustomers(int maxCustomers) {
        var faker = new Faker(new Locale("de"));
        List<CustomerDto> fakeSequence = faker.collection(() -> new CustomerDto(UUID.randomUUID(),
                        faker.name().firstName(), faker.name().lastName(),
                        faker.internet().emailAddress(), faker.company().name(), faker.date().birthday()))
                .maxLen(maxCustomers - CustomerDataStore.size())
                .generate();


        return Stream.of(fakeSequence, CustomerDataStore.values().stream().toList())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDto> getCustomer(UUID customerId) {
        return Optional.ofNullable(CustomerDataStore.get(customerId));
    }

    public CustomerValidationModel saveCustomer(CustomerRequestModel customer) {

        if (!customerValidator.validateCustomer(customer)) {
            return new CustomerValidationModel(false, null);
        }

        var customerDto = new CustomerDtoBuilder().setCustomerId(UUID.randomUUID())
                .setFirstName(customer.firstName())
                .setLastName(customer.lastName())
                .setEmail(customer.email())
                .setCompanyName(customer.companyName())
                .setBirthday(customer.birthday())
                .createCustomerDto();
        CustomerDataStore.put(customerDto.customerId(), customerDto);
        return new CustomerValidationModel(true, customerDto);
    }
}
