package tspsolver;

import tspproblem.*;
import individual.*;

public class LocalSearchJump extends TSPSolver {

	void generateNeighbor(Individual solution, int i, int j) {
		int dir = j > i ? 1 : -1;
		int temp = solution.permutation[i];
		for (int k = i; k != j; k += dir) {
			solution.permutation[k] = solution.permutation[k + dir];
		}
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
					if(ileft == j) continue;
					int jleft = j - 1, jright = (j + 1) % size;
					// 3-opt (replacing 3 edges) and 2 neighbors since jump is asymmetrical
					double costChange1 = 0 - problem.distance(solution.permutation[i], solution.permutation[ileft])
							- problem.distance(solution.permutation[i], solution.permutation[iright])
							- problem.distance(solution.permutation[j], solution.permutation[jright])
							+ problem.distance(solution.permutation[ileft], solution.permutation[iright])
							+ problem.distance(solution.permutation[i], solution.permutation[j])
							+ problem.distance(solution.permutation[i], solution.permutation[jright]);
					if(costChange1 < 0){
						improving = true;
						generateNeighbor(solution, i, j);
					}
					double costChange2 = 0 - problem.distance(solution.permutation[j], solution.permutation[jleft])
							- problem.distance(solution.permutation[j], solution.permutation[jright])
							- problem.distance(solution.permutation[i], solution.permutation[ileft])
							+ problem.distance(solution.permutation[jleft], solution.permutation[jright])
							+ problem.distance(solution.permutation[i], solution.permutation[j])
							+ problem.distance(solution.permutation[j], solution.permutation[ileft]);
					if(costChange2 < 0) {
						improving = true;
						generateNeighbor(solution, j, i);
					}
				}
			}
		}
		solution.evaluate(problem);
		return solution.cost();
	}
}
