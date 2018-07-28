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
}
