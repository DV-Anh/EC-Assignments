package population;

import java.util.*;

import tspproblem.*;
import individual.*;

public class Population {
	public List<Individual> list=new ArrayList<Individual>();

	public Population()
	// Create a population of size 0
	{}

	public Population (int popsize, TSPProblem problem)
	// Create a new population with "popsize" number of members
	// with individuals scored against "problem" tspproblem
	{
		for (int i = 0; i<popsize; i++) {
			Individual individual = new Individual(problem.size);
			individual.randomise();
			eval_add(individual, problem);
		}
	}

	public void Population(Population population, TSPProblem problem)
	// Clone a population and evaluate it against "problem" TSPProblem
	{
		eval_add(population, problem);
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

	public void sort()
	// Sort so that lowest score is at entry 0
	{Collections.sort(list);}

	public void kill_worst (int num)
	// Use sorting to kill the worst "num" number of population members
	{
		sort();
		for (int i=0; i<num; i++) list.remove(list.size()-1);
	}

	public int size()
	// Returns the size of the population
	{return list.size();}

	public double bestcost()
	// Uses sorting to return the cost of the best individual in the population
	{
		sort();
		return list.get(0).cost();
	}

	public double worstcost()
	// Uses sorting to return the cost of the worst individual in the population
	{
		sort();
		return list.get(list.size()-1).cost();
	}
}
