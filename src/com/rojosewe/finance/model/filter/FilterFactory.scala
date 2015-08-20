package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Randomizer

/**
 * @author sensefields
 */
object FilterFactory {

  val availableFilters = List("GoogleTrendsAppender", "TriangularSmoothing")

  def GoogleTrendsAppender(stock: Stock): GoogleTrendsAppender = {
    new GoogleTrendsAppender(stock)
  }

  def RandomTriangularSmoothing(stock: Stock): TriangularSmoothing = {
    val filter: TriangularSmoothing = new TriangularSmoothing(stock)
    filter.randomize()
    return filter
  }

  def getCopyTriangularSmoothing(smoothing: TriangularSmoothing) {
    val filter: TriangularSmoothing = new TriangularSmoothing(smoothing.stock)
    filter.points = smoothing.points
  }

  def getRandomFilter(stock: Stock): Filter = {
    val filter = Randomizer.getInt(availableFilters.length) match {
      case 1 => GoogleTrendsAppender(stock)
      case 2 => RandomTriangularSmoothing(stock)
    }
    return filter
  }
}