package Selection;

import tsp.Population;
import tspproblem.TSPProblem;

import java.util.Random;

public class TournamentSelection extends Selection{


    /**
     *
     * @param population
     * @param selectionNum
     * @param tournamentSize
     * @return
     */
    public int[] selection(Population population, int selectionNum, int tournamentSize, TSPProblem tspProblem)
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

            selectedElements[currentMembersNum]=hightestFitness(indexOfIndividuals,population,tspProblem);
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
    public int hightestFitness(int[] indexOfIndividuals,Population population,TSPProblem tspProblem)
    {
        double[] fintnessOfIndividuals=fitness(population,tspProblem);

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
}
