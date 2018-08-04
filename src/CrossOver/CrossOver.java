package CrossOver;

import java.util.ArrayList;
import java.util.Random;

import tsp.Individual;

public abstract class CrossOver {
	public Random rand;
	
	public CrossOver()
	{
		rand = new Random();
	}
	
	public abstract Individual apply(Individual p1,Individual p2);
	
	protected int ismember(int[] array,int val)
	{
		for (int i=0;i<array.length;i++)
		{
			if (array[i]==val)
				return i;
		}
		return -1;
	}	
	
	
	protected ArrayList<Integer> ntimes(int[] array,int val)
	{
		
		ArrayList<Integer> ind = new ArrayList<Integer>();
		
		for (int i=0;i<array.length;i++)
		{
			if (array[i]==val)
				ind.add(i);				
		}
		return ind;
	}

}
