package InverOverOperator;

import tspproblem.TSPProblem;

import java.util.*;


public class IOOperatorForTSP {

    public double lastMinCost = Double.POSITIVE_INFINITY;
    public int population = 50;

    float p = 0.02f;
    int times = 0;
    int timelimit = 20000;

    ArrayList<int[]> populationList;
    TSPProblem problem;

    public IOOperatorForTSP(TSPProblem problem,int pop_size, int gen_limit) {
        this.populationList = new ArrayList<>();
        this.problem = problem;
        timelimit=gen_limit;
        pop_size=pop_size;
    }

    public void generatePopulation() {
        int swapIndex,swapTemp;
        Random rng = new Random();
        for (int i = 0; i < population; i++) {
            int[] individual = new int[problem.size()];
            for (int j = 0; j < problem.size(); j++){
                individual[i] = i;
                swapIndex = rng.nextInt(i+1);
                swapTemp = individual[i];
                individual[i] = individual[swapIndex];
                individual[swapIndex] = swapTemp;
            }
            populationList.add(individual);
        }
    }

    public double getMinCost() {
        double minCost = Double.POSITIVE_INFINITY;
        for (int i = 0; i< populationList.size(); i++) {
            int[] individual = populationList.get(i);
            double currentCost = problem.cost(individual);
            minCost = minCost > currentCost ? currentCost : minCost;
        }
        return minCost;
    }

    public static int getNextIndexFromIndividual(int[] individual, int c) {
        // Wrap around
        return (individual[c] + 1) % individual.length;
    }

    public static void inverseSection(int[] S, int c, int cp) {
        int dir = cp > c ? 1 : -1;
        for (int i = c, j = cp; cp > c ? i < j: i > j ; i += dir, j -= dir) {
            int temp = S[i];
            S[i]=S[j];
            S[j]=temp;
        }
    }

    public void copyList(int[] l1, int[] l2) {
        for (int i=0;i<l2.length;i++) {
            l1[i]=l2[i];
        }
    }
    public void inverOverCalculate() {
        this.populationList.clear();
        generatePopulation();
        Random rd = new Random();
        times = 0;
        while (times>=timelimit) {
            for (int i = 0; i < populationList.size(); i++) {
                int[] S = new int[problem.size()];
                copyList(S, populationList.get(i));
                int c = S[rd.nextInt(S.length)];
                int cp;
                do {
                    double calp = Math.random();
                    if( calp <= p ) {
                        // select the city c' from the remaining cities in S'
                        do {
                            cp = S[rd.nextInt(S.length)];
                        }while (cp == c);
                    } else {
                        // select (randomly) an individual from P
                        int[] randIndividual = populationList.get(rd.nextInt(populationList.size()));
                        cp = randIndividual[getNextIndexFromIndividual(randIndividual, c)];
                    }
                    int nextC = getNextIndexFromIndividual(S, c);
                    int nextCP = getNextIndexFromIndividual(S, cp);
                    int indexCP = (nextCP - 1 + S.length) % S.length;
                    if (nextC == indexCP || ((nextC - 1 + S.length) % S.length) == nextCP) {
                        break;
                    }
                    // inverse the section from the next city of city c to the city c' in S'
                    if(indexCP > nextC) inverseSection(S, nextC, indexCP);
                    else inverseSection(S, nextCP, nextC - 1);
                    c = cp;
                } while (true);

                double costOfS = problem.cost(populationList.get(i));
                double newCostOfS = problem.cost(S);
                if (newCostOfS < costOfS) {
                    populationList.set(i, S);
                }
            }
            times++;
        }
    }
}