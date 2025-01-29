package ch.ipt.JpaSwaggerLearning.service.User;

import ch.ipt.JpaSwaggerLearning.mappers.UserMapper;
import ch.ipt.JpaSwaggerLearning.model.UserEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserCreateDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserDTO;
import ch.ipt.JpaSwaggerLearning.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Integer createUser(UserCreateDTO userCreateDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userCreateDTO.getName());
        userEntity.setPassword(userCreateDTO.getPassword());
        userRepository.save(userEntity);

        return userEntity.getId();
    }

    // Fetch paginated users
    public Page<UserEntity> getUsers(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public UserDTO getUserById(int id){
        UserEntity entity = userRepository.getReferenceById(id);
        return userMapper.mapUserEntityToUserDTO(entity);
    }
}
