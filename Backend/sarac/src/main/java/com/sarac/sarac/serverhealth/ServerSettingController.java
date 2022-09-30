package com.sarac.sarac.serverhealth;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class ServerSettingController {

    @GetMapping("/health")
    public String getHealth() {
        return "Up";
    }

}
