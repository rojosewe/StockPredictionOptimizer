package com.rojosewe.finance.model

import com.rojosewe.finance.model.filter.Filter
import com.rojosewe.finance.model.prediction.PredictionModel
import weka.core.Attribute

/**
 * @author sensefields
 */
class Specimen(var attributes:List[Attribute], var values:List[StockValue], val generation:Int, val filter:Filter, val predictionModel:PredictionModel, val parent1:Specimen = null, val parent2:Specimen = null) extends Comparable[Specimen]{
  
  var fitness:Double = 0.0
  
  def compareTo(other:Specimen):Int = {
    this.fitness.compareTo(other.fitness)
  }
  
}