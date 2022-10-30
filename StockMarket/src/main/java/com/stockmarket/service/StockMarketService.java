package com.stockmarket.service;

import com.stockmarket.model.StockPrice;
import com.stockmarket.repository.StockMarketRepository;
import com.stockmarket.util.StockMarketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service for interacting with the database
 *
 * @author leelasai
 */
@Service
public class StockMarketService {

  @Autowired
  StockMarketRepository repository;

  /**
   * Saves a multipart file
   */
  public void save(MultipartFile file) {
    try {
      List<StockPrice> stockPrices = StockMarketUtil.csvToStockPrices(file.getInputStream());
      repository.saveAll(stockPrices);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  /**
   * Saves a stock price record
   */
  public void saveRecord(Map<String, Object> payload) {
    List<StockPrice> prices = repository.findAll();
    StockPrice stockPrice = new StockPrice(
            prices.size() + 1,
            Integer.parseInt(payload.get("quarter").toString()),
            payload.get("stock").toString(),
            new Date(payload.get("date").toString()),
            payload.get("open").toString(),
            payload.get("high").toString(),
            payload.get("low").toString(),
            payload.get("close").toString(),
            !payload.get("volume").equals("") ? Float.parseFloat(payload.get("volume").toString()) : 0,
            !payload.get("percent_change_price").equals("") ? Float.parseFloat(payload.get("percent_change_price").toString()) : 0,
            !payload.get("percent_change_volume_over_last_wk").equals("") ? Float.parseFloat(payload.get("percent_change_volume_over_last_wk").toString()) : 0,
            !payload.get("previous_weeks_volume").equals("") ? Float.parseFloat(payload.get("previous_weeks_volume").toString()) : 0,
            payload.get("next_weeks_open").toString(),
            payload.get("next_weeks_close").toString(),
            !payload.get("percent_change_next_weeks_price").equals("") ? Float.parseFloat(payload.get("percent_change_next_weeks_price").toString()) : 0,
            !payload.get("days_to_next_dividend").equals("") ? Float.parseFloat(payload.get("days_to_next_dividend").toString()) : 0,
            !payload.get("percent_return_next_dividend").equals("") ? Float.parseFloat(payload.get("percent_return_next_dividend").toString()) : 0
    );
    List<StockPrice> stockPrices = new ArrayList<>();
    stockPrices.add(stockPrice);
    repository.saveAll(stockPrices);
  }

  /**
   * Loads the stock prices from the database
   */
  public ByteArrayInputStream load() {
    List<StockPrice> stockPrices = repository.findAll();
    return StockMarketUtil.stockPricesToCSV(stockPrices);
  }

  /**
   * Finds all the stock prices in the database
   */
  public List<StockPrice> getAllStockPrices() {
    return repository.findAll();
  }

  /**
   * Finds the stock prices from the database based on stock ticker
   */
  public List<StockPrice> getAllStockPrices(String stockTicker) {
    return repository.findByStockTicker(stockTicker);
  }
}
