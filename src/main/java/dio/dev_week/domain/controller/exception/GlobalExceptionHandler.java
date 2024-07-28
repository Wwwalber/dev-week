package dio.dev_week.domain.controller.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // logger to log the exceptions. See the last method bellow
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // create method to handle exceptions
    @ExceptionHandler(IllegalArgumentException.class)          // a mensagem de erro está na camada de negócios
    public ResponseEntity<String> handleBusinessException(IllegalArgumentException businessException){ // recebe a mensagem de erro
                    //retorna outra mensagem de erro
        return new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
                                    // corpo
    }

    @ExceptionHandler(NoSuchElementException.class)          
    public ResponseEntity<String> handleNotFoundException(NoSuchElementException notFoundException){ 
                    //retorna outra mensagem de erro
        return new ResponseEntity<>("Resouce ID not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)          
    public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException){ 
        var message = "Unexpected server erro, see the logs"; // Java 17 não exige tipagem quando já é implicito
        logger.error(message, unexpectedException);
                    //retorna outra mensagem de erro
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
                                                            // we have to register in the log. see above
    }
}
