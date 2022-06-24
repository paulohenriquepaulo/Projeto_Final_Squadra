package br.com.squadra.app.controller.advice;

import br.com.squadra.app.exception.SquadraException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class SquadraAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("mensagem", "O campo ".concat(ex.getName().concat(" é inválido")));
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("mensagem", ex.getMessage());
        errors.put("status", String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            if (fieldName.equals("status")) {
                fieldName = "_status";
            }
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(SquadraException.class)
    public ResponseEntity<Map<String, String>> handlerSquadraException(SquadraException ex) {
        return ResponseEntity.status(ex.getStatus().value()).body(ex.getErrors());
    }

}
