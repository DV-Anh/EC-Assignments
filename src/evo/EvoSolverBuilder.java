package evo;

import evo.core.Individual;
import evo.core.Population;
import tspproblem.TSPProblem;

import java.util.List;

public class EvoSolverBuilder {
    private EvoSolverBuilder() {};

    public static TSPSolver buildGeneric(
            int popSize,
            int genNum,
            ParentSelectionOp parentSelection,
            CrossOverOp crossOver,
            MutationOp mutation,
            SurvivorSelectionOp suvivorSelection,
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


    private static class GenericTemplate extends TSPSolver {
        private SurvivorSelectionOp survivorSelectionOp;
        private ParentSelectionOp parentSelectionOp;
        private MutationOp mutationOp;
        private CrossOverOp crossOverOp;
        private Reporter reporter;
        private int populationSize;
        private int generationNum;

        public GenericTemplate(
                int popSize,
                int genNum,
                ParentSelectionOp parentSelection,
                CrossOverOp crossOver,
                MutationOp mutation,
                SurvivorSelectionOp survivorSelection,
                Reporter reporter
        ) {
            this.parentSelectionOp = parentSelection;
            this.survivorSelectionOp = survivorSelection;
            this.mutationOp = mutation;
            this.crossOverOp = crossOver;
            this.reporter = reporter;
            this.populationSize = popSize;
            this.generationNum = genNum;
        }

        @Override
        public void solve(TSPProblem instance) {
            Population population = new Population(this.populationSize, instance);

            int currentGen = 0;
            reporter.apply(currentGen, population);
            while (currentGen < this.generationNum) {

                List<Individual> parents = this.parentSelectionOp.apply(population);

                List<Individual> offsprings = this.crossOverOp.apply(parents);

                List<Individual> mutated = this.mutationOp.apply(offsprings);

                this.survivorSelectionOp.apply(population, parents, mutated);

                currentGen++;
                reporter.apply(currentGen, population);
            }
        }
    }
}
