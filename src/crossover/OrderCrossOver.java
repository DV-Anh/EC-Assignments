package crossover;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public class OrderCrossOver extends CrossOver {
	public Random random = new Random();
	public int percent;

	public OrderCrossOver(int pcent) {percent=pcent;}

	public Population breed(Population parents, TSPProblem problem)
	// Perform Order Crossover on every pair of individuals in the parent population
	// with a set percentage chance that the pair will or wont crossover
	{
		Population children = new Population();

		for (int i = 0; i<parents.size()-1; i +=2) {
			Individual parent1=new Individual(parents.list.get(i));
			Individual parent2=new Individual(parents.list.get(i+1));

			// Make only the required percent of the population crossover
			if (percent<=random.nextInt(100)) {
				children.eval_add(parent1, problem);
				children.eval_add(parent2, problem);
				continue;
			}

			// Choose two different indexes for the crossover
			// ensuring index1 < index2 by swapping if needed
			int size=parent1.size();
			int index1=random.nextInt(size);
			int index2=index1;
			while (index2==index1) index2=random.nextInt(size);
			if (index1>index2) {
				int temp=index1;
				index1=index2;
				index2=temp;
			}

			// Perform the actual crossover and add individuals to children population
			children.eval_add(crossOver(parent1, parent2, index1, index2), problem);
			children.eval_add(crossOver(parent2, parent1, index1, index2), problem);
		}
		return children;
	}

	private Individual crossOver(Individual parent1, Individual parent2, int index1, int index2)
	// Method performs the Order Crossover operation
	{
		int size=parent1.size();
		Individual child = new Individual(size);

		for (int index=index1; index<=index2; index++)
			child.permutation[index] = parent1.permutation[index];

		int pointer=index2+1;
		for (int index=index2+1; index<index2+size+1; index++) {
			if (!Arrays.asList(child.permutation).contains(parent2.permutation[index%size])) {
				child.permutation[pointer%size] = parent2.permutation[index%size];
				pointer++;
			}
		}
		return child;
	}
}
