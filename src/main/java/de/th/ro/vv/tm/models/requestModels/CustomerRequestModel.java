package de.th.ro.vv.tm.models.requestModels;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(name = "CustomerRequestModel", description = "Customer Request Model")
public record CustomerRequestModel(String firstName, String lastName, String email, String companyName, Date birthday){
}