package com.tradeassignment.config;

import com.tradeassignment.model.ExpiredEnum;
import com.tradeassignment.model.TradeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * this is a data structure to hold the trades in store.
 *
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@Component
public class TradeStoreDataStructureConfig {

    public static Set<TradeModel> tradesSet = new HashSet<>();

    /**
     *  this method will load the trades inside the store at the starting of the server.
     *  this method can be removed if not required.
     */
    @Bean
    public void fillMapWithValues() {
        TradeModel t1 = new TradeModel("T1", 1l, "CP-1", "B1", LocalDate.of(2020, 05, 20), LocalDate.now(), ExpiredEnum.N);
        tradesSet.add(t1);

    }

}
