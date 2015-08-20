package com.rojosewe.finance.model

import java.util.Date

/**
 * @author sensefields
 */
class StockPrediction(stockObj:Stock, stockValue:StockValue) {
  
  val stock:Stock = stockObj
  val date:Date = stockValue.getDate()
  val predicted:Double = 0.0
  
  override def toString():String = {
    stock.symbol + ":" + date.toString() + ": " + predicted
  }
}