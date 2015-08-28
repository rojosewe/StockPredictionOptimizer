package com.rojosewe.finance.model

import scala.collection.mutable.HashMap
import java.util.Date
import weka.core.Instance
import weka.core.Attribute
import java.util.ArrayList

/**
 * @author sensefields
 */
class StockValue(val stock:Stock, val date:Date, var attributes: List[Attribute], val closing:Double, openingPrice:Double, highPrice:Double, lowPrice:Double, totalVolume:Int){
  
  var valueMap:HashMap[String, Double]  = new HashMap[String, Double]()
  valueMap .+= ("opening" -> openingPrice, "high" -> highPrice, 
      "low" -> lowPrice, "volume" -> totalVolume) 
  
  override def toString():String = {
    stock.symbol + ":" + date.toString() + ": " + valueMap("opening") + " - " + closing
  }
  
  def getDate():Date = {
    date
  }
  
  def toInstance():Instance = {
    val i:Instance = new Instance(attributes.size)
    for(att <- attributes){
    	i.setValue(att, valueMap(att.name()))
    }
    i.setClassValue(closing)
    return i
  }
}