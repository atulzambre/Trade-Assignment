package com.tradeassignment.util;

import com.tradeassignment.config.TradeStoreDataStructureConfig;
import com.tradeassignment.model.ExpiredEnum;
import com.tradeassignment.model.TradeModel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This config class will schedule a task to run every day at 00:01 am to update the expiry status.
 *
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@Component
public class UpdateExpiredTradeSchedulerUtil {

    /**
     * This is a scheduler task which updates the expiry status for each trade when the maturity date is past current date.
     *
     */
    @Scheduled(cron = "0 1 0 * * *")
    public void updateExpiredFlag() {
        Set<TradeModel> updateTradeModels = TradeStoreDataStructureConfig.tradesSet.stream().filter(t -> t.getMaturityDate().isBefore(LocalDate.now()) && t.getExpired().equals(ExpiredEnum.N)).collect(Collectors.toSet());
        for (TradeModel t : updateTradeModels) {
            TradeStoreDataStructureConfig.tradesSet.remove(t);
            t.setExpired(ExpiredEnum.Y);
            TradeStoreDataStructureConfig.tradesSet.add(t);
        }
    }

}
