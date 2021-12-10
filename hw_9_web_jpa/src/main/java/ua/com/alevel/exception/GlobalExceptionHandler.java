package ua.com.alevel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<String> defaultErrorHandler(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + exception.getMessage() + "\"}");
    }

    @ExceptionHandler(value = IncorrectInputData.class)
    public ResponseEntity<String> IncorrectInputDataErrorHandler(IncorrectInputData exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"" + exception.getMessage() + "\"}");
    }
}
