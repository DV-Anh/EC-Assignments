package mutation;

import population.*;
import individual.*;

public abstract class Mutation {
	public int percent;

	public abstract void mutate(Population population);

	// Abstract invert required so InverOver can use InversionMutation invert
	public abstract void invert(Individual individual, int position, int distance);
}
