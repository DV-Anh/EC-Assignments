package tspsolver;

import java.util.*;

import tspproblem.*;
import mutation.*;
import population.*;
import selection.*;
import crossover.*;
import population.*;
import individual.*;

public class TSPSolver
// Default TSPSolver that allows parameters to be set
// so parameters can be copied to other TSPSolvers
// but returns error if search is attempted
{
	public int population_size;
	public int generations;
	public int displayevery;
	public Mutation mutation;
	public Selection selection;
	public CrossOver crossover;

	public static void error(String message)
	// Prints error message and exits program
	{System.out.println("Error: "+message); System.exit(1);}

	public TSPSolver(){}

	public TSPSolver(TSPSolver solver)
	// Copy parameters from another solver
	// Makes it easier to code front end
	{
		population_size=solver.population_size;
		generations=solver.generations;
		displayevery=solver.displayevery;
		mutation=solver.mutation;
		selection=solver.selection;
		crossover=solver.crossover;
	}

	public double search(TSPProblem problem) {
		System.out.println("Error: No valid algorithm selected");
		System.exit(1);
		return 0;
	}
}
