package platform.codingnomads.co.springsecurity.authorization.custompermissions.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public String handleError(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error-page";
    }
}
