package com.tradeassignment.exception;

/**
 * Custom exception when there is not trade in store.
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
public class TradeStoreEmptyException extends RuntimeException{
    public TradeStoreEmptyException(String message) {
        super(message);
    }
}
