package crossover;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public abstract class CrossOver {
	public int percent;
	public abstract Population breed(Population parents, TSPProblem problem);
}
