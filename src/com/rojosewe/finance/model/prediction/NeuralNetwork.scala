package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.utils.Randomizer
import weka.core.Instances
import weka.core.Attribute
import weka.core.FastVector

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

  def randomize() {
    hiddenNeurons = Randomizer.getInt(30)
    learningRate = Randomizer.getDouble
    momentum = Randomizer.getDouble
  }

  def train(trainData:List[StockValue], attributes:List[Attribute]): Boolean = {
    var data:Instances = getInstances(attributes, trainData.length)
    for(sv:StockValue <- trainData){
      data.add(sv.toInstance(attributes))
    }
    
    return true
  }

  def predict(testData:List[StockValue], attributes:List[Attribute]): Array[Double] = {
    val predictions = Array[Double]()

    return predictions
  }
  
   def getInstances(atts:List[Attribute], size:Int):Instances = {
    var fv:FastVector = new FastVector()
    for (attr <- atts) {
      fv.addElement(attr)
    }
    return new Instances("Stocks", fv, size)
  }
}