package crossover;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public class OrderCrossOver extends CrossOver {
	public Random random = new Random();
	public int percent;

	public OrderCrossOver(int pcent) {percent=pcent;}

	public Population breed(Population parents, TSPProblem problem) {
		Population children = new Population();
		for (int i = 0; i <parents.size()-1; i +=2) {
			Individual parent1=parents.list.get(i);
			Individual parent2=parents.list.get(i+1);

			if (percent<=random.nextInt(100)){
				children.add(parent1, problem);
				children.add(parent2, problem);
				continue;
			}

			int size=parent1.size;
			int left=random.nextInt(size);
			int right=left;
			while (right==left) right=random.nextInt(size);
			if (left>right) {
				int temp=left;
				left=right;
				right=temp;
			}
			children.add(crossOver(parent1, parent2, left, right), problem);
			children.add(crossOver(parent2, parent1, left, right), problem);
		}

		return children;
	}

	private Individual crossOver(Individual parent1, Individual parent2, int left, int right) {
		int size=parent1.size;
		Individual child = new Individual(size);

		for (int index=0; index<size; index++)
			child.permutation[index]=-1;

		if (left<right) {
			for (int index=left; index<=right; index++)
				child.permutation[index] = parent1.permutation[index];

			int pointer=right+1;
			for (int index=right+1; index<right+size+1; index++)
			{
				if (!Arrays.asList(child.permutation).contains(parent2.permutation[index%size]))
				{
					child.permutation[pointer%size] = parent2.permutation[index%size];
					pointer++;
				}
			}
		}
		return child;
	}
}
