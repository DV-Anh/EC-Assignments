package individual;

import java.util.Collections;
import java.util.Random;
import tspproblem.*;

public class Individual implements Comparable<Individual> {
	public Random random = new Random();
	public int[] permutation;
	private int size;
	private double cost;

	public Individual(int sze) {
		size=sze;
		permutation=new int[size];
	}

	public Individual(Individual individual) {
		permutation=individual.permutation.clone();
		size=individual.size;
		cost=individual.cost;
	}

	@Override
	public int compareTo(Individual other) {
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

	public void evaluate(TSPProblem problem) {
		cost=problem.cost(permutation);
	}

	public int size() {return size;}

	public double cost() {return cost;}
}
