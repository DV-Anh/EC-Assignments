package evo.core;

import tspproblem.TSPProblem;

import java.util.*;

public class Population {
    private List<Individual> popSet;
    private TSPProblem tspInstance;
    private Comparator<Individual> comparator = new IndividualComp();

    // Generate a population of popSize size from the TSPProblem instance
    public Population(int popSize, TSPProblem instance) {
        this.tspInstance = instance;
        this.popSet = new ArrayList<>();
        for (int i = 0; i < popSize; i++)
            popSet.add(new Individual(instance.size()));
        popSet.sort(comparator);
    }


    public Population(List<Individual> newPop, TSPProblem instance) {
        this.tspInstance = instance;
        this.popSet = new ArrayList<>();
        this.popSet.addAll(newPop);
        popSet.sort(comparator);
    }


    public void add(List<Individual> offsprings) {
        popSet.addAll(offsprings);
        popSet.sort(comparator);
    }

    public void removeWorst(int size) {
        for (int i = 0; i < size; i++)
            popSet.remove(popSet.size()-1);
    }

    public double bestTourCost() {
        return fitness(popSet.get(0));
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

    private double fitness(Individual i) {
        return this.tspInstance.cost(i.clonePerm());
    }

    public List<Individual> getTop(int selectedNum) {
        int realNum = selectedNum <= popSet.size() ? selectedNum : popSet.size();

        List<Individual> topList = new ArrayList<>();
        int i = 0;
        for (Individual ind : popSet) {
            if (i >= selectedNum)
                break;
            topList.add(ind);
            i++;
        }

        return topList;
    }

}
