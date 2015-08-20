package com.rojosewe.finance.utils

import scala.util.Random

/**
 * @author sensefields
 */
object Randomizer {
  
  val random = new Random(121)
  
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