package tspsolver;

import tspproblem.*;
import individual.*;

public class LocalSearchExchange extends TSPSolver {
	void generateNeighbor(Individual solution, int i, int j) {
		int temp = solution.permutation[i];
		solution.permutation[i] = solution.permutation[j];
		solution.permutation[j] = temp;
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
					int jleft = j - 1, jright = (j + 1) % size;
					// 4-opt (replacing 4 edges)
					double costChange = 0;
					if (iright == j) {
						costChange += problem.distance(solution.permutation[j],solution.permutation[ileft])
						+ problem.distance(solution.permutation[i],solution.permutation[jright])
						- problem.distance(solution.permutation[i],solution.permutation[ileft])
						- problem.distance(solution.permutation[j],solution.permutation[jright]);
					}
					else if(ileft == j) {
						costChange += problem.distance(solution.permutation[j],solution.permutation[iright])
								+ problem.distance(solution.permutation[i],solution.permutation[jleft])
								- problem.distance(solution.permutation[i],solution.permutation[iright])
								- problem.distance(solution.permutation[j],solution.permutation[jleft]);
					}
					else {
						costChange += problem.distance(solution.permutation[i],solution.permutation[jleft])
								+ problem.distance(solution.permutation[j],solution.permutation[iright])
								+ problem.distance(solution.permutation[j],solution.permutation[ileft])
								+ problem.distance(solution.permutation[i],solution.permutation[jright])
								- problem.distance(solution.permutation[i],solution.permutation[ileft])
								- problem.distance(solution.permutation[j],solution.permutation[jright])
								- problem.distance(solution.permutation[i],solution.permutation[iright])
								- problem.distance(solution.permutation[j],solution.permutation[jleft]);
					}
					if (costChange < 0) {
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
