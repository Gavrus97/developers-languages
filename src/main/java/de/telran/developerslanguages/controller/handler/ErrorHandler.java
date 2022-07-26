package de.telran.developerslanguages.controller.handler;

import de.telran.developerslanguages.dto.response.HttpErrorResponseDTO;
import de.telran.developerslanguages.dto.response.ValidationErrorResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<HttpErrorResponseDTO> handleHttpErrors(ResponseStatusException ex) {
        var body = HttpErrorResponseDTO
                .builder()
                .status(ex.getStatus())
                .message(ex.getReason())
                .build();

        return ResponseEntity
                .status(body.getStatus())
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        var responseBody = ex.getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                DefaultMessageSourceResolvable::getDefaultMessage,
                                Collectors.toList()
                        )
                ));

        var body = ValidationErrorResponseDTO
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .errors(responseBody)
                .build();
        return ResponseEntity
                .status(body.getStatus())
                .body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex) {
        var body = HttpErrorResponseDTO
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return ResponseEntity
                .status(body.getStatus())
                .body(body);
    }
}
