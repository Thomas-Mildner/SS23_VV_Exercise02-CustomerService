package de.th.ro.vv.tm.models.builder;

import de.th.ro.vv.tm.models.dtos.CustomerDto;

import java.util.Date;
import java.util.UUID;

public class CustomerDtoBuilder {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private Date birthday;

    public CustomerDtoBuilder setCustomerId(UUID customerId) {
        this.customerId = customerId;
        return this;
    }

    public CustomerDtoBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerDtoBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerDtoBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDtoBuilder setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public CustomerDtoBuilder setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public CustomerDto createCustomerDto() {
        return new CustomerDto(customerId, firstName, lastName, email, companyName, birthday);
    }
}