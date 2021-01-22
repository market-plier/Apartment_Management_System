package com.netcracker.controllers.exception;

import com.netcracker.controllers.ApartmentInfoController;
import com.netcracker.controllers.web.ApiError;
import com.netcracker.controllers.web.ResponseEntityBuilder;
import com.netcracker.exception.ObjectNotUniqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ControllerAdvice(basePackageClasses = {ApartmentInfoController.class})
public class CommunalUtilityExceptionHandler {

    @ExceptionHandler(value = {ObjectNotUniqueException.class})
    public ResponseEntity<Object> handleUniqueRegistrationDataException(ObjectNotUniqueException e, WebRequest request) {

        List<String> details = Collections.singletonList(e.getMessage());

        ApiError message = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "ARGUMENTS CONFLICT", details, e.getErrorCode());

        return ResponseEntityBuilder.build(message);
    }

}
