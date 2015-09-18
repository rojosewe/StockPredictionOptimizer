package com.rojosewe.finance.utils

import scala.util.Random

/**
 * @author sensefields
 */
object Randomizer {
  
  val seed = 1
  val random = new Random(seed)
  
  def getInt:Int = {
    random.nextInt()
  }
  
  def getDouble:Double = {
    random.nextDouble()
  } 
  
  def getInt(max:Int):Int = {
    random.nextInt(max)
  }
  
}