package uz.ppdiary.pp_diary.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.ppdiary.pp_diary.exceptions.InvalidDataException;
import uz.ppdiary.pp_diary.exceptions.MissingFieldException;
import uz.ppdiary.pp_diary.util.annotation.ValidUsername;

public class UsernameValidator implements ConstraintValidator<ValidUsername,String> {
    private boolean required;

    @Override
    public void initialize(ValidUsername constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && value == null)
            return true;
        if (value == null)
            throw new MissingFieldException(context.getDefaultConstraintMessageTemplate());

        if (value.matches("^[a-zA-Z0-9_-]{3,20}$"))
            return true;

        throw new InvalidDataException(context.getDefaultConstraintMessageTemplate());
    }
}
