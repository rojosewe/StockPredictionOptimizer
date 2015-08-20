package com.rojosewe.finance.model.prediction

/**
 * @author sensefields
 */
trait PredictionModel {
  def randomize()
  def predict(mode: String): Array[Double]
  def getError(predictions: Array[Double]): Double
  def test(mode: String): Array[Double]
}