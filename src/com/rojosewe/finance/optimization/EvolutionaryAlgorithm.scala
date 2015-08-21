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
class EvolutionaryAlgorithm(stockObj: Stock) {

  val population = new TreeSet[Specimen]()
  var genCounter: Int = 0
  var currentGeneration = List[Specimen]()
  var nextGeneration = List[Specimen]()
  val stock: Stock = stockObj
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
      currentGeneration ::= new Specimen(0, FilterFactory.getRandomFilter(stock), ModelFactory.getRandomModel(stock))
    }
  }

  def evaluate {
    for (parent <- currentGeneration) {
      val result = SpecimenEvaluation.evaluate(parent)
      if (population.size < survivalSize) {
        population.add(parent)
      } else if (result < population.last().fitness) {
        population.pollLast()
        population.add(parent)
      }
    }
    if (bestSpecimenEver == null || population.first().fitness < bestSpecimenEver.fitness)
      bestSpecimenEver = population.first()
    currentGeneration = List[Specimen]()
    while (!population.isEmpty()) {
      currentGeneration ::= population.pollFirst()
    }
  }

  def select {
    for ( i <- 0 until untouched) {
      val parent = currentGeneration(i)
      nextGeneration ::= new Specimen(genCounter, parent.filter, parent.predictionModel, parent.parent1, parent.parent2)
    }
    
    for ( i <- untouched until currentGeneration.length) {
      val parent = currentGeneration(i)
      if (Randomizer.getDouble < selectionProb) {
        nextGeneration ::= new Specimen(genCounter, parent.filter, parent.predictionModel, parent.parent1, parent.parent2)
      }
    }
  }

  def recombine(p1: Specimen, p2: Specimen) {
    val child: Specimen = new Specimen(genCounter, p1.filter, p2.predictionModel, p1, p2)
    nextGeneration ::= child
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
        val child: Specimen = new Specimen(genCounter, parent.filter, parent.predictionModel, parent)
        nextGeneration ::= child
        child.filter.randomize()
        child.predictionModel.randomize()

      }
    }
  }

  def mutateFilter {
    for (parent <- currentGeneration) {
      if (Randomizer.getDouble < filterMutationProb) {
        nextGeneration ::= new Specimen(genCounter, FilterFactory.getRandomFilter(stock), parent.predictionModel, parent)
      }
    }
  }

  def mutateModel {
    for (parent <- currentGeneration) {
      if (Randomizer.getDouble < modelMutationProb) {
        nextGeneration ::= new Specimen(genCounter, parent.filter, ModelFactory.getRandomModel(stock), parent)
      }
    }
  }

  def refill {
    for (i <- nextGeneration.length until populationSize) {
      nextGeneration ::= new Specimen(0, FilterFactory.getRandomFilter(stock), ModelFactory.getRandomModel(stock))
    }
  }
}