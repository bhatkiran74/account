package com.micobank.account.rest;


import com.micobank.account.constants.AccountConstants;
import com.micobank.account.dto.CustomerDto;
import com.micobank.account.dto.AccountDto;
import com.micobank.account.dto.ResponseDto;
import com.micobank.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/account", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountRestController {

    @Autowired
    private IAccountService iAccountService;

    @PostMapping("/create")
    ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));
    }

    @PutMapping("/update")
    ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto){


        boolean isUpdated = iAccountService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(AccountConstants.STATUS_500,AccountConstants.MESSAGE_500));
        }
    }



    @GetMapping("/fetch")
    ResponseEntity<CustomerDto> fetchAccountDetailsUsingMobileNo(@RequestParam String mobileNumber){

        CustomerDto customerDto = iAccountService.findAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

}
