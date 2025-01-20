package ch.ipt.JpaSwaggerLearning.service.Transaction;

import ch.ipt.JpaSwaggerLearning.mappers.TransactonMapper;
import ch.ipt.JpaSwaggerLearning.model.TransactionEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.TransactionDTO;
import ch.ipt.JpaSwaggerLearning.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactonMapper transactionMapper;

    public List<TransactionDTO> listTransactions(){
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        List<TransactionDTO> transactions = new ArrayList<>();

        for(TransactionEntity transactionEntity : transactionEntities){
            transactions.add(transactionMapper.MapTransactionEntityToTransactionDTO(transactionEntity));
        }
        return transactions;
    }

    public List<TransactionDTO> getTransactionsByCardId(int cardId){
        List<TransactionEntity> transactionEntities = transactionRepository.findTransactionEntityByCardId(cardId);
        List<TransactionDTO> transactions = new ArrayList<>();

        for(TransactionEntity transactionEntity : transactionEntities){
            transactions.add(transactionMapper.MapTransactionEntityToTransactionDTO(transactionEntity));
        }
        return transactions;
    }

}
