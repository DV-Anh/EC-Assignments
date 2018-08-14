package population;

import java.util.*;

import tspproblem.*;
import individual.*;

public class Population {
	public List<Individual> list=new ArrayList<Individual>();

	public Population(){}

	public Population (int popsize, TSPProblem problem) {
		for (int i = 0; i<popsize; i++) {
			Individual individual = new Individual(problem.size);
			individual.randomise();
			add(individual, problem);
		}
	}

	public void add(Individual individual, TSPProblem problem) {
		individual.evaluate(problem);
		list.add(individual);
	}

	public void add(Population population, TSPProblem problem) {
		for (int i=0; i<population.size(); i++) add(population.list.get(i), problem);
	}

	// Sort so that lowest score is at entry 0
	public void sort(){Collections.sort(list);}

	// Kill some of the worst of the population at the higest entries of the list
	public void kill_worst (int num) {
		sort();
		for (int i=0; i<num; i++) list.remove(list.size()-1);
	}

	public double bestscore() {
		sort();
		return list.get(0).cost;
	}

	public int size() {return list.size();}
}
