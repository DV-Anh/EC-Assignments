package InverOverOperator;

import tspproblem.TSPProblem;

import java.util.*;

/*
 * Author: Zizheng Pan
 * Date  : 31 July 2018
 *
 * There are some problems that still need to be solved.
 * 1. What if we pick the last element of another individual when choose c'
 *      the current solution is to change into another individual and pick c' again
 *
 * 2. Sometimes the code will fall into an infinite loop.
 *      line 144 - 147, when the counts of poplutions is less than 3
 *      line 136 - 163, So far, I can't figure out why.
 * 
 * Test Code in TSPProblem.java
 *      TSPProblem t = new TSPProblem("EIL76.tsp");
        double IOCost = 0;
        for (int i = 0; i<30; i++) {
            IOOperatorForTSP IOTsp = new IOOperatorForTSP(t);
            IOTsp.inverOverCalculate();
            IOCost += IOTsp.lastMinCost;
        }
        System.out.println("Average Cost for 30 times: " + IOCost/30);
 */


public class IOOperatorForTSP {

    public double lastMinCost = Double.POSITIVE_INFINITY;
    public int population = 100;

    float p = 0.02f;
    int times = 0;
    int equalTimes = 0;

    ArrayList<List<Integer>> populationList;
    TSPProblem problem;

    public IOOperatorForTSP(TSPProblem problem) {
        this.populationList = new ArrayList<List<Integer>>();
        this.problem = problem;
    }

    public void generatePopulation() {
        for (int i = 0; i < population; i++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < problem.size(); j++)
                list.add(j);
            Collections.shuffle(list);
            populationList.add(list);
        }
    }

    public double getCost(List<Integer> S) {
        double cost = 0;
        for (int i = 0; i < S.size() - 1; i++) {
            cost += problem.distance(S.get(i), S.get(i + 1));
        }
        return cost + problem.distance(S.get(S.size() - 1), S.get(0));
    }

    public double getMinCost() {
        double minCost = Double.POSITIVE_INFINITY;
        for (int i = 0; i< populationList.size(); i++) {
            List<Integer> individual = populationList.get(i);
            double currentCost = getCost(individual);
            minCost = minCost > currentCost ? currentCost : minCost;
        }
        return minCost;
    }

    public boolean satifiedTermination() {
        double currentCost = getMinCost();
        if(times >= 20000) {return true;}
        if (currentCost < lastMinCost) {
            lastMinCost = currentCost;
            equalTimes = 0;
            return false;
        } else {
            if (equalTimes >= 500) {
                return true;
            }
            equalTimes ++;
            return false;
        }

    }

    public static int getNextIndexFromIndividual(List<Integer> individual, int c) {
        // Wrap around
        return (individual.indexOf(c) + 1) % individual.size();
    }

    public static void inverseSection(List<Integer> S, int c, int cp) {
        int dir = cp > c ? 1 : -1;
        for (int i = c, j = cp; cp > c ? i < j: i > j ; i += dir, j -= dir) {
            int temp = S.get(i);
            S.set(i, S.get(j));
            S.set(j, temp);
        }
    }

    public void copyList(List<Integer> l1, List<Integer> l2) {
        for (int i : l2) {
            l1.add(i);
        }
    }
    public void inverOverCalculate() {
        this.populationList.clear();
        generatePopulation();
        Random rd = new Random();
        times = 0; equalTimes = 0;
        while (!satifiedTermination()) {
            for (int i = 0; i < populationList.size(); i++) {
                List<Integer> S = new ArrayList<>();
                copyList(S, populationList.get(i));
                int c = S.get(rd.nextInt(S.size()));
                int cp;
                int whileTime = 0;
                do {
                    whileTime ++;
                    double calp = Math.random();
                    if( calp <= p ) {
                        // select the city c' from the remaining cities in S'
                        do {
                            cp = S.get(rd.nextInt(S.size()));
                        }while (cp == c);
                    } else {
                        // select (randomly) an individual from P
                        List<Integer> randIndividual = populationList.get(rd.nextInt(populationList.size()));
                        cp = randIndividual.get(getNextIndexFromIndividual(randIndividual, c));
                    }
                    int nextC = getNextIndexFromIndividual(S, c);
                    int nextCP = getNextIndexFromIndividual(S, cp);
                    int indexCP = (nextCP - 1 + S.size()) % S.size();
                    if (nextC == indexCP || ((nextC - 1 + S.size()) % S.size()) == nextCP) {
                        break;
                    }
                    // inverse the section from the next city of city c to the city c' in S'
                    if(indexCP > nextC) inverseSection(S, nextC, indexCP);
                    else inverseSection(S, nextCP, nextC - 1);
                    c = cp;

                    if (whileTime > 5000) {
                        System.out.println("overflow - " + i);
                        break;
                    }
                } while (true);

                double costOfS = getCost(populationList.get(i));
                double newCostOfS = getCost(S);
                if (newCostOfS < costOfS) {
                    populationList.set(i, S);
                }
            }
            times++;
        }
    }
}