package selection;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public class FitnessProportionateSelection extends Selection {
	public Random random = new Random();

	public Population choose(Population population, TSPProblem problem, int size) {
		Population parents=new Population();

		// Ensure that the population being chosen from is sorted
		population.sort();

		// Create a list that will contain the indexes for the parents chosen
		List<Integer> indexList = new ArrayList<Integer>();

		// Determine the probability divisor for the population
		double divisor=0;
		double worstcost=population.worstcost();
		for (int index=0; index<population.size(); index++)
			divisor+=worstcost-population.list.get(index).cost();

		// Get the required number of parents
		while (parents.size()<size) {

			// Select a random position in the probability distribution range (0,1)
			double selector=random.nextDouble();

			// Find the index of the individual associated with this probability choice
			double cumulative=0;
			int index=-1;
			Individual chosen;
			do {
				index++;
				chosen=population.list.get(index);
				cumulative+=worstcost-chosen.cost();
			} while (cumulative/divisor<selector);

			// If the individual has not already been added to the parent's list
			// Add the individual to the parents list and
			// add it to the list of individuals that have been added
			if (!Arrays.asList(indexList).contains(index)){
				parents.eval_add(new Individual(chosen), problem);
				indexList.add(index);
			}
		}
		return parents;
	}
}
