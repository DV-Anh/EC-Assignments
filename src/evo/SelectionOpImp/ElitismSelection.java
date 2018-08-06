package evo.SelectionOpImp;


import evo.SelectionOp;
import evo.core.Individual;
import evo.core.Population;

import java.util.List;

public class ElitismSelection implements SelectionOp {
    private int selectedNum;

    public ElitismSelection(int selectedNum) {
        this.selectedNum = selectedNum;
    }

    @Override
    public List<Individual> apply(Population p) {
        return p.getTop(this.selectedNum);
    }
}
