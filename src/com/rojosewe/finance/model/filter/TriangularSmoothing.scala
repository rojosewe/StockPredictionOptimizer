package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Randomizer
import weka.core.Attribute

/**
 * @author sensefields
 */
class TriangularSmoothing(val stock: Stock) extends Filter {

  var points: Int = 0

  def preprocess(attributes: List[Attribute], values: List[StockValue]) {
    for (att <- attributes) {
      updateValue(values, att)
    }
  }

  def updateValue(values: List[StockValue], att: Attribute) = {
    for (i <- 0 to values.length) {
      var lsum = 0.0
      var lcount = 0
      for (l <- 0 to Math.min(i, points)) {
        lsum += values(l).valueMap(att.name())
      }
      lcount = Math.min(i, points)

      var rsum = 0.0
      var rcount = 0
      for (r <- i to Math.min(values.length, points)) {
        rsum += values(r).valueMap(att.name())
      }
      rcount = Math.min(values.length, points)

      values(i).valueMap(att.name()) = (values(i).valueMap(att.name()) + rsum + lsum) / (rcount + lcount + 1)
    }
  }

  def randomize() {
    points = Randomizer.getInt(50)
  }
}