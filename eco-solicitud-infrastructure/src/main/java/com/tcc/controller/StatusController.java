package com.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatusController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return messageSource.getMessage("hello.message", null, LocaleContextHolder.getLocale());
    }
    
    @GetMapping("/status")
    @ResponseBody
    public String checkStatus() {
        return messageSource.getMessage("status.message", null, LocaleContextHolder.getLocale());
    }
}
