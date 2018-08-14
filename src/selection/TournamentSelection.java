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
		List<Integer> intList = new ArrayList<Integer>();
		for (int i=0; i<population.size(); i++) intList.add(i);

		Population parents = new Population();
		int competitors=(population.size()*percent+50)/100;
		competitors=(competitors<size)? size : competitors;
		int index;
		for (int i=0; i<competitors; i++) {
			index = random.nextInt(intList.size());
			intList.remove(index);
			parents.add(population.list.get(index), problem);
		}
		parents.kill_worst(competitors-size);
		return parents;
	}
}
