package ch.ipt.JpaSwaggerLearning.service.Account;

import ch.ipt.JpaSwaggerLearning.mappers.AccountMapper;
import ch.ipt.JpaSwaggerLearning.model.AccountEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountReferenceDTO;
import ch.ipt.JpaSwaggerLearning.repository.AccountRepository;
import ch.ipt.JpaSwaggerLearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountMapper accountMapper;

    public int createAccount(AccountReferenceDTO accountReferenceDTO){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUser(userRepository.getReferenceById(accountReferenceDTO.getUserId()));
        accountRepository.save(accountEntity);

        return accountEntity.getId();
    }

    public List<AccountDTO> listAccounts(){
        List<AccountEntity> accountEntities = accountRepository.findAll();
        List<AccountDTO> accounts = new ArrayList<>();
        for(AccountEntity account : accountEntities){
            AccountDTO accountDTO = accountMapper.MapAccountEntityToAccountDTO(account);
            accounts.add(accountDTO);
        }
        return accounts;
    }
}
