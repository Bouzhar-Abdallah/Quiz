package bouzhar.quiz.demo.exception;

public class CustomValidationException extends RuntimeException{
    public CustomValidationException(String message) {
        super(message);
    }
}
