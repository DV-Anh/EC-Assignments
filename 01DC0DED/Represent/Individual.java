package Represent;

import tspproblem.TSPProblem;

import java.util.Random;

public class Individual implements Comparable<Individual> {
    private int[] permutation;
    private TSPProblem problem;
    private double fitness;
    private double cost;
    private int permutationSize;
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

    public Individual(int[] permutation,TSPProblem tsp)
    {
        permutationSize=tsp.size();
        problem=tsp;
        this.permutation=permutation;
        this.cost= tsp.cost(permutation);
        this.fitness=1/cost;

    }

    public Individual(TSPProblem tsp)
    {
problem=tsp;
    }
    public void setIndividual(int[] permutation)
    {
        this.permutation=permutation;
        cost=problem.cost(permutation);
        fitness=1/cost;
    }
    public int[] getPermutation() {
        return permutation;
    }

    public void setPermutation(int[] permutation) {
        permutation = permutation;
        cost= problem.cost(permutation);
        fitness=1/cost;
    }

    public double getFitness() {
        return fitness;
    }


    public double getCost() {
        return cost;
    }


    @Override
    public int compareTo(Individual o) {
        return Double.compare(this.fitness,o.fitness);
    }
}
