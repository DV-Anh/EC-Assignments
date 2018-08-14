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
public class TournamentSelection implements ParentSelectionOp {

    private final Random rand;
    private final int selectedNum;
    private final int k;

    public TournamentSelection(int n, int k, long seed) {
        this.rand = new Random(seed);
        this.selectedNum = n;
        this.k = k;
    }

    public TournamentSelection(int n, int k) {
        this.rand = new Random();
        this.selectedNum = n;
        this.k = k;
    }

    @Override
    public List<Individual> apply(Population p) {
        List<Individual> parents = new ArrayList<>();
        for (int i = 0; i < this.selectedNum; i++)
            parents.add(select(p));

        return parents;
    }

    private Individual select(Population p) {
        int pSize = p.size();

        Individual parent = p.get(this.rand.nextInt(pSize));
        double pValue = p.fitness(parent);
        for (int i = 1; i < k; i++) {
            Individual cand = p.get(this.rand.nextInt(pSize));
            double candValue = p.fitness(cand);

            if (candValue < pValue) {
                parent = cand;
                pValue = candValue;
            }
        }

        return parent;
    }
}
