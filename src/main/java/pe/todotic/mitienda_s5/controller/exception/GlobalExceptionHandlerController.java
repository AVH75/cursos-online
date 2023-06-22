package pe.todotic.mitienda_s5.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler({EntityNotFoundException.class})
    String handleEntityNotFoundException(EntityNotFoundException ex) {
        return "error-404";
    }

}
