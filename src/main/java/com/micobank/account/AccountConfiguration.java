package com.micobank.account;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "account")
@Data
public class AccountConfiguration {

    private String name;
    private String version;
    private String description;
    private Contact contact;

    @Data
    public static class Contact{
        private String name;
        private String email;
    }
}
