package com.rojosewe.finance.utils

/**
 * @author sensefields
 */

object Log {
  val DEBUG = 0
  val INFO = 1
  val WARN = 2
  val ERROR = 3

  val level = Log.DEBUG
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
}

