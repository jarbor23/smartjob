package co.com.smartjob.api.handler;

import co.com.smartjob.api.dto.Metadata;
import co.com.smartjob.model.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static co.com.smartjob.api.common.Constants.OCCURRED_WHILE_CONSUMING_THE_SERVICE;
import static co.com.smartjob.api.common.Constants.MALFORMED_JSON_REQUEST;


@EnableWebMvc
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(
            BusinessException ex) {

        Metadata metadata =Metadata.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .errors("Business exception: ".concat(ex.getMessage()))
                .build();
        return new ResponseEntity<>(metadata, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInternalError(
            Exception ex, WebRequest request, HttpServletRequest httpServletRequest) {
        log.error("Error internal ", ex);
        Metadata metadata = Metadata.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .errors("Internal error : ".concat(ex.getMessage()))
                .build();

        return buildResponseEntity(metadata, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {
        Metadata metadata = Metadata.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .errors(MALFORMED_JSON_REQUEST)
                .build();
        return buildResponseEntity(metadata, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        List<String> errors =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
        errors.addAll(ex.getBindingResult().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList());

        Metadata metadata =Metadata.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .errors("Validation Failed: ".concat(errors.toString()))
                .build();
        return new ResponseEntity<>(metadata, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        Metadata metadata = Metadata.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .errors(error)
                .build();
        return buildResponseEntity(metadata, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex) {
        StringBuilder error = new StringBuilder()
                .append(ex.getMethod())
                .append(" method is not supported for this request. Supported methods are ");
        for (HttpMethod t : Objects.requireNonNull(ex.getSupportedHttpMethods())) {
            error.append(t).append(' ');
        }
        Metadata metadata = Metadata.builder()
                .message(OCCURRED_WHILE_CONSUMING_THE_SERVICE)
                .errors(error.toString())
                .build();
        return buildResponseEntity(metadata, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private ResponseEntity<Object> buildResponseEntity(Metadata metadata, HttpStatus httpStatus) {
        return new ResponseEntity<>(metadata, httpStatus);
    }


}
