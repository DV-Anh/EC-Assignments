package crossover;

import tspproblem.*;
import population.*;

public class CycleCrossOver extends CrossOver {
	public int percent;
	public CycleCrossOver(int pcent) {percent=pcent;}

	public Population breed(Population parents, TSPProblem problem) {return new Population();}
}
