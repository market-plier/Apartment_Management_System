package com.netcracker.controllers.exception;

import com.netcracker.controllers.ApartmentInfoController;
import com.netcracker.controllers.web.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ControllerAdvice(basePackageClasses = {ApartmentInfoController.class})
public class ApartmentInfoExceptionHandler extends GlobalExceptionHandler {


    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiError> handleUniqueRegistrationDataException(IllegalArgumentException e, WebRequest request) {

        List<String> details = Collections.singletonList(e.getLocalizedMessage());

        ApiError message = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "ARGUMENTS CONFLICT", details);

        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.CONFLICT);
    }
}
