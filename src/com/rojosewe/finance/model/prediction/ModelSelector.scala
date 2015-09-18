package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer


/**
 * @author sensefields
 */
object ModelFactory{

  val MultilayerPerceptron = 0
  val KalmanFilter = 1
  val LeastMedSquare = 2
  val LinearRegression = 3
  val PLSClassifier = 4
  val RBFNetwork = 5
  val GaussianProcesses = 6
  val SMOReg = 7
  val NeuralNetwork = 8
  
  val availableModels = List(MultilayerPerceptron, KalmanFilter, LeastMedSquare, 
      LinearRegression, PLSClassifier, RBFNetwork, GaussianProcesses,
      SMOReg, NeuralNetwork)
  
  def randomizeModel(model:PredictionModel):PredictionModel = {
    model.randomize()
    return model
  }
  
  def getModelCopy(model:PredictionModel):PredictionModel = {
    model.copyModel()
  }
  
  def getRandomModel():PredictionModel = {
    val model = Randomizer.getInt(availableModels.length) match{
      case MultilayerPerceptron => randomizeModel(new MultilayerPerceptronModel())
      case KalmanFilter => randomizeModel(new KalmanFilterModel())
      case LeastMedSquare => randomizeModel(new LeastMedSquareModel())
      case LinearRegression => randomizeModel(new LinearRegressionModel())
      case PLSClassifier => randomizeModel(new PLSClassifierModel())
      case RBFNetwork => randomizeModel(new RBFNetworkModel())
      case GaussianProcesses => randomizeModel(new GaussianProcessModel())
      case SMOReg => randomizeModel(new SMORegModel())
      case NeuralNetwork => randomizeModel(new NeuralNetworkModel())
    }
    return model
  }
  
  def getModel(model:Int):PredictionModel = {
    val selectedModel = model match{
      case MultilayerPerceptron => randomizeModel(new MultilayerPerceptronModel())
      case KalmanFilter => randomizeModel(new KalmanFilterModel())
      case LeastMedSquare => randomizeModel(new LeastMedSquareModel())
      case LinearRegression => randomizeModel(new LinearRegressionModel())
      case PLSClassifier => randomizeModel(new PLSClassifierModel())
      case RBFNetwork => randomizeModel(new RBFNetworkModel())
      case GaussianProcesses => randomizeModel(new GaussianProcessModel())
      case SMOReg => randomizeModel(new SMORegModel())
      case NeuralNetwork => randomizeModel(new NeuralNetworkModel())
    }
    return selectedModel
  }
}