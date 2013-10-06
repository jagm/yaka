package pl.jagm.kanban.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mvc.extensions.ajax.AjaxUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class ErrorHandlingController {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandlingController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView errorHandler(WebRequest request, HttpServletResponse response, Exception exception) {
        log.error("Exception", exception);
        String viewName = AjaxUtils.isAjaxRequest(request) ? "ajaxError" : "error";
        ModelAndView modelAndView = new ModelAndView(viewName);

        // TODO: add more accurate message
        modelAndView.addObject("errorMessage", "Sorry, an error occurred while processing your request");

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

}
