package evo.tests;

import evo.core.Individual;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by LujunW on 2018/8/6.
 */
class IndividualTest {
    @Test
    void swap() {
        Individual ind = new Individual(new int[]{1, 2, 3, 4, 5});

        Individual actual = ind.swap(1, 3);

        int[] expected = new int[]{1, 4, 3, 2, 5};
        assertArrayEquals(expected, actual.clonePerm());
        assertNotEquals(actual, ind);
    }

}