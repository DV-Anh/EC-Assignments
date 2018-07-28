# EC Assignments

> This Repository is for class Evolutionary Computation

## Introduction

### Assignment 1 - [The Traveling Salesman Problem](https://en.wikipedia.org/wiki/Travelling_salesman_problem)

#### Exercise 1 - Problem Representation and TSPlib 

Write a class TSP-Problem which represents the TSP problem. Your class should enable the construction of TSP problems from the files of the symmetric traveling salesman problem (EDGE WEIGHT TYPE : EUC 2D) of the TSPlib which is available at 

http://elib.zib.de/pub/mp-testdata/tsp/tsplib/tsp/index.html

#### Exercise 2 - Local Search 

- Implement three local search algorithms based on jump, exchange and 2-opt neighbourhoods as described in the lecture. 

- Test your local algorithms on the instances EIL51, EIL76, EIL101, ST70, KROA100, KROC100, KROD100, LIN105, PCB442, PR2392, USA13509 from TSPlib. Run each local search algorithm on each instance 30 times where in each run the initial permutation is chosen uniformly at random. Report for each algorithm on each instance the minimum and mean tour length obtained in a table. Summarize and compare the results obtained in 1-2 paragraphs. 

#### Exercise 3 - Individual and Population Representation 

Represent a possible solution to the TSP as a permutation of the given cities and im- plement it in a class Individual. Evolutionary algorithms often start with solutions that are constructed uniformly at random. Write a method that constructs such a solution in linear time. A population in an evolutionary algorithm represents a set of solutions. Implement a class Population for representing a population which is a set of individuals. Make sure that you can evaluate the quality of a solution with respect to a given problem. 

#### Exercise 4 - Variation operators

- Implement the different mutation operators (insert, swap, inversion) for permutations given in the lecture. 

- Implement the different crossover operators (Order Crossover, PMX Crossover, Cy- cle Crossover, Edge Recombination) for permutations given in the lecture. 

#### Exercise 5 - Selection

Implement the different selection methods (fitness-proportional, tournament selection, elitism) given in the lecture. 

#### Exercise 6 - Evolutionary Algorithms and Benchmarking

- Design three different evolutionary algorithms using crossover and mutation on the basis on the implementation of your different modules. Your algorithms should perform as best as possible. Explain your design choices. 

- Test your algorithms with population sizes 20, 50, 100, 200 on the instances EIL51, EIL76, EIL101, ST70, KROA100, KROC100, KROD100, LIN105, PCB442, PR2392, USA13509 from TSPlib. Report the outcomes after 2000, 5000, 10000, and 20000 generations. 
- Run your best algorithm with a population size of 50 for 20000 generations on the TSPlib instances mentioned above. Run your algorithm on each instance 30 times. Report the average cost of the tour you obtained for each instance as well as the standard deviation. 

#### Exercise 7 - Inver-over Evolutionary Algorithm 

- Readthepaper”Inver-overOperatorfortheTSP”byGuoTao,ZbigniewMichalewicz available at 
  http://www.cs.adelaide.edu.au/~zbyszek/Papers/p44.pdf

- Implement the algorithm as described in the paper. 
- Run the inver-over algorithm with a population size of 50 for 20000 generations on the TSPlib instances mentioned above. Run the algorithm on each instance 30 times. Report the average cost of the tour you obtained for each instance as well the standard deviation. 

## Tasks

| Name                                                         | Task   |
| ------------------------------------------------------------ | ------ |
| [Đỗ Việt Anh](https://www.facebook.com/fa.n.ciendum)         | Ex 1&2 |
| [Qinghao Liu](https://www.facebook.com/qinghao.liu.5)        | Ex 5   |
| [Abdulrahman Almalki](https://www.facebook.com/abdulrahman.almalki.31) | Ex 6   |
| [Suraj Yathish](https://www.facebook.com/surajyathish19)     | Ex 3&4 |
| [Zizheng Pan](https://www.facebook.com/profile.php?id=100019479787936) | Ex 7   |
| [Jiaxiu Wei](https://www.facebook.com/jiaxiu.wei.927)        | Ex 6   |

## Instructions

You can choose git or http to clone this repository

> git clone git@github.com:HubHop/EC-Assignments.git

> git clone https://github.com/HubHop/EC-Assignments.git

Make sure you have already set up your git environment in your laptop and have ssh keys.
