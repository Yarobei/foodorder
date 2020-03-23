package by.it.academy.foodorder.parent.validator;

import by.it.academy.foodorder.parent.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PhoneValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty");
        if(!user.getPhoneNumber().matches("(^\\+{1}375)([0-9]{2})([0-9]{3})([0-9]{2})([0-9]{2}$)")){
            errors.rejectValue("phoneNumber", "PhoneNumber.form.error");
        }
    }

}
