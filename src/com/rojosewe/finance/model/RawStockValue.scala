package com.rojosewe.finance.model

import java.util.Date

/**
 * @author sensefields
 */
class RawStockValue(stockObj:Stock, valueDate:Date, openingPrice:Double, closingPrice:Double, highPrice:Double, lowPrice:Double, totalVolume:Int) extends StockValue{
  
  val stock = stockObj
  val date = valueDate
  val opening = openingPrice
  val high = highPrice
  val low = lowPrice
  val closing = closingPrice
  val volume = totalVolume
  
  override def toString():String = {
    stock.symbol + ":" + date.toString() + ": " + opening + " - " + closing
  }
  
  def getDate():Date = {
    date
  }
  
  def getRawStockValue():RawStockValue = {
    this
  }
  
}