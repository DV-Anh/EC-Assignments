package evo.SuvivorSelectionOpImp;


import evo.SurvivorSelectionOp;
import evo.core.Individual;
import evo.core.Population;

import java.util.List;

public class ElitismSuvivorSelection implements SurvivorSelectionOp {
    private int selectedNum;

    public ElitismSuvivorSelection(int selectedNum) {
        this.selectedNum = selectedNum;
    }

    @Override
    public void apply(
            Population origPop, List<Individual> parents, List<Individual> offsprings) {
        origPop.add(offsprings);
        origPop.removeWorst(offsprings.size());
    }
}
