package com.tradeassignment.controller;

import com.tradeassignment.model.TradeModel;
import com.tradeassignment.service.TradeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Trade contoller will save trades in store and return all the present trades from the trade store.
 *
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@RestController
@RequestMapping("/api")
public class TradeStoreController {

    @Autowired
    private TradeStoreService tradeStoreService;

    /**
     * This endpoint will return all the trades present in the trade store.
     * @exception throws TradeStoreEmptyException if no trade is present
     * @return list of trades
     */
    @GetMapping("getTrades")
    public ResponseEntity<List<TradeModel>> getAllTrades() {
        return new ResponseEntity<>(tradeStoreService.getAllTrades(), HttpStatus.OK);
    }

    /**
     * This post endpoint will save the valid trades in the trade store.
     *
     * Note: current date parameter is not populated with system now date to verify expired trades. for production we can populate it with LocalDate.now()
     * @param newTradeModel this is the request trade model.
     * @exception throws PastMatu and lower version trade exception.
     * @return saved trade.
     */
    @PostMapping("saveTrade")
    public ResponseEntity<TradeModel> saveTrade(@RequestBody TradeModel newTradeModel) {
        return new ResponseEntity<>(tradeStoreService.saveTrade(newTradeModel), HttpStatus.CREATED);
    }
}
