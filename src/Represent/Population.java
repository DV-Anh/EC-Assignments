package Represent;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Individual> individualList=new ArrayList<>();
    public void addIndividual(Individual individual)
    {
        individualList.add(individual);
    }
    public void addPopulation(Individual[] individuals)
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
