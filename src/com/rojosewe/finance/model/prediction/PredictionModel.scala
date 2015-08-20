package com.rojosewe.finance.model.prediction

/**
 * @author sensefields
 */
trait PredictionModel {
  def randomize()
  def predict(): Array[Double]
  def getError(predictions: Array[Double]): Double
}