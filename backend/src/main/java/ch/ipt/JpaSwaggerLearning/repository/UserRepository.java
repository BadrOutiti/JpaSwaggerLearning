package ch.ipt.JpaSwaggerLearning.repository;

import ch.ipt.JpaSwaggerLearning.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
