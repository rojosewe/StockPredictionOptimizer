package com.rojosewe.finance.model

import java.util.Date

/**
 * @author sensefields
 */
trait StockValue{
  
  def getDate():Date
  def getRawStockValue():RawStockValue
}