package de.th.ro.vv.tm.api;

import de.th.ro.vv.tm.models.dtos.HealthStatusDto;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.lang.management.ManagementFactory;
import java.util.Date;

@Controller("/v1/health")
public class HealthController {


    @Get(uri = "/", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Health Status", description = "Returns Health Status Object if application is running")
    @ApiResponse(content = @Content(mediaType = "text/json", schema = @Schema(type = "string")))
    @ApiResponse(responseCode = "200", description = "Application is running - returning health status")
    @Tag(name = "Health")
    public HealthStatusDto healthStatus() {
        return new HealthStatusDto("Application is running", new Date(ManagementFactory.getRuntimeMXBean().getStartTime()).toString());
    }
}
