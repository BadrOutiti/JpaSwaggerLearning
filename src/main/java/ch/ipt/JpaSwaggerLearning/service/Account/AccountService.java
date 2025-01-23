package ch.ipt.JpaSwaggerLearning.service.Account;

import ch.ipt.JpaSwaggerLearning.mappers.AccountMapper;
import ch.ipt.JpaSwaggerLearning.model.AccountEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.AccountReferenceDTO;
import ch.ipt.JpaSwaggerLearning.repository.AccountRepository;
import ch.ipt.JpaSwaggerLearning.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
    }

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
            AccountDTO accountDTO = accountMapper.mapAccountEntityToAccountDTO(account);
            accounts.add(accountDTO);
        }
        return accounts;
    }

    public AccountDTO getAccount(int accountId) {
        AccountEntity accountEntity = accountRepository.getReferenceById(accountId);
        return accountMapper.mapAccountEntityToAccountDTO(accountEntity);
    }
}
