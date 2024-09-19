package uz.ppdiary.pp_diary.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String m) {
        super(m + " not found");
    }
}
