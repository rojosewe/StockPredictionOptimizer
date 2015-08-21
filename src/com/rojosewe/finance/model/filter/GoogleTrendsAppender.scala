package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.model.Stock
import weka.core.Attribute

/**
 * @author sensefields
 */
class GoogleTrendsAppender(stockObj:Stock) extends Filter {

  val stock = stockObj
  
  def preprocess(values: List[StockValue], attributes: List[Attribute]) {
    
  }

  def randomize() {}
}