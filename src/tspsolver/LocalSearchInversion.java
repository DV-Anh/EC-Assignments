package tspsolver;

import tspproblem.*;
import individual.*;

public class LocalSearchInversion extends TSPSolver {

	public LocalSearchInversion(TSPSolver solver)
	// Copy parameters from another solver
	// Required to make it easier to code front end
	{super(solver);}

	void generateNeighbor(Individual solution, int i, int j) {
		for (int k = i, l = j; k < l; k++, l--) {
			int temp = solution.permutation[k];
			solution.permutation[k] = solution.permutation[l];
			solution.permutation[l] = temp;
		}
	}

	public double search(TSPProblem problem) {
		int size = problem.size;
		Individual solution=new Individual(problem.size);
		solution.randomise();
		if (size<=2) {
			solution.evaluate(problem);
			return solution.cost();
		}

		boolean improving=true;
		while (improving) {
			improving = false;
			// Iterate through all neighbors and pick the best one:
			for (int i = 0; i < size - 1; i++) {
				int ileft = (i - 1 + size) % size, iright = i + 1;
				for (int j = i + 1; j < size; j++) {
					if(ileft == j) continue;
					int jleft = j - 1, jright = (j + 1) % size;
					// 2-opt (replacing 2 edges)
					double costChange = 0 - problem.distance(solution.permutation[i], solution.permutation[ileft])
							- problem.distance(solution.permutation[j], solution.permutation[jright])
							+ problem.distance(solution.permutation[i], solution.permutation[jright])
							+ problem.distance(solution.permutation[j], solution.permutation[ileft]);
					if(costChange < -0.00000000000001){
						improving = true;
						generateNeighbor(solution, i, j);
					}
				}
			}
		}
		solution.evaluate(problem);
		return solution.cost();
	}
}
