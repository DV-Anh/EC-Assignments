package crossover;

import tspproblem.*;
import population.*;

public abstract class CrossOver {
	public int percent;
	
	public abstract Population breed(Population parents, TSPProblem problem);
}
