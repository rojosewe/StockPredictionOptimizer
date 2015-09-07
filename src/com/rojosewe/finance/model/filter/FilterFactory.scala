package com.rojosewe.finance.model.filter

import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.utils.Randomizer

/**
 * @author sensefields
 */
object FilterFactory {

  val availableFilters = List("GoogleTrendsAppender", "TriangularSmoothing", "SmileAndNod")

  def googleTrendsAppender(): GoogleTrendsAppender = {
    new GoogleTrendsAppender()
  }

  def randomTriangularSmoothing(): TriangularSmoothing = {
    val filter: TriangularSmoothing = new TriangularSmoothing()
    filter.randomize()
    return filter
  }

  def getCopyTriangularSmoothing(smoothing: TriangularSmoothing) {
    val filter: TriangularSmoothing = new TriangularSmoothing()
    filter.points = smoothing.points
  }
  
  def smileAndNod():SmileAndNod = {
    return new SmileAndNod()
  }

  def getRandomFilter(): Filter = {
    val filter = Randomizer.getInt(availableFilters.length) match {
      case 0 => googleTrendsAppender()
      case 1 => randomTriangularSmoothing()
      case 2 => smileAndNod()
      
    }
    return filter
  }
}