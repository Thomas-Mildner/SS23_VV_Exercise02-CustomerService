package de.th.ro.vv.tm.validators;

import de.th.ro.vv.tm.models.requestModels.CustomerRequestModel;
import jakarta.inject.Singleton;

@Singleton
public class CustomerValidator {


    public boolean validateCustomer(CustomerRequestModel customer){
        if (customer == null)
            return false;

        return !customer.lastName().isEmpty() && !customer.firstName().isEmpty() && !customer.email().isEmpty();

    }
}
