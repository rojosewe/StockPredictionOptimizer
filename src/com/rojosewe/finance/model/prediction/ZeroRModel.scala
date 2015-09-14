package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer
import weka.classifiers.functions.PLSClassifier
import weka.core.Instance
import weka.core.Instances
import weka.filters.supervised.attribute.PLSFilter
import weka.classifiers.rules.ZeroR
/**
 * @author sensefields
 */
class ZeroRModel() extends WekaPredictionModelBuilder {

  val zr:ZeroR = new ZeroR()

  def randomize() {
  }

  def copyModel(): PredictionModel = {
    val clone = new ZeroRModel()
    return clone
  }

  def classify(unlabeled: Instance): Double = {
    zr.classifyInstance(unlabeled)
  }

  def buildClassifier(data: Instances) {
    log.info("Start building the prediction model " + this)
    zr.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }

  override def toString(): String = {
    return "ZeroRModel()"
  }
}