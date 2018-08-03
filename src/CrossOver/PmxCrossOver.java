package CrossOver;

import java.util.ArrayList;

import tsp.Individual;

public class PmxCrossOver extends CrossOver
{
	public PmxCrossOver()
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
		
		if (i>j)
		{
			int tmp=i;
			i=j;
			j=tmp;
		}
		
		int[] newperm=new int[n];
		for (int ii=0;ii<n;ii++)
			newperm[ii]=p1.permutation[ii];
		for (int ii=i;ii<=j;ii++)
			newperm[ii]=p2.permutation[ii];
		
		
		int[] prt1= new int[j-i+1];
		int[] prt2= new int[j-i+1];
		for (int ii=i;ii<=j;ii++)
		{
			prt1[ii-i]=p2.permutation[ii];
			prt2[ii-i]=p1.permutation[ii];
		}    
	    
	    for (int jj=0;jj<prt1.length;jj++)
	    {
	        int tmp=prt1[jj]; 
	        ArrayList<Integer> I=ntimes(newperm,tmp);
	        while (I.size()>1) 
	        {
	            int jjj=ismember(prt1,tmp);
	            	            
	            for (int ii=0;ii<I.size();ii++)
	            	if (I.get(ii)<i || I.get(ii)>j)
	            	{
	            		newperm[I.get(ii)]=prt2[jjj];
	    	            tmp=newperm[I.get(ii)];
	            	}
	            I=ntimes(newperm,tmp);
	        }	    
	    }
	    return new Individual(newperm);		
	}

}
