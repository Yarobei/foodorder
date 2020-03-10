package by.it.academy.foodorder.parent.services.interfaces;

import by.it.academy.foodorder.parent.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

}
