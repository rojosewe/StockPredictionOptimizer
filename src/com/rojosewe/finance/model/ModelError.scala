package com.rojosewe.finance.model

import com.rojosewe.finance.model.filter.Filter
import com.rojosewe.finance.model.prediction.PredictionModel
import weka.core.Attribute
import com.rojosewe.finance.model.filter.Reapplicable

/**
 * @author sensefields
 */
class ModelError() {

  var rmse: Double = 0.0
  var lowerThanReal: Int = 0
  var higherThanReal: Int = 0
  var averageError: Double = 0.0
  var stdDev: Double = 0.0

  override def toString(): String = {
    new StringBuilder("GaussianProcesses(")
    .append(rmse).append(" rmse, ")
    .append(lowerThanReal).append(" lowerThanReal, ")
    .append(higherThanReal).append(" higherThanReal, ")
    .append(averageError).append(" averageError ")
    .append(stdDev).append(" stdDev")
    .append(")").toString()
  }

}