package by.it.academy.foodorder.parent.services.impl;

import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.repository.UserRepository;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean addUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Delete user with id: {}", id);
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    @Override
    public void deleteUser(User user) {
        log.info("Delete user: {}", user);
        userRepository.delete(user);
    }

    @Override
    public void updateUser(User user) {
        if(userRepository.existsById(user.getUserId())){
            userRepository.delete(user);
            log.info("Update user: {}", user);
            userRepository.save(user);
        }else{
            log.info("Update user: {}", user);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String name, String password) {
        return userRepository.findByUsernameAndPassword(name, password);
    }

}
