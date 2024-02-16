package com.ailu.feeds;

import com.ailu.feeds.demos.web.vo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value =Exception.class)
    public Response<Void> exceptionHandler(Exception e) {
        return new Response<>(500, e.getMessage());
    }
}
