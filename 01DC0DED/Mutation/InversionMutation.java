package Mutation;

import tsp.Individual;

public class InversionMutation extends Mutation
{
	// inversion mutation creator
	public InversionMutation()
	{
		super();
	}
	// inversion mutation algorithm
	public Individual apply(Individual p)
	{
		int n=p.permutation.length;
		
		int i=rand.nextInt(n);
		int j=rand.nextInt(n);
		while (i==j)
			j=rand.nextInt(n);
		
		if (i>j)
		{
			int tmp=i;
			i=j;
			j=tmp;
		}
		// Use 2 indexes moving opposite direction until touching/crossing each other
		for (int ii=i,jj=j;ii<jj;ii++,jj--)
		{
			int tmp=p.permutation[ii];
			p.permutation[ii]=p.permutation[jj];
			p.permutation[jj]=tmp;
		}
		return p;
	}
	public int[] apply1(Represent.Individual p)
	{
		int n=p.getPermutation().length;

		int i=rand.nextInt(n);
		int j=rand.nextInt(n);
		while (i==j)
			j=rand.nextInt(n);

		if (i>j)
		{
			int tmp=i;
			i=j;
			j=tmp;
		}
		// Use 2 indexes moving opposite direction until touching/crossing each other
		int[] temp=p.getPermutation();
		for (int ii=i,jj=j;ii<jj;ii++,jj--)
		{
			int tmp=temp[ii];
			temp[ii]=temp[jj];
			temp[jj]=tmp;
		}
		return temp;
	}



}

