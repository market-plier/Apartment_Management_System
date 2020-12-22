package com.netcracker.controllers.exception;

import com.netcracker.controllers.ReportController;
import com.netcracker.controllers.web.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@ControllerAdvice(basePackageClasses={ReportController.class})
public class ReportExceptionHandler extends GlobalExceptionHandler {


    @ExceptionHandler({IOException.class, ParseException.class})
    public ResponseEntity<ApiError> handleReportControllerException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof IOException)
        {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            IOException ioException = (IOException)ex;
            return handleIOException(ioException,headers,status,request);
        }else if(ex instanceof ParseException)
        {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            ParseException parseException = (ParseException) ex;
            return handleParseException(parseException,headers,status,request);
        }

        return super.handleCustomException(ex, request);

    }

    protected ResponseEntity<ApiError> handleIOException(IOException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());

        return handleExceptionInternal(ex, new ApiError(LocalDateTime.now(), status, "Input output exception", errors), headers, status, request);
    }

    protected ResponseEntity<ApiError> handleParseException(ParseException ex, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request)
    {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(LocalDateTime.now(), status, "Parse date exception", errors), headers, status, request);
    }
}
