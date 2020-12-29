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

import javax.validation.constraints.Null;
import javax.xml.bind.ValidationException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice(basePackageClasses = {ApartmentInfoController.class})
public class ApartmentInfoExceptionHandler  extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {ObjectNotUniqueException.class})
    public ResponseEntity<Object> handleUniqueRegistrationDataException(ObjectNotUniqueException e, WebRequest request) {

        List<String> details = Collections.singletonList(e.getMessage());

        ApiError message = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "ARGUMENTS CONFLICT", details,e.getErrorCode());

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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Wrong format Apartment`s data",
                Collections.singletonList("Validation Errors"),
                BigInteger.valueOf(75));
        if (ex.getMessage().contains("apartmentNumber")) {
            err.setMessage("Apartment`s number is wrong type");
        }
        if (ex.getMessage().contains("floor")) {
            err.setMessage("Apartment`s floor is wrong type");
        }
        if (ex.getMessage().contains("peopleCount")) {
            err.setMessage("Apartment`s peopleCount is wrong type");
        }
        if (ex.getMessage().contains("squareMetres")) {
            err.setMessage("Apartment`s squareMetres is wrong type");
        }
        if (ex.getMessage().contains("role")) {
            err.setMessage("Role is wrong type, should be empty or OWNER");
        }
        if (ex.getMessage().contains("accountId")) {
            err.setMessage("AccountId is wrong type, should be null");
        }
        if (ex.getMessage().contains("firstName")) {
            err.setMessage("First name is wrong type, there must be letters");
        }
        if (ex.getMessage().contains("lastName")) {
            err.setMessage("Last Name is wrong type, there must be letters");
        }
        if (ex.getMessage().contains("email")) {
            err.setMessage("Email is wrong type, there must be valid email");
        }
        return ResponseEntityBuilder.build(err);
    }
}
