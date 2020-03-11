package by.it.academy.foodorder.parent.repository;

import by.it.academy.foodorder.parent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    HashSet<Role> findByName(String name);
}
