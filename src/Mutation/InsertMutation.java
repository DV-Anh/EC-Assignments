package Mutation;

import tsp.Individual;

public class InsertMutation extends Mutation
{	
	// insert mutation creator
	public InsertMutation()
	{
		super();
	}
	
	// insert mutation algorithm
	public Individual apply(Individual p)
	{
		int n=p.permutation.length;
		
		int i=rand.nextInt(n);
		int j=rand.nextInt(n);
		while (i==j)
			j=rand.nextInt(n);
		
		
		if (i<j)
			{
				int tmp=p.permutation[j];
				for (int ii=j-1;ii>=i;ii--)
					p.permutation[ii+1]=p.permutation[ii];
				p.permutation[i+1]=tmp;			
			}
			else
			{
				int tmp=p.permutation[j];
				for (int ii=j+1;ii<i;ii++)
					p.permutation[ii-1]=p.permutation[ii];
				p.permutation[i-1]=tmp;			
			}
		return p;
	}
	
	

}
