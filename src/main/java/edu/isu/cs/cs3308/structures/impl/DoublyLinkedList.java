package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;
import edu.isu.cs.cs3308.structures.impl.Node;

public class DoublyLinkedList<E> implements List<E> {


    public Node<E> head;
    public Node<E> tail;

    public int size = 0;



    //builds head and tail sentry nodes and sets references to each other
    public DoublyLinkedList() {
        head = new Node<>(null);
        tail = new Node<>(null);
        tail.setPrev(head);
        head.setNext(tail);
    }


    public E first() {
        if (isEmpty()) return null;
        return head.getNext().getElement();
    }


    public E last() {
        if (isEmpty()) return null;
        return tail.getPrev().getElement();
    }

    public void addLast(E element) {
        if (element == null) {}
        else {
            Node<E> newNode = new Node<>(element);
            newNode.setNext(tail);
            newNode.setPrev(tail.getPrev());
            tail.setPrev(newNode);
            newNode.getPrev().setNext(newNode);

            size++;
        }
    }

    public void addFirst(E element){
        if (element == null){}

        else {
            Node<E> newNode = new Node<>(element);
            newNode.setNext(head.getNext());
            newNode.setPrev(head);
            head.setNext(newNode);
            newNode.getNext().setPrev(newNode);

            size++;
        }
    }

    public E removeFirst() {
        if (isEmpty()) { return null; }

        Node<E> temp = head.getNext();
        temp.getPrev().setNext(temp.getNext());
        temp.getNext().setPrev(temp.getPrev());
        temp.setNext(null);
        temp.setPrev(null);
        size--;
        return temp.getElement();
    }

    public E removeLast(){
        if (isEmpty()){ return null;}

        Node<E> temp = tail.getPrev();
        temp.getPrev().setNext(temp.getNext());
        temp.getNext().setPrev(temp.getPrev());
        temp.setNext(null);
        temp.setPrev(null);
        size--;
        return temp.getElement();
    }

    public void insert(E element, int index){
        if (index < 0 || element == null) {

        }
        else if (index >= size ){
            addLast(element);
        }
        else{
            Node<E> newNode = new Node<>(element);
            Node<E> current = head.getNext();
            for (int i = 0; i < index -1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            newNode.setPrev(current);
            current.setNext(newNode);
            size++;


        }

    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<E> current = head.getNext();
        for (int i = 0; i < index - 1; i++)
            current = current.getNext();

        Node<E> toRemove = current.getNext();
        current.setNext(toRemove.getNext());
        toRemove.getNext().setPrev(toRemove.getPrev());
        toRemove.setNext(null);
        toRemove.setPrev(null);
        size--;
        return toRemove.getElement();
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            return null;
        Node<E> current = head.getNext();
        for (int i = 0; i < index; i++)
            current = current.getNext();

        return current.getElement();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);

    }

    public void printList(){
        Node<E> current = head.getNext();
        for (int i = 0; i < size; i++){
            System.out.println(current.getElement());
            current = current.getNext();
        }
    }

}
