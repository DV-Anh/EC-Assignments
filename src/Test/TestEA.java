package Test;

import Crossover.OrderCrossOver;
import Mutation.InversionMutation;
import Represent.Individual;
import Represent.Population;
import Selections.ElitismSelection;
import Selections.FitnessProportionateSelection;

import constants.Constants;


import localsearch.LocalSearch;
import localsearch.LocalSearchJump;
import tspproblem.TSPProblem;

import java.util.Random;

public class TestEA {
    public int[] generateRandomPermutation(int length) {
        Random rng = new Random();
        int swapIndex, swapTemp;
        int[] permutation = new int[length];
        for (int i = 0; i < length; i++) {
            permutation[i] = i;
            swapIndex = rng.nextInt(i+1);
            swapTemp = permutation[i];
            permutation[i] = permutation[swapIndex];
            permutation[swapIndex] = swapTemp;
        }
        return permutation;
    }
    public static void main(String[] args) {
        int populationSize=20;
        int generateNum=0;
        double crossoverProbability=0.25;
        double mutationProbability=0.25;

        Random random=new Random();
        TSPProblem tspProblem=new TSPProblem(Constants.TESTPATH+Constants.TESTFIES[0]);
        FitnessProportionateSelection pntSelection=new FitnessProportionateSelection();
        ElitismSelection selectionElitism=new ElitismSelection();
        Individual[] individuals=new Individual[populationSize];
        int currentPopulationSize=0;
        while(currentPopulationSize<populationSize)
        {
            TestEA testEA=new TestEA();
           // System.out.println(IntArrayToString(testEA.generateRandomPermutation(tspProblem.size())));
            individuals[currentPopulationSize]=new Individual(testEA.generateRandomPermutation(tspProblem.size()),1);
     //       System.out.println(IntArrayToString(individuals[currentPopulationSize].getPermutation()));
            currentPopulationSize++;
        }
        Represent.Population population=new Represent.Population(individuals,3);
        OrderCrossOver orderCrossOver=new OrderCrossOver();
        InversionMutation inversionMutation=new InversionMutation();
        LocalSearch localSearch=new LocalSearchJump(tspProblem);
        while (generateNum<30) {
            while (population.getPopulationSize() <= populationSize) {
                // selectedIndiv[]----- Return index of selected individuals
                Represent.Individual individual=new Individual();
                int selectedIndiv[] = (pntSelection).selection1(population, 2);
                //Crossover probability:0.25

                if (random.nextDouble() <= crossoverProbability) {
                    // Apply CrossOver
                    System.out.println(IntArrayToString(individuals[selectedIndiv[0]].getPermutation())+'\n'+IntArrayToString(individuals[selectedIndiv[1]].getPermutation()));
                   individual= individual.setIndividual(orderCrossOver.apply1(population.getIndividual(selectedIndiv[0]),population.getIndividual(selectedIndiv[1])));
                }
                //Mutating probability:0.25
                if (random.nextDouble() <= mutationProbability) {
                    //Apply mutation
//                    individual=individual.setIndividual(inversionMutation.apply1(individual));
                }
                population.addIndividual(individual);
                System.out.println(population.getPopulationSize());

            }
         //   selectionElitism.selection(individuals,tspProblem,populationSize);
         //   intermediatepool=populationSize;
            generateNum++;
        }

    }
    public static String IntArrayToString(int[] intArray)
    {
        String string="";
        for (int a:intArray) {
            string=string+String.valueOf(a)+" ";
        }
        return string;

    }

    }


