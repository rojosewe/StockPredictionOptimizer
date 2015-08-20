package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Randomizer

/**
 * @author sensefields
 */
class TriangularSmoothing(stockObj:Stock) extends Filter{
  
  var points:Int = 0
  val stock = stockObj
  
  def preprocess:List[StockValue] = {
   return stock.values
 }
  
  def randomize(){
    points = Randomizer.getInt(50)
  }
}