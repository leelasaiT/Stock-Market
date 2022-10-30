package com.stockmarket.util;

import com.stockmarket.model.StockPrice;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Util for manipulating csv and stock data
 *
 * @author leelasai
 */
public class StockMarketUtil {

  public static String TYPE = "text/csv";

  /**
   * Checks if file is a csv file
   */
  public static boolean hasCSVFormat(MultipartFile file) {
    return TYPE.equals(file.getContentType());
  }

  /**
   * Stock price csv field mapper
   */
  public static List<StockPrice> csvToStockPrices(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
         CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<StockPrice> stockPrices = new ArrayList<>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
      int i = 1;
      for (CSVRecord csvRecord : csvRecords) {
          StockPrice stockPrice = new StockPrice(
                  Integer.parseInt(String.valueOf(i)),
                  Integer.parseInt(csvRecord.get("quarter")),
                  csvRecord.get("stock"),
                  new Date(csvRecord.get("date")),
                  csvRecord.get("open"),
                  csvRecord.get("high"),
                  csvRecord.get("low"),
                  csvRecord.get("close"),
                  !csvRecord.get("volume").equals("") ? Float.parseFloat(csvRecord.get("volume")) : 0,
                  !csvRecord.get("percent_change_price").equals("") ? Float.parseFloat(csvRecord.get("percent_change_price")) : 0,
                  !csvRecord.get("percent_change_volume_over_last_wk").equals("") ? Float.parseFloat(csvRecord.get("percent_change_volume_over_last_wk")) : 0,
                  !csvRecord.get("previous_weeks_volume").equals("") ? Float.parseFloat(csvRecord.get("previous_weeks_volume")) : 0,
                  csvRecord.get("next_weeks_open"),
                  csvRecord.get("next_weeks_close"),
                  !csvRecord.get("percent_change_next_weeks_price").equals("") ? Float.parseFloat(csvRecord.get("percent_change_next_weeks_price")) : 0,
                  !csvRecord.get("days_to_next_dividend").equals("") ? Float.parseFloat(csvRecord.get("days_to_next_dividend")) : 0,
                  !csvRecord.get("percent_return_next_dividend").equals("") ? Float.parseFloat(csvRecord.get("percent_return_next_dividend")) : 0
          );
        stockPrices.add(stockPrice);
        i++;
      }
      return stockPrices;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

  /**
   * Stock price list to csv mapper
   */
  public static ByteArrayInputStream stockPricesToCSV(List<StockPrice> tutorials) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
         CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (StockPrice stockPrice : tutorials) {
        List<String> data = Arrays.asList(
                String.valueOf(stockPrice.getId()),
                String.valueOf(stockPrice.getQuarter()),
                stockPrice.getStock(),
                stockPrice.getDate().toString(),
                String.valueOf(stockPrice.getOpen()),
                String.valueOf(stockPrice.getHigh()),
                String.valueOf(stockPrice.getLow()),
                String.valueOf(stockPrice.getClose()),
                String.valueOf(stockPrice.getVolume()),
                String.valueOf(stockPrice.getPercent_change_price()),
                String.valueOf(stockPrice.getPercent_change_volume_over_last_wk()),
                String.valueOf(stockPrice.getPrevious_weeks_volume()),
                String.valueOf(stockPrice.getNext_weeks_open()),
                String.valueOf(stockPrice.getPercent_change_next_weeks_price()),
                String.valueOf(stockPrice.getDays_to_next_dividend()),
                String.valueOf(stockPrice.getPercent_return_next_dividend())
            );
        csvPrinter.printRecord(data);
      }
      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
  }

}
