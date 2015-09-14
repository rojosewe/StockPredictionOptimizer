package com.rojosewe.finance.utils

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author sensefields
 */

object Log {
  val DEBUG = 0
  val INFO = 1
  val WARN = 2
  val ERROR = 3

  val level = Log.DEBUG
  val sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS z")
}


class Log(loggingClass: Class[_]) {

  def debug(message: Any) {
    if (Log.level <= Log.DEBUG) {
      println("DEBUG: " + loggingClass.getSimpleName + ": " + message)
    }
  }

  def info(message: Any) {
    if (Log.level <= Log.INFO) {
      println("INFO: " + loggingClass.getSimpleName + ": " + message)
    }
  }
  
  def warn(message: Any) {
    if (Log.level <= Log.WARN) {
      println("WARN: " + loggingClass.getSimpleName + ": " + message)
    }
  }
  
  def error(message: Any) {
    if (Log.level <= Log.ERROR) {
      println("ERROR: " + loggingClass.getSimpleName + ": " + message)
    }
  }
  
  private def log(level:String, message:Any){
    val sb:StringBuilder = new StringBuilder(Log.sdf.format(new Date()))
    sb.append("- ").append(level).append(": ").append(loggingClass.getSimpleName).append(": ").append(message)
    println(sb.toString())
  }
}

