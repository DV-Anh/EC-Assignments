package crossover;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public class PMXCrossOver extends CrossOver {
	public Random random = new Random();
	public int percent;

	public PMXCrossOver(int pcent) {percent=pcent;}

	public Population breed(Population parents, TSPProblem problem)
	// Perform Edge Crossover on every pair of individuals in the parent population
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

	private ArrayList<Integer> ntimes(int[] array, int val) {

		ArrayList<Integer> ind = new ArrayList<Integer>();

		for (int i = 0; i < array.length; i++) {
			if (array[i] == val)
				ind.add(i);
		}
		return ind;
	}

	private int ismember(int[] array, int val) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == val)
				return i;
		}
		return -1;
	}

	private Individual crossOver(Individual parent1, Individual parent2) {
		int size = parent1.size();
		Individual child = new Individual(size);

		int i = random.nextInt(size);
		int j = random.nextInt(size);
		while (i == j)
			j = random.nextInt(size);

		if (i > j) {
			int tmp = i;
			i = j;
			j = tmp;
		}

		for (int index = 0; index < size; index++)
			child.permutation[index] = parent1.permutation[index];
		for (int index = i; index <= j; index++)
			child.permutation[index] = parent2.permutation[index];


		int[] prt1 = new int[j - i + 1];
		int[] prt2 = new int[j - i + 1];
		for (int index = i; index <= j; index++) {
			prt1[index - i] = parent2.permutation[index];
			prt2[index - i] = parent1.permutation[index];
		}

		for (int index2 = 0; index2 < prt1.length; index2++) {
			int tmp = prt1[index2];
			ArrayList<Integer> I = ntimes(child.permutation, tmp);
			while (I.size() > 1) {
				int index3 = ismember(prt1, tmp);

				for (int index = 0; index < I.size(); index++)
					if (I.get(index) < i || I.get(index) > j) {
						child.permutation[I.get(index)] = prt2[index3];
						tmp = child.permutation[I.get(index)];
					}
				I = ntimes(child.permutation, tmp);
			}
		}
		return child;
	}
}
