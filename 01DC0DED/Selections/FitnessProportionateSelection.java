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

    public int[] selection1(Represent.Population population,int selectedNum)
    {
        double sum=sumOfFitness1(population);
        int[] indexOfInndividual =new int[selectedNum];
        int currentMembersNum=0;
        while (currentMembersNum<selectedNum)
        {
            int indexOfArrays=0;
            double random=Math.random();
            double cumulativeProbability=0;
            while( cumulativeProbability+(population.getIndividual(indexOfArrays).getFitness()/sum) < random)
            {
                indexOfArrays++;
                if(indexOfArrays+1>=population.getPopulationSize())
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

    public double sumOfFitness1(Represent.Population population)
    {
        double sum=0;
        for (int i = 0; i <population.getPopulationSize(); i++)
        {
            sum=sum+population.getIndividual(i).getFitness(); //Caclulating distance
        }

        return sum;
    }

}
