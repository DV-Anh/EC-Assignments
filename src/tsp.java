import tspproblem.*;
import tspsolver.*;
import mutation.*;
import population.*;
import selection.*;
import crossover.*;

// Compile:
// javac tsp.java

// Run:
// java tsp [OPTIONS] mapfilename

// Options:
// -r(int)		Repeat the method (int) number of times, eg: -r30
// -l2			Local search implemented with inversion (2-opt)
// -l3			Local search implemented with jump (3-opt)
// -l4			Local search implemented with exchange (4-opt)
// -p(int)		Population search with (int) number of individuals, eg: -p100
// -g(int)		Run population method for (int) number of generations, eg: -g10000
// -d(int)		Display the best population result every (int) generation, eg: -d1000
// -mn(int)		Insert mutation (int)% of the time
// -ms(int)		Swap mutation (int)% of the time
// -mv(int)		Inversion mutation (int)% of the time
// -sf			Fitness proportionate selection
// -st(int)		Tournament selection from (int)% of the population, eg: -st10
// -xc(int)		Cycle crossover (int)% of the time
// -xe(int)		Edge Recombination crossover (int)% of the time
// -xo(int)		Order crossover (int)% of the time
// -xp(int)		PMX crossover (int)% of the time

public class tsp {
	static final String INVALID="Invalid option ";
	static int repetitions=1;
	static TSPSolver solver;
	static ElitistSearch elitesearch = new ElitistSearch();

	public static void error(String message)
	// Prints error message and exits program
	{System.out.println("Error: "+message); System.exit(1);}

	public static void main(String[] args)
	// Runs search using command line parameters
	{
		// Read command line switches to set search parameters
		set_algorithm(args);

		// Read in map file
		TSPProblem problem = new TSPProblem(args[args.length-1]);

		// Run the algorithm, calculating and printing statistics
		double cost=0;
		double sum1=0;
		double sum2=0;
		double max=0;
		double min=Double.POSITIVE_INFINITY;
		for (int i=0; i<repetitions; i++) {
			try {cost=solver.search(problem);}
			catch(NullPointerException ex) {error("No valid algorithm selected");}
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
	// Reads in commmand line arguments and
	// sets the algorithm parameters
	{
		// Read through all but last argument
		// ensuring argument format is correct
		// before setting the appropriate parameter.
		// Last argument is map file name,
		// which is not read here.
		for (int i=0; i<args.length-1; i++) {
			// ensure argument starts with a dash
			if (args[i].charAt(0)!='-') error(INVALID+args[i]);

			// use switch-cases to parse argument
			switch(args[i].charAt(1)){
			case 'r': repetitions=get_option_value(2,args[i]); break;
			case 'g': elitesearch.generations=get_option_value(2,args[i]); break;
			case 'd': elitesearch.displayevery=get_option_value(2,args[i]); break;

			case 'l':
				switch(args[i].charAt(2)){
				case '2': solver = new LocalSearchInversion(); break;
				case '3': solver = new LocalSearchJump(); break;
				case '4': solver = new LocalSearchExchange(); break;
				default: error(INVALID+args[i]);
				}
				break;
			case 'p':
				solver = elitesearch;
				elitesearch.population_size=get_option_value(2,args[i]);
				break;
			case 'm':
				solver = elitesearch;
				switch(args[i].charAt(2)){
				case 'n': elitesearch.mutation=new InsertMutation(get_option_value(3,args[i])); break;
				case 's': elitesearch.mutation=new SwapMutation(get_option_value(3,args[i])); break;
				case 'v': elitesearch.mutation=new InversionMutation(get_option_value(3,args[i])); break;
				default: error(INVALID+args[i]);
				}
				break;
			case 's':
				solver = elitesearch;
				switch(args[i].charAt(2)){
				case 'f': elitesearch.selection=new FitnessProportionateSelection();break;
				case 't': elitesearch.selection=new TournamentSelection(get_option_value(3,args[i]));break;
				default: error(INVALID+args[i]);
				}
				break;
			case 'x':
				solver = elitesearch;
				switch(args[i].charAt(2)){
				case 'c': elitesearch.crossover=new CycleCrossOver(get_option_value(3,args[i]));break;
				case 'e': elitesearch.crossover=new EdgeCrossOver(get_option_value(3,args[i]));break;
				case 'o': elitesearch.crossover=new OrderCrossOver(get_option_value(3,args[i]));break;
				case 'p': elitesearch.crossover=new PMXCrossOver(get_option_value(3,args[i]));break;
				default: error(INVALID+args[i]);
				}
				break;					
			default: error(INVALID+args[i]);
			}
		}
	}

	public static int get_option_value(int position, String arg)
	// Returns integer value part of a command line argument
	// from the "position" of the "arg" string onwards
	{
		int value=0;
		try {
			value=Integer.parseInt(arg.substring(position, arg.length()));
		}
		catch (NumberFormatException ex) {
			error(INVALID+arg);
		}
		return value;
	}
}
