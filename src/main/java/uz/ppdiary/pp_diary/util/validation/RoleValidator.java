package uz.ppdiary.pp_diary.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.ppdiary.pp_diary.exceptions.InvalidDataException;
import uz.ppdiary.pp_diary.exceptions.MissingFieldException;
import uz.ppdiary.pp_diary.util.annotation.ValidRole;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {
    private boolean required;

    @Override
    public void initialize(ValidRole constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!required && value == null)
            return true;
        if (value == null)
            throw new MissingFieldException(context.getDefaultConstraintMessageTemplate());

        if (value.matches("^ROLE_[A-Z]+$"))
            return true;

        throw new InvalidDataException(context.getDefaultConstraintMessageTemplate());
    }
}
