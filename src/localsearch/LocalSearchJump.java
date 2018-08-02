package localsearch;

import tspproblem.TSPProblem;

public class LocalSearchJump extends LocalSearch {
    public LocalSearchJump(TSPProblem problem){
        super(problem);
    }
    public LocalSearchJump(){super();}
    // Jump operator
    @Override
    void generateNeighbor(int[] candidate, int i, int j) {
        int dir = Integer.compare(j, i);
        int temp = candidate[i];
        for (int k = i + dir; k != j; k += dir) {
            candidate[k - dir] = candidate[k];
        }
        candidate[j] = temp;
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

    // Override to support asymmetrical neighbor generator and improve performance
    @Override
    public int[] search(TSPProblem problem){
        int size = problem.size();
        // Generate initial permutation
        int[] currentSolution = super.generateRandomPermutation(size);
        double currentCost = problem.cost(currentSolution);
        double nextCost = currentCost;
        if(size <= 2) return currentSolution;
        // Iterate until no better neighbor available
        do{
            currentCost = nextCost;
            // Variable holding pair of jump indexes for best neighbor
            int besti, bestj = besti = 0;
            double currentCostChange = 0;
            // Iterate through all neighbors and pick the best one
            neighborloop:
            for (int i = 0; i < size - 1; i++) {
                int ileft = (i - 1 + size) % size, iright = i + 1;
                for (int j = i + 1; j < size; j++) {
                    if(ileft == j) continue;
                    int jleft = j - 1, jright = (j + 1) % size;
                    // 3-opt (replacing 3 edges) and 2 neighbors since jump is asymmetrical
                    double costChange1 = 0 - problem.distanceSquare(currentSolution[i], currentSolution[ileft])
                            - problem.distanceSquare(currentSolution[i], currentSolution[iright])
                            - problem.distanceSquare(currentSolution[j], currentSolution[jright])
                            + problem.distanceSquare(currentSolution[ileft], currentSolution[iright])
                            + problem.distanceSquare(currentSolution[i], currentSolution[j])
                            + problem.distanceSquare(currentSolution[i], currentSolution[jright]);
                    double costChange2 = 0 - problem.distanceSquare(currentSolution[j], currentSolution[jleft])
                            - problem.distanceSquare(currentSolution[j], currentSolution[jright])
                            - problem.distanceSquare(currentSolution[i], currentSolution[ileft])
                            + problem.distanceSquare(currentSolution[jleft], currentSolution[jright])
                            + problem.distanceSquare(currentSolution[i], currentSolution[j])
                            + problem.distanceSquare(currentSolution[j], currentSolution[ileft]);
                    if(costChange1 < currentCostChange){
                        currentCostChange = costChange1;
                        besti = i; bestj = j;//break neighborloop;
                    }if(costChange2 < currentCostChange){
                        currentCostChange = costChange2;
                        besti = j; bestj = i;//break neighborloop;
                    }
                }
            }generateNeighbor(currentSolution, besti, bestj);
            nextCost = problem.cost(currentSolution);
        }while(nextCost < currentCost);
        return currentSolution;
    }
}
