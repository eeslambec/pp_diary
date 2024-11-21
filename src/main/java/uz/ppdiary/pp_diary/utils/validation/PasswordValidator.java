package uz.ppdiary.pp_diary.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.ppdiary.pp_diary.exceptions.InvalidDataException;
import uz.ppdiary.pp_diary.exceptions.MissingFieldException;
import uz.ppdiary.pp_diary.utils.annotation.ValidPassword;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private boolean required;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && value == null)
            return true;
        if (value == null)
            throw new MissingFieldException(context.getDefaultConstraintMessageTemplate());

        if (value.matches("^[a-zA-Z0-9_@]{8,}"))
            return true;

        throw new InvalidDataException(context.getDefaultConstraintMessageTemplate());
    }

}
