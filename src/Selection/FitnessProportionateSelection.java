package Selection;


import constants.Constants;
import tsp.Population;
import tsp.TSPLib;
import tspproblem.TSPProblem;

public class FitnessProportionateSelection {




    public int[] selection(Population population, int selectionNum,TSPProblem tspProblem)
    {

        double[] matingPool_Fitness=fitness(population,tspProblem);
        double sum=sumOfFitness(matingPool_Fitness);
//        for (int i = 0; i <population.pop.length; i++)
//        {
//            sum=sum+matingPool_Fitness[i];
//        }
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
     * Using f(x)=x as fitness function
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

//    public static void main(String[] args)
//    {
//
//        FitnessProportionateSelection a=new FitnessProportionateSelection();
//        int [] ss=a.selection(new double[]{1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,2,2.01,2.12,2.13,2.14,2.15,2.16},5);
//        for(double sss:ss)
//        {
//            System.out.println(sss);
//        }
//    }
    public double[] fitness(Population population,TSPProblem tspProblem)
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
