package com.micobank.account.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "account")
@Data
public class AccountConfiguration {

    private String name;
    private String version;
    private String description;
    private Map<String, String> contact;

}