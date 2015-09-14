package com.rojosewe.finance.optimization

import java.util.TreeSet
import com.rojosewe.finance.model.Specimen
import com.rojosewe.finance.model.Stock
import com.rojosewe.finance.model.prediction.PredictionModel
import com.rojosewe.finance.utils.Randomizer
import com.rojosewe.finance.model.prediction.ModelFactory
import com.rojosewe.finance.model.filter.FilterFactory

/**
 * @author sensefields
 */
class EvolutionaryAlgorithm(val stock: Stock) {

  val population = new TreeSet[Specimen]()
  var genCounter: Int = 0
  var currentGeneration = List[Specimen]()
  var nextGeneration = List[Specimen]()
  val populationSize = 100
  val maxGens = 100
  val survivalSize = populationSize * 0.2
  val untouched = 3

  val mutationProb = 0.0
  val modelMutationProb = 0.0
  val filterMutationProb = 0.0

  val recombinationProb = 0.0
  val selectionProb = 0.0
  val replacementProb = 0.0

  var bestSpecimenEver: Specimen = null

  def start() {
    initialize
    for (i <- 0 until maxGens) {
      evaluate
      genCounter += 1
      select
      crossover
      mutate
      refill
      currentGeneration = nextGeneration
      nextGeneration = List[Specimen]()
    }
    evaluate
  }

  def initialize() {
    for (i <- 0 until populationSize) {
      currentGeneration ::= new Specimen(stock.attributes, stock.values, 0, FilterFactory.getRandomFilter(), ModelFactory.getRandomModel())
    }
  }

  def evaluate {
    for (parent <- currentGeneration) {
      val result = SpecimenEvaluation.evaluate(parent)
      if (population.size < survivalSize) {
        population.add(parent)
      } else if (result < population.last().fitness.rmse) {
        population.pollLast()
        population.add(parent)
      }
    }
    if (bestSpecimenEver == null || population.first().fitness.rmse < bestSpecimenEver.fitness.rmse)
      bestSpecimenEver = population.first()
    currentGeneration = List[Specimen]()
    
    while (!population.isEmpty()) {
      currentGeneration = currentGeneration :+ population.pollFirst()
    }
  }

  def select {
    for ( i <- 0 until untouched) {
      val parent = currentGeneration(i)
      nextGeneration = nextGeneration :+ new Specimen(parent.attributes, parent.values, genCounter, FilterFactory.smileAndNod(), parent.predictionModel, parent.filters, parent.parent1, parent.parent2)
    }
    
    for ( i <- untouched until currentGeneration.length) {
      val parent = currentGeneration(i)
      if (Randomizer.getDouble < selectionProb) {
        nextGeneration = nextGeneration :+ new Specimen(parent.attributes, parent.values, genCounter, FilterFactory.smileAndNod(), parent.predictionModel, parent.filters, parent.parent1, parent.parent2)
      }
    }
  }

  def recombine(p1: Specimen, p2: Specimen) {
    try{
    	val child: Specimen = new Specimen(p2.attributes, p2.values, genCounter, p1.filter, p2.predictionModel, p2.filters, p1, p2)      
 			nextGeneration = nextGeneration :+ child
    }catch {
      case t: IllegalArgumentException => t.printStackTrace()
    }
  }

  def crossover {
    if (currentGeneration.length < 2)
      return
    val r1: Int = Randomizer.getInt(currentGeneration.length)
    var r2: Int = -1
    while (r2 >= 0 && r2 != r1) {
      r2 = Randomizer.getInt(currentGeneration.length)
    }
    recombine(currentGeneration(r1), currentGeneration(r2))
  }

  def mutate {
    mutateParameters
    mutateFilter
    mutateModel
  }

  def mutateParameters {
    for (parent <- currentGeneration) {
      if (Randomizer.getDouble < mutationProb) {
        val child: Specimen = new Specimen(parent.attributes, parent.values, genCounter, parent.filter, parent.predictionModel, parent.filters, parent)
        nextGeneration = nextGeneration :+ child
        child.filter.randomize()
        child.predictionModel.randomize()

      }
    }
  }

  def mutateFilter {
    for (parent <- currentGeneration) {
      if (Randomizer.getDouble < filterMutationProb) {
        nextGeneration ::= new Specimen(parent.attributes, parent.values, genCounter, FilterFactory.getRandomFilter(), parent.predictionModel, parent.filters, parent)
      }
    }
  }

  def mutateModel {
    for (parent <- currentGeneration) {
      if (Randomizer.getDouble < modelMutationProb) {
        nextGeneration = nextGeneration :+ new Specimen(parent.attributes, parent.values, genCounter, parent.filter, ModelFactory.getRandomModel(), parent.filters, parent)
      }
    }
  }

  def refill {
    for (i <- nextGeneration.length until populationSize) {
      nextGeneration = nextGeneration :+ new Specimen(stock.attributes, stock.values, 0, FilterFactory.getRandomFilter(), ModelFactory.getRandomModel())
    }
  }
}