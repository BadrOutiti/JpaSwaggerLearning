package ch.ipt.JpaSwaggerLearning.mappers;

import ch.ipt.JpaSwaggerLearning.model.UserEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO MapUserEntityToUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getName());
        return userDTO;
    }
}
