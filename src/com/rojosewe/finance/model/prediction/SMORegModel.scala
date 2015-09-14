package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer
import weka.classifiers.functions.LeastMedSq
import weka.core.Instance
import weka.core.Instances
import weka.classifiers.functions.SMOreg
import weka.classifiers.functions.supportVector.PolyKernel
import weka.classifiers.functions.supportVector.RegSMOImproved
/**
 * @author sensefields
 */
class SMORegModel() extends WekaPredictionModelBuilder {

  val smo:SMOreg = new SMOreg()
  val randomSeed = Randomizer.seed
  var c = 1.0
  var kernel =  new PolyKernel()
  var exponent = 0.1
  var optimizer = new RegSMOImproved()
  var epsilon = Math.pow(10, -12)
  var epsilonParameter = 0.001
  var tolerance = 0.001
  val seed = Randomizer.seed

  def randomize() {
    c = Math.pow(10, Randomizer.getInt(10) - 5)
    epsilon = Math.pow(10, - Randomizer.getInt(11) - 1)
    epsilonParameter = Randomizer.getDouble
    tolerance = Randomizer.getDouble
  }

  def copyModel(): PredictionModel = {
    val clone = new SMORegModel()
    clone.c = c
    clone.epsilon = epsilon
    clone.kernel = kernel
    clone.exponent = exponent
    clone.optimizer = optimizer
    clone.epsilonParameter = epsilonParameter
    clone.tolerance = tolerance
    return clone
  }

  def classify(unlabeled: Instance): Double = {
    smo.classifyInstance(unlabeled)
  }

  def buildClassifier(data: Instances) {
    log.info("Start building the prediction model " + this)
    smo.setC(c)
    kernel.setExponent(exponent)
    smo.setKernel(kernel)
    optimizer.setTolerance(tolerance)
    optimizer.setEpsilon(epsilon)
    optimizer.setEpsilonParameter(epsilonParameter)
    optimizer.setSeed(seed)
    smo.setRegOptimizer(optimizer)
    smo.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }

  override def toString(): String = {
    return "SMOReg(" + c + " c, " + epsilon + " epsilon, " + epsilonParameter + " epsilonParameter, " + tolerance + " tolerance)"
  }
}