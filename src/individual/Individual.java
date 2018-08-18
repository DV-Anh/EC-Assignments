package individual;

import java.util.Collections;
import java.util.Random;
import tspproblem.*;

public class Individual implements Comparable<Individual> {
	public Random random = new Random();
	public int[] permutation;
	private int size;
	private double cost;

	public Individual(int sze)
	// Create a new individual with permutation size "sze"
	{
		size=sze;
		permutation=new int[size];
	}

	public Individual(Individual individual)
	// Clone an individual
	{
		permutation=individual.permutation.clone();
		size=individual.size;
		cost=individual.cost;
	}

	@Override
	public int compareTo(Individual other)
	// Create comparison for sorting so that lowest score is at entry 0
	{
		return Double.compare(this.cost,other.cost);
	}

	public void randomise()
	// Insertion shuffle
	{
		int index;
		for (int i=0; i<size; i++) {
			index = random.nextInt(i+1);
			permutation[i] = permutation[index];
			permutation[index] = i;
		}
	}

	public void evaluate(TSPProblem problem)
	// Evaluate individual against "problem" tspproblem
	{
		cost=problem.cost(permutation);
	}

	public int size()
	// Return size of individual
	{return size;}

	public double cost()
	// Return cost of individual
	{return cost;}
}
