package evo.core;

// Default representation is a list of objects, e.g. a list of integer
// The Individual should be immutable
public class Individual {
    private int[] permutation;

    public Individual(int[] newperm) {
        this.permutation = newperm.clone();
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
