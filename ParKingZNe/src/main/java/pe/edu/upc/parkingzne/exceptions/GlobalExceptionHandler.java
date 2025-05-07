package pe.edu.upc.parkingzne.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Faltan datos obligatorios o contienen errores.");
        body.put("errores", errores);

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }

    // Manejo de excepciones personalizadas lanzadas desde servicios
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(BadRequestException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error: " + ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }

    // Otros errores inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralErrors(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error interno inesperado");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RequestBodyException.class)
    public ResponseEntity<String> handleRequestBodyException(RequestBodyException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
