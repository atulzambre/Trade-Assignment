package com.tradeassignment.exception;

import com.tradeassignment.model.CustomExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

/**
 * Custom exception handler to handle custom and default exceptions.
 * which will return the custom exception model and status code.
 *
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@ControllerAdvice
public class TradeAssignmentExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * this method handles all the default exceptions.
     * @param ex
     * @param rq
     * @return ResponseEntity with custom exception and status code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomExceptionModel> handleAllExceptions(Exception ex, WebRequest rq) {
        CustomExceptionModel customExceptionModel = new CustomExceptionModel(LocalDate.now(), ex.getMessage());
        return new ResponseEntity<>(customExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * this method will handle the exceptions thrown for LowerTradeVersionException.
     * @param ex
     * @param rq
     * @return ResponseEntity with custom LowerTradeVersionException and status code
     */
    @ExceptionHandler(LowerTradeVersionException.class)
    public ResponseEntity<CustomExceptionModel> handleLowerVersionException(Exception ex, WebRequest rq) {
        CustomExceptionModel customExceptionModel = new CustomExceptionModel(LocalDate.now(), ex.getMessage());
        return new ResponseEntity<>(customExceptionModel, HttpStatus.BAD_REQUEST);
    }

    /**
     * this method will handle the exceptions thrown for PastMaturityDateException.
     * @param ex
     * @param rq
     * @return ResponseEntity with custom PastMaturityDateException and status code
     */
    @ExceptionHandler(PastMaturityDateException.class)
    public ResponseEntity<CustomExceptionModel> handlePastMaturityDateException(Exception ex, WebRequest rq) {
        CustomExceptionModel customExceptionModel = new CustomExceptionModel(LocalDate.now(), ex.getMessage());
        return new ResponseEntity<>(customExceptionModel, HttpStatus.BAD_REQUEST);
    }
    /**
     * this method will handle the exceptions thrown for TradeStoreEmptyException.
     * @param ex
     * @param rq
     * @return ResponseEntity with custom TradeStoreEmptyException and status code
     */
    @ExceptionHandler(TradeStoreEmptyException.class)
    public ResponseEntity<CustomExceptionModel> handleTradeStoreEmptyException(Exception ex, WebRequest rq) {
        CustomExceptionModel customExceptionModel = new CustomExceptionModel(LocalDate.now(), ex.getMessage());
        return new ResponseEntity<>(customExceptionModel, HttpStatus.NOT_FOUND);
    }

}
