package com.tradeassignment.exception;

/**
 * Custom exception for lower trade version condition
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
public class LowerTradeVersionException extends RuntimeException {
    public LowerTradeVersionException(String message) {
        super(message);
    }
}
