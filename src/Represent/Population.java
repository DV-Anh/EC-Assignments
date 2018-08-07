package Represent;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Individual> individualList=new ArrayList<>();

    public Population(Individual[] individuals, int i) {
        for (Individual individual:individuals)
        {
            individualList.add(individual);
        }
    }

    public void addIndividual(Individual individual)
    {
        individualList.add(individual);
    }
    public void addPopulation(Individual[] individuals,int k)
    {
        for (Individual i:individuals)
        {
            individualList.add(i);
        }
    }

    public Individual getIndividual(int index)
    {
        return individualList.get(index);
    }

    public int getPopulationSize()
    {
        return individualList.size();
    }
}
