package com.tradeassignment.serviceimpl;

import com.tradeassignment.exception.LowerTradeVersionException;
import com.tradeassignment.exception.PastMaturityDateException;
import com.tradeassignment.exception.TradeStoreEmptyException;
import com.tradeassignment.model.ExpiredEnum;
import com.tradeassignment.model.TradeModel;
import com.tradeassignment.util.InitializeCollectionUtilTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@RunWith(MockitoJUnitRunner.class)
public class TradeStoreServiceImplTest {

    @InjectMocks
    private TradeStoreServiceImpl tradeService;

    @BeforeClass
    public static void setup() {
        InitializeCollectionUtilTest.init();
    }

    @Test
    public void saveTrade_whenNewTrade_thenSaveTradeInStore() {
        TradeModel newTradeModel = new TradeModel("T8", 1l, "CP-1", "B2", LocalDate.of(2021, 05, 20), LocalDate.now(), ExpiredEnum.N);
        TradeModel savedTradeModel =tradeService.saveTrade(newTradeModel);
        Assertions.assertEquals(newTradeModel, savedTradeModel);
    }

    @Test
    public void getTrades_whenTradesPresentInStore_thenReturnAllTrades() {
        List<TradeModel> tradeModelList = tradeService.getAllTrades();
        Assert.notEmpty(tradeModelList, "Trade list must not be empty");
    }

    @Test(expected = LowerTradeVersionException.class)
    public void saveTrade_whenTradeWithLowerVersion_thenThrowException() {
        TradeModel lowerVersionTradeModel = new TradeModel("T2", 1l, "CP-1", "B2", LocalDate.of(2021, 05, 20), LocalDate.now(), ExpiredEnum.N);
        TradeModel tradeModel = tradeService.saveTrade(lowerVersionTradeModel);
//      Assertions.assertThrows(LowerVersionException.class,()->tradeService.saveTrade(lowerVersionTrade));
    }

    @Test
    public void saveTrade_whenTradeWithSameVersion_thenReplaceTrade() {
        TradeModel lowerVersionTradeModel = new TradeModel("T2", 3l, "CP-1", "B2", LocalDate.of(2021, 05, 20), LocalDate.now(), ExpiredEnum.N);
        TradeModel savedTradeModel = tradeService.saveTrade(lowerVersionTradeModel);
        Assertions.assertEquals(savedTradeModel.getTradeId(), lowerVersionTradeModel.getTradeId());
    }

    @Test(expected = PastMaturityDateException.class)
    public void saveTrade_whenMaturityDatePastCurrentDate_thenThrowException() {
        TradeModel pastMaturityDateTradeModel = new TradeModel("T2", 3l, "CP-1", "B2", LocalDate.of(2020, 05, 20), LocalDate.now(), ExpiredEnum.N);
        TradeModel tradeModel = tradeService.saveTrade(pastMaturityDateTradeModel);
//      Assertions.assertThrows(MaturityDateException.class,()->tradeService.saveTrade(pastMaturityDateTrade));
    }

    @Test
    public void saveTrade_whenMaturityDateAfterCurrentDate_thenSaveTrade() {
        TradeModel afterMaturityDate = new TradeModel("T4", 1l, "CP-1", "B2", LocalDate.of(2021, 05, 20), LocalDate.now(), ExpiredEnum.N);
        TradeModel savedTradeModel = tradeService.saveTrade(afterMaturityDate);
        Assertions.assertEquals(savedTradeModel.getTradeId(), afterMaturityDate.getTradeId());
    }

    @Test
    public void saveTrade_whenNewTrade_thenSaveTradeWithNotExpiredFlag() {
        TradeModel afterMaturityDate = new TradeModel("T5", 1l, "CP-1", "B2", LocalDate.of(2021, 05, 20), LocalDate.now(), ExpiredEnum.Y);
        TradeModel savedTradeModel = tradeService.saveTrade(afterMaturityDate);
        Assertions.assertEquals(savedTradeModel.getExpired(), ExpiredEnum.N);
    }


}
