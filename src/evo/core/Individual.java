package evo.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Default representation is a list of objects, e.g. a list of integer
// The Individual should be immutable
public class Individual {
    private int[] permutation;

    public Individual(int[] newperm) {
        this.permutation = newperm.clone();
    }

    public Individual(Individual another) {
        this.permutation = another.permutation.clone();
    }

    public Individual(int dim) {
        List<Integer> dummy = new ArrayList<>();
        for (int i = 0; i < dim; i++)
            dummy.add(i);
        Collections.shuffle(dummy);

        // Turn a list of Integers to an array of int.
        this.permutation = dummy.stream().mapToInt(i -> i).toArray();

    }

    public int length() {
        return permutation.length;
    }

    // Clone an individual, swap elements in index i and j and return
    // the new individual
    public Individual swap(int i, int j) {

        Individual newInd = new Individual(this.permutation);
        int t = newInd.permutation[i];
        newInd.permutation[i] = newInd.permutation[j];
        newInd.permutation[j] = t;

        return newInd;
    }

    public int[] clonePerm() {
        return this.permutation.clone();
    }
}
