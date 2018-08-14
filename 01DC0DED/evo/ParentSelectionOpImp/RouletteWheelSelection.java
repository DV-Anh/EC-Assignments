package evo.ParentSelectionOpImp;

import evo.ParentSelectionOp;
import evo.core.Individual;
import evo.core.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LujunW on 2018/8/7.
 */
public class RouletteWheelSelection implements ParentSelectionOp {
    private final Random rand;
    private final int selectedNum;

    public RouletteWheelSelection(int n, long seed) {
        this.rand = new Random(seed);
        this.selectedNum = n;
    }

    public RouletteWheelSelection(int n) {
        this.rand = new Random();
        this.selectedNum = n;
    }

    @Override
    public List<Individual> apply(Population p) {
        double[] fitness = new double[p.size()];
        double worstScore = p.getWorstFitnessValue();
        double fitSum = 0.0;
        for (int i = 0; i < p.size(); i++) {
            fitness[i] = worstScore - p.fitness(p.get(i));
            fitSum += fitness[i];
        }

        double[] cumProb = new double[p.size()];
        double prevSum = 0.0;
        for (int i = 0; i < p.size(); i++) {
            cumProb[i] = prevSum + fitness[i] / fitSum;
            prevSum = cumProb[i];
        }

        List<Individual> parents = new ArrayList<>();
        for (int j = 0; j < this.selectedNum; j++) {
            double r = this.rand.nextDouble();
            int i = 0;
            while (cumProb[i] < r)
                i++;

            parents.add(p.get(i));
        }

        return parents;
    }
}
