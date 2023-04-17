package de.th.ro.vv.tm.models;

import de.th.ro.vv.tm.models.dtos.CustomerDto;
import io.micronaut.core.annotation.Introspected;

@Introspected
public record CustomerValidationModel(boolean isValid, CustomerDto customer) {
}
