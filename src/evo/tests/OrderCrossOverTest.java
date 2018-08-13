package evo.tests;

import evo.core.CrossOverOp;
import evo.CrossOverOpImp.OrderCrossOver;
import evo.core.Individual;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderCrossOverTest {
    @Test
    void apply() {
        List<Individual> parents = new ArrayList<>();
        parents.add(new Individual(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        parents.add(new Individual(new int[]{9, 3, 7, 8, 2, 6, 5, 1, 4}));
        CrossOverOp op = new OrderCrossOver(0);

        List<Individual> offsprings = op.apply(parents);

        int[] expected = new int[]{9, 3, 2, 6, 5, 1, 7, 8, 4};
        int[] actual = offsprings.get(0).clonePerm();
        assertArrayEquals(expected, actual);

        expected = new int[]{2, 3, 4, 6, 7, 8, 5, 1, 9};
        actual = offsprings.get(1).clonePerm();
        assertArrayEquals(expected, actual);
    }

}