package com.navi.school.controller;

import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception exception){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("errorMsg", exception.getMessage());
        return modelAndView;
    }
}
