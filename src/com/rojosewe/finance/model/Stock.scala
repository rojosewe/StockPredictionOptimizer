package com.rojosewe.finance.model

import weka.core.Attribute

/**
 * @author sensefields
 */
class Stock(val name:String, val symbol:String, val category:String) {

  var values = List[StockValue]()
  var attributes = List[Attribute]()
  
  override def toString():String = {
    name
  }
}