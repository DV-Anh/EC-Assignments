package evo;


import evo.core.Population;

public interface Reporter {
    public void apply(int gen, Population p);
}
