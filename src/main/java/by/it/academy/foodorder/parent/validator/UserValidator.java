package by.it.academy.foodorder.parent.validator;

import by.it.academy.foodorder.parent.model.User;
import by.it.academy.foodorder.parent.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if(user.getUsername().length() < 5 || user.getUsername().length() > 20){
            errors.rejectValue("username", "Size.userForm.username");
        }
        if(userService.getByUsername(user.getUsername())!=null){
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if(user.getPassword().length()<6){
            errors.rejectValue("password", "Size.userForm.password");
        }
        if(!user.getPasswordConfirm().equals(user.getPassword())){
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty");
        if(!user.getPhoneNumber().matches("(^\\+{1}375)([0-9]{2})([0-9]{3})([0-9]{2})([0-9]{2}$)")){
            errors.rejectValue("phoneNumber", "PhoneNumber.form.error");
        }
    }

}
