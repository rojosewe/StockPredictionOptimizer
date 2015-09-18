package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.utils.Log
import com.rojosewe.finance.utils.Randomizer

import amten.ml.NNParams
import amten.ml.NeuralNetwork
import amten.ml.matrix.Matrix
import weka.core.Attribute

/**
 * @author sensefields
 */
class NeuralNetworkModel() extends PredictionModel {

  var params = new NNParams();
  var nn: NeuralNetwork = new NeuralNetwork(params)
  var learningRate: Double = 0.1

  def randomize() {
    learningRate = Randomizer.getDouble
  }

  val log = new Log(this.getClass())

  def train(attributes: List[Attribute], trainData: List[StockValue]): Boolean = {
    buildClassifier(getData(trainData, attributes, true))
    return true
  }

  def predict(attributes: List[Attribute], testData: List[StockValue]): Array[Double] = {
    val data: Matrix = getData(testData, attributes, true)
    val unlabeled: Matrix = data.getColumns(0, data.numColumns() - 2);
    val labels: Matrix = data.getColumns(data.numColumns() - 1, data.numColumns() - 1);
    val predictions: Matrix = nn.getPredictions(unlabeled)

    return predictions.getData
  }

  def getData(trainData: List[StockValue], attributes: List[Attribute], addClass: Boolean): Matrix = {
    val valuesMatrix = Array.ofDim[Double](trainData.length, attributes.length)
    for ((stockValue, i) <- trainData.zipWithIndex) {
      for ((key, j) <- stockValue.valueMap.keySet.zipWithIndex) {
        valuesMatrix(i)(j) = stockValue.valueMap.get(key).get
      }
    }
    new Matrix(valuesMatrix)
  }

  def buildClassifier(data: Matrix) {
    params.learningRate = learningRate
    nn = new NeuralNetwork(params)
    log.info("Start building the prediction model " + this)
    val xTrain = data.getColumns(0, data.numColumns() - 2);
    val yTrain = data.getColumns(data.numColumns() - 1, data.numColumns() - 1);
    nn.train(xTrain, yTrain)
    log.info("Finish building the prediction model " + this)
  }

  def copyModel(): PredictionModel = {
    val clone = new NeuralNetworkModel()
    clone.learningRate = learningRate
    return clone
  }

  override def toString(): String = {
    return "NeuralNetwork(" + learningRate + " lr)"
  }
}