package Test;

import Crossover.OrderCrossOver;
import Mutation.InversionMutation;
import Selections.ElitismSelection;
import Selections.FitnessProportionateSelection;
import Selections.Selection;
import constants.Constants;

import tsp.Individual;
import tsp.Population;
import tspproblem.TSPProblem;

import java.util.Random;

public class TestEA {
    public static void main(String[] args) {
        int populationSize=20;
        int generateNum=0;
        double crossoverProbability=0.25;
        double mutationProbability=0.25;

        Random random=new Random();
        TSPProblem tspProblem=new TSPProblem(Constants.TESTFIES[1]);
        Population population=new Population(populationSize,tspProblem.coordinates2D.length);
        FitnessProportionateSelection pntSelection=new FitnessProportionateSelection();
        ElitismSelection selectionElitism=new ElitismSelection();
        double[] fitnessOfIndividuals=new double[tspProblem.coordinates2D.length];


        fitnessOfIndividuals=pntSelection.fitness(population,tspProblem);
        int currentPopulationSize=0;
        OrderCrossOver orderCrossOver=new OrderCrossOver();
        InversionMutation inversionMutation=new InversionMutation();
        while (generateNum<30) {
            while (currentPopulationSize <= populationSize) {
                // selectedIndiv[]----- Return index of selected individuals

                int selectedIndiv[] = (pntSelection).selection(population, 2, tspProblem);
                //Crossover probability:0.25
                Individual individual=new Individual(tspProblem.coordinates2D.length);
                if (random.nextDouble() <= crossoverProbability) {
                    // Apply CrossOver
                   individual= orderCrossOver.apply(population.pop[selectedIndiv[0]],population.pop[selectedIndiv[1]]);
                }
                //Mutating probability:0.25
                if (random.nextDouble() <= mutationProbability) {
                    //Apply mutation
                    individual=inversionMutation.apply(individual);
                }
                //Which function add offspring?


            }
            generateNum++;
        }


    }
    }


