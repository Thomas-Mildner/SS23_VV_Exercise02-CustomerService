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
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Bean
public class CustomerService {

    public static Map<UUID, CustomerDto> CustomerDataStore = new HashMap<>();
    private final CustomerValidator customerValidator;


    public CustomerService(CustomerValidator customerValidator) {
        this.customerValidator = customerValidator;
    }

    public List<?> getCustomers(int maxCustomers) {
        var customers = new ArrayList<>();
        IntStream.rangeClosed(1, maxCustomers - CustomerDataStore.size()).boxed().toList().parallelStream().forEach(t -> {
            customers.add(generateCustomer());
        });

        return Stream.of(customers, CustomerDataStore.values().stream().toList())
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


    private CustomerDto generateCustomer() {

        var faker = new Faker(new Locale("de"));
        var random = new Random();
        return new CustomerDtoBuilder()
                .setCustomerId(UUID.randomUUID())
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setEmail(faker.internet().emailAddress())
                .setCompanyName(random.nextBoolean() ? faker.company().name() : "")
                .setBirthday(faker.date().birthday())
                .createCustomerDto();
    }


}
