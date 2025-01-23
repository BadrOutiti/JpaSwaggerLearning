package ch.ipt.JpaSwaggerLearning.service.Account;


import ch.ipt.JpaSwaggerLearning.openapi.api.AccountApiDelegate;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountReferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class AccountApiDelegateImpl implements AccountApiDelegate {

    @Autowired
    private AccountService accountService;

    @Override
    public ResponseEntity<AccountDTO> createAccount(AccountReferenceDTO accountReferenceDTO) {
        int accountId = accountService.createAccount(accountReferenceDTO);

        //TODO: Dont use hardcoded string
        URI location = URI.create("http://localhost:8080/account/" + accountId);

        // Return 201 Created with Location header
        return ResponseEntity.created(location).body(accountService.getAccount(accountId));

    }

    @Override
    public ResponseEntity<List<AccountDTO>> listAccounts(){
        return ResponseEntity.ok(accountService.listAccounts());
    }
}