package tsp;

import tspproblem.TSPProblem;

import java.util.Random;

public class Individual {
	
	public int[] permutation;
	public double cost;
	
	public Individual(int[] givenind)
	{
		permutation=givenind.clone();
	}
	
	public Individual(int ncities)
	{
		Random rand = new Random();
	    permutation = new int[ncities];
		int swapIndex, swapTemp;
		for (int i = 0; i < ncities; i++) {
			permutation[i] = i;
			swapIndex = rand.nextInt(i+1);
			swapTemp = permutation[i];
			permutation[i] = permutation[swapIndex];
			permutation[swapIndex] = swapTemp;
		}
	}
	
	public void calculateCost(TSPProblem tsp)
	{
		cost=tsp.cost(permutation);
		
	}
	
	public Individual clone()
	{
		Individual res= new Individual(this.permutation);
		res.cost=this.cost;	
		
		return res;
	}
}
