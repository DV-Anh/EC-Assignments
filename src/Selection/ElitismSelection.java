package Selection;

import tsp.Population;
import tspproblem.TSPProblem;

/**
 * Using f(x)=1/distance as fitness function
 */
public class ElitismSelection extends Selection {


    public int hightestFitness(int numOfElitism, Population population,TSPProblem tspProblem)
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


}
