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
}
