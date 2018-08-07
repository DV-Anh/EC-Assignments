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

    public Individual(int[] permutation,int i)
    {
        this.permutation=permutation;
        this.cost= TSPProblem.cost(permutation);
        this.fitness=1/cost;
    }

    public Individual()
    {

    }
    public Individual setIndividual(int[] permutation)
    {
        permutation=permutation;
        cost=TSPProblem.cost(permutation);
        fitness=1/cost;
        return this;
    }
    public int[] getPermutation() {
        return permutation;
    }

    public void setPermutation(int[] permutation) {
        permutation = permutation;
        cost= TSPProblem.cost(permutation);
        fitness=1/cost;
    }

    public double getFitness() {
        return fitness;
    }


    public double getCost() {
        return cost;
    }



}
