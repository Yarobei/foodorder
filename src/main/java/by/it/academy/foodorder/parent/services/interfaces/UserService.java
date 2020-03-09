package by.it.academy.foodorder.parent.services.interfaces;

import by.it.academy.foodorder.parent.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    void saveUser(User user);

    User getByUsername(String username);

    User getUserById(Long id);

    void deleteUserById (Long id);

    void deleteUser (User user);

    void updateUser (Long id);

    List<User> getAllUsers();

    Optional<User> findByUsernameAndPassword(String name, String password);

    boolean existsByUsername(String name);

}
