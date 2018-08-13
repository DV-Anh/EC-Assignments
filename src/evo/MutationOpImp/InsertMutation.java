package evo.MutationOpImp;

import evo.core.MutationOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertMutation implements MutationOp {
    private Random rand;
    private double probability = 1;

    public InsertMutation(double p) {
        this.probability = p;
        this.rand = new Random();
    }

    public InsertMutation(long seed) {
        this.rand = new Random(seed);
    }

    @Override
    public List<Individual> apply(List<Individual> p) {
        List<Individual> mutated = new ArrayList<>();

        for (Individual one : p) {
            if (this.rand.nextDouble() > this.probability) {
                mutated.add(new Individual(one.clonePerm()));
                continue;
            }
            mutated.add(insert(one));
        }

        return mutated;
    }


    private Individual insert(Individual p) {
        int n = p.length();
        int[] perm = p.clonePerm();

        int i=rand.nextInt(n);
        int j=rand.nextInt(n);
        while (i==j)
            j=rand.nextInt(n);


        if (i<j)
        {
            int tmp=perm[j];
            for (int ii=j-1;ii>=i;ii--)
                perm[ii+1]=perm[ii];
            perm[i+1]=tmp;
        }
        else
        {
            int tmp=perm[j];
            for (int ii=j+1;ii<i;ii++)
                perm[ii-1]=perm[ii];
            perm[i-1]=tmp;
        }
        return new Individual(perm);
    }
}