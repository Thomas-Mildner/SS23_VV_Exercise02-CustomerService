package de.th.ro.vv.tm.models.dtos;

import io.micronaut.core.annotation.Introspected;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;
import java.util.UUID;

@Introspected
@Schema(name = "Customer", description = "Customer Implementation")
public record CustomerDto(UUID customerId, String firstName, String lastName, String email, String companyName, Date birthday){
}
