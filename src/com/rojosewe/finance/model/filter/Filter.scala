package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.model.Stock
import weka.core.Attribute

/**
 * @author sensefields
 */
trait Filter {
  
 def preprocess(attributes: List[Attribute], values: List[StockValue])

 def randomize()
}