package tsp;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.IOException;
//import java.util.Random;

public class TSPLib 
{
	private int ncities;
	private double[][] distance;
	double[][] coord;
	
	public TSPLib(String fileName)
	{
		
        // This will reference one line at a time
        String line = null;

        try 
        {
            
            FileReader fileReader = new FileReader(fileName);            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            line = bufferedReader.readLine();
            
            while (!line.equals("NODE_COORD_SECTION"))
            {
            	if (line.substring(0, 9).equals("DIMENSION"))
            	{
            		String[] values = line.split(" ");                    
                    this.ncities=Integer.parseInt(values[values.length-1]);
                    this.distance = new double[this.ncities][this.ncities];
            		
            	}
            	line = bufferedReader.readLine();
            }          

            
            coord = new double[this.ncities][2];
            String[] values = line.split(" ");
            // parse rest of them as id,x,y 
            int n=1;
            while((line = bufferedReader.readLine()) != null && n<=this.ncities) 
            {
            	if (!line.equals("EOF"))
            	{
            		values = line.split(" ");
            		int id=Integer.parseInt(values[0]);
            		coord[id-1][0]=Double.parseDouble(values[1]);
            		coord[id-1][1]=Double.parseDouble(values[2]);
            		n++;
            		//System.out.println(line);            		
            	}                
            }   

            // Always close files.
            bufferedReader.close();   
            
            // calculate distance matrix
            for (int i=0;i<this.ncities-1;i++)
            {
            	for (int j=i+1;j<this.ncities;j++)
                {
            		double dx=coord[i][0]-coord[j][0];
            		double dy=coord[i][1]-coord[j][1];
            		this.distance[i][j]=Math.sqrt(dx*dx+dy*dy);
            		this.distance[j][i]=this.distance[i][j];
                	
                }
            }            
        }
        
        catch(Exception ex) 
        { 
        	System.out.println("Unable to open file '" +fileName + "'");                
        }         
		
	}
	
	public int getNcities()
	{
		return this.ncities;
	}
	
	public double evaluateCandidate(int[] permutation)
	{
	    double f = distance[permutation[this.ncities-1]][permutation[0]];
	    for (int i = 1;i<this.ncities;i++)
	    {
	        f = f + distance[permutation[i-1]][permutation[i]];
	        //System.out.println(permutation[i-1]+"  "+permutation[i]+ " "+f);
	    }
		return f;		
	}
	
}
