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
			eval_add(individual, problem);
		}
	}

	public void eval_add(Individual individual, TSPProblem problem)
	// Evaluate and add an individual to the population
	{
		individual.evaluate(problem);
		list.add(individual);
	}

	public void eval_add(Population population, TSPProblem problem)
	// Evaluate and add a population to the population
	{
		for (int i=0; i<population.size(); i++) eval_add(population.list.get(i), problem);
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
		return list.get(0).cost();
	}

	public int size() {return list.size();}
}
