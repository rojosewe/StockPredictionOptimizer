package com.rojosewe.finance.model.prediction

import weka.classifiers.functions.LinearRegression
import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.utils.Randomizer
import com.rojosewe.finance.utils.Log
import weka.core.Instances
import weka.core.Instance

/**
 * @author sensefields
 */
class LinearRegressionModel() extends WekaPredictionModelBuilder {

  val lr: LinearRegression = new LinearRegression()

  def randomize() {
  }

  def copyModel(): PredictionModel = {
    val clone = new LinearRegressionModel()
    return clone
  }

  def classify(unlabeled: Instance): Double = {
    lr.classifyInstance(unlabeled)
  }

  def buildClassifier(data: Instances) {
    log.info("Start building the prediction model " + this)
    lr.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }

  override def toString(): String = {
    return "LinearRegression"
  }
}