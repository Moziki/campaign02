package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Queue;

public class LinkedQueue<E> implements Queue<E> {
    DoublyLinkedList<E> data;

    public LinkedQueue() {data = new DoublyLinkedList<>();}

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void offer(E element) {
        data.addLast(element);
    }

    public E peek() {
        return data.first();
    }

    public E poll() {
        return data.removeFirst();
    }

    public void printQueue() {
        data.printList();
    }

    /**
     *
     * @param into The new queue onto which the reversed order of contents from
     * this queue are to be transferred to the top of, unless the provided queue
     */
    public void transfer(Queue<E> into) {
        if (into == null){}
        else {
            LinkedQueue<E> reverse = new LinkedQueue<>();

            //@param thisQueue & paramQueue: thess are used in the for loops, acting as indexes.
            // Ensures accurate original size through the steps
            int thisQueue = this.size();
            int paramQueue = into.size();
            if (this.size() == 0) {
            } else {
                //reverses order of called upon stack
                for (int i = 0; i < thisQueue; i++) {
                    reverse.offer(this.poll());
                }
                //stores param queue into called queue
                if (paramQueue != 0) {
                    for (int i = 0; i < paramQueue; i++) {
                        this.offer(into.poll());
                    }
                }
                //@param stac : called here because the size is not known until first for loop
                int stac = reverse.size();
                //puts stack into param queue (called on stack in reverse)
                for (int i = 0; i < stac; i++) {
                    into.offer(reverse.poll());
                }
                //puts original param queue back on the bottom of param queue
                if (paramQueue != 0) {
                    for (int i = 0; i < thisQueue; i++) {
                        into.offer(this.poll());
                    }
                }
            }
        }
    }
    //Reverses queue by polling into stack and popping back out
    public void reverse() {
        LinkedQueue<E> reverse = new LinkedQueue<>();
        while (!(this.isEmpty())) {
            reverse.offer(this.poll());
        }
        while (!reverse.isEmpty()) {
            this.offer(reverse.poll());
        }
    }

    /**
     *
     * @param from Queue whose contents are to be merged onto the bottom of
     * this queue
     */
    public void merge(Queue<E> from) {
        if (from == null) {}
        else {
            int index = from.size();
            for (int i = 0; i < index; i++) {
                this.offer(from.peek());
                from.offer(from.poll());
            }
        }
    }
}
