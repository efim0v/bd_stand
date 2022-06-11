package ru.efimov.projects.db.dbstand.controllers.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.efimov.projects.db.dbstand.exceptions.EntityAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({EntityAlreadyExistsException.class})
    @ResponseBody
    public ExceptionDTO handleException(Exception exception, HttpServletRequest request) {
        log.warn("handling");
        return ExceptionDTO.builder()
                .message(exception.getMessage())
                .url(request.getRequestURL().toString())
                .build();
    }
}
