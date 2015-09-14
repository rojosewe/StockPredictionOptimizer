package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer
import weka.classifiers.functions.GaussianProcesses
import weka.classifiers.functions.supportVector.RBFKernel
import weka.core.Instance
import weka.core.Instances
import weka.classifiers.meta.AdditiveRegression
import weka.classifiers.trees.ADTree
import weka.classifiers.trees.DecisionStump
import weka.classifiers.Classifier
import weka.classifiers.trees.RandomTree
import weka.classifiers.trees.J48
import weka.classifiers.trees.FT
import weka.classifiers.trees.Id3
import weka.classifiers.trees.RandomForest
import weka.classifiers.trees.REPTree


/**
 * @author sensefields
 */
class AdditiveRegressionModel() extends WekaPredictionModelBuilder {

  val ar: AdditiveRegression = new AdditiveRegression()
  var numIterations = 10
  var shrinkage = 0.1
  var classifierAlg:Classifier = new ADTree()
  
  def randomize() {
    numIterations = Randomizer.getInt(100)
    shrinkage = Randomizer.getDouble
     val classifierAlg = Randomizer.getInt(8) match{
      case 0 => new ADTree()
      case 1 => new DecisionStump()
      case 2 => new FT()
      case 3 => new Id3()
      case 4 => new J48()
      case 5 => new RandomForest()
      case 6 => new RandomTree()
      case 7 => new REPTree()
    }
  }

  def classify(unlabeled: Instance): Double = {
    ar.classifyInstance(unlabeled)
  }
  
  def copyModel():PredictionModel = {
     val clone = new AdditiveRegressionModel()
     clone.numIterations = numIterations
     clone.shrinkage = shrinkage
     clone.classifierAlg = classifierAlg
     return clone
   }
  
  def buildClassifier(data: Instances) {
    ar.setShrinkage(shrinkage)
    ar.setNumIterations(numIterations)
    ar.setClassifier(classifierAlg)
    log.info("Start building the prediction model " + this)
    ar.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }

  override def toString(): String = {
    return "AdditiveRegressionier(" + numIterations + " numIterations, " + shrinkage + " shrinkage, " + classifierAlg + " classifierAlg)"
  }
}