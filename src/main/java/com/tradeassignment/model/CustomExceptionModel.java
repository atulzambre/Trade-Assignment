package com.tradeassignment.model;

import java.time.LocalDate;

/**
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
public class CustomExceptionModel {
    private LocalDate date;
    private String exceptionMessage;

    public CustomExceptionModel(LocalDate date, String exceptionMessage) {
        this.date = date;
        this.exceptionMessage = exceptionMessage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "CustomExceptionModel{" +
                "date=" + date +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                '}';
    }
}
