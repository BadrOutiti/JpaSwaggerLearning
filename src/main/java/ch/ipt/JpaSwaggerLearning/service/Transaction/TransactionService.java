package ch.ipt.JpaSwaggerLearning.service.Transaction;

import ch.ipt.JpaSwaggerLearning.mappers.TransactionMapper;
import ch.ipt.JpaSwaggerLearning.model.TransactionEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.TransactionDTO;
import ch.ipt.JpaSwaggerLearning.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public List<TransactionDTO> listTransactions(){
        List<TransactionEntity> transactionEntities = transactionRepository.findAll();
        List<TransactionDTO> transactions = new ArrayList<>();

        for(TransactionEntity transactionEntity : transactionEntities){
            transactions.add(transactionMapper.mapTransactionEntityToTransactionDTO(transactionEntity));
        }
        return transactions;
    }

    public List<TransactionDTO> getTransactionsByCardId(int cardId){
        List<TransactionEntity> transactionEntities = transactionRepository.findTransactionEntityByCardId(cardId);
        List<TransactionDTO> transactions = new ArrayList<>();

        for(TransactionEntity transactionEntity : transactionEntities){
            transactions.add(transactionMapper.mapTransactionEntityToTransactionDTO(transactionEntity));
        }
        return transactions;
    }

}
