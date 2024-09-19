package uz.ppdiary.pp_diary.exceptions;

public class MissingFieldException extends RuntimeException {
    public MissingFieldException(String fieldName){
        super("Missing required field(s): " + fieldName);
    }
}
