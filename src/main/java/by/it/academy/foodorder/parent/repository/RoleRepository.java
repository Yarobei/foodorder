package by.it.academy.foodorder.parent.repository;

import by.it.academy.foodorder.parent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;


public interface RoleRepository extends JpaRepository<Role, Long> {

    HashSet<Role> findByName(String name);
}
