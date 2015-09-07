package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Randomizer


/**
 * @author sensefields
 */
object ModelFactory{
  
  val availableModels = List("NeutralNetwork", "KalmanFilter")
  
  def RandomKalmanFilter():KalmanFilter = {
    val model:KalmanFilter = new KalmanFilter()
    model.randomize()
    return model
  }
  
  def RandomNeuralNetwork():NeuralNetwork = {
    val model:NeuralNetwork = new NeuralNetwork()
    model.randomize()
    return model
  }
  
  def getCopyKalmanFilter(kalmanFilter:KalmanFilter){
    val model:KalmanFilter = new KalmanFilter()
  }
  
  def getCopyNeuralNetwork(neuralNetwork:NeuralNetwork){
    val model:NeuralNetwork = new NeuralNetwork()
    model.hiddenNeurons = neuralNetwork.hiddenNeurons
    model.learningRate= neuralNetwork.learningRate
    model.momentum = neuralNetwork.momentum
  }
  
  def getRandomModel():PredictionModel = {
    val model = Randomizer.getInt(availableModels.length) match{
      case 1 => RandomNeuralNetwork()
      case 2 => RandomKalmanFilter()
    }
    return model
  }
}