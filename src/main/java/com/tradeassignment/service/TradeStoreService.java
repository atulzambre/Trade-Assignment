package com.tradeassignment.service;

import com.tradeassignment.model.TradeModel;

import java.util.List;

public interface TradeStoreService {

    public List<TradeModel> getAllTrades();

    public TradeModel saveTrade(TradeModel newTradeModel);
}
