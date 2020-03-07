package by.it.academy.foodorder.parent.services.interfaces;

import by.it.academy.foodorder.parent.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    boolean addUser(User user);

    User getByUsername(String username);

    Optional<User> getUserById(Long id);

    void deleteUserById (Long id);

    void deleteUser (User user);

    void updateUser (User user);

    List<User> getAllUsers();

    User findByUsernameAndPassword(String name, String password);

}
