package com.tradeassignment.exception;
/**
 * Custom exception for Past maturity date.
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
public class PastMaturityDateException extends RuntimeException {
    public PastMaturityDateException(String message) {
        super(message);
    }
}
