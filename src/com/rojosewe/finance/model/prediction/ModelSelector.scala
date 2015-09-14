package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer


/**
 * @author sensefields
 */
object ModelFactory{

  val availableModels = List("NeutralNetwork", "KalmanFilter", "LeastMedSquare", 
      "LinearRegression", "PLSClassifier", "RBFNetwork", "GaussianProcesses",
      "SMORegModel")
  
  def randomizeModel(model:PredictionModel):PredictionModel = {
    model.randomize()
    return model
  }
  
  def getModelCopy(model:PredictionModel):PredictionModel = {
    model.copyModel()
  }
  
  def getRandomModel():PredictionModel = {
    val model = Randomizer.getInt(availableModels.length) match{
      case 0 => randomizeModel(new NeuralNetworkModel())
      case 1 => randomizeModel(new KalmanFilterModel())
      case 2 => randomizeModel(new LeastMedSquareModel())
      case 3 => randomizeModel(new LinearRegressionModel())
      case 4 => randomizeModel(new PLSClassifierModel())
      case 5 => randomizeModel(new RBFNetworkModel())
      case 6 => randomizeModel(new GaussianProcessModel())
      case 7 => randomizeModel(new SMORegModel())
    }
    return model
  }
}