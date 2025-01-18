package ch.ipt.JpaSwaggerLearning.service;

import ch.ipt.JpaSwaggerLearning.model.AccountEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountReferenceDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserDTO;
import ch.ipt.JpaSwaggerLearning.repository.AccountRepository;
import ch.ipt.JpaSwaggerLearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    private UserRepository userRepository;

    public int createAccount(AccountReferenceDTO accountReferenceDTO){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUser(userRepository.getReferenceById(accountReferenceDTO.getUserId()));
        accountRepository.save(accountEntity);

        return accountEntity.getId();
    }

//    public List<AccountDTO> listAccounts(){
//        List<AccountEntity> accountEntities = accountRepository.findAll();
//
//    }




}
