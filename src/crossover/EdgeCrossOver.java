package crossover;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public class EdgeCrossOver extends CrossOver {
	public Random random = new Random();
	public int percent;

	public EdgeCrossOver(int pcent) {percent=pcent;}

	public Population breed(Population parents, TSPProblem problem)
	// Perform Edge Crossover on every pair of individuals in the parent population
	// with a set percentage chance that the pair will or wont crossover
	{
		Population children = new Population();

		for (int i = 0; i<parents.size()-1; i +=2) {
			Individual parent1=new Individual(parents.list.get(i));
			Individual parent2=new Individual(parents.list.get(i+1));

			// Make only the required percent of the population crossover
			if (percent<=random.nextInt(100)){
				children.eval_add(parent1, problem);
				children.eval_add(parent2, problem);
				continue;
			}

			// Perform the actual crossover and add individuals to children population
			children.eval_add(crossOver(parent1, parent2), problem);
			children.eval_add(crossOver(parent2, parent1), problem);
		}
		return children;
	}

	private Individual crossOver(Individual parent1, Individual parent2) {
		int size = parent1.size();
		Individual child = new Individual(size);
		int[] used=new int[size];

		float[][] edge= new float[size][size];

		for (int i=0;i<size;i++)
			for (int j=0;i<size;i++)
				edge[i][j]=0;

		for (int i=0;i<size;i++) {
			int im1=i-1;
			if (im1<0)
				im1=im1+size;

			edge[parent1.permutation[i]][parent1.permutation[(im1)%size]]++;
			edge[parent1.permutation[i]][parent1.permutation[(i+1)%size]]++;
			edge[parent2.permutation[i]][parent2.permutation[(im1)%size]]++;
			edge[parent2.permutation[i]][parent2.permutation[(i+1)%size]]++;
		}

		// we decrease common nieghbour value from 2 to 0.9 to get it select
		// since in this manner, we just select min sum of edge row's
		for (int i=0;i<size;i++)
			for (int j=0;i<size;i++)
				if (edge[i][j]==2)
					edge[i][j]=0.9f;

		for (int i=0;i<size;i++)
			used[i]=1;

		child.permutation[0]=random.nextInt(size);
		used[child.permutation[0]]=0;

		for (int i=1;i<size;i++) {
			// remove all previous selected id from edge
			for (int j=0;j<size;j++) {
				edge[j][child.permutation[i-1]]=0;
			}
			// potantial candidates are edge[child.permutation[i-1]][:] which are greater than 0

			float[] s= new float[size];
			float mns=10;
			int mny=-1;
			for (int j=0;j<size;j++) {
				s[j]=0;

				if (edge[child.permutation[i-1]][j]>0)
					for (int k=0;k<size;k++)
						s[j]=s[j]+edge[j][k];

				if (s[j]>0 && s[j]<mns) {
					mns=s[j];
					mny=j;
				}
			}

			// not find any city, so select random from remaining list
			if (mny==-1) {
				int tmp=0;
				for (int j=0;j<size;j++)
					tmp=tmp+used[j];
				int r=random.nextInt(tmp)+1;

				int j=0;
				tmp=used[j];

				while(tmp!=r) {
					j++;
					tmp=tmp+used[j];
				}
				mny=j;
			}

			child.permutation[i]=mny;
			used[child.permutation[i]]=0;
		}
		return child;
	}
}