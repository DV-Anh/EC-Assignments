package Selection;

import constants.Constants;
import tsp.Population;
import tspproblem.TSPProblem;

import java.util.Random;

public class TournamentSelection {

    TSPProblem t = new TSPProblem(Constants.TESTFIES[1]);

    /**
     *
     * @param population
     * @param selectionNum
     * @param tournamentSize
     * @return
     */
    public int[] selection(Population population, int selectionNum, int tournamentSize )
    {
        Random random=new Random();
        int[] selectedElements =new int[selectionNum];
        int currentMembersNum=0;
        while (currentMembersNum<selectionNum)
        {
            int[] indexOfIndividuals=new int[tournamentSize];
            int currentRandomNum=0;
            while(currentRandomNum<tournamentSize)
            {
                int randomNum=random.nextInt(population.pop.length);
                if(!whetherInArray(indexOfIndividuals,randomNum))
                {
                    indexOfIndividuals[currentRandomNum]=randomNum;
                    currentRandomNum++;
                }
            }

            selectedElements[currentMembersNum]=hightestFitness(indexOfIndividuals,population);
            currentMembersNum++;

        }
        return selectedElements;
    }

    /**
     *
     * @param indexOfIndividuals
     * @param population
     * @return
     */
    public int hightestFitness(int[] indexOfIndividuals,Population population)
    {
        double[] fintnessOfIndividuals=fitness(population);

        int aar_index = 0;
        if(indexOfIndividuals.length>0) {
            double aar_Max = fintnessOfIndividuals[indexOfIndividuals[0]];

            for (int i = 0; i < indexOfIndividuals.length; i++) {
                if (fintnessOfIndividuals[indexOfIndividuals[i]] > aar_Max) {//比较后赋值
                    aar_Max = fintnessOfIndividuals[indexOfIndividuals[i]];
                    aar_index = indexOfIndividuals[i];
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

    public static boolean whetherInArray(int[] arr, int targetValue) {
        for(int s: arr){
            if(s==targetValue)
                return true;
        }
        return false;
    }

//    public static void main(String[] args)
//    {
//        TournamentSelection a=new TournamentSelection();
//        double[] ss=a.selection(new double[] {5,18,91,23,66,56,45,76,235},3,2);
//        for(double sss:ss)
//        {
//            System.out.println(sss);
//        }
//    }
}
