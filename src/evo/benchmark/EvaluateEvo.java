package evo.benchmark;

import evo.*;
import evo.CrossOverOpImp.OrderCrossOver;
import evo.MutationOpImp.InversionMutation;
import evo.MutationOpImp.SwapMutation;
import evo.ParentSelectionOpImp.RouletteWheelSelection;
import evo.ParentSelectionOpImp.TournamentSelection;
import evo.SuvivorSelectionOpImp.ElitismSuvivorSelection;
import evo.core.*;
import tspproblem.TSPProblem;

public class EvaluateEvo {
    private static class SimpleReporter implements Reporter {

        private final String algoName;
        private final String fn;
        private final int popSize;

        public SimpleReporter(String algo, String fn, int popSize) {
            this.algoName = algo;
            this.fn = fn;
            this.popSize = popSize;
        }

        @Override
        public void apply(int gen, Population p) {
            switch (gen) {
//                case 0: case 2000:
//                case 5000: case 10000:
                case 20000:
                    report(gen, p.getBestFitnessValue());
                    break;
                default:
                    break;
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
        Reporter reporter = new SimpleReporter("TOI", fn, popSize);

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
        Reporter reporter = new SimpleReporter("TOS", fn, popSize);

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
        Reporter reporter = new SimpleReporter("ROI", fn, popSize);

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
        quickTest();
        // slowTest();
        // benchmarkTOI();
    }

    private static void slowTest() {
        System.out.println(
                "AlgoName,FileName,PopSize,Gen,Cost"
        );
        testEvo("testfiles/pr2392.tsp");
        testEvo("testfiles/usa13509.tsp");
    }
    private static void quickTest() {
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

    public static void benchmarkTOI() {
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
                "testfiles/pr2392.tsp"
        };
        for (String fn : files) {
            for (int i = 0; i < 30; i++) {
                TOI(50, 20000, fn);
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
