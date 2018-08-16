package crossover;

import tspproblem.*;
import population.*;

public class EdgeCrossOver extends CrossOver {
	public int percent;
	public EdgeCrossOver(int pcent) {percent=pcent;}

	public Population breed(Population parents, TSPProblem problem) {return new Population();}
}
