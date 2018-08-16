package crossover;

import tspproblem.*;
import population.*;

public class PMXCrossOver extends CrossOver {
	public int percent;
	public PMXCrossOver(int pcent) {percent=pcent;}

	public Population breed(Population parents, TSPProblem problem) {return new Population();}
}
