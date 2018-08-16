package mutation;

import java.util.*;

import population.*;

public class SwapMutation extends Mutation {
	public Random random = new Random();
	public int percent;

	public SwapMutation (int pcent) {percent=pcent;}

	public void mutate(Population population) {
		for (int i=0; i<population.size(); i++) {

			// Only mutate the required percent of the population
			if (percent<=random.nextInt(100)) continue;

			// Choose one index and the distance to the next index with wrap-around
			// Refactored from previous method that may have had to call nextInt multiple times
			int size = population.list.get(i).size();
			int position=random.nextInt(size);
			int distance=1+random.nextInt(size-2);

			// Swap two elements for this individual
			int temp = population.list.get(i).permutation[position];
			population.list.get(i).permutation[position]=population.list.get(i).permutation[(position+distance)%size];
			population.list.get(i).permutation[(position+distance)%size]=temp;
		}
	}
}
