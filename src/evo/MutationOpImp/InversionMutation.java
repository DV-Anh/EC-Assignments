package evo.MutationOpImp;

import evo.core.MutationOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InversionMutation implements MutationOp {
    private Random rand;
    private double probability = 1;

    public InversionMutation(double p) {
        this.probability = p;
        this.rand = new Random();
    }

    public InversionMutation(long seed) {
        this.rand = new Random(seed);
    }

    @Override
    public List<Individual> apply(List<Individual> p) {
        int permLen = p.get(0).length();
        List<Individual> mutated = new ArrayList<>();

        for (Individual one : p) {
            if (this.rand.nextDouble() > this.probability) {
                mutated.add(new Individual(one.clonePerm()));
                continue;
            }
            int i = this.rand.nextInt(permLen);
            int j = this.rand.nextInt(permLen);
            while (i == j)
                j = this.rand.nextInt(permLen);

            mutated.add(inverse(one, i, j));
        }

        return mutated;
    }

    private Individual inverse(Individual one, int i, int j) {
        int[] perm = one.clonePerm();

        int l = i > j ? j : i;
        int r = i > j ? i : j;
        while (l < r) {
            int t = perm[l];
            perm[l] = perm[r];
            perm[r] = t;
            l++;
            r--;
        }

        return new Individual(perm);
    }
}
