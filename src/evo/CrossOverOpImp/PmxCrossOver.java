package evo.CrossOverOpImp;

import evo.core.CrossOverOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PmxCrossOver implements CrossOverOp {
    private Random rand;
    private double probability = 1;

    public PmxCrossOver(long seed) {
        this.rand = new Random(seed);
    }

    public PmxCrossOver(double probability) {
        this.rand = new Random();
        this.probability = probability;
    }

    @Override
    public List<Individual> apply(List<Individual> parents) {
        List<Individual> offsprings = new ArrayList<>();

        for (int i = 0; i < parents.size() - 1; i += 2) {

            Individual p1 = parents.get(i);
            Individual p2 = parents.get(i + 1);
            if (this.rand.nextDouble() > this.probability) {
                offsprings.add(new Individual(p1.clonePerm()));
                offsprings.add(new Individual(p2.clonePerm()));
                continue;
            }

            offsprings.add(crossOver(p1, p2));
            offsprings.add(crossOver(p2, p1));
        }

        return offsprings;
    }

    private ArrayList<Integer> ntimes(int[] array, int val) {

        ArrayList<Integer> ind = new ArrayList<Integer>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] == val)
                ind.add(i);
        }
        return ind;
    }

    private int ismember(int[] array, int val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == val)
                return i;
        }
        return -1;
    }

    private Individual crossOver(Individual p1, Individual p2) {
        int n = p1.length();
        int[] p1perm = p1.clonePerm();
        int[] p2perm = p1.clonePerm();

        int i = rand.nextInt(n);
        int j = rand.nextInt(n);
        while (i == j)
            j = rand.nextInt(n);

        if (i > j) {
            int tmp = i;
            i = j;
            j = tmp;
        }

        int[] newperm = new int[n];
        for (int ii = 0; ii < n; ii++)
            newperm[ii] = p1perm[ii];
        for (int ii = i; ii <= j; ii++)
            newperm[ii] = p2perm[ii];


        int[] prt1 = new int[j - i + 1];
        int[] prt2 = new int[j - i + 1];
        for (int ii = i; ii <= j; ii++) {
            prt1[ii - i] = p2perm[ii];
            prt2[ii - i] = p1perm[ii];
        }

        for (int jj = 0; jj < prt1.length; jj++) {
            int tmp = prt1[jj];
            ArrayList<Integer> I = ntimes(newperm, tmp);
            while (I.size() > 1) {
                int jjj = ismember(prt1, tmp);

                for (int ii = 0; ii < I.size(); ii++)
                    if (I.get(ii) < i || I.get(ii) > j) {
                        newperm[I.get(ii)] = prt2[jjj];
                        tmp = newperm[I.get(ii)];
                    }
                I = ntimes(newperm, tmp);
            }
        }
        return new Individual(newperm);
    }
}
