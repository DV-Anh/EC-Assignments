package tspproblem;

import InverOverOperator.IOOperatorForTSP;
import constants.Constants;
import localsearch.LocalSearch;
import localsearch.LocalSearchExchange;
import localsearch.LocalSearchInversion;
import localsearch.LocalSearchJump;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TSPProblem {
    public static final double[][] coordinates2D = new double[2][];
    private static double[][] distanceMatrix;
    /**
     * Constructor reads file name, read and store TSP from the file to array of coordinates
     * @param filename name of problem file
     */
    public TSPProblem(String filename){
        int size;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))){
            int coordIndex = -1;
            for(String line; (line = br.readLine()) != null;){
                if(line.startsWith(Constants.EOF) || line.trim().length() < 1){
                    br.close();
                    break;
                }
                if(coordIndex >= 0){
                    String[] linePart = line.split(Constants.COORD_DELIMITER);
                    coordinates2D[0][coordIndex] = Double.valueOf(linePart[1]);
                    coordinates2D[1][coordIndex++] = Double.valueOf(linePart[2]);
                    distanceMatrix[coordIndex - 1] = new double[coordIndex];
                    for (int i = 0; i < coordIndex; i++) {
                        double dx = coordinates2D[0][coordIndex - 1] - coordinates2D[0][i];
                        double dy = coordinates2D[1][coordIndex - 1] - coordinates2D[1][i];
                        distanceMatrix[coordIndex - 1][i] = Math.sqrt(dx*dx + dy*dy);
                    }
                }else{
                    if(line.startsWith(Constants.COORDSTART)){
                        coordIndex = 0; continue;
                    }
                    String[] linePart = line.split(Constants.HEADER_DELIMITER);
                    if(linePart[0].startsWith(Constants.DIMENSION)){
                        size = Integer.valueOf(linePart[1].trim());
                        coordinates2D[0] = new double[size];
                        coordinates2D[1] = new double[size];
                        distanceMatrix = new double[size][];
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Returns size of TSP
     * @return size of TSP
     */
    public int size(){return coordinates2D[0].length;}

    /**
     * Return distance from node i to node j
     * @param i index i
     * @param j index j
     * @return distance from node i to node j
     */
    public static double distance(int i, int j){
        return j > i ? distanceMatrix[j][i] : distanceMatrix[i][j];
    }
    /**
     * Returns cost of the given candidate
     * @param candidate the candidate
     * @return the cost
     */
    public static double cost(int[] candidate){
        double cost = 0;
        for (int i = 0; i < candidate.length - 1; i++) {
            cost += distance(candidate[i], candidate[i + 1]);
        }return cost + distance(candidate[candidate.length - 1], candidate[0]);
    }
    // Unit testing
    public static void main(String[] args){
        // Using the class by passing file name into constructor
        for (int i = 0; i < Constants.TESTFIES.length; i++) {
            System.out.println(Constants.TESTFIES[i]);
            TSPProblem t = new TSPProblem(Constants.TESTPATH + Constants.TESTFIES[i]);
            LocalSearch l1 = new LocalSearchExchange();
            LocalSearch l2 = new LocalSearchInversion();
            LocalSearch l3 = new LocalSearchJump();
            //IOOperatorForTSP o = new IOOperatorForTSP(t);
            double min1, min2, min3, min4 = min3 = min2 = min1 = Double.POSITIVE_INFINITY;
            double sum1, sum2, sum3, sum4 = sum3 = sum2 = sum1 = 0;
            int n = 30;
            for (int j = 0; j < n; j++) {
                double c1 = t.cost(l1.search(t));
                double c2 = t.cost(l2.search(t));
                double c3 = t.cost(l3.search(t));
                //o.inverOverCalculate();
                //double c4 = o.getMinCost();
                sum1 += c1;
                sum2 += c2;
                sum3 += c3;
                //sum4 += c4;
                min1 = min1 > c1 ? c1 : min1;
                min2 = min2 > c2 ? c2 : min2;
                min3 = min3 > c3 ? c3 : min3;
                //min4 = min4 > c4 ? c4 : min4;
                System.out.print(" "+j);
            }
            System.out.println();
            System.out.println(min1 + " - " + sum1 / n);
            System.out.println(min2 + " - " + sum2 / n);
            System.out.println(min3 + " - " + sum3 / n);
            //System.out.println(min4 + " - " + sum4 / n);
        }
    }

}
