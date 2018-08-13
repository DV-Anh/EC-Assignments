package evo.core;


import evo.core.Individual;

import java.util.List;


public interface CrossOverOp {
    public List<Individual> apply(List<Individual> p);

}
