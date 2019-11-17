package glim.antony.spring_led_market.services;

import glim.antony.spring_led_market.entities.Category;
import glim.antony.spring_led_market.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }
}
