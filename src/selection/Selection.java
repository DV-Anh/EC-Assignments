package selection;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public abstract class Selection {
	public abstract Population choose(Population population, TSPProblem problem, int size);
}
