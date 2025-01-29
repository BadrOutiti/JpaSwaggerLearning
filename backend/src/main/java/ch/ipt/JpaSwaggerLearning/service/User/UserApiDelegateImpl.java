package ch.ipt.JpaSwaggerLearning.service.User;

import ch.ipt.JpaSwaggerLearning.model.UserEntity;
import ch.ipt.JpaSwaggerLearning.openapi.api.UserApiDelegate;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserCreateDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserApiDelegateImpl implements UserApiDelegate {

    private final UserService userService;

    public UserApiDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        // Create the user using the service
        int userId = userService.createUser(userCreateDTO);

        //TODO: Dont use hardcoded string
        URI uri = URI.create("http://localhost:8080/user/" + userId);

        // Return 201 Created with the created object
        return ResponseEntity.created(uri).body(userService.getUserById(userId));
    }

    @Override
    //TODO: Limit can be null
    public ResponseEntity<List<UserDTO>> listUsers(Integer limit) {
        Page<UserEntity> userEntities = userService.getUsers(0, limit);
        List<UserDTO> userDTOS = userEntities.stream()
                .map(entity -> new UserDTO(entity.getId(), entity.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(userDTOS);
    }

    @Override
    public ResponseEntity<UserDTO> showUserById(Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
