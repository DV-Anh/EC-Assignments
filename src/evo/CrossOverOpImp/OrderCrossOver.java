package evo.CrossOverOpImp;

import evo.core.CrossOverOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderCrossOver implements CrossOverOp {
    private Random rand;
    private double probability = 1;

    public OrderCrossOver(long seed) {
        this.rand = new Random(seed);
    }

    public OrderCrossOver(double probability) {
        this.rand = new Random();
        this.probability = probability;
    }

    @Override
    public List<Individual> apply(List<Individual> parents) {
        List<Individual> offsprings = new ArrayList<>();

        for (int i = 0; i < parents.size() - 1; i += 2) {

            Individual p1 = parents.get(i);
            Individual p2 = parents.get(i+1);
            if (this.rand.nextDouble() > this.probability) {
                offsprings.add(new Individual(p1.clonePerm()));
                offsprings.add(new Individual(p2.clonePerm()));
                continue;
            }
            int n = p1.length();
            int l = this.rand.nextInt(n);
            int r = this.rand.nextInt(n);
            while (l == r)
                r = this.rand.nextInt(n);
            if (l > r) {
                int t = l;
                l = r;
                r = t;
            }

            offsprings.add(crossOver(p1, p2, l, r));
            offsprings.add(crossOver(p2, p1, l, r));
        }

        return offsprings;
    }

    private int ismember(int[] array,int val)
    {
        for (int i=0;i<array.length;i++)
        {
            if (array[i]==val)
                return i;
        }
        return -1;
    }

    private Individual crossOver(
            Individual p1, Individual p2,
            int i, int j) {

        int[] p1Perm = p1.clonePerm();
        int[] p2Perm = p2.clonePerm();

        int n = p1Perm.length;

        int[] newperm = new int[n];
        for (int ii=0;ii<n;ii++)
            newperm[ii]=-1;

        if (i<j)
        {
            for (int ii=i;ii<=j;ii++)
                newperm[ii] = p1Perm[ii];

            int t=j+1;
            for (int ii=j+1;ii<j+1+n;ii++)
            {
                if (ismember(newperm, p2Perm[ii%n])<0)
                {
                    newperm[t%n] = p2Perm[ii%n];
                    t++;
                }
            }
        }
        return new Individual(newperm);

    }
}
