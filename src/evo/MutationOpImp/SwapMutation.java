package evo.MutationOpImp;

import evo.MutationOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SwapMutation implements MutationOp {
    private double probability;

    public SwapMutation(double p) {
        this.probability = p;
    }

    public SwapMutation() {
        this.probability = 1;
    }

    @Override
    public List<Individual> apply(List<Individual> p) {
        int permLen = p.get(0).length();
        Random rand = new Random();

        List<Individual> mutated = new ArrayList<>();

        for (Individual one : p) {
            int i = rand.nextInt(permLen);
            int j = rand.nextInt(permLen);
            while (i == j)
                j = rand.nextInt(permLen);

            mutated.add(one.swap(i, j));
        }

        return mutated;
    }
}
