package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Randomizer
import weka.core.Attribute
import com.rojosewe.finance.utils.Log

/**
 * @author sensefields
 */
class TriangularSmoothing() extends Filter with Reapplicable{

  val log = new Log(this.getClass)
  
  var points: Int = 3

  def preprocess(attributes: List[Attribute], values: List[StockValue]) {
    for (att <- attributes) {
      updateValue(values, att)
    }
  }

  def updateValue(values: List[StockValue], att: Attribute) = {
	  var lsum = 0.0
    var lcount = 0
    var rsum = 0.0
    var rcount = 0
    log.debug(att.name())
    var newValues:List[Double] = List[Double]() 
    for (r <- 0 to Math.min(values.length, points - 1)) {
      log.debug( r + ", " + values(r).valueMap(att.name()))
    	rsum += values(r).valueMap(att.name())
    }
	  rcount = Math.min(values.length, points)

    for (i <- 0 to values.length - 1) {
      if(i > values.length - points - 3)
        log.debug("Stop here")
      log.debug("Current " +  i + ", " + values(i).valueMap(att.name()))
      if(i > 0){
        lsum += values(i - 1).valueMap(att.name())  
        lcount += 1 
      }
      if(i >= points){
        log.debug("Remove left " +  (i - points) + ", " + values(i - points).valueMap(att.name())) 
        lsum -= values(i - points).valueMap(att.name())
        lcount -= 1 
      }
      if( i + points <= values.length - 1){
        log.debug("Add right" +  (i + points) + ", " + values(i + points).valueMap(att.name()))
         rsum += values(i + points).valueMap(att.name())
      }else
         rcount -= 1
      rsum -= values(i).valueMap(att.name())
      newValues = newValues :+ (values(i).valueMap(att.name()) + rsum + lsum) / (rcount + lcount + 1)
    }
    for (i <- 0 to values.length - 1) {
      values(i).valueMap(att.name()) = newValues(i)
    }
  }
  
  override def toString():String = {
    return "TriangularSmooting("+points + " points)"
  }

  def randomize() {
    points = Randomizer.getInt(50)
  }
}