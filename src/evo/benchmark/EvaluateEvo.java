package evo.benchmark;

import evo.*;
import evo.CrossOverOpImp.OrderCrossOver;
import evo.MutationOpImp.InversionMutation;
import evo.MutationOpImp.SwapMutation;
import evo.ParentSelectionOpImp.GetTopSelection;
import evo.SuvivorSelectionOpImp.ElitismSuvivorSelection;
import evo.core.Population;
import tspproblem.TSPProblem;

public class EvaluateEvo {
    private static class SimpleReporter implements Reporter {

        @Override
        public void apply(int gen, Population p) {
                if (gen % 2000 == 0) {
                    System.out.println(gen + ": " + p.bestTourCost());
                }
        }
    }


    public static void evo1(int popSize, int genNum, String fn) {
        System.out.println(fn + ":" + popSize);
        TSPProblem inst = new TSPProblem(fn);
        ParentSelectionOp pSelOp = new GetTopSelection(2);
        CrossOverOp coOp = new OrderCrossOver(0.8);
        MutationOp muOp = new SwapMutation(0.2);
        SurvivorSelectionOp sSelOp = new ElitismSuvivorSelection();
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

    public static void evo2(int popSize, int genNum, String fn) {
        System.out.println(fn + ":" + popSize);
        TSPProblem inst = new TSPProblem(fn);
        ParentSelectionOp pSelOp = new GetTopSelection(2);
        CrossOverOp coOp = new OrderCrossOver(0.8);
        MutationOp muOp = new InversionMutation(0.2);
        SurvivorSelectionOp sSelOp = new ElitismSuvivorSelection();
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

    public static void main(String[] args) {
        evo2(50, 20000, "testfiles/eil51.tsp");
//        evo1(200, 20000, "testfiles/eil101.tsp");
//        evo1(200, 20000, "testfiles/kroC100.tsp");
//        evo1(200, 20000, "testfiles/pcb442.tsp");

    }
}
