package com.ob11to.spring.http.handler;

import com.ob11to.spring.dto.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {

    private static final String SOMETHING_WENT_WRONG = "Something went wrong! Due to: ";
    private static final String RESPONSE_ERROR_WAS_HANDLED_IN_CONTROLLER_ADVICE = "Response error was handled in controller advice :{}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handle(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
        ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST.value(),
                errorMap.toString());
        log.debug(RESPONSE_ERROR_WAS_HANDLED_IN_CONTROLLER_ADVICE, responseError);
        return responseError;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handle(Exception e) {
        ResponseError responseError = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                SOMETHING_WENT_WRONG + e.getMessage());
        log.error(RESPONSE_ERROR_WAS_HANDLED_IN_CONTROLLER_ADVICE, responseError);
        return responseError;
    }
}
