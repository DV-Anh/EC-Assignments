package individual;

import java.util.Random;
import tspproblem.*;

public class Individual implements Comparable<Individual> {
	public Random random = new Random();
	public int[] permutation;
	public int size;
	public double cost;

	public Individual(int sze) {
		// Method written by: Clint Gamlin (a1038415)
		size=sze;
		permutation=new int[size];
	}

	@Override
	public int compareTo(Individual other) {
		// Method written by: Clint Gamlin (a1038415)
		return Double.compare(this.cost,other.cost);
	}

	public void randomise() {
		// Method written by: Clint Gamlin (a1038415)
		int index;
		for (int i=0; i<size; i++) {
			index = random.nextInt(i+1);
			permutation[i] = permutation[index];
			permutation[index] = i;
		}
	}

	public void evaluate(TSPProblem problem) {
		// Method written by: Clint Gamlin (a1038415)
		cost=problem.cost(permutation);
	}

	public int size() {return size;}
}
