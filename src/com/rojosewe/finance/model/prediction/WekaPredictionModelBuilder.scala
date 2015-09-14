package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.Stock 
import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.utils.Randomizer
import com.rojosewe.finance.utils.Log
import weka.core.Instances
import weka.core.Instance
import weka.core.Attribute
import weka.core.FastVector
import weka.classifiers.functions.MultilayerPerceptron

/**
 * @author sensefields
 */
abstract class WekaPredictionModelBuilder() extends PredictionModel {

  val log = new Log(this.getClass())

  def randomize

  def train(attributes:List[Attribute], trainData:List[StockValue]): Boolean = {
    buildClassifier(getData(trainData,attributes, true))
    return true
  }
  
  def classify(unlabeled:Instance):Double

  def predict(attributes:List[Attribute], testData:List[StockValue]): Array[Double] = {
    val unlabeled:Instances = getData(testData,attributes, true)
    val predictions = Array.ofDim[Double](testData.length)
    for (i <- 0 to unlabeled.numInstances() - 1){
       predictions(i) = classify(unlabeled.instance(i));   
    }
    return predictions
  }
  
  def getData(trainData: List[StockValue], attributes: List[Attribute], addClass:Boolean):Instances = {
    var data: Instances = getInstances(attributes, trainData.length)
    log.debug(data)
    for (sv: StockValue <- trainData) {
      data.add(sv.toInstance(data))
    }
    if(addClass)
      data.setClassIndex(attributes.length - 1)
    return data
  }

  def getInstances(atts: List[Attribute], size: Int): Instances = {
    var fv: FastVector = new FastVector()
    for (attr <- atts) {
      fv.addElement(attr)
    }
    return new Instances("Stocks", fv, size)
  }
  
  def buildClassifier(data:Instances)
  
}