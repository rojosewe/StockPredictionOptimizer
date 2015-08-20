package com.rojosewe.finance.model

import java.util.HashMap
import java.util.Date

/**
 * @author sensefields
 */
class Stock(stockName:String, stockSymbol:String, stockCategory:String) {
  
  val name:String = stockName
  val symbol:String = stockSymbol
  val category:String = stockCategory
  var values = List[StockValue]()
  
  override def toString():String = {
    name
  }
}