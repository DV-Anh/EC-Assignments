package Mutation;

import tsp.Individual;

public class SwapMutation extends Mutation
{
	// swap mutation creator
	public SwapMutation()
	{
		super();
	}
	
	// wap mutation algorithm
	public Individual apply(Individual p)
	{
		int n=p.permutation.length;
		
		int i=rand.nextInt(n);
		int j=rand.nextInt(n);
		while (i==j)
			j=rand.nextInt(n);
		
		int tmp=p.permutation[i];
		p.permutation[i]=p.permutation[j];
		p.permutation[j]=tmp;
		return p;
	}
	
	

}
