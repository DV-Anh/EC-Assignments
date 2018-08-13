package evo.core;

import evo.core.Individual;
import evo.core.Population;

import java.util.List;

public interface ParentSelectionOp {
    public List<Individual> apply(Population p);
}
