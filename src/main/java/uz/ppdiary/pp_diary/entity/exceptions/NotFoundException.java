package uz.ppdiary.pp_diary.entity.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String m) {
        super(m + " not found");
    }
}
