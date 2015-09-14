package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer

import weka.classifiers.functions.LeastMedSq
import weka.core.Instance
import weka.core.Instances
/**
 * @author sensefields
 */
class LeastMedSquareModel() extends WekaPredictionModelBuilder {

  val lms: LeastMedSq = new LeastMedSq()
  val randomSeed = Randomizer.seed
  var sample = 4

  def randomize() {
    sample = Randomizer.getInt(30)
  }

  def copyModel(): PredictionModel = {
    val clone = new LeastMedSquareModel()
    clone.sample = sample
    return clone
  }

  def classify(unlabeled: Instance): Double = {
    lms.classifyInstance(unlabeled)
  }

  def buildClassifier(data: Instances) {
    log.info("Start building the prediction model " + this)
    lms.setRandomSeed(randomSeed)
    lms.setSampleSize(sample)
    lms.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }

  override def toString(): String = {
    return "LeastMedSq(" + randomSeed + " seed, " + sample + " sample)"
  }
}