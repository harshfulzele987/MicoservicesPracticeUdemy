package com.eazybytes.exceptions;

import com.eazybytes.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handelCustomerAlreadyExistException(
            CustomerAlreadyExistException customerAlreadyExistException,
            WebRequest webRequest
    )
    {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorMessage(customerAlreadyExistException.getMessage());
        errorResponseDto.setErrocode(HttpStatus.BAD_REQUEST);
        errorResponseDto.setErrorTime(LocalDate.now());

        return new ResponseEntity<>(errorResponseDto , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handelGlobalException(
            CustomerAlreadyExistException customerAlreadyExistException,
            WebRequest webRequest
    )
    {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorMessage(customerAlreadyExistException.getMessage());
        errorResponseDto.setErrocode(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponseDto.setErrorTime(LocalDate.now());

        return new ResponseEntity<>(errorResponseDto , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handelResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException,
            WebRequest webRequest
    )
    {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorMessage(resourceNotFoundException.getMessage());
        errorResponseDto.setErrocode(HttpStatus.BAD_REQUEST);
        errorResponseDto.setErrorTime(LocalDate.now());

        return new ResponseEntity<>(errorResponseDto , HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
    {
        Map<String, String> validationError = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String validationmsg = error.getDefaultMessage();
            validationError.put(fieldName , validationmsg);
        });
        return new ResponseEntity<>(validationError ,HttpStatus.BAD_REQUEST);

    }

}
