package evo.tests;

import evo.core.Individual;
import evo.core.Population;
import org.junit.jupiter.api.Test;
import tspproblem.TSPProblem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by LujunW on 2018/8/6.
 */
class PopulationTest {
    private final static double epsilon = 0.000001;
    @Test
    void bestTourCost() {
        TSPProblem inst = new TSPProblem("testfiles/square.tsp");
        List<Individual> indis = new ArrayList<>();
        indis.add(new Individual(new int[]{0, 1, 2, 3}));
        indis.add(new Individual(new int[]{0, 2, 1, 3}));
        indis.add(new Individual(new int[]{1, 3, 0, 2}));
        Population pop = new Population(indis, inst);

        assertEquals(4, pop.bestTourCost(), epsilon);
    }

    @Test
    void removeWorst() {
        TSPProblem inst = new TSPProblem("testfiles/square.tsp");
        List<Individual> indis = new ArrayList<>();
        indis.add(new Individual(new int[]{0, 1, 2, 3}));
        indis.add(new Individual(new int[]{0, 2, 1, 3}));
        indis.add(new Individual(new int[]{1, 3, 0, 2}));
        Population pop = new Population(indis, inst);

        assertEquals(3, pop.size());
        pop.removeWorst(2);
        assertEquals(1, pop.size());
        assertEquals(4, pop.bestTourCost(), epsilon);
    }

    @Test
    void getTop() {
        TSPProblem inst = new TSPProblem("testfiles/square.tsp");
        List<Individual> indis = new ArrayList<>();
        indis.add(new Individual(new int[]{0, 1, 2, 3}));
        indis.add(new Individual(new int[]{0, 2, 1, 3}));
        indis.add(new Individual(new int[]{1, 3, 0, 2}));
        Population pop = new Population(indis, inst);

        List<Individual> best = pop.getTop(2);

        assertEquals(4, inst.cost(best.get(0).clonePerm()), epsilon);
        assertEquals(4.82842712475, inst.cost(best.get(1).clonePerm()), epsilon);
    }
}