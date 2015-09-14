package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer

import weka.classifiers.functions.GaussianProcesses
import weka.classifiers.functions.supportVector.RBFKernel
import weka.core.Instance
import weka.core.Instances
/**
 * @author sensefields
 */
class GaussianProcessModel() extends WekaPredictionModelBuilder {

  val gp: GaussianProcesses = new GaussianProcesses()
  var filterType = GaussianProcesses.FILTER_NONE
  var kernel = new RBFKernel()
  var noise = 0.1
  var gamma = 0.1

  def randomize() {
    filterType = Randomizer.getInt(2);
    noise = Randomizer.getDouble
    kernel = new RBFKernel()
    kernel.setGamma(Randomizer.getDouble)
  }

  def classify(unlabeled: Instance): Double = {
    gp.classifyInstance(unlabeled)
  }
  
  def copyModel():PredictionModel = {
     val clone = new GaussianProcessModel()
     clone.filterType = filterType
     clone.kernel = kernel
     clone.noise = noise
     clone.gamma = gamma
     return clone
   }
  
  def buildClassifier(data: Instances) {
    kernel.setGamma(gamma)
    gp.setNoise(noise)
    gp.setKernel(kernel)
    log.info("Start building the prediction model " + this)
    gp.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }

  override def toString(): String = {
    return "GaussianProcesses(" + noise + " noise, " + kernel + " kernel, " + gamma + " gamma)"
  }
}