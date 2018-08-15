package benchmark;

import evo.*;
import evo.CrossOverOpImp.OrderCrossOver;
import evo.MutationOpImp.InversionMutation;
import evo.MutationOpImp.SwapMutation;
import evo.ParentSelectionOpImp.RouletteWheelSelection;
import evo.ParentSelectionOpImp.TournamentSelection;
import evo.SuvivorSelectionOpImp.ElitismSuvivorSelection;
import evo.core.*;
import tspproblem.TSPProblem;

/**
 * This class evaluate evolutionary algorithms as required in the assignment handout.
 *
 * 1. Design and test three evolutionary algorithms on all required instances,
 *    with population size 20, 50, 100, 200. Report the outcomes after 2000,
 *    5000, 10000 and 20000 generations
 * 2. Evaluate the best of the three with a population size of 50 for 20000 generations.
 *    Report mean, standard deviation for running 30 times on each instances.
 */
public class EvaluateEvo {
    private static class SimpleReporter implements Reporter {

        private final String algoName;
        private final String fn;
        private final int popSize;
        private final boolean onlyLast;

        public SimpleReporter(String algo, String fn, int popSize, boolean onlyLast) {
            this.algoName = algo;
            this.fn = fn;
            this.popSize = popSize;
            this.onlyLast = onlyLast;
        }

        @Override
        public void apply(int gen, Population p) {
            if (!this.onlyLast) {
                switch (gen) {
                    case 0:
                    case 2000:
                    case 5000:
                    case 10000:
                    case 20000:
                        report(gen, p.getBestFitnessValue());
                        break;
                    default:
                        break;
                }
            } else {
                if (gen == 20000)
                    report(gen, p.getBestFitnessValue());
            }
        }

        private void report(int gen, double bestFitnessValue) {
            StringBuilder record = new StringBuilder();
            record.append(algoName).append(',');
            record.append(fn).append(',');
            record.append(popSize).append(',');
            record.append(gen).append(',');
            record.append(bestFitnessValue);

            System.out.println(record.toString());
        }
    }


    public static void TOI(int popSize, int genNum, String fn) {
        TSPProblem inst = new TSPProblem(fn);
        ParentSelectionOp pSelOp = new TournamentSelection(2, (int)(popSize/3));
        CrossOverOp coOp = new OrderCrossOver(1.0);
        MutationOp muOp = new InversionMutation(1.0);
        SurvivorSelectionOp sSelOp = new ElitismSuvivorSelection();
        Reporter reporter = new SimpleReporter("TOI", fn, popSize, false);

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

    public static void TOIReportLast(int popSize, int genNum, String fn) {
        TSPProblem inst = new TSPProblem(fn);
        ParentSelectionOp pSelOp = new TournamentSelection(2, (int)(popSize/3));
        CrossOverOp coOp = new OrderCrossOver(1.0);
        MutationOp muOp = new InversionMutation(1.0);
        SurvivorSelectionOp sSelOp = new ElitismSuvivorSelection();
        Reporter reporter = new SimpleReporter("TOI", fn, popSize, true);

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

    public static void TOS(int popSize, int genNum, String fn) {
        TSPProblem inst = new TSPProblem(fn);
        ParentSelectionOp pSelOp = new TournamentSelection(2, (int)(popSize/3));
        CrossOverOp coOp = new OrderCrossOver(1.0);
        MutationOp muOp = new SwapMutation(1.0);
        SurvivorSelectionOp sSelOp = new ElitismSuvivorSelection();
        Reporter reporter = new SimpleReporter("TOS", fn, popSize, false);

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

    public static void ROI(int popSize, int genNum, String fn) {
        TSPProblem inst = new TSPProblem(fn);
        ParentSelectionOp pSelOp = new RouletteWheelSelection(2);
        CrossOverOp coOp = new OrderCrossOver(1.0);
        MutationOp muOp = new InversionMutation(1.0);
        SurvivorSelectionOp sSelOp = new ElitismSuvivorSelection();
        Reporter reporter = new SimpleReporter("ROI", fn, popSize, false);

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
        System.out.println("# Test three evolutionary algorithms and benchmark the best");
        System.out.println("# ROI: RouletteWheel,\tOrderCrossOver(p=1.0),\tInversion Mutation(p=1.0)");
        System.out.println("# TOI: Tournament(k=popSize/3),\tOrderCrossOver(p=1.0),\tInversion Mutation(p=1.0)");
        System.out.println("# TOS: Tournament(k=popSize/3),\tOrderCrossOver(p=1.0),\tSwap Mutation(p=1.0");
        System.out.println("# Notes: Results for PR2392 and USA13509 will come at the end because of long time of running.");
        System.out.println("# Notes: Results are in CSV format");
        System.out.println();
        quickTest();
        quickBenchmark();

        System.out.println();
        System.out.println("# The following running might take a long time");
        slowTest();
        slowBenchmark();

    }

    private static void slowTest() {
        System.out.println("# Compare three algorithms with varying population sizes");
        System.out.println(
                "AlgoName,FileName,PopSize,Gen,Cost"
        );
        testEvo("testfiles/pr2392.tsp");
        testEvo("testfiles/usa13509.tsp");
    }

    private static void quickTest() {
        System.out.println("# Compare three algorithms with varying population sizes");
        System.out.println(
                "AlgoName,FileName,PopSize,Gen,Cost"
        );
        testEvo("testfiles/eil51.tsp");
        testEvo("testfiles/eil76.tsp");
        testEvo("testfiles/eil101.tsp");
        testEvo("testfiles/st70.tsp");
        testEvo("testfiles/kroA100.tsp");
        testEvo("testfiles/kroC100.tsp");
        testEvo("testfiles/kroD100.tsp");
        testEvo("testfiles/lin105.tsp");
        testEvo("testfiles/pcb442.tsp");
    }

    public static void quickBenchmark() {
        System.out.println("# Benchmark the best algorithm of the three");
        System.out.println(
                "AlgoName,FileName,PopSize,Gen,Cost"
        );
        String[] files = new String[]{
                "testfiles/eil51.tsp",
                "testfiles/eil76.tsp",
                "testfiles/eil101.tsp",
                "testfiles/st70.tsp",
                "testfiles/kroA100.tsp",
                "testfiles/kroC100.tsp",
                "testfiles/kroD100.tsp",
                "testfiles/lin105.tsp",
                "testfiles/pcb442.tsp",
        };
        for (String fn : files) {
            for (int i = 0; i < 30; i++) {
                TOIReportLast(50, 20000, fn);
            }
        }
    }

    public static void slowBenchmark() {
        System.out.println("# Benchmark the best algorithm of the three");
        System.out.println(
                "AlgoName,FileName,PopSize,Gen,Cost"
        );
        String[] files = new String[]{
                "testfiles/pr2392.tsp",
                "testfiles/usa13509.tsp"
        };
        for (String fn : files) {
            for (int i = 0; i < 30; i++) {
                TOIReportLast(50, 20000, fn);
            }
        }
    }

    public static void testEvo(String fn) {
        int[] popSizes = new int[]{20, 50, 100, 200};
        int gen = 20000;
        for (int size : popSizes) {
            ROI(size, gen, fn);
            TOI(size, gen, fn);
            TOS(size, gen, fn);
        }
    }
}
