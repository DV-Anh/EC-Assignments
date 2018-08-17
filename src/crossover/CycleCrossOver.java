package crossover;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public class CycleCrossOver extends CrossOver {
	public Random random = new Random();
	public int percent;

	public CycleCrossOver(int pcent) {percent=pcent;}

	public Population breed(Population parents, TSPProblem problem)
	// Perform Cycle Crossover on every pair of individuals in the parent population
	// with a set percentage chance that the pair will or wont crossover
	{
		Population children = new Population();

		for (int i = 0; i<parents.size()-1; i +=2) {
			Individual parent1=new Individual(parents.list.get(i));
			Individual parent2=new Individual(parents.list.get(i+1));

			// Make only the required percent of the population crossover
			if (percent<=random.nextInt(100)){
				children.eval_add(parent1, problem);
				children.eval_add(parent2, problem);
				continue;
			}

			// Perform the actual crossover and add individuals to children population
			children.eval_add(crossOver(parent1, parent2), problem);
			children.eval_add(crossOver(parent2, parent1), problem);
		}
		return children;
	}


	private ArrayList<Integer> ntimes(int[] array,int val) {
		ArrayList<Integer> ind = new ArrayList<Integer>();
		for (int i=0;i<array.length;i++) if (array[i]==val) ind.add(i);
		return ind;
	}

	private Individual crossOver(Individual parent1, Individual parent2) {
		int size = parent1.size();
		Individual child = new Individual(size);

		int i=random.nextInt(size);
		int j=i;
		while (j==i) j=random.nextInt(size);

		// create used vector from stating parent
		Boolean[] used=new Boolean[size];
		for (int index=0;index<size;index++) {
			used[index]=false;
			child.permutation[index]=-1;
		}

		// use first element
		used[0]=true;
		child.permutation[0]=parent1.permutation[0];

		int status=1;
		int index=0;
		while (status==1) {
			ArrayList<Integer> I=ntimes(parent2.permutation,child.permutation[index]);
			if (I.size()>0 && !used[I.get(0)]) {
				child.permutation[I.get(0)]=parent1.permutation[I.get(0)];
				used[I.get(0)]=true;
				index=I.get(0);
			}
			else
				status=0;
		}
		for (index=0;index<size;index++) {
			if (!used[index])
				child.permutation[index]=parent2.permutation[index];
		}
		return child;
	}
}
