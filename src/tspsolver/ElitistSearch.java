package tspsolver;

import java.util.*;

import tspproblem.*;
import mutation.*;
import population.*;
import selection.*;
import crossover.*;
import population.*;
import individual.*;

public class ElitistSearch extends TSPSolver {
	public Random random = new Random();
	public int population_size;
	public int generations;
	public int displayevery=0;
	public Mutation mutation;
	public Selection selection;
	public CrossOver crossover;

	public double search(TSPProblem problem) {
		Population population = new Population(population_size, problem);
		for (int i=0; i<generations; i++) {
			Population parents=selection.choose(population, problem, 2);
			Population children=crossover.breed(parents, problem);
			children=mutation.mutate(children);
			population.add(children, problem);
			population.kill_worst(2);
			if (displayevery!=0 && (i+1)%displayevery==0) System.out.println((i+1)+" "+population.bestscore());
		}
		return population.bestscore();
	}
}
