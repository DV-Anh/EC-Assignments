import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.*; 
import tspproblem.*;
import localsearch.*;

// Compile:
// javac tsp.java

// Run:
// java tsp [OPTIONS] mapfilename

// Options:
// -r(int)		repeat the method (int) number of times, eg: -r30
// -p(int)		create a population of (int) number of individuals, eg: -p100
// -g(int)		run the method for (int) number of generations, eg: -g10000
// -lj			local search implemented with jump
// -le			local search implemented with exchange
// -l2			local search implemented with 2-opt neighbourhood
// -mn			Insert mutation
// -ms			Swap mutation
// -mv			Inversion mutation
// -xo			Order crossover
// -xp			PMX crossover
// -xc			Cycle crossover
// -xe 			Edge Recombination crossover
// -sf			Fitness-proportional selection
// -st			Tournament selection
// -se			Elitism selection

public class tsp {
	// Parameters for defining the algorithm to use
	static char algorithm_type='.';
	static int repetitions=1;
	static int population_size=1;
	static int generations=1;
	static char mutation_type;
	static char crossover_type;
	static char selection_type;

	public static void error(String message)
	// Method written by: Clint Gamlin (a1038415)
	// Prints error message and exits program
	{System.out.println("Error: "+message); System.exit(1);}

	public static void main(String[] args)
	// Method written by: Clint Gamlin (a1038415)
	{
		TSPProblem problem = new TSPProblem(args[args.length-1]);
		set_algorithm(args);
		
		double cost=0;
		double sum1=0;
		double sum2=0;
		double max=0;
		double min=Double.POSITIVE_INFINITY;
		LocalSearch search;
		for (int i=0; i<repetitions; i++) {
			switch(algorithm_type) {
				case 'j': // jump
					search = new LocalSearchJump();
					cost = problem.cost(search.search(problem));
					break;
				case 'e': // exchange
					search = new LocalSearchExchange();
					cost = problem.cost(search.search(problem));
					break;
				case '2': // 2-opt neighbourhood
					search = new LocalSearchInversion();
					cost = problem.cost(search.search(problem));
					break;
				case 'p': // population based
				case 'i': // InverOver
				break;
			default: error("No valid algorithm type selected");
			}
			if (cost<min) min=cost;
			if (cost>max) max=cost;
			sum1+=cost;
			sum2+=cost*cost;
		}
		double avg=sum1/repetitions;
		double std=Math.sqrt(sum2/repetitions-avg*avg);
		System.out.println("Min: "+min);
		System.out.println("Max: "+max);
		System.out.println("Avg: "+avg);
		System.out.println("Std: "+std);
	}

	public static void set_algorithm(String[] args)
	// Method written by: Clint Gamlin (a1038415)
	// Reads in commmand line arguments and
	// sets the algorithm parameters
	// TODO: Add required options for InverOver
	{
		// Read through all but last argument
		// ensuring argument format is correct
		// before setting the appropriate parameter.
		// Last argument is map file name,
		// which is not read here.
		for (int i=0; i<args.length-1; i++) {
			// ensure argument starts with a dash
			if (args[i].charAt(0)!='-') error("Invalid option "+args[i]);

			// use switch-cases to parse argument
			switch(args[i].charAt(1)){
			case 'f': algorithm_type='f'; break;
			case 'd': algorithm_type='d'; break;
			case 'l':
				switch(args[i].charAt(2)){
				case 'j': algorithm_type='j'; break;
				case 'e': algorithm_type='e'; break;
				case '2': algorithm_type='2'; break;
				default: error("Invalid option "+args[i]);
				}
				break;
			case 'm':
				algorithm_type='p';
				switch(args[i].charAt(2)){
				case 'n': mutation_type='n'; break;
				case 's': mutation_type='s'; break;
				case 'v': mutation_type='v'; break;
				default: error("Invalid option "+args[i]);
				}
				break;
			case 'x':
				algorithm_type='p';
				switch(args[i].charAt(2)){
				case 'o': crossover_type='o'; break;
				case 'p': crossover_type='p'; break;
				case 'c': crossover_type='c'; break;
				case 'e': crossover_type='e'; break;				
				default: error("Invalid option "+args[i]);
				}
				break;
			case 's':
				algorithm_type='p';
				switch(args[i].charAt(2)){
				case 'f': selection_type='f'; break;
				case 't': selection_type='t'; break;
				case 'e': selection_type='e'; break;
				default: error("Invalid option "+args[i]);
				}
				break;
			case 'r': repetitions=get_option_value(2,args[i]); break;
			case 'p': population_size=get_option_value(2,args[i]); break;
			case 'g': generations=get_option_value(2,args[i]); break;						
			default: error("Invalid option "+args[i]);
			}
		}
	}

	public static int get_option_value(int position, String arg)
	// Method written by: Clint Gamlin (a1038415)
	// Returns integer value part of a command line argument
	// TODO: catch and return errors
	{
		return Integer.parseInt(arg.substring(position, arg.length()));
	}
}
