package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.model.Stock

/**
 * @author sensefields
 */
trait Filter {
  
 def preprocess():List[StockValue]

 def randomize()
}