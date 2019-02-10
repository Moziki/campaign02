package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.Queue;
import edu.isu.cs.cs3308.structures.impl.LinkedQueue;
import edu.isu.cs.cs3308.structures.impl.DoublyLinkedList;
import edu.isu.cs.cs3308.structures.impl.Node;
import java.util.Random;

/**
 * Class representing a wait time simulation program.
 *
 * @author Isaac Griffith
 * @author
 */
public class Simulation {

    private int arrivalRate;
    private int maxNumQueues;
    private Random r;
    private int numIterations = 50;
    public DoublyLinkedList<LinkedQueue> lines;

    // You will probably need more fields

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the current time. This defaults to using 50 iterations.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     */
    public Simulation(int arrivalRate, int maxNumQueues) {
        this.arrivalRate = arrivalRate;

        this.maxNumQueues = maxNumQueues;
        r = new Random();

        this.lines = new DoublyLinkedList<>();



    }

    /**
     * Constructs a new siulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the provided seed value, and the number of iterations is set to
     * the provided value.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     * @param numIterations the number of iterations used to improve data
     * @param seed the initial seed value for the random number generator
     */
    public Simulation(int arrivalRate, int maxNumQueues, int numIterations, int seed) {
        this(arrivalRate, maxNumQueues);
        r = new Random(seed);
        this.numIterations = numIterations;
    }

    /**
     * Executes the Simulation
     */
    public void runSimulation() {
        String printResults = "";
        System.out.println("Arrival rate: " + this.arrivalRate);
        int currentMaxLines = 1;
        for (int queueLoop = 0; queueLoop < this.maxNumQueues; queueLoop++) {
            int average = 0;

            for (int iterations = numIterations; iterations > 0; iterations--) {
                int totPeople = 0;
                int totWaittime = 0;
            for (int i = 0; i < currentMaxLines; i++) {
                LinkedQueue<Integer> oneQueue = new LinkedQueue<>();
                lines.addFirst(oneQueue);
            }




                    for (int minutes = 720; minutes >= 0; minutes--) {


                        int curLine = lines.head.getNext().getElement().size();
                        int people = getRandomNumPeople(this.arrivalRate);
                        for (int person = people; person >= 0; person--) {
                            Node<LinkedQueue> current = lines.head.getNext();
                            Node<LinkedQueue> smallestLine = lines.head.getNext();
                                for (int lineCheck = 0; lineCheck < currentMaxLines; lineCheck++) {
                                    if (current.getElement().isEmpty()) {
                                        smallestLine = current;
                                    }
                                    else {
                                        if (current.getElement().size() < curLine) smallestLine = current;
                                    }
                                    current = current.getNext();
                                }
                                smallestLine.getElement().offer(0);
                            }
                        Node<LinkedQueue> current = lines.head.getNext();
                        for (int leavingLine = currentMaxLines; leavingLine > 0; leavingLine--) {
                            if (current.getElement().isEmpty()){}
                            else {
                                totPeople++;
                                totWaittime += (int)current.getElement().poll();
                            }
                            if (current.getElement().isEmpty()){}
                            else {
                                totPeople++;
                                totWaittime += ((int)current.getElement().poll()); }
                                current = current.getNext();
                        }
                        Node<LinkedQueue> currentOne = lines.head.getNext();
                        for (int x = 0; x < currentMaxLines; x++) {
                            if (currentOne.getElement() == null || currentOne.getElement().isEmpty()){}
                            else {
                                for (int y = 0; y < currentOne.getElement().size(); y++) {
                                    int single = (Integer) currentOne.getElement().poll();
                                    single += 1;
                                    currentOne.getElement().offer(single);

                                }
                            }
                            currentOne = currentOne.getNext();

                        }

            }
                    average += totWaittime / totPeople;
                    }
                    average = average / numIterations;

                    printResults += "Average wait time using " + currentMaxLines + " queue(s): " + average +"\r\n";

                    currentMaxLines++;
                }
            System.out.println(printResults);
            }

    /**
     * returns a number of people based on the provided average
     *
     * @param avg The average number of people to generate
     * @return An integer representing the number of people generated this minute
     */
    //Don't change this method.
    private static int getRandomNumPeople(double avg) {
        Random r = new Random();
        double L = Math.exp(-avg);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
