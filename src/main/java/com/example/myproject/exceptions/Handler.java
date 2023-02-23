package com.example.myproject.exceptions;


import com.example.myproject.helpers.CODE;
import com.example.myproject.responses.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice(basePackages = {"com.example.myproject"})
@Slf4j
public class Handler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<?>> notFoundException(NotFoundException exception){
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder()
                .message(exception.getMessage())
                .code(CODE.NOT_FOUND.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CustomBadCredentialsException.class)
    public ResponseEntity<Response<?>> exception(CustomBadCredentialsException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder()
                .message(exception.getMessage())
                .code(CODE.UNAUTHORIZED.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Response<?>> exception(UserNotFoundException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder()
                .message(exception.getMessage())
                .code(CODE.UNAUTHORIZED.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value=ValidationException.class)
    public ResponseEntity<Response<?>> exception(ValidationException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder()
                .message(exception.getMessage())
                .code(CODE.BAD_REQUEST.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Response<?>> exception(SQLIntegrityConstraintViolationException exception) {
        log.error("Error during request processing.", exception);
        Response<Object> response = Response.builder()
                .message(exception.getMessage())
                .code(CODE.INTERNAL_SERVER_ERROR.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>  handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());


        Response<Object> response = Response.builder()
                .message(getErrorsMap(errors).get("errors").toString())
                .code(CODE.BAD_REQUEST.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }





    public class DatabaseExceptionHandler {
        @ExceptionHandler(value = {CannotCreateTransactionException.class})
        public ResponseEntity<?> cannotCreateTransactionException(CannotCreateTransactionException exception, WebRequest request) {
            if (exception.contains(ConnectException.class)) {
                log.error("DB ConnectException :  {}", exception.getMessage());
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    // fallback method
    @ExceptionHandler(Exception.class) // exception handled
    public ResponseEntity<Response<?>> handleExceptions(
            Exception e
    ) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

        // converting the stack trace to String
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();

        Response<Object> response = Response.builder()
                .message(e.getMessage())
                .code(CODE.INTERNAL_SERVER_ERROR.getId())
                .success(false)
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);


    }
}
