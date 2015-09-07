package com.rojosewe.finance.optimization

import com.rojosewe.finance.model.Specimen
import weka.core.Instances
import weka.core.Instance
import com.rojosewe.finance.model.StockValue
import weka.core.converters.ConverterUtils.DataSource
import weka.core.FastVector
import weka.core.Attribute
import com.rojosewe.finance.utils.Log

/**
 * @author sensefields
 */
object SpecimenEvaluation {

  val trainSize = 0.7
  val log = new Log(this.getClass())

  def evaluate(spec: Specimen): Double = {
    spec.filter.preprocess(spec.attributes, spec.values)
    var trainData = splitTrainData(spec.values)
    var testData = splitTestData(spec.values)
    spec.predictionModel.train(spec.attributes, trainData)
    spec.fitness = calculateError(testData, spec.predictionModel.predict(spec.attributes, testData))
    return spec.fitness
  }

  def splitTrainData(data: List[StockValue]): List[StockValue] = {
    var trainData = List[StockValue]()
    for (i <- 0 until (data.length * trainSize).asInstanceOf[Int]) {
      trainData = trainData :+ data(i)
    }
    return trainData
  }

  def splitTestData(data: List[StockValue]): List[StockValue] = {
    var testData = List[StockValue]()
    for (i <- (data.length * trainSize).asInstanceOf[Int] until data.length) {
      testData = testData :+ data(i)
    }
    return testData
  }

  def calculateError(testData: List[StockValue], results: Array[Double]): Double = {
    var error: Double = 0.0
    for ((t, i) <- testData.zipWithIndex) {
      log.debug(t.closing, results(i))
      error += Math.pow(t.closing - results(i), 2)
    }
    return Math.sqrt(error / results.length)
  }
}