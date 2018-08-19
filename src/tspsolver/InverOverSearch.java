package tspsolver;

import java.util.*;

import tspproblem.*;
import mutation.*;
import population.*;
import selection.*;
import crossover.*;
import population.*;
import individual.*;

public class InverOverSearch extends TSPSolver {
	public Random random = new Random();

	public InverOverSearch(TSPSolver solver)
	// Copy parameters from another solver
	// Required to make it easier to code front end
	{super(solver);}

	public static void error(String message)
	// Prints error message and exits program
	{System.out.println("Error: "+message); System.exit(1);}

	public double search(TSPProblem problem)
	// Performs an (n+2) elitist search for a selected number of generations
	// using interchangable parent selection, crossover, and mutation methods
	{
		// Check that we have a fully defined algorithm
		if (population_size<=0) error("Population size not set");
		if (generations<=0) error("Number of generations not set");

		Population population = new Population(population_size, problem);
		for (int generation=0; generation<generations; generation++) {
			for (int popindex=0; popindex<population_size; popindex++) {
				Individual child = new Individual(population.list.get(popindex));
				int position,distance;
				while (true) {
					if (random.nextDouble()<mutation.percent) {
						// Randomly select two cities in the child
						// by position of the first and
						// distance in the list to the second
						position=random.nextInt(child.size());
						distance=1+random.nextInt(child.size()-2);
					}
					else {
						// Randomly select a city in child
						position=random.nextInt(child.size());
						int city1=child.permutation[position];
						// Find the city next to the above chosen city
						// in a randomly chosen different population member
						int temp;
						temp=popindex;
						while (temp==popindex) temp=random.nextInt(population_size);
						Individual parent=population.list.get(temp);
						temp=0;
						while (parent.permutation[temp]!=city1) temp++;
						int city2=parent.permutation[(temp+1)%parent.size()];
						// Find the distance between city1 and city2 in child
						distance=1;
						while (child.permutation[(position+distance)%child.size()]!=city2) distance++;
					}
					// Exit infinite loop if ends to swap over are next to each other
					if (distance==1 || distance==child.size()-1) break;
					// Move distance and position so inversion will make 
					// city1 and city2 end up next to each other
					// with city1 staying in place
					position=(position+1)%child.size();
					distance--;
					// Perform inversion and evaluate child
					mutation.invert(child, position, distance);
					child.evaluate(problem);
				}
				// Replace parent with child if child is better than parent
				if (child.cost()<population.list.get(popindex).cost()) {
					population.list.set(popindex,child);
				}
			}
		}
		return population.bestcost();
	}
}
