package com.tradeassignment.model;


import org.springframework.lang.NonNull;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
public class TradeModel {

    @NonNull
    private String tradeId;

    @NonNull
    private Long version;

    @NonNull
    private String counterPartyId;

    @NonNull
    private String bookId;

    @NonNull
    @Past
    private LocalDate maturityDate;

    @NonNull
    private LocalDate createdDate;

    private ExpiredEnum expired;

    public TradeModel() {
    }

    public TradeModel(String tradeId, Long version, String counterPartyId, String bookId, LocalDate maturityDate, LocalDate createdDate, ExpiredEnum expired) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = createdDate;
        this.expired = expired;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCounterPartyId() {
        return counterPartyId;
    }

    public void setCounterPartyId(String counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public ExpiredEnum getExpired() {
        return expired;
    }

    public void setExpired(ExpiredEnum expired) {
        this.expired = expired;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Trade trade = (Trade) o;
//        return tradeId.equals(trade.getTradeId()) && version.compareTo(trade.getVersion())==0;
//    }
//
//    @Override
//    public int hashCode() {
//        return tradeId.hashCode()+version.hashCode();
//    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId='" + tradeId + '\'' +
                ", version=" + version +
                ", counterPartyId='" + counterPartyId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", maturityDate=" + maturityDate +
                ", createdDate=" + createdDate +
                ", expired=" + expired +
                '}';
    }
}
