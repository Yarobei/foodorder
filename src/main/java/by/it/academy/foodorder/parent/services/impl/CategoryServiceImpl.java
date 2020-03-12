package by.it.academy.foodorder.parent.services.impl;

import by.it.academy.foodorder.parent.model.Category;
import by.it.academy.foodorder.parent.repository.CategoryRepository;
import by.it.academy.foodorder.parent.services.interfaces.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category getCategoryById(Long id) {
        return categoryRepository.findByCategoryId(id);
    }
}

