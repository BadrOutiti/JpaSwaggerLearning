package ch.ipt.JpaSwaggerLearning.mappers;

import ch.ipt.JpaSwaggerLearning.model.TransactionEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    @Autowired
    private CardMapper cardMapper;

    public TransactionDTO mapTransactionEntityToTransactionDTO(TransactionEntity transactionEntity){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.amountInChf(transactionEntity.getAmountInChf());
        transactionDTO.card(cardMapper.mapCardEntityToCardDTO(transactionEntity.getCardEntity()));
        transactionDTO.date((int) (transactionEntity.getDate().getTime() / 1000));

        return transactionDTO;
    }

}
