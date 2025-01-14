package az.turing.springdemoapp.exception;

import az.turing.springdemoapp.model.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyException.class)
    public ResponseEntity<GlobalErrorResponse> alreadyException(AlreadyException ex) {
        logError(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalErrorResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.ALREADY_EXISTS)
                .message(ex.getMessage())
                .timeStump(LocalDateTime.now())
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> notFoundException(NotFoundException ex) {
        logError(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalErrorResponse.builder()
                .requestId(UUID.randomUUID())
                .errorCode(ErrorCode.NOT_FOUND)
                .message(ex.getMessage())
                .timeStump(LocalDateTime.now())
                .build());
    }

    public void logError(Exception e) {
        log.error("{} happened {}", e.getClass().getSimpleName(), e.getMessage());
    }
}
