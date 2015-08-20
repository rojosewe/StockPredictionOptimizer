package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Randomizer


/**
 * @author sensefields
 */
object ModelFactory{
  
  val availableModels = List("NeutralNetwork", "KalmanFilter")
  
  def RandomKalmanFilter(stock:Stock):KalmanFilter = {
    val model:KalmanFilter = new KalmanFilter(stock)
    model.randomize()
    return model
  }
  
  def RandomNeuralNetwork(stock:Stock):NeuralNetwork = {
    val model:NeuralNetwork = new NeuralNetwork(stock)
    model.randomize()
    return model
  }
  
  def getCopyKalmanFilter(kalmanFilter:KalmanFilter){
    val model:KalmanFilter = new KalmanFilter(kalmanFilter.stock)
  }
  
  def getCopyNeuralNetwork(neuralNetwork:NeuralNetwork){
    val model:NeuralNetwork = new NeuralNetwork(neuralNetwork.stock)
    model.hiddenNeurons = neuralNetwork.hiddenNeurons
    model.learningRate= neuralNetwork.learningRate
    model.momentum = neuralNetwork.momentum
  }
  
  def getRandomModel(stock:Stock):PredictionModel = {
    val model = Randomizer.getInt(availableModels.length) match{
      case 1 => RandomNeuralNetwork(stock)
      case 2 => RandomKalmanFilter(stock)
    }
    return model
  }
}