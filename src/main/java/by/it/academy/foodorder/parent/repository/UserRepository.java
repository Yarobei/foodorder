package by.it.academy.foodorder.parent.repository;

import by.it.academy.foodorder.parent.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String name);

    Optional<User> findByUsernameAndPassword(String name, String password);

    boolean existsByUsername(String name);

    User findByUserId(Long id);

    User getByUserId(Long id);

}
