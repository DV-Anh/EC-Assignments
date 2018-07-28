package localsearch;

import tspproblem.TSPProblem;

public class LocalSearchJump extends LocalSearch {
    public LocalSearchJump(TSPProblem problem){
        super(problem);
    }
    // Jump operator
    @Override
    int[] generateNeighbor(int[] candidate, int i, int j) {
        int[] neighbor = new int[candidate.length];
        int dir = Integer.compare(j, i);
        for (int k = 0; k < neighbor.length; k++) {
            neighbor[k] = candidate[k == i ? k + dir : k == j ? i : isBetween(k, i, j) ? k + dir : k];
        }
        return neighbor;
    }

    /**
     * Returns true if a is strictly between b and c, false otherwise
     * @param a number
     * @param b number
     * @param c number
     * @return true if a is strictly between b and c, false otherwise
     */
    private boolean isBetween(int a, int b, int c){
        return (b > a && a > c) || (b < a && a < c);
    }
    // Override to support asymmetrical neighbor generator
    @Override
    public int[] search(TSPProblem problem){
        int size = problem.size();
        // Generate initial permutation
        int[] currentSolution = super.generateRandomPermutation(size);
        int[] nextSolution = currentSolution;
        double currentCost = cost(problem, currentSolution);
        double nextCost = currentCost;
        // Iterate until no better neighbor available
        do{
            currentSolution = nextSolution;
            currentCost = nextCost;
            // Iterate through all neighbors and pick the best one
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    // This part is modified to support asymmetrical operator
                    int[] neighbor1 = generateNeighbor(currentSolution, i, j);
                    int[] neighbor2 = generateNeighbor(currentSolution, j, i);
                    double neighborCost1 = cost(problem, neighbor1);
                    double neighborCost2 = cost(problem, neighbor2);
                    if(neighborCost1 < nextCost){
                        nextCost = neighborCost1;
                        nextSolution = neighbor1;
                    }if(neighborCost2 < nextCost){
                        nextCost = neighborCost2;
                        nextSolution = neighbor2;
                    }
                }
            }
        }while(nextCost < currentCost);
        return currentSolution;
    }
}
