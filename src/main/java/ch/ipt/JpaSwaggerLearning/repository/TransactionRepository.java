package ch.ipt.JpaSwaggerLearning.repository;

import ch.ipt.JpaSwaggerLearning.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    @Query(value = "SELECT t.* FROM t_transaction t INNER JOIN t_card c ON t.card_id = c.id WHERE c.id = :cardId",
            nativeQuery = true)
    List<TransactionEntity> findTransactionEntityByCardId(@Param("cardId") int cardId);
}
