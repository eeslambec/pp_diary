package uz.ppdiary.pp_diary.entity.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String m){
        super("Invalid data in field(s) " + m);
    }
}
