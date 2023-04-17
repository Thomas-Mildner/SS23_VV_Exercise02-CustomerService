package de.th.ro.vv.tm.models.dtos;


import io.micronaut.core.annotation.Introspected;

@Introspected
public record HealthStatusDto(String healthStatus, String startupTime) {
}
