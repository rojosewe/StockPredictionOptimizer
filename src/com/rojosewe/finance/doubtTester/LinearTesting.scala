package com.rojosewe.finance.doubtTester

import com.rojosewe.finance.Loader.DataLoader
import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Log
import com.rojosewe.finance.model.Specimen
import com.rojosewe.finance.model.Specimen
import com.rojosewe.finance.model.filter.TriangularSmoothing
import com.rojosewe.finance.optimization.SpecimenEvaluation
import com.rojosewe.finance.model.prediction.ModelFactory

/**
 * @author sensefields
 */
object LinearTesting {
  
  val log:Log = new Log(LinearTesting.getClass)
  
  def main(args: Array[String]): Unit = {
   testAll() 
  }
  
  def testAll(){
    val stock = new Stock("TSLA", "TSLA", "TECH")
    loadData(stock)
  }
  
  def loadData(stock:Stock):Boolean = {
    log.info("Testing loading the data")
    val dl = new DataLoader()
    dl.loadStockHistory(stock)
    log.debug(stock.values)
    log.info("finished evaluating loadData")
    val filter = new TriangularSmoothing()
    filter.randomize()
    log.info(filter)
    val model = ModelFactory.getRandomModel()
    log.info(model)
    val spec:Specimen = new Specimen(stock.attributes, stock.values, 
        0, filter, model)
    SpecimenEvaluation.evaluate(spec)
    log.info(spec)
    return true
  } 
}