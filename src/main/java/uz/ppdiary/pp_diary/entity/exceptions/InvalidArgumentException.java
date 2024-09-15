package uz.ppdiary.pp_diary.entity.exceptions;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(message + " is invalid");
    }
}
