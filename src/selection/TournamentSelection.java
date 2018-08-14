package selection;

import java.util.*;

import tspproblem.*;
import population.*;
import individual.*;

public class TournamentSelection extends Selection {
	public Random random = new Random();
	public int percent;

	public TournamentSelection(int pcent) {percent=pcent;}

	public Population choose(Population population, TSPProblem problem, int size) {
		Population parents = new Population();
		int competitors=(population.size()*percent+50)/100;
		competitors=(competitors<size)? size : competitors;
		int index;
		for (int i=0; i<2; i++) {
			index=random.nextInt(population.size());
			parents.add(population.list.get(index), problem);
			population.list.remove(index);
		}
		parents.kill_worst(competitors-size);
		return parents;
	}
}
