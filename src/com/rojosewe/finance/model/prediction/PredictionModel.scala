package com.rojosewe.finance.model.prediction

import com.rojosewe.finance.model.StockValue
import weka.core.Attribute

/**
 * @author sensefields
 */
trait PredictionModel {
  def randomize()
  def train(attributes:List[Attribute], trainData:List[StockValue]):Boolean
  def predict(attributes:List[Attribute], testData:List[StockValue]): Array[Double]
}