package ch.ipt.JpaSwaggerLearning.service.Transaction;

import ch.ipt.JpaSwaggerLearning.openapi.api.TransactionApiDelegate;
import ch.ipt.JpaSwaggerLearning.openapi.model.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionApiDelegateImpl implements TransactionApiDelegate {

    private final TransactionService transactionService;

    public TransactionApiDelegateImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity<List<TransactionDTO>> listTransactions(){
        return ResponseEntity.ok(transactionService.listTransactions());
    }

    @Override
    public ResponseEntity<List<TransactionDTO>> getTransactionsByCard(Integer cardId) {
        return ResponseEntity.ok(transactionService.getTransactionsByCardId(cardId));
    }
}
