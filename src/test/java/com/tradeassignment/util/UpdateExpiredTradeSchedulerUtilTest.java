package com.tradeassignment.util;

import com.tradeassignment.config.TradeStoreDataStructureConfig;
import com.tradeassignment.model.ExpiredEnum;
import com.tradeassignment.model.TradeModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateExpiredTradeSchedulerUtilTest {

    @Autowired
    private UpdateExpiredTradeSchedulerUtil updateExpiredTradeSchedulerUtil;

    @BeforeClass
    public static void setup() {
        InitializeCollectionUtilTest.init();
    }

    @Test
    public void updateExpiredStatus_whenSchedulerExecutes_thenExpiredFlagUpdate() throws InterruptedException {
        updateExpiredTradeSchedulerUtil.updateExpiredFlag();
        TradeModel expiryUpdatedTradeModel = TradeStoreDataStructureConfig.tradesSet.stream().filter(t -> t.getTradeId().equals("T7")).findFirst().get();
        Assert.assertEquals(expiryUpdatedTradeModel.getExpired(), ExpiredEnum.Y);
    }
}
