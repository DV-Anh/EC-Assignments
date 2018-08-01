package localsearch;

import tspproblem.TSPProblem;

import java.util.Random;

public abstract class LocalSearch {
    int[] result;
    double cost;
    public LocalSearch(TSPProblem problem){
        result = search(problem);
        cost = problem.cost(result);
    }
    public LocalSearch(){}
    /**
     * Generates and returns a neighbor candidate of the given candidate with 2 index parameters
     * This is the default function and can be overridden to customize
     * @param candidate input candidate
     * @param i generation parameter
     * @param j generation parameter
     * @return a neighbor of the input candidate
     */
    abstract int[] generateNeighbor(int[] candidate, int i, int j);

    /**
     * Return a uniformly random permutation of consecutive numbers from 0 to given number
     * @param length the size of the permutation
     * @return an array of distinct numbers from 0 to length
     */
    int[] generateRandomPermutation(int length){
        Random rng = new Random();
        int swapIndex, swapTemp;
        int[] permutation = new int[length];
        for (int i = 0; i < length; i++) {
            permutation[i] = i;
            swapIndex = rng.nextInt(i+1);
            swapTemp = permutation[i];
            permutation[i] = permutation[swapIndex];
            permutation[swapIndex] = swapTemp;
        }return permutation;
    }

    /**
     * Perform local search to find the solution to the given TSP with lowest cost
     * @param problem the TSP
     * @return the solution tour
     */
    public int[] search(TSPProblem problem){
        int size = problem.size();
        // Generate initial permutation
        int[] currentSolution = generateRandomPermutation(size);
        int[] nextSolution = currentSolution;
        double currentCost = problem.cost(currentSolution);
        double nextCost = currentCost;
        if(size <= 2) return currentSolution;
        // Iterate until no better neighbor available
        do{
            currentSolution = nextSolution;
            currentCost = nextCost;
            // Iterate through all neighbors and pick the best one
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    int[] neighbor = generateNeighbor(currentSolution, i, j);
                    double neighborCost = problem.cost(neighbor);
                    if(neighborCost < nextCost){
                        nextCost = neighborCost;
                        nextSolution = neighbor;
                    }
                }
            }
        }while(nextCost < currentCost);
        return currentSolution;
    }
}
