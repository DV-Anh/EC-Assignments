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

    public double lastMinCost;
    public int population = 10;

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
            for (int j = 1; j < problem.size(); j++)
                list.add(new Integer(j));
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

    public double getMinCost(ArrayList<List<Integer>> populationList) {
        double currentCost = 0;
        double minCost = 0;
        for (int i = 0; i< populationList.size(); i++) {
            List<Integer> individual = populationList.get(i);
            currentCost = getCost(individual);
            if (i == 0) {
                minCost = currentCost;
            }

            //minCost = currentCost;
            if (currentCost < minCost) {
                minCost = currentCost;
            }
        }
        return minCost;
    }

    public boolean satifiedTermination(ArrayList<List<Integer>> populationList) {
        double currentCost = getMinCost(populationList);
        if (times == 0) {
            lastMinCost = currentCost;
            return false;
        }
        if (currentCost < lastMinCost) {
            lastMinCost = currentCost;
            equalTimes = 0;
            return false;
        } else {
            if (equalTimes >= 50) {
                return true;
            }
            equalTimes ++;
            return false;
        }

    }

    public static int getNeighborFromIndividual(List<Integer> individual, int c) {
        for (int i = 0; i < individual.size(); i++) {
            int cp  = individual.get(i);
            if (cp == c) {
                if (i == individual.size() - 1) {
                    return -1;
                }
                return individual.get(i + 1);
            }
        }
        return -1;
    }

    public static void inverseSection(List<Integer> S, int c, int cp) {
        int cIndex = S.indexOf(c);
        int cpIndex = S.indexOf(cp);
        if (cIndex < cpIndex) {
            List<Integer> middlePart = S.subList(cIndex, cpIndex);
            Collections.reverse(middlePart);
        } else {
            List<Integer> middlePart = S.subList(cpIndex, cIndex);
            Collections.reverse(middlePart);
        }
    }

    public void copyList(List<Integer> l1, List<Integer> l2) {
        for (int i : l2) {
            l1.add(new Integer(i));
        }
    }

    public void inverOverCalculate() {
        generatePopulation();


        while (!satifiedTermination(populationList)) {
            for (int i = 0; i < populationList.size(); i++) {
                List<Integer> S = new ArrayList<Integer>();
                copyList(S, populationList.get(i));
                Random rd = new Random();
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
                        List<Integer> randIndividual;
                        do {
                            randIndividual = populationList.get(rd.nextInt(populationList.size() - 1));
                            cp = getNeighborFromIndividual(randIndividual, c);
                        }while (cp == -1);
                    }
                    int neighborC = getNeighborFromIndividual(S, c);
                    if ( neighborC == cp) {
                        break;
                    }
                    // inverse the section from the next city of city c to the city c' in S'
                    inverseSection(S, c, cp);

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