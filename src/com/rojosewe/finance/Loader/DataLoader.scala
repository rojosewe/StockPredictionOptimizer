package com.rojosewe.finance.Loader

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.FileReader
import com.rojosewe.finance.utils.CSV
import apiCall.YahooFinance
import com.rojosewe.finance.model.StockValue
import com.rojosewe.finance.model.RawStockValue
import java.util.Date
import com.rojosewe.finance.model.RawStockValue
/**
 * @author sensefields
 */
class DataLoader {

  val stockfile = "./Data/stocks.csv"
  val months = 12
  val DATE_INDEX = 0
  val OPENING_INDEX = 1
  val HIGH_INDEX = 2
  val LOW_INDEX = 3
  val CLOSING_INDEX = 4
  val VOLUME_INDEX = 5

  def loadStockList():List[Stock] = {
    var stockList = List[Stock]()
    for (stockLine <- FileReader.csvRead(stockfile)) {
      stockList ::= new Stock(stockLine(0), stockLine(1), stockLine(2))
    }
    return stockList
  }

  def loadStockHistory(stock: Stock) {
    val data = new CSV().stringToData(YahooFinance.csvHistory(stock.symbol, months), true)
    for(row <- data){
      val stockValue:StockValue = new RawStockValue(
          stock,
          new Date(row(DATE_INDEX).asInstanceOf[Long]), 
          row(OPENING_INDEX), 
          row(CLOSING_INDEX), 
          row(HIGH_INDEX), 
          row(LOW_INDEX), 
          row(VOLUME_INDEX).asInstanceOf[Int])
      stock.values ::= stockValue
    }
  }
}

object Test{
  def main(args: Array[String]): Unit = {
    val dl = new DataLoader()
    val stock = new Stock("TSLA","TSLA","TECH")
    dl.loadStockHistory(stock)
    println(stock.values)
  } 
}