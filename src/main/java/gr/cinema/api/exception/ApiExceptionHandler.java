package gr.cinema.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends Throwable {

    private final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    private final HttpStatus notFound = HttpStatus.NOT_FOUND;
    private final HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
    HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;


    @ExceptionHandler(value = {ApiRequestException.class})
    private ResponseEntity<ApiException> handleApiRequestException(ApiRequestException exception) {

        ApiException error =
                new ApiException(
                        exception.getMessage(),
                        badRequest,
                        ZonedDateTime.now(ZoneId.of("Z"))
                );

        return new ResponseEntity<>(error, badRequest);
    }


//    @ExceptionHandler(value = {BindException.class})
//    private ResponseEntity<ApiException> handleException(BindException exception) {
//
//        ApiException error =
//                new ApiException(
//                        exception.getMessage(),
//                        exception,
//                        badRequest,
//                        ZonedDateTime.now(ZoneId.of("Z"))
//                );
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//
//    @ExceptionHandler(value = {NotFoundException.class})
//    private ResponseEntity<ApiException> handleApiExceptionResponseEntity(NotFoundException exception) {
//
//        ApiException error =
//                new ApiException(
//                        exception.getMessage(),
//                        exception,
//                        notFound,
//                        ZonedDateTime.now(ZoneId.of("Z"))
//                );
//
//        return new ResponseEntity<>(error, notFound);
//
//    }
//
//    @ExceptionHandler(value = {TokenExpiredException.class})
//    private ResponseEntity<ApiException> handleException(TokenExpiredException exception) {
//
//        ApiException error =
//                new ApiException(
//                        exception.getMessage(),
//                        exception,
//                        unauthorized,
//                        ZonedDateTime.now(ZoneId.of("Z"))
//                );
//
//        return new ResponseEntity<>(error, unauthorized);
//    }
//
//    @ExceptionHandler(value = HttpServerErrorException.InternalServerError.class)
//    private ResponseEntity<ApiException> handleException(HttpServerErrorException.InternalServerError exception) {
//
//        ApiException error =
//                new ApiException(
//                        exception.getMessage(),
//                        exception,
//                        internalServerError,
//                        ZonedDateTime.now(ZoneId.of("Z"))
//                );
//
//        return new ResponseEntity<>(error, internalServerError);
//    }
}
