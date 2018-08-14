package tsp;

import tspproblem.TSPProblem;

import java.util.List;
import java.util.Random;

public class Population {
	
	public int npop;
	public Individual[] pop;
	public double bestcost;
	public Individual bestind;
	public Random rand = new Random();
	
	public Population(int npops, int ncities)
	{
		// creates a poplulation which has npop number of individual,
		// each individual creates by random and has ncities length
		// we used 2*npop length array since we need it for new born individuals.
		npop=npops;
		
		pop=new Individual[2*npop];
		
		for (int i=0;i<npop;i++)
		{
			pop[i]= new Individual(ncities);
		}
	}
	
	public Population(int npop)
	{
		pop=new Individual[npop];		
	}
	
	public void evaluatePop(TSPProblem tsp, int startIx, int endIx)
	{
		// evaluate each individual's cost
		// find best individual
				
		for (int i=startIx;i<endIx;i++)
		{
			pop[i].calculateCost(tsp);
		}		
	}
	
	public Population clone()
	{
		Population np=new Population(2*npop);
		for (int i=0;i<2*npop;i++)
		{
			np.pop[i]=new Individual(pop[i].permutation);
			np.pop[i].cost=pop[i].cost;
		}
		return np;		
		
	}

}
