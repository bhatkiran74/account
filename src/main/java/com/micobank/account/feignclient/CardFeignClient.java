package com.micobank.account.feignclient;


import com.micobank.account.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "card")
public interface CardFeignClient {

    @GetMapping("/api/v1/card/fetch")
    ResponseEntity<CardDto> fetchCardDetails(@RequestParam String mobileNumber);
}
