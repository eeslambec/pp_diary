package uz.ppdiary.pp_diary.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.ppdiary.pp_diary.exceptions.InvalidDataException;
import uz.ppdiary.pp_diary.exceptions.MissingFieldException;
import uz.ppdiary.pp_diary.utils.annotation.ValidEmail;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private boolean required;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && value == null)
            return true;
        if (value == null)
            throw new MissingFieldException(context.getDefaultConstraintMessageTemplate());

        if (value.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))
            return true;

        throw new InvalidDataException(context.getDefaultConstraintMessageTemplate());
    }
}
