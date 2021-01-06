package com.netcracker.controllers.exception;

import com.netcracker.controllers.ApartmentInfoController;
import com.netcracker.controllers.web.ApiError;
import com.netcracker.controllers.web.ResponseEntityBuilder;
import com.netcracker.exception.ObjectNotUniqueException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice(basePackageClasses = {ApartmentInfoController.class})
public class ApartmentInfoExceptionHandler  extends GlobalExceptionHandler {


    @ExceptionHandler(value = {ObjectNotUniqueException.class})
    public ResponseEntity<Object> handleUniqueRegistrationDataException(ObjectNotUniqueException e, WebRequest request) {

        List<String> details = Collections.singletonList(e.getMessage());

        ApiError message = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "ARGUMENTS CONFLICT", details,e.getErrorCode());

        return ResponseEntityBuilder.build(message);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleUniqueRegistrationDataException(ValidationException e, WebRequest request) {

        List<String> details = Collections.singletonList(e.getMessage());

        ApiError message = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "ARGUMENTS CONFLICT", details,BigInteger.valueOf(71));

        return ResponseEntityBuilder.build(message);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()

                .map(error ->error.getCode() + error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError message = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "WRONG APARTMENT DATA", details,BigInteger.valueOf(75));

        return ResponseEntityBuilder.build(message);
    }


}
