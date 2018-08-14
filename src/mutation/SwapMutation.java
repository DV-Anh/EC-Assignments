package mutation;

import java.util.*;

import population.*;
import individual.*;

public class SwapMutation extends Mutation {
	public Random random = new Random();
	public int percent;

	public SwapMutation (int pcent) {percent=pcent;}

	public Population mutate(Population population) {
		for (int i=0; i<population.size(); i++){
			if (percent<=random.nextInt(100)) continue;
			Individual child = population.list.get(i);
			int first = random.nextInt(child.size);
			int second = first;
			while (second == first) second = random.nextInt(child.size);
			int temp = child.permutation[first];
			child.permutation[first]=child.permutation[second];
			child.permutation[second]=temp;
			population.list.set(i,child);
		}
		return population;
	}
}
