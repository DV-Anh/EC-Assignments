package evo;

import evo.core.Individual;
import evo.core.Population;
import tspproblem.TSPProblem;

import java.util.List;

public class EvoSolverBuilder {
    private EvoSolverBuilder() {};

    public TSPSolver buildGeneric(
            int popSize,
            int genNum,
            SelectionOp parentSelection,
            CrossOverOp crossOver,
            MutationOp mutation,
            SelectionOp suvivorSelection,
            Reporter reporter
    ) {
        return new GenericTemplate(
                popSize,
                genNum,
                parentSelection,
                crossOver,
                mutation,
                suvivorSelection,
                reporter
        );
    }


    private class GenericTemplate extends TSPSolver {
        private SelectionOp parentSelectionOp;
        private SelectionOp suvivorSelectionOp;
        private MutationOp mutationOp;
        private CrossOverOp crossOverOp;
        private Reporter reporter;
        private int populationSize;
        private int generationNum;

        public GenericTemplate(
                int popSize,
                int genNum,
                SelectionOp parentSelection,
                CrossOverOp crossOver,
                MutationOp mutation,
                SelectionOp suvivorSelection,
                Reporter reporter
        ) {
            this.parentSelectionOp = parentSelection;
            this.suvivorSelectionOp = suvivorSelection;
            this.mutationOp = mutation;
            this.crossOverOp = crossOver;
            this.reporter = reporter;
            this.populationSize = popSize;
            this.generationNum = genNum;
        }

        public void solve(TSPProblem instance) {
            Population population = new Population(this.populationSize, instance);

            int currentGen = 0;
            reporter.apply(currentGen, population);
            while (currentGen < this.generationNum) {

                List<Individual> parents = this.parentSelectionOp.apply(population);

                List<Individual> offsprings = this.crossOverOp.apply(parents);

                offsprings = this.mutationOp.apply(offsprings);

                population.add(offsprings);
                List<Individual> newPop = this.suvivorSelectionOp.apply(population);
                population = new Population(newPop, instance);

                currentGen++;
                reporter.apply(currentGen, population);
            }
        }
    }
}
