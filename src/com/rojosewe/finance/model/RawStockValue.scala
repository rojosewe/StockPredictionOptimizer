package com.rojosewe.finance.model

import java.util.Date
import weka.core.Instance
import weka.core.Attribute
import java.util.ArrayList

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
  
  def toInstance(atts:List[Attribute]):Instance = {
    val i:Instance = new Instance(atts.size)
    i.setValue(atts(0), opening)
    i.setValue(atts(1), high)
    i.setValue(atts(2), low)
    i.setValue(atts(3), volume)
    i.setClassValue(closing)
    return i
  }
}