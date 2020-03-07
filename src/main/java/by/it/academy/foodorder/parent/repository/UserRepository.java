package by.it.academy.foodorder.parent.repository;

import by.it.academy.foodorder.parent.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String name);

    User findByUsernameAndPassword(String name, String password);

}
