package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer

import weka.classifiers.functions.PLSClassifier
import weka.core.Instance
import weka.core.Instances
import weka.filters.supervised.attribute.PLSFilter
/**
 * @author sensefields
 */
class PLSClassifierModel() extends WekaPredictionModelBuilder {

  val pls: PLSClassifier = new PLSClassifier()
  var numComponents = 20
  var filter = new PLSFilter()

  def randomize() {
    numComponents = Randomizer.getInt(100)
  }

  def copyModel(): PredictionModel = {
    val clone = new PLSClassifierModel()
    clone.numComponents = numComponents
    clone.filter = filter
    return clone
  }

  def classify(unlabeled: Instance): Double = {
    pls.classifyInstance(unlabeled)
  }

  def buildClassifier(data: Instances) {
    log.info("Start building the prediction model " + this)
    filter.setNumComponents(numComponents)
    filter.setPerformPrediction(true)
    pls.setFilter(filter)
    pls.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }

  override def toString(): String = {
    return "PLSClassifier(" + numComponents + " numComponents, " + filter + " filter)"
  }
}