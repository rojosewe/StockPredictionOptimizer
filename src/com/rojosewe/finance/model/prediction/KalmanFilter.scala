package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.model.StockValue
import weka.core.Attribute

/**
 * @author sensefields
 */
class KalmanFilter(stockVar:Stock) extends PredictionModel{
  
  var stock:Stock = stockVar
  
  def randomize() {

  }

  def train(trainData:List[StockValue], attributes:List[Attribute]): Boolean = {

    return true
  }

  def predict(testData:List[StockValue], attributes:List[Attribute]): Array[Double] = {
    val predictions = Array[Double]()

    return predictions
  }
  
}