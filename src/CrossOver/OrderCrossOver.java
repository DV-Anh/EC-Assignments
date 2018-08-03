package CrossOver;
import tsp.Individual;

public class OrderCrossOver extends CrossOver
{
	public OrderCrossOver()
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
		
		int[] newperm=new int[n];
		for (int ii=0;ii<n;ii++)
			newperm[ii]=-1;
		
		if (i<j)
		{
			for (int ii=i;ii<=j;ii++)
				newperm[ii]=p1.permutation[ii];
			
			int t=j+1;
			for (int ii=j+1;ii<j+1+n;ii++)
			{
				if (ismember(newperm,p2.permutation[ii%n])<0)
				{
					newperm[t%n]=p2.permutation[ii%n];
					t++;
				}
			}
		}
		else
		{
			for (int ii=j;ii<=i;ii++)
				newperm[ii]=p1.permutation[ii];
			
			int t=j-1;
			for (int ii=j-1;ii>j-1-n;ii--)
			{
				int mii=ii;
				if (ii<0)
					mii=ii+n;
				
				if (ismember(newperm,p2.permutation[mii%n])<0)
				{
					if (t<0)
						t=t+n;
					
					newperm[t%n]=p2.permutation[mii%n];
					t--;
				}
			}
		}		
		return new Individual(newperm);
			
	}

}


