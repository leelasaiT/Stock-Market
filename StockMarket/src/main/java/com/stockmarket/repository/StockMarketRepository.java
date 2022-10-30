package com.stockmarket.repository;

import com.stockmarket.model.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for stockprice table
 *
 * @author leelasai
 */
public interface StockMarketRepository extends JpaRepository<StockPrice, Long> {

    public final static String FIND_BY_STOCK_TICKER_QUERY = "SELECT * FROM stockprice WHERE stock = :stock";

    /**
     * Find stock prices by stock ticker.
     */
    @Query(value = FIND_BY_STOCK_TICKER_QUERY, nativeQuery = true)
    public List<StockPrice> findByStockTicker(@Param("stock") String stock);

}
