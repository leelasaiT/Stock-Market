package com.stockmarket.model;

import com.sun.istack.Nullable;
import javax.persistence.*;
import java.util.Date;

/**
 * Stock Price Table Model
 *
 * @author leelasai
 */
@Entity
@Table(name = "stockprice")
public class StockPrice {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "quarter")
  @Nullable
  private Integer quarter;

  @Column(name = "stock")
  @Nullable
  private String stock;

  @Column(name = "date")
  @Nullable
  private Date date;

  @Column(name = "open")
  @Nullable
  private String open;

  @Column(name = "high")
  @Nullable
  private String high;

  @Column(name = "low")
  @Nullable
  private String low;

  @Column(name = "close")
  @Nullable
  private String close;

  @Column(name = "volume")
  @Nullable
  private float volume;

  @Column(name = "percent_change_price")
  @Nullable
  private float percent_change_price;

  @Column(name = "percent_change_volume_over_last_wk")
  @Nullable
  private float percent_change_volume_over_last_wk;

  @Column(name = "previous_weeks_volume")
  @Nullable
  private float previous_weeks_volume;

  @Column(name = "next_weeks_open")
  @Nullable
  private String next_weeks_open;

  @Column(name = "next_weeks_close")
  @Nullable
  private String next_weeks_close;

  @Column(name = "percent_change_next_weeks_price")
  @Nullable
  private float percent_change_next_weeks_price;

  @Column(name = "days_to_next_dividend")
  @Nullable
  private float days_to_next_dividend;

  @Column(name = "percent_return_next_dividend")
  @Nullable
  private float percent_return_next_dividend;

  public long getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public long getQuarter() {
    return quarter;
  }

  public void setQuarter(Integer quarter) {
    this.quarter = quarter;
  }

  public String getStock() {
    return stock;
  }

  public void setStock(String stock) {
    this.stock = stock;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getOpen() {
    return open;
  }

  public void setOpen(String open) {
    this.open = open;
  }

  public String getHigh() {
    return high;
  }

  public void setHigh(String high) {
    this.high = high;
  }

  public String getLow() {
    return low;
  }

  public void setLow(String low) {
    this.low = low;
  }

  public String getClose() {
    return close;
  }

  public void setClose(String close) {
    this.close = close;
  }

  public float getVolume() {
    return volume;
  }

  public void setVolume(float volume) {
    this.volume = volume;
  }

  public float getPercent_change_price() {
    return percent_change_price;
  }

  public void setPercent_change_price(float percent_change_price) {
    this.percent_change_price = percent_change_price;
  }

  public float getPercent_change_volume_over_last_wk() {
    return percent_change_volume_over_last_wk;
  }

  public void setPercent_change_volume_over_last_wk(float percent_change_volume_over_last_wk) {
    this.percent_change_volume_over_last_wk = percent_change_volume_over_last_wk;
  }

  public float getPrevious_weeks_volume() {
    return previous_weeks_volume;
  }

  public void setPrevious_weeks_volume(float previous_weeks_volume) {
    this.previous_weeks_volume = previous_weeks_volume;
  }

  public String getNext_weeks_open() {
    return next_weeks_open;
  }

  public void setNext_weeks_open(String next_weeks_open) {
    this.next_weeks_open = next_weeks_open;
  }

  public String getNext_weeks_close() {
    return next_weeks_close;
  }

  public void setNext_weeks_close(String next_weeks_close) {
    this.next_weeks_close = next_weeks_close;
  }

  public float getPercent_change_next_weeks_price() {
    return percent_change_next_weeks_price;
  }

  public void setPercent_change_next_weeks_price(float percent_change_next_weeks_price) {
    this.percent_change_next_weeks_price = percent_change_next_weeks_price;
  }

  public float getDays_to_next_dividend() {
    return days_to_next_dividend;
  }

  public void setDays_to_next_dividend(float days_to_next_dividend) {
    this.days_to_next_dividend = days_to_next_dividend;
  }

  public float getPercent_return_next_dividend() {
    return percent_return_next_dividend;
  }

  public void setPercent_return_next_dividend(float percent_return_next_dividend) {
    this.percent_return_next_dividend = percent_return_next_dividend;
  }

  public StockPrice() {
    // Default constructor
  }

  public StockPrice(Integer id, Integer quarter, String stock, Date date, String open, String high, String low, String close, float volume, float percent_change_price, float percent_change_volume_over_last_wk,
                    float previous_weeks_volume, String next_weeks_open, String next_weeks_close, float percent_change_next_weeks_price, float days_to_next_dividend, float percent_return_next_dividend) {
    this.id = id;
    this.quarter = quarter;
    this.stock = stock;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.percent_change_price = percent_change_price;
    this.percent_change_volume_over_last_wk = percent_change_volume_over_last_wk;
    this.previous_weeks_volume = previous_weeks_volume;
    this.next_weeks_open = next_weeks_open;
    this.next_weeks_close = next_weeks_close;
    this.percent_change_next_weeks_price = percent_change_next_weeks_price;
    this.days_to_next_dividend = days_to_next_dividend;
    this.percent_return_next_dividend = percent_return_next_dividend;
  }

}
