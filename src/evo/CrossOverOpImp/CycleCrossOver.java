package evo.CrossOverOpImp;

import evo.core.CrossOverOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CycleCrossOver implements CrossOverOp {
    private Random rand;
    private double probability = 1;

    public CycleCrossOver(long seed) {
        this.rand = new Random(seed);
    }

    public CycleCrossOver(double probability) {
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

            offsprings.add(crossOver(p1, p2));
            offsprings.add(crossOver(p2, p1));
        }

        return offsprings;
    }

    private ArrayList<Integer> ntimes(int[] array,int val)
    {

        ArrayList<Integer> ind = new ArrayList<Integer>();

        for (int i=0;i<array.length;i++)
        {
            if (array[i]==val)
                ind.add(i);
        }
        return ind;
    }

    private Individual crossOver(Individual p1, Individual p2)
    {
        int n = p1.length();
        int[] p1perm = p1.clonePerm();
        int[] p2perm = p2.clonePerm();

        int i=rand.nextInt(n);
        int j=rand.nextInt(n);
        while (i==j)
            j=rand.nextInt(n);

        // create used vector from stating parent
        Boolean[] used=new Boolean[n];
        int[] child=new int[n];
        for (int ii=0;ii<n;ii++)
        {
            used[ii]=false;
            child[ii]=-1;
        }

        // use first element
        used[0]=true;
        child[0]=p1perm[0];

        int status=1;
        int ii=0;
        while (status==1)
        {
            ArrayList<Integer> I=ntimes(p2perm,child[ii]);
            if (I.size()>0 && !used[I.get(0)])
            {
                child[I.get(0)]=p1perm[I.get(0)];
                used[I.get(0)]=true;
                ii=I.get(0);
            }
            else
                status=0;

        }
        for (ii=0;ii<n;ii++)
        {
            if (!used[ii])
                child[ii]=p2perm[ii];
        }

        return new Individual(child);

    }
}
