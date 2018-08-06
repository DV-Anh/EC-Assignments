package Represent;

import tspproblem.TSPProblem;

import java.util.Random;

public class Individual {
    private int[] permutation;
    private double fitness;
    private double cost;
    private int permutationSize=TSPProblem.coordinates2D.length;
    private boolean mutationFlag=false;
    private boolean selected=false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isMutationFlag() {
        return mutationFlag;
    }

    public void setMutationFlag(boolean mutationFlag) {
        this.mutationFlag = mutationFlag;
    }

    public boolean isCrossoverFlag() {
        return crossoverFlag;
    }

    public void setCrossoverFlag(boolean crossoverFlag) {
        this.crossoverFlag = crossoverFlag;
    }

    private boolean crossoverFlag=false;
    public Individual()
    {
        this.permutation=generateRandomPermutation(permutationSize);
        fitness= TSPProblem.cost(permutation);
        cost=1/fitness;
    }
    public Individual(int[] permutation)
    {
        this.permutation=permutation;
        fitness=TSPProblem.cost(permutation);
        cost=1/fitness;
    }
    public int[] getPermutation() {
        return permutation;
    }

    public void setPermutation(int[] permutation) {
        this.permutation = permutation;
        fitness= TSPProblem.cost(permutation);
        cost=1/fitness;
    }

    public double getFitness() {
        return fitness;
    }


    public double getCost() {
        return cost;
    }


    public int[] generateRandomPermutation(int length) {
        Random rng = new Random();
        int swapIndex, swapTemp;
        int[] permutation = new int[length];
        for (int i = 0; i < length; i++) {
            permutation[i] = i;
            swapIndex = rng.nextInt(i+1);
            swapTemp = permutation[i];
            permutation[i] = permutation[swapIndex];
            permutation[swapIndex] = swapTemp;
        }
        return permutation;
    }
}
