package edu.isu.cs.cs3308.structures.impl;

public class Node<E> {

        private E element;
        private Node<E> next;
        private Node<E> prev;


        public Node(E e) {
            element = e;
        }

        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
        public Node<E> getPrev() { return prev; }
        public void setNext(Node<E> n) { next = n; }
        public void setPrev(Node<E> n) { prev = n;}
    }

