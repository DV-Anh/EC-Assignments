package tsp;

import java.util.Random;

public class Individual {
	
	public int[] permutation;
	public double cost;
	public static Random rand = new Random();
	
	public Individual(int[] givenind)
	{
		permutation=givenind.clone();
	}
	
	public Individual(int ncities)
	{
		Random rand = new Random();
	    permutation = new int[ncities];
	    for(int i=0;i<ncities;i++)
	      permutation[i] = i;
	    
	    for(int i=ncities-1;i>=1;i--) {
	      int m = rand.nextInt(i+1);
	      if(m != i) {
	        int tmp = permutation[i];
	        permutation[i] = permutation[m];
	        permutation[m] = tmp;
	      }	      
	    }	    
	}
	
	public void calculateCost(TSPLib tsp)
	{
		cost=tsp.evaluateCandidate(permutation);
		
	}
	
	public Individual clone()
	{
		Individual res= new Individual(this.permutation);
		res.cost=this.cost;	
		
		return res;
	}
}
