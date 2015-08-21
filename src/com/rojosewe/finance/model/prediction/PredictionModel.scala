package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.StockValue
import weka.core.Attribute

/**
 * @author sensefields
 */
trait PredictionModel {
  def randomize()
  def train(trainData:List[StockValue], attributes:List[Attribute]):Boolean
  def predict(testData:List[StockValue], attributes:List[Attribute]): Array[Double]
}