package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock

/**
 * @author sensefields
 */
class KalmanFilter(stockVar:Stock) extends PredictionModel{
  
  var stock:Stock = stockVar
  
  def randomize(){
    
  }
  
  def predict(mode:String):Array[Double] = {
    val predictions = Array[Double]()
    
    return predictions
  }
  
  def test(mode:String):Array[Double] = {
    val predictions = Array[Double]()
    
    return predictions
  }
  
  def getError(predictions:Array[Double]):Double = {
    var error = Double.MaxValue
    
    return error
  }
  
}