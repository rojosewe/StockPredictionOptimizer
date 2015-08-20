package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock

/**
 * @author sensefields
 */
class NeuralNetwork(stockVar:Stock) extends PredictionModel{
  
  var inNeurons:Int = 0
  var hiddenNeurons:Int = 0
  var outNeurons:Int = 0
  var learningRate:Double = 0.1
  var momentum:Double = 0.1
  var stock:Stock = stockVar
  
  def randomize(){
    
  }
  
  def predict():Array[Double] = {
    val predictions = Array[Double]()
    
    return predictions
  }
  
  def test():Array[Double] = {
    val predictions = Array[Double]()
    
    return predictions
  }
  
  def getError(predictions:Array[Double]):Double = {
    var error = Double.MaxValue
    
    return error
  }
  
}