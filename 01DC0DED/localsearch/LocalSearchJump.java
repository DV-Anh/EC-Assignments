package localsearch;

import constants.Constants;
import tspproblem.TSPProblem;

public class LocalSearchJump extends LocalSearch {
    public LocalSearchJump(TSPProblem problem){
        super(problem);
    }
    public LocalSearchJump(){super();}
    // Jump operator
    @Override
    void generateNeighbor(int[] candidate, int i, int j) {
        int dir = j > i ? 1 : -1;
        int temp = candidate[i];
        for (int k = i; k != j; k += dir) {
            candidate[k] = candidate[k + dir];
        }
        candidate[j] = temp;
    }

    // Override to support asymmetrical neighbor generator and improve performance
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
                    // 3-opt (replacing 3 edges) and 2 neighbors since jump is asymmetrical
                    double costChange1 = 0 - problem.distance(currentSolution[i], currentSolution[ileft])
                            - problem.distance(currentSolution[i], currentSolution[iright])
                            - problem.distance(currentSolution[j], currentSolution[jright])
                            + problem.distance(currentSolution[ileft], currentSolution[iright])
                            + problem.distance(currentSolution[i], currentSolution[j])
                            + problem.distance(currentSolution[i], currentSolution[jright]);
                    if(costChange1 < -Constants.LOCALSEARCH_THRES){
                        improving = true;
                        generateNeighbor(currentSolution, i, j);
                    }
                    double costChange2 = 0 - problem.distance(currentSolution[j], currentSolution[jleft])
                            - problem.distance(currentSolution[j], currentSolution[jright])
                            - problem.distance(currentSolution[i], currentSolution[ileft])
                            + problem.distance(currentSolution[jleft], currentSolution[jright])
                            + problem.distance(currentSolution[i], currentSolution[j])
                            + problem.distance(currentSolution[j], currentSolution[ileft]);
                    if(costChange2 < -Constants.LOCALSEARCH_THRES){
                        improving = true;
                        generateNeighbor(currentSolution, j, i);
                    }
                }
            }
        }while(improving);
        return currentSolution;
    }
}
