package com.saif.blog.blogApp.exceptions;


import com.saif.blog.blogApp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodNotValidException(MethodArgumentNotValidException e){


    Map<String,String> resp=new HashMap<>();


    e.getBindingResult().getAllErrors().forEach((error)->{

        String fieldName = ((FieldError) error).getField();
        String defaultMessage = error.getDefaultMessage();

       resp.put(fieldName,defaultMessage);


    });

    return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);




    }



    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){

        String message=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(message,false);

        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }
}
