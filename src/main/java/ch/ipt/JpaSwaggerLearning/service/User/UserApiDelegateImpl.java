package ch.ipt.JpaSwaggerLearning.service.User;

import ch.ipt.JpaSwaggerLearning.model.UserEntity;
import ch.ipt.JpaSwaggerLearning.openapi.api.UsersApiDelegate;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserCreateDTO;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserApiDelegateImpl implements UsersApiDelegate {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        // Create the user using the service
        Integer userId = userService.createUser(userCreateDTO);

        // Construct the URI for the created resource
        URI location = URI.create(String.format("/users/%d", userId));

        // Return 201 Created with Location header
        return ResponseEntity.created(location).build();
    }

    @Override
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
