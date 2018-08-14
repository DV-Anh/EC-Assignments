package mutation;

import population.*;

public abstract class Mutation {
	public int percent;

	public abstract Population mutate(Population population);
}
