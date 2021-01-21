package com.tradeassignment.util;


import com.tradeassignment.config.TradeStoreDataStructureConfig;
import com.tradeassignment.model.ExpiredEnum;
import com.tradeassignment.model.TradeModel;
import org.junit.BeforeClass;

import java.time.LocalDate;

/**
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
public class InitializeCollectionUtilTest {

    @BeforeClass
    public static void init() {
        TradeStoreDataStructureConfig.tradesSet.clear();
        TradeModel t1 = new TradeModel("T1", 1l, "CP-1", "B1", LocalDate.of(2020, 05, 20), LocalDate.now(), ExpiredEnum.N);
        TradeModel t2 = new TradeModel("T2", 3l, "CP-2", "B1", LocalDate.of(2021, 05, 20), LocalDate.now(), ExpiredEnum.N);
        TradeModel t3 = new TradeModel("T2", 2l, "CP-1", "B1", LocalDate.of(2021, 05, 20), LocalDate.of(2015, 03, 14), ExpiredEnum.N);
        TradeModel t4 = new TradeModel("T3", 3l, "CP-3", "B2", LocalDate.of(2014, 05, 20), LocalDate.now(), ExpiredEnum.Y);
        TradeModel t7 = new TradeModel("T7", 3l, "CP-3", "B2", LocalDate.of(2014, 05, 20), LocalDate.now(), ExpiredEnum.N);
        TradeStoreDataStructureConfig.tradesSet.add(t1);
        TradeStoreDataStructureConfig.tradesSet.add(t2);
        TradeStoreDataStructureConfig.tradesSet.add(t3);
        TradeStoreDataStructureConfig.tradesSet.add(t4);
        TradeStoreDataStructureConfig.tradesSet.add(t7);
    }
}
