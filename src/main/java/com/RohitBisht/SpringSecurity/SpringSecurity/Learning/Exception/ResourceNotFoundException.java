package com.RohitBisht.SpringSecurity.SpringSecurity.Learning.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class ResourceNotFoundException extends RuntimeException{


        public ResourceNotFoundException(String message) {
            super(message);
        }
    
}
