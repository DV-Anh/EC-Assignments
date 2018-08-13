package evo.ParentSelectionOpImp;

import evo.core.ParentSelectionOp;
import evo.core.Individual;
import evo.core.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LujunW on 2018/8/7.
 */
public class RankBasedSelection implements ParentSelectionOp {

    private final Random rand;
    private final int selectedNum;

    public RankBasedSelection(int n, long seed) {
        this.rand = new Random(seed);
        this.selectedNum = n;
    }

    public RankBasedSelection(int n) {
        this.rand = new Random();
        this.selectedNum = n;
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
        int rankSum = (pSize + 1) * pSize / 2;
        int fixed = this.rand.nextInt(rankSum);
        int curSum = 0;

        int i = 0;
        for (; i < pSize; i++) {
            curSum += pSize - i;
            if (curSum > fixed)
                break;
        }
        return p.get(i);
    }
}
