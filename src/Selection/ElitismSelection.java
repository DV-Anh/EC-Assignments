package Selection;

import constants.Constants;
import tsp.Population;
import tspproblem.TSPProblem;

/**
 * Using f(x)=x as fitness function
 */
public class ElitismSelection {
    TSPProblem t = new TSPProblem(Constants.TESTFIES[1]);


    public int hightestFitness(int numOfElitism, Population population)
    {
        double[] fintnessOfIndividuals=fitness(population);

        int aar_index = 0;
        if(population.pop.length>0) {
            double aar_Max = fintnessOfIndividuals[0];

            for (int i = 0; i < population.pop.length; i++) {
                if (fintnessOfIndividuals[i] > aar_Max) {//比较后赋值
                    aar_Max = fintnessOfIndividuals[i];
                    aar_index = i;
                }
            }
        }
        return aar_index;
    }

    public double[] fitness(Population population)
    {
        //Caculating fitness of all cities; return a fitness array
        double[] fitnessOfPopulation=new double[population.pop.length];

        for (int i = 0; i < population.pop.length; i++) {
            fitnessOfPopulation[i]=1/population.pop[i].calculateCost(t);
        }
        return fitnessOfPopulation;
    }
}
