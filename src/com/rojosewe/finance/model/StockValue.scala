package com.rojosewe.finance.model

import java.util.Date

import weka.core.Attribute
import weka.core.Instance

/**
 * @author sensefields
 */
trait StockValue{
  
  def getDate():Date
  def getRawStockValue():RawStockValue
  def toInstance(atts:List[Attribute]):Instance
}