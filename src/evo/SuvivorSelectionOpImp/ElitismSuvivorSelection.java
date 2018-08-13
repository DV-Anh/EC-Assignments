package evo.SuvivorSelectionOpImp;


import evo.core.SurvivorSelectionOp;
import evo.core.Individual;
import evo.core.Population;

import java.util.List;

public class ElitismSuvivorSelection implements SurvivorSelectionOp {
    @Override
    public void apply(
            Population origPop, List<Individual> parents, List<Individual> offsprings) {
        origPop.removeWorst();
        origPop.removeWorst();
        origPop.addAll(offsprings);
    }
}
