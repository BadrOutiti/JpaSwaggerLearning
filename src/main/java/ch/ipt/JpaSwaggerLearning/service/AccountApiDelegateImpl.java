package ch.ipt.JpaSwaggerLearning.service;


import ch.ipt.JpaSwaggerLearning.openapi.api.AccountApiDelegate;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountReferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class AccountApiDelegateImpl implements AccountApiDelegate {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<Void> createAccount(AccountReferenceDTO accountReferenceDTO){
        int entityId = accountService.createAccount(accountReferenceDTO);

        // Construct the URI for the created resource
        URI location = URI.create(String.format("/accounts/%d", entityId));

        // Return 201 Created with Location header
        return ResponseEntity.created(location).build();

    }

//    public ResponseEntity<List<AccountDTO>> listAccounts(){
//
//        return ResponseEntity.ok();
//    }




}