/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import algoritmogenetico.controller.AlgoritmoGenetico;
import algoritmogenetico.controller.ManipulaIndividuo;
import algoritmogenetico.model.Individuo;
import caixeiroviajante.AdjMatrix;
import caixeiroviajante.FileManager;
import caixeiroviajante.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Douglas
 */
public class ProblemaCaixeiroViajanteAlgoritmoGenetico {
    private static final int NUM_CITIES =20;
    // The population of candidate solutions to the TSP problem
    private static int[][] population;

    // The size of the population
    private static final int POPULATION_SIZE = 1000;

    // The probability of crossover (0.0 - 1.0)
    private static final double CROSSOVER_PROBABILITY = .8;

    // The probability of mutation (0.0 - 1.0)
    private static final double MUTATION_PROBABILITY = .8;
    static Graph graph = null;
    // The number of generations to evolve the population
    private static final int NUM_GENERATIONS = 1000;
    public static void main(String[] args) {

        FileManager fileManager = new FileManager();
        ArrayList<String> text = fileManager.stringReader("./data/Teste_2.txt");



        int nVertex = 3;

        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if (i == 0){
                nVertex = Integer.parseInt(line.trim());
                graph = new AdjMatrix(nVertex);
            }
            else {
                int oriVertex = Integer.parseInt(line.split(" ")[0]);
                String splits[] = line.substring(line.indexOf(" "), line.length()).split(";");
                for (String part : splits){
                    String edgeData[] = part.split("-");
                    int targetVertex = Integer.parseInt(edgeData[0].trim());
                    int weight = Integer.parseInt(edgeData[1]);

                    graph.setEdge(oriVertex, targetVertex, weight);
                }
            }
        }
        criaPopulacao();
        for (int i = 0; i < NUM_GENERATIONS; i++) {
            int[][] parents = selectParents();

            int[][] children = crossover(parents);


            mutate(children);

            population = selectNextGeneration(population, children);
        }

        printBestSolution();
    }

    public static void criaPopulacao(){
        population = new int[POPULATION_SIZE][NUM_CITIES];
        for(int i = 0; i < POPULATION_SIZE;i++){
            for(int j = 0; j< NUM_CITIES;j++){
                population[i][j] = j;
            }
            shuffle(population[i]);

        }
    }
    public static void shuffle (int[] pop){
        Random random = new Random();
        for (int i = pop.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = pop[i];
            pop[i] = pop[j];
            pop[j] = temp;
        }
    }
    // Selects the parents for crossover using tournament selection
    private static int[][] selectParents() {
        int[][] parents = new int[POPULATION_SIZE][NUM_CITIES];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            int[][] subset = selectRandomSubset(population);

            parents[i] = getBestIndividual(subset);
        }
        return parents;
    }

    // Selects a random subset of the given population
    private static int[][] selectRandomSubset(int[][] population) {
        Random random = new Random();
        int subsetSize = (int) (POPULATION_SIZE * 0.2);
        int[][] subset = new int[subsetSize][NUM_CITIES];
        for (int i = 0; i < subsetSize; i++) {
            int index = random.nextInt(POPULATION_SIZE);
            subset[i] = population[index];
        }
        return subset;
    }

    // Returns the best individual in the given population
    private static int[] getBestIndividual(int[][] population) {
        int bestIndividualIndex = 0;
        int bestFitness = getFitness(population[bestIndividualIndex]);
        for (int i = 1; i < population.length; i++) {
            int fitness = getFitness(population[i]);
            if (fitness < bestFitness) {
                bestIndividualIndex = i;
                bestFitness = fitness;
            }
        }
        return population[bestIndividualIndex];
     }
    // Calculates the fitness of the given individual
    private static int getFitness(int[] individual) {
        int fitness = 0;
        for (int i = 0; i < NUM_CITIES; i++) {
            int city1 = individual[i];
            int city2 = individual[(i + 1) % NUM_CITIES];
            fitness += graph.getAdjacencyMatrix()[city1][city2];
        }
        return fitness;
    }
    public static boolean isInSubsequence(int city, int[] child, int startPos, int endPos) {
        for (int i = startPos; i <= endPos; i++) {
            if (child[i] == city) {
                return true;
            }
        }
        return false;
    }

    // Performs crossover on the given parents to produce the children

        public static int[][] crossover(int[][] parents) {
            int[][] children = new int[2][NUM_CITIES];

            int crossoverPoint = new Random().nextInt(NUM_CITIES);

            for (int i = 0; i < crossoverPoint; i++) {
                children[0][i] = parents[0][i];
            }

            for (int i = crossoverPoint; i < NUM_CITIES; i++) {
                children[0][i] = parents[1][i];
            }

            for (int i = 0; i < crossoverPoint; i++) {
                children[1][i] = parents[1][i];
            }

            for (int i = crossoverPoint; i < NUM_CITIES; i++) {
                children[1][i] = parents[0][i];
            }

            return children;
        }

    // Performs mutation on the given children
    private static void mutate(int[][] children) {
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            if (random.nextDouble() < MUTATION_PROBABILITY) {
                int city1 = random.nextInt(NUM_CITIES);
                int city2 = random.nextInt(NUM_CITIES);
                int temp = children[i][city1];
                children[i][city1] = children[i][city2];
                children[i][city2] = temp;
            }
        }
    }
    // Selects the best individuals from the current population and the children
// to form the new population
    public static int[][] selectNextGeneration(int[][] currentPopulation, int[][] children) {
        int[][] nextGeneration = new int[currentPopulation.length][NUM_CITIES];

        for (int i = 0; i < currentPopulation.length; i++) {
            nextGeneration[i] = currentPopulation[i];
        }

        for (int i = 0; i < children.length; i++) {
            nextGeneration[currentPopulation.length - children.length + i] = children[i];
        }

        Arrays.sort(nextGeneration, (a, b) -> -Double.compare(getFitness(a), getFitness(b)));

        return Arrays.copyOfRange(nextGeneration, 0, currentPopulation.length);
    }
    private static void printBestSolution() {
        int[] bestIndividual = getBestIndividual(population);
        int fitness = getFitness(bestIndividual);
        System.out.print("Path: ");

        for(int cidade: bestIndividual){
            System.out.printf("\t" + cidade + "\t");
        }
        System.out.println("Fitness: " + fitness);
    }




}

