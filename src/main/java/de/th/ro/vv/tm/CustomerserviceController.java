package de.th.ro.vv.tm;

import io.micronaut.http.annotation.*;

@Controller("/customerservice")
public class CustomerserviceController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}