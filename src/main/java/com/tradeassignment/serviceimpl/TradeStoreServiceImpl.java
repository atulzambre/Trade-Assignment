package com.tradeassignment.serviceimpl;

import com.tradeassignment.config.TradeStoreDataStructureConfig;
import com.tradeassignment.exception.LowerTradeVersionException;
import com.tradeassignment.exception.PastMaturityDateException;
import com.tradeassignment.exception.TradeStoreEmptyException;
import com.tradeassignment.model.ExpiredEnum;
import com.tradeassignment.model.TradeModel;
import com.tradeassignment.service.TradeStoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Trade Sevice contains business logic to return the trades present in trade store.
 * Also to save the trades in trade store based on the conditions.
 *
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@Service
public class TradeStoreServiceImpl implements TradeStoreService {
    /**
     * This method will return all the trades present in the trade store.
     * @exception throws TradeStoreEmptyException if no trade is present
     * @return list of trades.
     */
    @Override
    public List<TradeModel> getAllTrades() {
        List<TradeModel> tradeModelList=TradeStoreDataStructureConfig.tradesSet.stream().collect(Collectors.toList());
        if(tradeModelList.isEmpty()){
            throw new TradeStoreEmptyException("There are no trades present in store");
        }
        return tradeModelList;
    }

    /**
     * This method will save the valid trades in the trade store.
     *
     * @param newTradeModel this is the request trade model.
     * @exception throws past maturity date exception and lower version trade exception.
     * @return saved trade.
     */
    @Override
    public TradeModel saveTrade(TradeModel newTradeModel) {
        checkLowerVersionCondition(newTradeModel);
        newTradeModel.setExpired(ExpiredEnum.N);
        //check if the latest version trade is already available in the store
        Optional<TradeModel> trade = TradeStoreDataStructureConfig.tradesSet.stream().filter(t -> t.getTradeId().equals(newTradeModel.getTradeId()) && t.getVersion() == newTradeModel.getVersion()).findFirst();
        //if already available then replace it with new trade with same version
        if (trade.isPresent()) {
            checkMaturityDateCondition(newTradeModel);
            TradeStoreDataStructureConfig.tradesSet.remove(trade.get());
            TradeStoreDataStructureConfig.tradesSet.add(newTradeModel);
        }
        //if not present and the version is higher then create new trade.
        else {
            checkMaturityDateCondition(newTradeModel);

            TradeStoreDataStructureConfig.tradesSet.add(newTradeModel);
        }
        return TradeStoreDataStructureConfig.tradesSet.stream().filter(t -> t.getTradeId().equals(newTradeModel.getTradeId()) && t.getVersion().equals(newTradeModel.getVersion())).findFirst().get();
    }

    /**
     * this method will check if the maturity date is before the current date.
     *
     * @param newTradeModel
     * @exception throws PastMaturityDateException
     */
    private void checkMaturityDateCondition(TradeModel newTradeModel) {
        if (newTradeModel.getMaturityDate().isBefore(LocalDate.now())) {
            throw new PastMaturityDateException("Maturity Date should not past current date");
        }
    }


    /**
     * this method will check if the trade contains lower version.
     *
     * @param newTradeModel
     * @exception throws LowerTradeVersionException
     */
    private void checkLowerVersionCondition(TradeModel newTradeModel) {
        if (TradeStoreDataStructureConfig.tradesSet.stream().anyMatch(t -> t.getTradeId().equals(newTradeModel.getTradeId()) && t.getVersion() > newTradeModel.getVersion())) {
            throw new LowerTradeVersionException("Lower version trades are not allowed");
        }
    }
}
