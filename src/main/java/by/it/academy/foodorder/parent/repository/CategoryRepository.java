package by.it.academy.foodorder.parent.repository;

import by.it.academy.foodorder.parent.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findAllByCategoryId(Integer id);

}
