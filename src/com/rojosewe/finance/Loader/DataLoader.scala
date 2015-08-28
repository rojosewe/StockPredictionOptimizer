package com.rojosewe.finance.Loader

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.FileReader
import com.rojosewe.finance.utils.CSV
import com.rojosewe.finance.api.YahooFinance
import com.rojosewe.finance.model.StockValue
import java.util.Date
import weka.core.Attribute
import com.rojosewe.finance.utils.CSV
import com.rojosewe.finance.utils.Log
/**
 * @author sensefields
 */
class DataLoader {

  val log: Log = new Log(this.getClass)

  val stockfile = "./Data/stocks.csv"
  val months = 12
  val DATE_INDEX = 0
  val OPENING_INDEX = 1
  val HIGH_INDEX = 2
  val LOW_INDEX = 3
  val CLOSING_INDEX = 4
  val VOLUME_INDEX = 5

  def loadStockList(): List[Stock] = {
    var stockList = List[Stock]()
    for (stockLine <- FileReader.csvRead(stockfile)) {
      stockList ::= new Stock(stockLine(0), stockLine(1), stockLine(2))
    }
    return stockList
  }

  def loadOriginalAttributes(): List[Attribute] = {
    return List[Attribute](new Attribute("opening"), new Attribute("high"),
      new Attribute("low"), new Attribute("volume"))
  }

  def loadStockHistory(stock: Stock) {
    val data = new CSV().stringToData(YahooFinance.csvHistory(stock.symbol, months), true)
    stock.attributes = loadOriginalAttributes()
    for (row <- data) {
      log.debug(row(DATE_INDEX).asInstanceOf[Long])
      val stockValue: StockValue = new StockValue(
        stock,
        new Date(row(DATE_INDEX).asInstanceOf[Long] * 1000),
        stock.attributes,
        row(CLOSING_INDEX),
        row(OPENING_INDEX),
        row(HIGH_INDEX),
        row(LOW_INDEX),
        row(VOLUME_INDEX).asInstanceOf[Int])
      stock.values ::= stockValue
    }
  }
}