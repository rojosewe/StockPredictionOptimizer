package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.model.StockValue
import weka.core.Attribute

/**
 * @author sensefields
 */
class KalmanFilter() extends PredictionModel{
  
  def randomize() {

  }

  def train(attributes:List[Attribute], trainData:List[StockValue]): Boolean = {

    return true
  }

  def predict(attributes:List[Attribute], testData:List[StockValue]): Array[Double] = {
    val predictions = Array[Double]()

    return predictions
  }
  
}