package evo.CrossOverOpImp;

import evo.core.CrossOverOp;
import evo.core.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EdgeCrossOver implements CrossOverOp {
    private Random rand;
    private double probability = 1;

    public EdgeCrossOver(long seed) {
        this.rand = new Random(seed);
    }

    public EdgeCrossOver(double probability) {
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

    private Individual crossOver(Individual p1, Individual p2)
    {
        int n = p1.length();
        int[] p1perm = p1.clonePerm();
        int[] p2perm = p2.clonePerm();

        float[][] edge= new float[n][n];

        for (int i=0;i<n;i++)
            for (int j=0;i<n;i++)
                edge[i][j]=0;

        for (int i=0;i<n;i++)
        {
            int im1=i-1;
            if (im1<0)
                im1=im1+n;

            edge[p1perm[i]][p1perm[(im1)%n]]++;
            edge[p1perm[i]][p1perm[(i+1)%n]]++;
            edge[p2perm[i]][p2perm[(im1)%n]]++;
            edge[p2perm[i]][p2perm[(i+1)%n]]++;

        }
        // we decrease common nieghbour value from 2 to 0.9 to get it select
        // since in this manner, we just select min sum of edge row's
        for (int i=0;i<n;i++)
            for (int j=0;i<n;i++)
                if (edge[i][j]==2)
                    edge[i][j]=0.9f;


        int[] child=new int[n];
        int[] used=new int[n];

        for (int i=0;i<n;i++)
            used[i]=1;

        child[0]=rand.nextInt(n);
        used[child[0]]=0;

        for (int i=1;i<n;i++)
        {
            // remove all previous selected id from edge
            for (int j=0;j<n;j++)
            {
                edge[j][child[i-1]]=0;
                //edge[child[i-1]][j]=0;
            }
            // potantial candidates are edge[child[i-1]][:] which are greater than 0

            float[] s= new float[n];
            float mns=10;
            int mny=-1;
            for (int j=0;j<n;j++)
            {
                s[j]=0;
                if (edge[child[i-1]][j]>0)
                {
                    for (int k=0;k<n;k++)
                        s[j]=s[j]+edge[j][k];
                }
                if (s[j]>0 && s[j]<mns)
                {
                    mns=s[j];
                    mny=j;
                }
            }

            // not find any city, so select random from remaining list
            if (mny==-1)
            {
                int tmp=0;
                for (int j=0;j<n;j++)
                    tmp=tmp+used[j];
                int r=rand.nextInt(tmp)+1;

                int j=0;
                tmp=used[j];

                while(tmp!=r)
                {	j++;
                    tmp=tmp+used[j];
                }
                mny=j;
            }

            child[i]=mny;
            used[child[i]]=0;
        }

        return new Individual(child);
    }
}
