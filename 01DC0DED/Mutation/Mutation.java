package Mutation;

import java.util.Random;

import tsp.Individual;

// generic mutation class
public abstract class Mutation 
{
	public Random rand = new Random();
	public Mutation()
	{}
	
	public abstract Individual apply(Individual p);

}
