package Selections;


import tsp.Population;
import tspproblem.TSPProblem;

/**
 * Using f(x)=1/distance as fitness function
 */
public class ElitismSelection{

    public int selection(Population population,TSPProblem tspProblem, int selectedNum)
    {
        double[] fintnessOfIndividuals=fitness(population,tspProblem);

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
    public double[] fitness(Population population, TSPProblem tspProblem)
    {
        //Caculating fitness of all cities; return a fitness array
        double[] fitnessOfPopulation=new double[population.pop.length];

        for (int i = 0; i < population.pop.length; i++) {
            fitnessOfPopulation[i]=1/population.pop[i].calculateCost(tspProblem);
        }
        return fitnessOfPopulation;
    }

    public int selection1(Represent.Population population, int num)
    {
        int aar_index = 0;
        if(population.getPopulationSize()>0) {
            double aar_Max = population.getIndividual(0).getFitness();
            for (int i = 0; i < population.getPopulationSize(); i++) {
                if (population.getIndividual(i).getFitness() > aar_Max) {//比较后赋值
                    aar_Max = population.getIndividual(i).getFitness();
                    aar_index = i;
                }
            }
        }
        return aar_index;
    }



}
