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

	public ElitistSearch(TSPSolver solver)
	// Copy parameters from another solver
	// Required to make it easier to code front end
	{super(solver);}

	public double search(TSPProblem problem)
	// Performs an (n+2) elitist search for a selected number of generations
	// using interchangable parent selection, crossover, and mutation methods
	{
		// Check that we have a fully defined algorithm
		if (population_size<=0) error("Population size not set");
		if (generations<=0) error("Number of generations not set");
		if (selection==null) error("Selection type not set");
		if (crossover==null) error("CrossOver type not set");
		if (mutation==null) error("Mutation type not set");

		Population population = new Population(population_size, problem);
		for (int i=0; i<generations; i++) {

			// Choose 2 parents using chosen parent selection method
			Population parents=selection.choose(population, problem, 2);

			// Use chosen crossover method on the 2 parents to produce 2 children
			Population children=crossover.breed(parents, problem);

			// Use chosen mutation method on the 2 children
			mutation.mutate(children);

			// Evaluate and add the 2 children to the population
			population.eval_add(children, problem);

			// Kill the worst 2 members of the population
			population.kill_worst(2);

			// Regularly display progress if display progress switch has been set
			if (displayevery!=0 && (i+1)%displayevery==0) System.out.println((i+1)+" "+population.bestcost());
		}

		// Return the best cost found
		return population.bestcost();
	}
}
