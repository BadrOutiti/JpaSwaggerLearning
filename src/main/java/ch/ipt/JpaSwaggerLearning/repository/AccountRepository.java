package ch.ipt.JpaSwaggerLearning.repository;

import ch.ipt.JpaSwaggerLearning.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
}
