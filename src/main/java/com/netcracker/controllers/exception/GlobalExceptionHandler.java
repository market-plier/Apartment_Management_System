package com.netcracker.controllers.exception;

import com.netcracker.controllers.web.ApiError;
import com.netcracker.controllers.web.ResponseEntityBuilder;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.secutity.jwt.JwtAuthenticationException;
import com.netcracker.services.AuthenticationService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()

                .map(error ->error.getCode() + error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                details);

        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {

            List<String> details = exception.getConstraintViolations().stream().map(error -> error.getMessage()).collect(Collectors.toList());
            ApiError err = new ApiError(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Validation Errors",
                    details);
            return ResponseEntityBuilder.build(err);

    }



    @ExceptionHandler({DaoAccessException.class, NotBelongToAccountException.class, NullPointerException.class, JwtAuthenticationException.class})
    public final ResponseEntity<ApiError> handleCustomException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof DaoAccessException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            DaoAccessException accessException = (DaoAccessException) ex;

            return handleDaoAccessException(accessException, headers, status, request);
        } else if (ex instanceof NotBelongToAccountException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            NotBelongToAccountException accountException = (NotBelongToAccountException) ex;

            return handleNotBelongToAccountException(accountException, headers, status, request);
        } else if (ex instanceof NullPointerException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            NullPointerException nullPointerException = (NullPointerException) ex;
            return handleNullPointerException(nullPointerException, headers, status, request);
        } else if (ex instanceof AuthenticationServiceException) {
            HttpStatus status = HttpStatus.FORBIDDEN;
            AuthenticationServiceException authenticationException = (AuthenticationServiceException) ex;
            return handleJwtAccessException(authenticationException,headers,status,request);
        }
        else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }


    protected ResponseEntity<ApiError> handleDaoAccessException(DaoAccessException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());

        return handleExceptionInternal(ex, new ApiError(LocalDateTime.now(), status, "DAO ACCESS EXCEPTION",
                errors,ex.getErrorMessage()), headers, status, request);
    }


    protected ResponseEntity<ApiError> handleJwtAccessException(AuthenticationServiceException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(LocalDateTime.now(), status, "PASSWORD IS WRONG", errors), headers, status, request);
    }


    protected ResponseEntity<ApiError> handleNullPointerException(NullPointerException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(LocalDateTime.now(), status, "NULL POINTER", errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleNotBelongToAccountException(NotBelongToAccountException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(LocalDateTime.now(), status, "DAO exception", errors), headers, status, request);
    }


    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
