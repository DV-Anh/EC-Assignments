package mutation;

import java.util.*;

import population.*;

public class InversionMutation extends Mutation {
	public Random random = new Random();
	public int percent;
	public InversionMutation (int pcent) {percent=pcent;}

	public void mutate(Population population) {
		for (int i=0; i<population.size(); i++) {

			// Only mutate the required percent of the population
			if (percent<=random.nextInt(100)) continue;

			// Chooses the index at one end of the inversion
			// and the distance with wrap-around to the other end of the inversion
			// Refactored from previous method that may have had to call nextInt multiple times
			// and didn't use wrap-around
			int size = population.list.get(i).size();
			int position=random.nextInt(size);
			int distance=1+random.nextInt(size-2);

			// Inverts by swapping with wrap-around
			int temp;
			for (int j=0; j<(distance+1)>>1; j++) {
				temp=population.list.get(i).permutation[(position+j)%size];
				population.list.get(i).permutation[(position+j)%size]=population.list.get(i).permutation[(position+distance-j)%size];
				population.list.get(i).permutation[(position+distance-j)%size]=temp;
			}
		}
	}
}
