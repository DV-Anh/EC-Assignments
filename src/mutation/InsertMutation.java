package mutation;

import java.util.*;

import population.*;

public class InsertMutation extends Mutation {
	public Random random = new Random();
	public int percent;
	public InsertMutation (int pcent) {percent=pcent;}

	public void mutate(Population population) {
		for (int i=0; i<population.size(); i++) {

			// Only mutate the required percent of the population
			if (percent<=random.nextInt(100)) continue;

			// Choose the index to move and the distance to move it with wrap-around
			int size = population.list.get(i).size();
			int position=random.nextInt(size);
			int distance=1+random.nextInt(size-2);

			// Move the chosen index forward along the chosen distance with wrap-around
			int temp = population.list.get(i).permutation[position];
			for (int j=0; j<distance; j++)
				population.list.get(i).permutation[(position+j)%size]=population.list.get(i).permutation[(position+j+1)%size];
			population.list.get(i).permutation[(position+distance)%size]=temp;
		}
	}
}
