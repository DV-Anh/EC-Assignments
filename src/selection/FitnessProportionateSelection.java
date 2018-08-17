package selection;

import java.util.*;

import tspproblem.*;
import population.*;

public class FitnessProportionateSelection extends Selection {


	public Population choose(Population population, TSPProblem problem, int size)
	{
		double worstCost=population.getWorstCost();
		Population parent=new Population();

		double sum=sumOfFitness1(population,worstCost);
		int[] indexOfInndividual =new int[size];
		int currentMembersNum=0;
		while (currentMembersNum<size)
		{
			int indexOfArrays=0;
			double random=Math.random();
			double cumulativeProbability=0;
			while( cumulativeProbability+((worstCost-population.list.get(indexOfArrays).cost())/sum) < random)
			{
				indexOfArrays++;
				if(indexOfArrays+1>=population.size())
				{
					break;
				}
			}
			if(!whetherInArray(indexOfInndividual,indexOfArrays)) {
				indexOfInndividual[currentMembersNum] = indexOfArrays;
				currentMembersNum++;
			}
		}

		for(int i:indexOfInndividual)
		{
			parent.eval_add(population.list.get(i),problem);
		}

		return parent;
	}
	public double sumOfFitness1(Population population,double worstCost)
	{
		double sum=0;
		for (int i = 0; i <population.size(); i++)
		{
			sum=sum+(worstCost-population.list.get(i).cost()); //Caclulating distance
		}

		return sum;
	}
	public static boolean whetherInArray(int[] arr, int targetValue) {
		for(int s: arr){
			if(s==targetValue)
				return true;
		}
		return false;
	}


}
