package com.rojosewe.finance.model

import com.rojosewe.finance.model.filter.Filter
import com.rojosewe.finance.model.prediction.PredictionModel
import weka.core.Attribute
import com.rojosewe.finance.model.filter.Reapplicable

/**
 * @author sensefields
 */
class Filters() {

  private var filters = List[Filter]()

  @throws(classOf[IllegalArgumentException])
  def addFilter(filter: Filter) {
    if (!filter.isInstanceOf[Reapplicable]) {
      for (f <- filters) {
        if (f.getClass.equals(filter.getClass)) {
          throw new IllegalArgumentException("Filter is not reapplicable")
        }
      }
    }
  }

  def getCurrentFilter(): Filter = {
    filters.last
  }

  override def toString(): String = {
    "Filters: " + filters
  }

}