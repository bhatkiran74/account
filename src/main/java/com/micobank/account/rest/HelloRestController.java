package com.micobank.account.rest;


import com.micobank.account.config.AccountConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/conf", produces = {MediaType.APPLICATION_JSON_VALUE})
public class HelloRestController {


    @Value("${build.info}")
    private String buildInfo;

    @GetMapping("/build-info")
    public String getBuildInfo(){
        return buildInfo;
    }


    @Autowired
    private Environment environment;

    @GetMapping("/env")
    public String getEnvironmentObject(){
        return environment.getProperty("build.info");
    }


    @Autowired
    private AccountConfiguration accountConfiguration;

    @GetMapping("/config")
    public AccountConfiguration getConfigObject(){
        return accountConfiguration;
    }

}
