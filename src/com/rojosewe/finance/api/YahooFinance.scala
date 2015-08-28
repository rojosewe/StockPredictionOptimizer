package com.rojosewe.finance.api

/**
 * @author Rodrigo Weffer
 */
import java.util.{ GregorianCalendar, Calendar }
import com.rojosewe.finance.utils.Http

object YahooFinance {

  def csvHistory(symbol: String, months: Int): String = {
    val url = "http://ichart.finance.yahoo.com/table.csv"+ getHistoricParams(symbol, months)
    val body = "Date:" + Http(url).asString.body.replace("\n", "\nDate:")
    return body.substring(0,body.length - 5)
  }

  def getHistoricParams(symbol: String, months: Int): String = {
    val today = new GregorianCalendar()
    val ago = new GregorianCalendar()
    ago.add(Calendar.MONTH,  - months)
    
    val toMonth = today.get(Calendar.MONTH) + 1
    val day = today.get(Calendar.DAY_OF_MONTH)
    val year = today.get(Calendar.YEAR)
    
    val fromYear = ago.get(Calendar.YEAR)
    var fromMonth = ago.get(Calendar.MONTH) + 1
    
    return s"?s=$symbol&d=$toMonth&e=$day&f=$year&g=d&a=$fromMonth&b=$day&c=$fromYear&ignore=.csv"
  }

}