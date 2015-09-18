package com.rojosewe.finance.optimization

import com.rojosewe.finance.model.Specimen
import weka.core.Instances
import weka.core.Instance
import com.rojosewe.finance.model.StockValue
import weka.core.converters.ConverterUtils.DataSource
import weka.core.FastVector
import weka.core.Attribute
import com.rojosewe.finance.utils.Log
import com.rojosewe.finance.model.ModelError

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
    log.debug(spec.predictionModel)
    spec.predictionModel.train(spec.attributes, trainData)
    spec.fitness = calculateError(testData, spec.predictionModel.predict(spec.attributes, testData))
    return spec.fitness.rmse
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

  def calculateError(testData: List[StockValue], results: Array[Double]): ModelError = {
    var error: ModelError = new ModelError()
    for ((t, i) <- testData.zipWithIndex) {
      log.debug(t.closing, results(i))
      error.rmse += Math.pow(t.closing - results(i), 2)
      if(t.closing < results(i))
        error.higherThanReal += 1
      else if(t.closing > results(i))
        error.lowerThanReal += 1
      error.averageError += Math.abs(t.closing - results(i))
    }
    error.averageError = error.averageError / results.length
    for ((t, i) <- testData.zipWithIndex) {
      error.stdDev += Math.pow(error.averageError - Math.abs(t.closing - results(i)), 2)
    }
    error.stdDev = error.stdDev / results.length
    error.rmse = Math.sqrt(error.rmse / results.length)
    return error
  }
}