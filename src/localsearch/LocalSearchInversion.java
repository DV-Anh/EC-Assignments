package localsearch;

import tspproblem.TSPProblem;

public class LocalSearchInversion extends LocalSearch {
    public LocalSearchInversion(TSPProblem problem){
        super(problem);
    }
    // Inversion operator
    @Override
    int[] generateNeighbor(int[] candidate, int i, int j) {
        float mid = (float) ((i + j)/2.0);
        int[] neighbor = new int[candidate.length];
        for (int k = 0; k < neighbor.length; k++) {
            neighbor[k] = candidate[isBetween(k, i, j) ? k + Math.round((mid - (float)k)*2) : k];
        }
        return neighbor;
    }
    /**
     * Returns true if a is between b and c or equal to one of them, false otherwise
     * @param a number
     * @param b number
     * @param c number
     * @return true if a is between b and c or equal to one of them, false otherwise
     */
    private boolean isBetween(int a, int b, int c){
        return (b >= a && a >= c) || (b <= a && a <= c);
    }

    // Override to improve performance
    @Override
    public int[] search(TSPProblem problem){
        int size = problem.size();
        // Generate initial permutation
        int[] currentSolution = super.generateRandomPermutation(size);
        int[] nextSolution = currentSolution;
        double currentCost = cost(problem, currentSolution);
        double nextCost = currentCost;
        if(size <= 2) return currentSolution;
        // Iterate until no better neighbor available
        do{
            currentSolution = nextSolution;
            currentCost = nextCost;
            // Variable holding pair of jump indexes for best neighbor
            int besti, bestj = besti = 0;
            // Iterate through all neighbors and pick the best one
            for (int i = 0; i < size - 1; i++) {
                int ileft = (i - 1 + size) % size, iright = i + 1;
                for (int j = i + 1; j < size; j++) {
                    if(ileft == j) continue;
                    int jleft = j - 1, jright = (j + 1) % size;
                    // 2-opt (replacing 2 edges)
                    double neighborCost = currentCost - problem.distance(currentSolution[i], currentSolution[ileft])
                            - problem.distance(currentSolution[j], currentSolution[jright])
                            + problem.distance(currentSolution[i], currentSolution[jright])
                            + problem.distance(currentSolution[j], currentSolution[ileft]);
                    if(neighborCost < nextCost){
                        nextCost = neighborCost;
                        besti = i; bestj = j;
                    }
                }
            }nextSolution = generateNeighbor(currentSolution, besti, bestj);
            nextCost = cost(problem, nextSolution);
        }while(nextCost < currentCost);
        return currentSolution;
    }
}
