package evo.benchmark;

import evo.*;
import evo.CrossOverOpImp.OrderCrossOver;
import evo.MutationOpImp.SwapMutation;
import evo.ParentSelectionOpImp.GetTopSelection;
import evo.SuvivorSelectionOpImp.ElitismSuvivorSelection;
import evo.core.Population;
import tspproblem.TSPProblem;

public class EvaluateEvo {
    private static class SimpleReporter implements Reporter {

        @Override
        public void apply(int gen, Population p) {
            switch (gen) {
                case 0:
                case 2000:
                case 5000:
                case 10000:
                case 20000:
                    System.out.println(gen + ": " + p.bestTourCost());
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        TSPProblem inst = new TSPProblem("testfiles/eil51.tsp");
        System.out.println(inst.size());
        int popSize = 20;
        int genNum = 20000;
        ParentSelectionOp pSelOp = new GetTopSelection(2);
        CrossOverOp coOp = new OrderCrossOver();
        MutationOp muOp = new SwapMutation();
        SurvivorSelectionOp sSelOp = new ElitismSuvivorSelection(20);
        Reporter reporter = new SimpleReporter();

        TSPSolver solver = EvoSolverBuilder.buildGeneric(
                popSize,
                genNum,
                pSelOp,
                coOp,
                muOp,
                sSelOp,
                reporter
        );
        solver.solve(inst);
    }
}
