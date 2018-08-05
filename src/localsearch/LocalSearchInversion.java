package localsearch;

import constants.Constants;
import tspproblem.TSPProblem;

public class LocalSearchInversion extends LocalSearch {
    public LocalSearchInversion(TSPProblem problem){
        super(problem);
    }
    public LocalSearchInversion(){super();}
    // Inversion operator
    @Override
    void generateNeighbor(int[] candidate, int i, int j) {
        for (int k = i, l = j; k < l; k++, l--) {
            int temp = candidate[k];
            candidate[k] = candidate[l];
            candidate[l] = temp;
        }
    }

    // Override to improve performance
    @Override
    public int[] search(TSPProblem problem){
        int size = problem.size();
        // Generate initial permutation
        int[] currentSolution = super.generateRandomPermutation(size);
        if(size <= 2) return currentSolution;
        boolean improving;
        // Iterate until no better neighbor available
        do{
            // Variable holding pair of jump indexes for best neighbor
            improving = false;
            // Iterate through all neighbors and pick the best one
            neighborloop:
            for (int i = 0; i < size - 1; i++) {
                int ileft = (i - 1 + size) % size, iright = i + 1;
                for (int j = i + 1; j < size; j++) {
                    if(ileft == j) continue;
                    int jleft = j - 1, jright = (j + 1) % size;
                    // 2-opt (replacing 2 edges)
                    double costChange = 0 - problem.distance(currentSolution[i], currentSolution[ileft])
                            - problem.distance(currentSolution[j], currentSolution[jright])
                            + problem.distance(currentSolution[i], currentSolution[jright])
                            + problem.distance(currentSolution[j], currentSolution[ileft]);
                    if(costChange < -Constants.LOCALSEARCH_THRES){
                        improving = true;
                        generateNeighbor(currentSolution, i, j);
                    }
                }
            }
        }while(improving);
        return currentSolution;
    }
}
