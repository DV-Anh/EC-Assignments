package CrossOver;

import java.util.ArrayList;

import tsp.Individual;

public class CycleCrossOver extends CrossOver
{
	public CycleCrossOver()
	{
		super();
	}
	
	public Individual apply(Individual p1, Individual p2)
	{
		int n=p1.permutation.length;
		
		int i=rand.nextInt(n);
		int j=rand.nextInt(n);
		while (i==j)
			j=rand.nextInt(n);
		
		// create used vector from stating parent
	    Boolean[] used=new Boolean[n];
	    int[] child=new int[n]; 
	    for (int ii=0;ii<n;ii++)
	    {
	    	used[ii]=false;
	    	child[ii]=-1;	    	
	    }	    
	    
	    // use first element
	    used[0]=true;
	    child[0]=p1.permutation[0];
	    
	    int status=1;
	    int ii=0;
	    while (status==1)
	    {
	    	ArrayList<Integer> I=ntimes(p2.permutation,child[ii]);
	        if (I.size()>0 && !used[I.get(0)])
	        {
	            child[I.get(0)]=p1.permutation[I.get(0)];
	            used[I.get(0)]=true;
	            ii=I.get(0);
	        }
	        else
	            status=0;
	        
	    }
	    for (ii=0;ii<n;ii++)
	    {
	    	if (!used[ii])
	    		child[ii]=p2.permutation[ii];    		    	
	    }	
	    
	    return new Individual(child);
			
	}

}

