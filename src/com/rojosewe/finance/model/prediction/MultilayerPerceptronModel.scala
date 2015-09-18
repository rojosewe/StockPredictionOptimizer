package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock 
import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.utils.Randomizer
import com.rojosewe.finance.utils.Log
import weka.core.Instances
import weka.core.Instance
import weka.classifiers.functions.MultilayerPerceptron

/**
 * @author sensefields
 */
class MultilayerPerceptronModel() extends WekaPredictionModelBuilder {

  val mlp: MultilayerPerceptron = new MultilayerPerceptron()
  var hiddenNeurons: Int = 5
  var learningRate: Double = 0.1
  var momentum: Double = 0.1

  def randomize() {
    hiddenNeurons = Randomizer.getInt(30)
    learningRate = Randomizer.getDouble
    momentum = Randomizer.getDouble
  }

  def copyModel(): PredictionModel = {
    val clone = new MultilayerPerceptronModel()
    clone.hiddenNeurons = hiddenNeurons
    clone.learningRate = learningRate
    clone.momentum = momentum
    return clone
  }

  def classify(unlabeled: Instance): Double = {
    mlp.classifyInstance(unlabeled)
  }

  def buildClassifier(data: Instances) {
    mlp.setHiddenLayers(hiddenNeurons.toString())
    mlp.setLearningRate(learningRate)
    mlp.setMomentum(momentum)
    mlp.setSeed(Randomizer.seed)
    log.info("Start building the prediction model " + this)
    mlp.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }

  override def toString(): String = {
    return "MultilayerPerceptron(" + hiddenNeurons + " hiddenNeurons, " + learningRate + " lr, " + momentum + " momentum)"
  }
}