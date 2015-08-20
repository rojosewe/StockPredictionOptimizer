package com.rojosewe.finance.model

import com.rojosewe.finance.model.filter.Filter
import com.rojosewe.finance.model.prediction.PredictionModel

/**
 * @author sensefields
 */
class Specimen(currentGen:Int, chosenFilter:Filter, model:PredictionModel, p1:Specimen = null, p2:Specimen = null) extends Comparable[Specimen]{
  
  var fitness:Double = 0.0
  val generation:Int = 0
  val filter:Filter = chosenFilter
  val predictionModel:PredictionModel = model
  val parent1:Specimen = p1
  val parent2:Specimen = p2
  
  def compareTo(other:Specimen):Int = {
    this.fitness.compareTo(other.fitness)    
  }
  
}