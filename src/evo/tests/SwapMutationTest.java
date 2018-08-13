package evo.tests;

import evo.core.MutationOp;
import evo.MutationOpImp.SwapMutation;
import evo.core.Individual;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SwapMutationTest {
    @Test
    void apply() {
        List<Individual> offsprings = new ArrayList<>();
        offsprings.add(new Individual(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        offsprings.add(new Individual(new int[]{9, 3, 7, 8, 2, 6, 5, 1, 4}));
        MutationOp op = new SwapMutation(0);

        List<Individual> mutated = op.apply(offsprings);

    }

}