package evo.core;

import tspproblem.TSPProblem;

import java.util.*;

public class Population {
    private List<Individual> popSet = new ArrayList<>();
    private Map<Individual, Double> fitMatrix = new HashMap<>();
    private TSPProblem tspInstance;
    private Comparator<Individual> comparator = new IndividualComp();

    // Generate a population of popSize size from the TSPProblem instance
    public Population(int popSize, TSPProblem instance) {
        this.tspInstance = instance;
        for (int i = 0; i < popSize; i++)
            this.add(new Individual(instance.size()));
    }

    public Population(List<Individual> newPop, TSPProblem instance) {
        this.tspInstance = instance;
        this.addAll(newPop);
    }

    public void add(Individual individual) {
        this.popSet.add(individual);
        this.fitMatrix.put(individual, this.fitness(individual));
    }

    public void remove(Individual individual) {
        this.popSet.remove(individual);
        this.fitMatrix.remove(individual);
    }

    public double fitness(Individual i) {
        if (this.fitMatrix.containsKey(i))
            return this.fitMatrix.get(i);

        return this.tspInstance.cost(i.clonePerm());
    }

    public void addAll(List<Individual> offsprings) {
        for (Individual individual : offsprings)
            this.add(individual);
    }

    public void removeWorst() {
        Individual worst = this.get(0);
        double worstFitness = this.fitMatrix.get(worst);
        for (int i = 1; i < this.size(); i++) {
            Individual indi = this.get(i);
            double value = this.fitMatrix.get(indi);
            if (value > worstFitness) {
                worst = indi;
                worstFitness = value;
            }
        }
        this.remove(worst);
    }

    public double getWorstFitnessValue() {
        Individual worst = this.get(0);
        double worstFitness = this.fitMatrix.get(worst);
        for (int i = 1; i < this.size(); i++) {
            Individual indi = this.get(i);
            double value = this.fitMatrix.get(indi);
            if (value > worstFitness) {
                worst = indi;
                worstFitness = value;
            }
        }

        return this.fitness(worst);
    }

    public double getBestFitnessValue() {
        double bestFitness = this.fitMatrix.get(this.get(0));
        for (int i = 1; i < this.size(); i++) {
            Individual indi = this.get(i);
            if (this.fitMatrix.get(indi) < bestFitness) {
                bestFitness = this.fitMatrix.get(indi);
            }
        }
        return bestFitness;
    }

    public int size() {
        return popSet.size();
    }

    public Individual get(int i) {
        return popSet.get(i);
    }

    private class IndividualComp implements Comparator<Individual> {
        private final static double epsilon = 0.0000001;

        @Override
        public int compare(Individual o1, Individual o2) {
            double fit1 = fitness(o1);
            double fit2 = fitness(o2);
            double delta = fit1 - fit2;

            if (Math.abs(delta) <= epsilon)
                return 0;
            else if (delta < 0)
                return -1;
            else
                return 1;
        }
    }
}
