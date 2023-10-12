package org.elis.eventsmanager.handler;

import org.elis.eventsmanager.Exception.NotValidDataException;
import org.elis.eventsmanager.Exception.UserNotFoundException;
import org.elis.eventsmanager.dto.response.exception.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GenericExceptionHandler {
    /*
    @ExceptionHandler(NotValidDataException.class)
    public ResponseEntity<ErrorMessage> handleNotValidDataException(NotValidDataException e){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setError(e.getError());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
*/
/*
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException e){
            Map<String, String> map = new TreeMap<>();
            map.put("user", e.getMessage());
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setDate(LocalDateTime.now());
            errorMessage.setError(map);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        */

/*
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
        public ResponseEntity<ErrorMessage> sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
        Map<String, String> map = new TreeMap<>();
        map.put("user", e.getMessage());
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setError(map);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> notValidDataManager(MethodArgumentNotValidException e){
        Map<String,String> map = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        ErrorMessage m=new ErrorMessage();
        m.setDate(LocalDateTime.now());
        m.setError(map);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }


}
