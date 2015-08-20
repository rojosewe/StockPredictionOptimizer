package com.rojosewe.finance.optimization

import com.rojosewe.finance.model.Specimen

/**
 * @author sensefields
 */
object SpecimenEvaluation {
  
  def evaluate(spec:Specimen):Double = {
    val newData = spec.filter.preprocess()
    spec.fitness = spec.predictionModel.getError(spec.predictionModel.predict())
    return spec.fitness
  }
}