package evo.core;

import tspproblem.TSPProblem;

import java.util.Comparator;
import java.util.List;

public class Population {
    private List<Individual> popSet;
    private TSPProblem tspInstance;

    // Generate a population of popSize size from the TSPProblem instance
    public Population(int popSize, TSPProblem instance) {

        this.tspInstance = instance;
    }

    public Population(List<Individual> newPop, TSPProblem instance) {
        this.tspInstance = instance;
        this.popSet.addAll(newPop);
    }


    public void add(List<Individual> offsprings) {
        popSet.addAll(offsprings);
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
        popSet.sort(new IndividualComp());
        int realNum = selectedNum <= popSet.size() ? selectedNum : popSet.size();

        return popSet.subList(0, realNum);
    }

}
