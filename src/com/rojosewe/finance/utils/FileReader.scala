package com.rojosewe.finance.utils

import scala.io.Source

/**
 * @author sensefields
 */
object FileReader {
  
  def read(filePath:String):Array[String] = {
    val source = Source.fromFile(filePath)
    val lines = try source.mkString finally source.close()
    return lines.split("\n")
  }
  
  def csvRead(filePath:String):Array[Array[String]] = {
    val lines = read(filePath)
    if(lines.length == 0)
      return Array.ofDim[String](0, 3)
    val values = Array.ofDim[String](lines.length, lines(0).split(",").length)
    for((line, i) <- lines.zipWithIndex){
      val value = line.split(",")
      values(i)(0) = value(0)
      values(i)(1) = value(1)
      values(i)(2) = value(2)
    }
    return values
  }  
  
}