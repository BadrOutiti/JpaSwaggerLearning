package ch.ipt.JpaSwaggerLearning.service;

import ch.ipt.JpaSwaggerLearning.mappers.UserMapper;
import ch.ipt.JpaSwaggerLearning.model.UserEntity;
import ch.ipt.JpaSwaggerLearning.openapi.model.UserDTO;
import ch.ipt.JpaSwaggerLearning.repository.UserRepository;
import ch.ipt.JpaSwaggerLearning.service.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)


class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
    }

    @Test
    void getUsers() {
        // GIVEN: Test users
        List<UserEntity> allUsers = Arrays.asList(
                new UserEntity("Alice", "1234"),
                new UserEntity("Bob", "password"),
                new UserEntity("Charlie", "testpass"),
                new UserEntity("David", "securepass")
        );

        // Mimic real pagination logic (return only requested items)
        int page = 0;
        int size = 2;
        List<UserEntity> pagedUsers = allUsers.subList(page * size, Math.min((page + 1) * size, allUsers.size()));
        Page<UserEntity> userPage = new PageImpl<>(pagedUsers, PageRequest.of(page, size), allUsers.size());

        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage); // ✅ Mock correct pagination

        // WHEN
        Page<UserEntity> result = userService.getUsers(page, size);

        // THEN
        assertNotNull(result);
        assertEquals(2, result.getContent().size()); // ✅ Should return only 2 users
        assertEquals(4, result.getTotalElements()); // ✅ Ensure total count is still 4
        verify(userRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void getUserById() {
        // GIVEN
        UserEntity user = new UserEntity("Alice", "test");
        UserDTO userDTO = new UserDTO(1, "Alice");

        when(userRepository.getReferenceById(1)).thenReturn(user);
        when(userMapper.MapUserEntityToUserDTO(user)).thenReturn(userDTO); // ✅ Mock the mapping

        // WHEN
        UserDTO result = userService.getUserById(1);

        // THEN
        assertNotNull(result);
        assertEquals("Alice", result.getName());
        verify(userRepository, times(1)).getReferenceById(1);
        verify(userMapper, times(1)).MapUserEntityToUserDTO(user);
    }
}