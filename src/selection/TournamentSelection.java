package selection;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public class TournamentSelection extends Selection {
	public Random random = new Random();
	public int percent;

	public TournamentSelection(int pcent) {percent=pcent;}

	public Population choose(Population population, TSPProblem problem, int size)
		// Chooses a subset from the total population
		// then returns the best "size" number of individuals
		// from that subset
	{
		Population parents = new Population();

		// Create a list of integers from 0 to population size
		List<Integer> intList = new ArrayList<Integer>();
		for (int i=0; i<population.size(); i++) intList.add(i);

		// Determine the number of competitors in the competition
		// Either required percent of population, rounded up
		// or at least the number of individuals required to be
		// returned (which is int size)
		int competitors=(population.size()*percent+50)/100;
		competitors=(competitors<size)? size : competitors;

		// Randomly and without replacement add competitors to the parent list
		// by randomly choosing and removing elements from intList
		int index,chosen;
		for (int i=0; i<competitors; i++) {
			index=random.nextInt(intList.size());
			chosen=intList.get(index);
			intList.remove(index);
			parents.eval_add(new Individual(population.list.get(chosen)), problem);
		}

		// Cull the parents list down to "size" number required
		parents.kill_worst(competitors-size);
		return parents;
	}
}
