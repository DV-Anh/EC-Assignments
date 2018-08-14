package evo;


import evo.core.Individual;
import evo.core.Population;

import java.util.List;

public interface SurvivorSelectionOp {

    /**
     * The implementation of this interface will do suvivor selection,
     * given the following parameters. The implementation shall modify
     * the Population in-place instead of trying to generate new one.
     *
     * @param p The original population without offsprings
     * @param parents Selected parents
     * @param offsprings The offsprings generated from the selected parents
     */
    public void apply(
            Population p,
            List<Individual> parents,
            List<Individual> offsprings
    );
}
