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
		
		int md=(i+j)/2;
		
		if (i>j)
		{
			int tmp=i;
			i=j;
			j=tmp;
		}
		
		for (int ii=0;ii<=md-i;ii++)
		{
			int tmp=p.permutation[i+ii];
			p.permutation[i+ii]=p.permutation[j-ii];
			p.permutation[j-ii]=tmp;
		}
		return p;
	}
	
	

}

