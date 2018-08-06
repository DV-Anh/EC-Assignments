package evo.CrossOverOpImp;

import evo.CrossOverOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LujunW on 2018/8/6.
 */
public class OrderCrossOver implements CrossOverOp {

    @Override
    public List<Individual> apply(List<Individual> parents) {
        List<Individual> offsprings = new ArrayList<>();

        for (int i = 0; i < parents.size() - 1; i += 2) {
            Individual p1 = parents.get(i);
            Individual p2 = parents.get(i+1);

            offsprings.add(crossOver(p1, p2));
            offsprings.add(crossOver(p2, p1));
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

    private Individual crossOver(Individual p1, Individual p2) {
        Random rand = new Random();

        int[] p1Perm = p1.clonePerm();
        int[] p2Perm = p2.clonePerm();

        int n = p1Perm.length;
        int i = rand.nextInt(n);
        int j = rand.nextInt(n);
        while (i==j)
            j = rand.nextInt(n);

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
        else
        {
            for (int ii=j;ii<=i;ii++)
                newperm[ii] = p1Perm[ii];

            int t=j-1;
            for (int ii=j-1;ii>j-1-n;ii--)
            {
                int mii=ii;
                if (ii<0)
                    mii=ii+n;

                if (ismember(newperm, p2Perm[mii%n])<0)
                {
                    if (t<0)
                        t=t+n;

                    newperm[t%n] = p2Perm[mii%n];
                    t--;
                }
            }
        }
        return new Individual(newperm);

    }
}
