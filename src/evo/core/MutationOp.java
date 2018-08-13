package evo.core;

import evo.core.Individual;

import java.util.List;

public interface MutationOp {
    public List<Individual> apply(List<Individual> p);
}
