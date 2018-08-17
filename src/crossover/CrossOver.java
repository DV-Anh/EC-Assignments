package crossover;

import tspproblem.*;
import population.*;

public abstract class CrossOver {
	public abstract Population breed(Population parents, TSPProblem problem);
}
