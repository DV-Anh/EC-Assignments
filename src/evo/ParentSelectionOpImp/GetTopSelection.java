package evo.ParentSelectionOpImp;

import evo.ParentSelectionOp;
import evo.core.Individual;
import evo.core.Population;

import java.util.List;


public class GetTopSelection implements ParentSelectionOp {
    private int top;

    public GetTopSelection(int top) {
        this.top = top;
    }
    @Override
    public List<Individual> apply(Population p) {
        return p.getTop(this.top);
    }
}
