package evo.core;

import evo.core.Individual;
import evo.core.Population;

import java.util.List;

/**
 * Created by LujunW on 2018/8/6.
 */
public interface ParentSelectionOp {
    public List<Individual> apply(Population p);
}
