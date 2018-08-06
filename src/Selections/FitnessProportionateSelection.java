package Selections;


import tsp.Population;
import tspproblem.TSPProblem;

public class FitnessProportionateSelection extends Selection{


    public int[] selection(Population population, int selectionNum,TSPProblem tspProblem)
    {

        double[] matingPool_Fitness=fitness(population,tspProblem);
        double sum=sumOfFitness(matingPool_Fitness);
        int[] indexOfInndividual =new int[selectionNum];
        int currentMembersNum=0;
        while (currentMembersNum<selectionNum)
        {
            int indexOfArrays=0;
            double random=Math.random();
            double cumulativeProbability=0;
            while( cumulativeProbability+(matingPool_Fitness[indexOfArrays]/sum) < random)//需要修改为累计概率
            {
                indexOfArrays++;
                if(indexOfArrays+1>=matingPool_Fitness.length)
                {
                    break;
                }
            }
            if(!whetherInArray(indexOfInndividual,indexOfArrays)) {
                indexOfInndividual[currentMembersNum] = indexOfArrays;
                currentMembersNum++;
            }
        }

        return indexOfInndividual;
    }

    /**
     * Using f(x)=1/distance as fitness function
     * @param fitnessofIndividuals
     * @return
     */
    public double sumOfFitness(double[] fitnessofIndividuals)
    {
        double sum=0;
        for (int i = 0; i <fitnessofIndividuals.length; i++)
        {
            sum=sum+fitnessofIndividuals[i]; //Caclulating distance
        }

        return sum;
    }
}
