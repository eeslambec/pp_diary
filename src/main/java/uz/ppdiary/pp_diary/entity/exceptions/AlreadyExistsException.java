package uz.ppdiary.pp_diary.entity.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String m){
        super(m + " already exists");
    }
}
