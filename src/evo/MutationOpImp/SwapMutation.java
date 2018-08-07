package evo.MutationOpImp;

import evo.MutationOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SwapMutation implements MutationOp {
    private Random rand;
    private double probability = 1;

    public SwapMutation(double p) {
        this.probability = p;
        this.rand = new Random();
    }

    public SwapMutation(long seed) {
        this.rand = new Random(seed);
    }

    public SwapMutation() {
    }

    @Override
    public List<Individual> apply(List<Individual> p) {
        int permLen = p.get(0).length();
        List<Individual> mutated = new ArrayList<>();

        for (Individual one : p) {
            if (this.rand.nextDouble() > this.probability){
                mutated.add(new Individual(one.clonePerm()));
                continue;
            }
            int i = this.rand.nextInt(permLen);
            int j = this.rand.nextInt(permLen);
            while (i == j)
                j = this.rand.nextInt(permLen);

            mutated.add(one.swap(i, j));
        }

        return mutated;
    }
}
