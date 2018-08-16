package tspproblem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TSPProblem {
	// String constants
	public static final String EOF = "EOF";
	public static final String COORD_DELIMITER = " ";
	public static final String COORDSTART = "NODE_COORD_SECTION";
	public static final String HEADER_DELIMITER = ":";
	public static final String DIMENSION = "DIMENSION";

	// Data
	private final double[][] coordinates2D = new double[2][];
	private double[][] distanceMatrix;
	public int size=0;

	public static void error(String message)
	// Prints error message and exits program
	{System.out.println("Error: "+message); System.exit(1);}

	public TSPProblem(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))){
			int coordIndex = -1;
			for(String line; (line = br.readLine()) != null;){
				if(line.startsWith(EOF) || line.trim().length() < 1){
					br.close();
					break;
				}
				if(coordIndex >= 0){
					String[] linePart = line.split(COORD_DELIMITER);
					coordinates2D[0][coordIndex] = Double.valueOf(linePart[1]);
					coordinates2D[1][coordIndex++] = Double.valueOf(linePart[2]);
					distanceMatrix[coordIndex - 1] = new double[coordIndex];
					for (int i = 0; i < coordIndex; i++) {
						double dx = coordinates2D[0][coordIndex - 1] - coordinates2D[0][i];
						double dy = coordinates2D[1][coordIndex - 1] - coordinates2D[1][i];
						distanceMatrix[coordIndex - 1][i] = Math.sqrt(dx*dx + dy*dy);
					}
				}
				else {
					if(line.startsWith(COORDSTART)){
						coordIndex = 0; continue;
					}
					String[] linePart = line.split(HEADER_DELIMITER);
					if(linePart[0].startsWith(DIMENSION)){
						size = Integer.valueOf(linePart[1].trim());
						coordinates2D[0] = new double[size];
						coordinates2D[1] = new double[size];
						distanceMatrix = new double[size][];
					}
				}
			}
		}
		catch (IOException e){
			error(" Cannot find map file "+filename);
		}
	}

	public double distance(int i, int j){
		return j > i ? distanceMatrix[j][i] : distanceMatrix[i][j];
	}

	public double cost(int[] candidate){
		double cost = 0;
		for (int i = 0; i < candidate.length - 1; i++) {
			cost += distance(candidate[i], candidate[i + 1]);
		}return cost + distance(candidate[candidate.length - 1], candidate[0]);
	}
}
