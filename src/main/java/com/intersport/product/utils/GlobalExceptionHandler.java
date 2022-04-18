package com.intersport.product.utils;

import com.intersport.product.utils.exceptions.ResourceExistException;
import com.intersport.product.utils.exceptions.ResourceInUseException;
import com.intersport.product.utils.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceInUseException.class})
    public ResponseEntity<String> handleResourceInUseException(ResourceInUseException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Resource is in use \n" + exception.getMessage());
    }

    @ExceptionHandler({ResourceNotFound.class})
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found \n" + exception.getMessage());
    }

    @ExceptionHandler({ResourceExistException.class})
    public ResponseEntity<String> handleResourceExistException(ResourceExistException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Type already exists");
    }


}
