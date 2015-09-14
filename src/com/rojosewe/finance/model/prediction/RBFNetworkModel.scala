package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.utils.Randomizer

import weka.classifiers.functions.RBFNetwork
import weka.core.Instance
import weka.core.Instances
/**
 * @author sensefields
 */
class RBFNetworkModel() extends WekaPredictionModelBuilder {

  val rbf:RBFNetwork = new RBFNetwork()
  var minStdDev:Double = 0.1
  var maxItrs:Integer = -1
  var numClusters = 2
  val clusteringSeed = Randomizer.seed
  
  
  def randomize() {
    minStdDev = Randomizer.getDouble
    maxItrs = Randomizer.getInt(101) -1
    numClusters = Randomizer.getInt(10)
  }

   def classify(unlabeled:Instance):Double = {
     rbf.classifyInstance(unlabeled)
   }
   
   def copyModel():PredictionModel = {
     val clone = new RBFNetworkModel()
     clone.minStdDev = minStdDev
     clone.maxItrs = maxItrs
     clone.numClusters = numClusters
     return clone
   }
  
  def buildClassifier(data:Instances){
    rbf.setClusteringSeed(clusteringSeed)
    rbf.setMaxIts(maxItrs)
    rbf.setMinStdDev(minStdDev)
    rbf.setNumClusters(numClusters)
    log.info("Start building the prediction model " + this)
    rbf.buildClassifier(data)
    log.info("Finish building the prediction model " + this)
  }
  
  override def toString():String = {
    return "PLSClassifier(" + minStdDev + " minStdDev, " + maxItrs + " maxItrs, " + numClusters + " numClusters)"
  }
}