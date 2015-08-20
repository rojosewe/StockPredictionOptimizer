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
  val mutationProb = 0.0
  val modelMutationProb = 0.0
  val filterMutationProb = 0.0
  val recombinationProb = 0.0
  val selectionProb = 0.0
  val replacementProb = 0.0
  val maxGens = 100

  def initialize() {
    for (i <- 1 until populationSize) {
      currentGeneration ::= new Specimen(0, FilterFactory.getRandomFilter(stock), ModelFactory.getRandomModel(stock))
    }
  }
  
  def start(){
    initialize()
    for (i <- 1 until maxGens) {
    	evaluate
      crossover
      mutate
      mutateFilter
      select
      replace
      genCounter += 1
      currentGeneration = nextGeneration
      nextGeneration = List[Specimen]()
    }
    evaluate
  }

  def crossover {

  }

  def mutate {
    for (parent <- currentGeneration) {
      if (Randomizer.getDouble > mutationProb) {
        val child:Specimen = new Specimen(genCounter, parent.filter, parent.predictionModel, parent)
        nextGeneration ::= child
        child.filter.randomize()
        child.predictionModel.randomize()
        
      }
    }
  }

  def mutateFilter {
    for (parent <- currentGeneration) {
      if (Randomizer.getDouble > filterMutationProb) {
        nextGeneration ::= new Specimen(genCounter, FilterFactory.getRandomFilter(stock), parent.predictionModel, parent)
      }
    }
  }

  def mutateModel {
    for (parent <- currentGeneration) {
      if (Randomizer.getDouble > modelMutationProb) {
        nextGeneration ::= new Specimen(genCounter, parent.filter, ModelFactory.getRandomModel(stock), parent)
      }
    }
  }

  def select {
  for (parent <- currentGeneration) {
      if (Randomizer.getDouble > selectionProb) {
        nextGeneration ::= new Specimen(genCounter, parent.filter, parent.predictionModel, parent.parent1, parent.parent2)
      }
    }
  }
  
  def replace {
  for (parent <- currentGeneration) {
      if (Randomizer.getDouble > replacementProb) {
        nextGeneration ::= new Specimen(genCounter, FilterFactory.getRandomFilter(stock), 
            ModelFactory.getRandomModel(stock))
      }
    }
  }

  def evaluate {
    for (parent <- currentGeneration) {
      val result = SpecimenEvaluation.evaluate(parent)
      if(population.size < populationSize){
       population.add(parent) 
      }else if(result < population.last().fitness){
        population.pollLast()
        population.add(parent) 
      }
    }
  }
}