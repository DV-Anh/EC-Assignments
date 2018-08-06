package evo;


import evo.core.Individual;
import evo.core.Population;

import java.util.List;

public interface SelectionOp {
    public List<Individual> apply(Population p);
}
