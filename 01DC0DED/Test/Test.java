package Test;

import constants.Constants;
import localsearch.LocalSearch;
import localsearch.LocalSearchExchange;
import localsearch.LocalSearchInversion;
import localsearch.LocalSearchJump;
import tspproblem.TSPProblem;

import java.io.*;

public class Test {
    public static void main(String[] args)  {
        File f=new File("/Users/liuqinghao/Desktop/EC-Assignments/src/Outputs/LocalSearchResults");

        try {
            OutputStream fout=new FileOutputStream(f);
            OutputStreamWriter writer=new OutputStreamWriter(fout);
            for (int i = 0; i <Constants.TESTFIES.length; i++) {
                TSPProblem tspProblem=new TSPProblem(Constants.TESTFIES[i]);
                writer.append('\n'+"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+'\n'+"Test file:"+Constants.TESTFIES[i]+'\n');
                writer.flush();
                double sum=0;
                for(int j=0;j<30;j++)
                {
                    LocalSearch localSearchInversion=new LocalSearchJump(tspProblem);
                    int[] result=localSearchInversion.search(tspProblem);
                    writer.append("Times:"+(j+1));
                    writer.append('\n');
                    writer.flush();


                    for (int a :result)
                    {
                        writer.append(a+" ");
                    }
                    writer.append('\n');
                    writer.flush();

                    double cost=tspProblem.cost(result);
                    System.out.println(cost);
                    sum=sum+cost;

                    writer.append("Cost:  "+String.valueOf(tspProblem.cost(result))+'\n');
                    writer.flush();


                }

                System.out.println("Sum Cost: "+ sum+ "Average cost:"+(sum/30.0)+'\n');
                writer.append("Sum Cost: "+ sum+ "Average cost:"+(sum/30.0)+'\n');
                writer.flush();


            }
        }
        catch (IOException e)
        {

            System.out.print("Exception");
        }




    }
}
