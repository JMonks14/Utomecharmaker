package com.tome.main.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.baeldung.web.dto.UserDto;
import com.tome.main.security.PlayerDTO;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final PlayerDTO user = (PlayerDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
