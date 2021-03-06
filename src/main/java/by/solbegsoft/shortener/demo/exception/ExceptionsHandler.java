package by.solbegsoft.shortener.demo.exception;

import by.solbegsoft.shortener.demo.dto.CustomErrorResponse;
import lombok.NonNull;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static java.lang.String.format;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatus status,
                                                                  @NonNull WebRequest request) {
        String fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> format("[%s] with message [%s]", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining(" and "));
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setError("Validation failed, please fill fields correctly. Validation failed for: " + fields);
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setTimestamp(LocalDateTime.now());

        return handleExceptionInternal(
                ex, customErrorResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UrlDataException.class)
    protected ResponseEntity<?> urlData(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtTokenException.class)
    protected ResponseEntity<?> wrongJwt(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ShortUrlNotFoundException.class)
    protected ResponseEntity<?> shortUrlNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> ex(Exception e) {
        return new ResponseEntity<>("Check input data and try refresh", HttpStatus.BAD_REQUEST);
    }

}