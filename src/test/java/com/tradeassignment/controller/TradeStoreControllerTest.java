package com.tradeassignment.controller;

import com.tradeassignment.config.TradeStoreDataStructureConfig;
import com.tradeassignment.model.ExpiredEnum;
import com.tradeassignment.model.TradeModel;
import com.tradeassignment.service.TradeStoreService;
import com.tradeassignment.util.InitializeCollectionUtilTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@RunWith(SpringRunner.class)
public class TradeStoreControllerTest {

    @InjectMocks
    private TradeStoreController tradeStoreController;

    @Mock
    private TradeStoreService tradeStoreService;

    @BeforeClass
    public static void setup() {
        InitializeCollectionUtilTest.init();
    }

    @Test
    public void saveTrade_whenNewTrade_thenSaveTradeInStore(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        TradeModel newTradeModel = new TradeModel("T9", 1l, "CP-1", "B2", LocalDate.of(2021, 05, 20), LocalDate.now(), ExpiredEnum.N);
        Mockito.when(tradeStoreService.saveTrade(Mockito.any())).thenReturn(newTradeModel);
        ResponseEntity responseEntity= tradeStoreController.saveTrade(newTradeModel);
        Assertions.assertEquals(responseEntity.getStatusCode().toString(),"201 CREATED");


    }

    @Test
    public void getTrades_whenGetAllTrades_thenReturnAllTradesFromStore(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Mockito.when(tradeStoreService.getAllTrades()).thenReturn(TradeStoreDataStructureConfig.tradesSet.stream().collect(Collectors.toList()));
        ResponseEntity responseEntity= tradeStoreController.getAllTrades();
        Assertions.assertEquals(responseEntity.getStatusCode().toString(),"200 OK");

    }

}
