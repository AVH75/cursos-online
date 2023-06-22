package pe.todotic.mitienda_s5.controller.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HttpErrorController implements ErrorController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/error")
    String handleError(Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Exception exception = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
        }

        if (exception != null) {
            model.addAttribute("status", status);
            model.addAttribute("message", exception.getMessage());
            model.addAttribute("trace", exception.getStackTrace());
            model.addAttribute("exception", exception.getCause());
            model.addAttribute("error", exception.getCause());
        }
        return "error";
    }

}
