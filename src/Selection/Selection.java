package Selection;

import tsp.Population;
import tspproblem.TSPProblem;

public class Selection {
    public double[] fitness(Population population, TSPProblem tspProblem)
    {
        //Caculating fitness of all cities; return a fitness array
        double[] fitnessOfPopulation=new double[population.pop.length];

        for (int i = 0; i < population.pop.length; i++) {
            fitnessOfPopulation[i]=1/population.pop[i].calculateCost(tspProblem);
        }
        return fitnessOfPopulation;
    }

    public static boolean whetherInArray(int[] arr, int targetValue) {
        for(int s: arr){
            if(s==targetValue)
                return true;
        }
        return false;
    }
}
