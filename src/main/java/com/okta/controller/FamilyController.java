package com.okta.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(path = "/hello")
public class FamilyController {

    @GetMapping("/world")
    public ResponseEntity<String> getFamily(){


            return new ResponseEntity<>("Hello World", HttpStatus.ACCEPTED);
       
    }
}
