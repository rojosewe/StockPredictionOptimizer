package com.rojosewe.finance.doubtTester

import com.rojosewe.finance.Loader.DataLoader
import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Log

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
    return true
  } 
}