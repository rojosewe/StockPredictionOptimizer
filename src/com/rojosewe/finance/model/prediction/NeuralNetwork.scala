package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.utils.Randomizer
import weka.core.Instances
import weka.core.Attribute
import weka.core.FastVector
import weka.classifiers.functions.MultilayerPerceptron

/**
 * @author sensefields
 */
class NeuralNetwork(stockVar: Stock) extends PredictionModel {

  val mlp:MultilayerPerceptron = new MultilayerPerceptron()
  var hiddenNeurons: Int = 0
  var learningRate: Double = 0.1
  var momentum: Double = 0.1
  var stock: Stock = stockVar

  def randomize() {
    hiddenNeurons = Randomizer.getInt(30)
    learningRate = Randomizer.getDouble
    momentum = Randomizer.getDouble
  }

  def train(attributes:List[Attribute], trainData:List[StockValue]): Boolean = {
    buildClassifier(getData(trainData,attributes))
    return true
  }

  def predict(attributes:List[Attribute], testData:List[StockValue]): Array[Double] = {
    val unlabeled:Instances = getData(testData,attributes)
    val predictions = Array[Double]()
    for (i <- 0 to testData.length){
       predictions(i) = mlp.classifyInstance(unlabeled.instance(i));   
    }
    return predictions
  }
  
  def getData(trainData: List[StockValue], attributes: List[Attribute]):Instances = {
    var data: Instances = getInstances(attributes, trainData.length)
    for (sv: StockValue <- trainData) {
      data.add(sv.toInstance())
    }
    return data
  }

  def getInstances(atts: List[Attribute], size: Int): Instances = {
    var fv: FastVector = new FastVector()
    for (attr <- atts) {
      fv.addElement(attr)
    }
    return new Instances("Stocks", fv, size)
  }
  
  def buildClassifier(data:Instances){
    mlp.setHiddenLayers(hiddenNeurons.toString())
    mlp.setLearningRate(learningRate)
    mlp.setMomentum(momentum)
    mlp.setSeed(Randomizer.seed)
    mlp.buildClassifier(data)
  }
}