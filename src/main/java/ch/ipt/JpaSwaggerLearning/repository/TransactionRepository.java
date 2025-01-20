package ch.ipt.JpaSwaggerLearning.repository;

import ch.ipt.JpaSwaggerLearning.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    @Query("SELECT t FROM TransactionEntity t JOIN FETCH t.cardEntity WHERE t.cardEntity.id = :cardId")
    List<TransactionEntity> findTransactionEntityByCardIdTesting(@Param("cardId") int cardId);

    List<TransactionEntity> findTransactionEntitiesByCardEntity_Uuid(String cardEntity_uuid);


}
