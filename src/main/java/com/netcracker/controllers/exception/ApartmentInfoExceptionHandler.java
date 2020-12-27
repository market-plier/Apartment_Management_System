package com.netcracker.controllers.exception;

import com.netcracker.controllers.ApartmentInfoController;
import com.netcracker.controllers.web.ApiError;
import com.netcracker.controllers.web.ResponseEntityBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.Null;
import javax.xml.bind.ValidationException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@ControllerAdvice(basePackageClasses = {ApartmentInfoController.class})
public class ApartmentInfoExceptionHandler extends GlobalExceptionHandler {


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiError> handleUniqueRegistrationDataException(IllegalArgumentException e, WebRequest request) {

        List<String> details = Collections.singletonList(e.getMessage());

        ApiError message = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "ARGUMENTS CONFLICT", details);

        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {javax.validation.ValidationException.class})
    public ResponseEntity<ApiError> handleUniqueRegistrationDataException(javax.validation.ValidationException e, WebRequest request) {

        List<String> details = Collections.singletonList(e.getLocalizedMessage());

        ApiError message = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "WRONG APARTMENT DATA", details);
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Wrong format Apartment`s data",
                Collections.singletonList("Validation Errors"),
                BigInteger.valueOf(71));
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
