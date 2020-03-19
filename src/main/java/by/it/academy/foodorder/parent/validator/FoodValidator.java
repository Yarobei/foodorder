package by.it.academy.foodorder.parent.validator;

import by.it.academy.foodorder.parent.model.Food;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class FoodValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Food.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Food food = (Food) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cookingTime", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ingredients", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "NotEmpty");
        if (food.getName().length() < 5 || food.getName().length() > 100) {
            errors.rejectValue("name", "Food.sizeName.error");
        }
        if (food.getIngredients().length() < 5 || food.getIngredients().length() > 255) {
            errors.rejectValue("ingredients", "Food.sizeIngredients.error");
        }
    }
}
