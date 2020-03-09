package by.it.academy.foodorder.parent.services.impl;

import by.it.academy.foodorder.parent.model.Role;
import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.repository.RoleRepository;
import by.it.academy.foodorder.parent.repository.UserRepository;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getByUserId(id);
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
    public void updateUser(Long id) {
        User user = userRepository.findByUserId(Long.valueOf(id));
        if(userRepository.existsById(Long.valueOf(id))){
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
    public Optional<User> findByUsernameAndPassword(String name, String password) {
        return userRepository.findByUsernameAndPassword(name, password);
    }

    @Override
    public boolean existsByUsername(String name) {
        return !userRepository.existsByUsername(name);
    }


}
