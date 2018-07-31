package localsearch;

import tspproblem.TSPProblem;

public class LocalSearchExchange extends LocalSearch {
    public LocalSearchExchange(TSPProblem problem){
        super(problem);
    }
    // Exchange operator
    @Override
    int[] generateNeighbor(int[] candidate, int i, int j) {
        int[] neighbor = new int[candidate.length];
        for (int k = 0; k < neighbor.length; k++) {
            neighbor[k] = candidate[k == i ? j : k == j ? i : k];
        }
        return neighbor;
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
                    int jleft = j - 1, jright = (j + 1) % size;
                    // 4-opt (replacing 4 edges)
                    double neighborCost = currentCost;
                    if(iright == j){
                        neighborCost += problem.distance(currentSolution[j],currentSolution[ileft])
                        + problem.distance(currentSolution[i],currentSolution[jright])
                        - problem.distance(currentSolution[i],currentSolution[ileft])
                        - problem.distance(currentSolution[j],currentSolution[jright]);
                    }else if(ileft == j){
                        neighborCost += problem.distance(currentSolution[j],currentSolution[iright])
                                + problem.distance(currentSolution[i],currentSolution[jleft])
                                - problem.distance(currentSolution[i],currentSolution[iright])
                                - problem.distance(currentSolution[j],currentSolution[jleft]);
                    }else{
                        neighborCost += problem.distance(currentSolution[i],currentSolution[jleft])
                                + problem.distance(currentSolution[j],currentSolution[iright])
                                + problem.distance(currentSolution[j],currentSolution[ileft])
                                + problem.distance(currentSolution[i],currentSolution[jright])
                                - problem.distance(currentSolution[i],currentSolution[ileft])
                                - problem.distance(currentSolution[j],currentSolution[jright])
                                - problem.distance(currentSolution[i],currentSolution[iright])
                                - problem.distance(currentSolution[j],currentSolution[jleft]);
                    }
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
