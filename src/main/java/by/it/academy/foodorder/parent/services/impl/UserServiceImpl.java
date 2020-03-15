package by.it.academy.foodorder.parent.services.impl;

import by.it.academy.foodorder.parent.model.Basket;
import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.repository.RoleRepository;
import by.it.academy.foodorder.parent.repository.UserRepository;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void saveUser(User user) {
        if(user.getUserId()==null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(new HashSet<>(roleRepository.findByName("user")));
            Basket basket = new Basket();
            userRepository.save(user);
            basket.setBasketId(user.getUserId());
            user.setBasket(basket);
            userRepository.save(user);
        }else{
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.getByUserId(id);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        log.info("Delete user with id: {}", id);
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        log.info("Delete user: {}", user);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> findByUsernameAndPassword(String name, String password) {
        return userRepository.findByUsernameAndPassword(name, password);
    }

    @Override
    @Transactional
    public boolean existsByUsername(String name) {
        return !userRepository.existsByUsername(name);
    }

    @Override
    public Long getIdByUsername(String name) {
        return userRepository.findByUsername(name).getUserId();
    }


}
