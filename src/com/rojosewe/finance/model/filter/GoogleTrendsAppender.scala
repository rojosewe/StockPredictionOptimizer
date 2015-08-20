package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.model.Stock

/**
 * @author sensefields
 */
class GoogleTrendsAppender(stockObj:Stock) extends Filter {

  val stock = stockObj
  
  def preprocess(): List[StockValue] = {
    return stock.values
  }

  def randomize() {}
}