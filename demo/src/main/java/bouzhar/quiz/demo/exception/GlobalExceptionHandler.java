package bouzhar.quiz.demo.exception;

import bouzhar.quiz.demo.level.exceptions.LevelPointsException;
import bouzhar.quiz.demo.validation.exceptions.ValidationPointsException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //handling resource not found errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public Map<String, String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("recource not found error", ex.getMessage());
        return errorResponse;
    }

    //handling illegal state errors
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalStateException.class)
    public Map<String, String> handleIllegalStateException(IllegalStateException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Data error", ex.getMessage());
        return errorResponse;
    }

    //handling persistence validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> validationErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        errors.put("validation error", validationErrors);
        return errors;
    }

    //handling custom validation errors
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomValidationException.class)
    public Map<String, String> handleCustomValidationExceptions(CustomValidationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return errorResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> errors = new HashMap<>();
        Map<String, String> validationErrors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            validationErrors.put(fieldName, errorMessage);
        });

        errors.put("constraint violation error", validationErrors);
        return errors;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationPointsException.class)
    public Map<String, String> handleValidationPointsException(ValidationPointsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("cannot add validation", ex.getMessage());
        return errorResponse;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LevelPointsException.class)
    public Map<String, String> handleLevelPointsException(LevelPointsException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("cannot add level", ex.getMessage());
        return errorResponse;
    }
}
