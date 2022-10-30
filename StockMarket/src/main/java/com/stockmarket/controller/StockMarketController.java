package com.stockmarket.controller;

import com.stockmarket.message.ResponseMessage;
import com.stockmarket.model.StockPrice;
import com.stockmarket.service.StockMarketService;
import com.stockmarket.util.StockMarketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

/**
 * Controller for stock market operations
 *
 * @author leelasai
 */
@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api/stockmarket")
public class StockMarketController {

  @Autowired
  StockMarketService stockMarketService;

  /**
   * Uploads the given csv file
   *
   * @param file csv file
   * @return response message
   */
  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    if (StockMarketUtil.hasCSVFormat(file)) {
      try {
        stockMarketService.save(file);
        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
      }
    }
    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
  }

  /**
   * Get a list of stock prices
   *
   * @return list of stock prices
   */
  @GetMapping("/stockPrices")
  public ResponseEntity<List<StockPrice>> getAllStockPrices() {
    try {
      List<StockPrice> stockPrices = stockMarketService.getAllStockPrices();
      if (stockPrices.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(stockPrices, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Downloads the stock prices to a csv file
   *
   * @return csv file as resource
   */
  @GetMapping("/download")
  public ResponseEntity<Resource> downloadStockPrices() {
    String filename = "dow_jones_index.csv";
    InputStreamResource file = new InputStreamResource(stockMarketService.load());
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }

  /**
   * Add a stock price
   *
   * @param payload the stock price data
   * @return the response message
   */
  @PostMapping("/add")
  public ResponseEntity<ResponseMessage> addStockPrice(@RequestBody Map<String, Object> payload) {
    String message = "";
    try {
      stockMarketService.saveRecord(payload);
      message = "Saved the record successfully";
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not save the record successfully!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  /**
   * List of stock prices based on stock ticker
   *
   * @param stockTicker the stock
   * @return the list of stock price
   */
  @GetMapping("/stockPricesByStockTicker")
  public ResponseEntity<List<StockPrice>> getByStockTicker(@RequestParam String stockTicker) {
    try {
      List<StockPrice> stockPrices = stockMarketService.getAllStockPrices(stockTicker);
      if (stockPrices.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(stockPrices, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
