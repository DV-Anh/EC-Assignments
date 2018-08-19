package mutation;

import java.util.*;

import population.*;
import individual.*;

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
			Individual individual=population.list.get(i);
			int size=individual.size();
			int position=random.nextInt(size);
			int distance=1+random.nextInt(size-2);

			invert(individual, position, distance);
			}
		}

	public void invert(Individual individual, int position, int distance)
	// Inverts by swapping with wrap-around
	{
		int temp;
		for (int j=0; j<(distance+1)>>1; j++) {
			temp=individual.permutation[(position+j)%individual.size()];
			individual.permutation[(position+j)%individual.size()]=individual.permutation[(position+distance-j)%individual.size()];
			individual.permutation[(position+distance-j)%individual.size()]=temp;
		}
	}
}
