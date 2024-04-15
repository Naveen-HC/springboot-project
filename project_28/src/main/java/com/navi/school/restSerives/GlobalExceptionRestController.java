package com.navi.school.restSerives;

import com.navi.school.model.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(1)
public class GlobalExceptionRestController  {

    @ExceptionHandler
    public ResponseEntity<Response> globalExceptionHandler(Exception exception){
        Response response = new Response();
        response.setStatusCode("500");
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//        Response response = new Response();
//        response.setStatusCode(status.toString());
//        response.setMessage(ex.getBindingResult().toString());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}
