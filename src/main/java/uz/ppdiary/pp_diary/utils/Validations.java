package uz.ppdiary.pp_diary.utils;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Validations {
    public static <T> T requireNonNullElse(@NotBlank T obj, T defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof String s) {
            if (s.trim().isEmpty()) {
                return defaultValue;
            }
        } else if (obj instanceof Integer || obj instanceof Double) {
            double number = (obj instanceof Integer) ? ((Integer) obj).doubleValue() : (Double) obj;
            if (number < 0) {
                return defaultValue;
            }
        } else if (obj instanceof List<?> list) {
            if (list.isEmpty())
                return defaultValue;
        }
        return obj;
    }
}
