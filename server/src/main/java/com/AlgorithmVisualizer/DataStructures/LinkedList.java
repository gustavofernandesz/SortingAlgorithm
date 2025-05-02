package com.AlgorithmVisualizer.DataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class LinkedList<E> implements List<E> {
    private Node<E> head, tail;
    private int num_elements = 0;

    static class Node<E> {
        public E element;
        public Node<E> next, prev;

        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }       
    }

    @Override
    public int size() {
        return num_elements;
    }

    @Override
    public boolean isEmpty() {
        return num_elements == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> node = head;

        while (node != null) {
            if (Objects.equals(o, node.element)) return true;
            node = node.next;
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                return (currentNode.next != null);
            }

            @Override
            public E next() {
                currentNode = currentNode.next;
                return currentNode.element;
            }
        };

        return it;
    }

    // Coloca os elementos em uma array previamente alocada com espaço suficiente
    private void copyElementsToArray(E[] arr) {
        Node<E> node = head;

        for (int i = 0; i < num_elements; i++) {
            arr[i] = node.element;
            node = node.next;
        }
    }

    @Override
    public Object[] toArray() {
        @SuppressWarnings("unchecked")
        E[] arr = (E[]) new Object[num_elements];

        copyElementsToArray(arr);

        return arr;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        E[] arr = (E[]) ((a.length < num_elements) ? new Object[num_elements] : a);
        
        copyElementsToArray(arr);
        
        if (a.length > num_elements) 
            arr[num_elements] = null;
        
        return (T[]) arr;
    }

    @Override
    public boolean add(E e) {
        add(num_elements, e);

        return true;
    }

    private E removeNode(Node<E> node) {
        if (node == head)
            head = head.next;
        else
            node.prev.next = node.next;

        if (node == tail) 
            tail = tail.prev;
        else
            node.next.prev = node.prev;

        node.next = null;
        node.prev = null;
        num_elements--;

        return node.element;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> node = head;

        while (node != null) {
            if (Objects.equals(o, node.element)) {
                removeNode(node);
                return true;
            }
            
            node = node.next;
        }

        return false;
    }

    @Override
    public void clear() {
        Node<E> node = head;

        while (node != null) {
            Node<E> next_node = node.next;
            node.prev = null;
            node.next = null;
            node = next_node;
        }

        num_elements = 0;
    }

    private boolean isIndexValid(int index) {
        return (index >= 0 && index < num_elements);
    }

    private Node<E> getNode(int index) {
        if (!isIndexValid(index)) return null;

        Node<E> node = head;

        for (int i = 0; i < index; i++) node = node.next;

        return node;
    }

    @Override
    public E get(int index) throws ArrayIndexOutOfBoundsException {
        Node<E> node = getNode(index);
        if (node == null) throw new ArrayIndexOutOfBoundsException();

        return node.element;
    }

    @Override
    public E set(int index, E element) throws ArrayIndexOutOfBoundsException {
        Node<E> node = getNode(index);
        if (node == null) throw new ArrayIndexOutOfBoundsException(index);

        E prev_element = node.element;
        node.element = element;

        return prev_element;
    }

    @Override
    public void add(int index, E element) throws ArrayIndexOutOfBoundsException {
        if (!isIndexValid(index) && index != num_elements) 
            throw new ArrayIndexOutOfBoundsException(index);
        
        Node<E> node = getNode(index);
        Node<E> new_node = new Node<>(element, null, null);

        // node substitui a head
        if (index == 0) {
            if (head != null) head.prev = new_node;
            new_node.next = head;
            head = new_node;
        }

        // node substitui a tail
        if (index == num_elements) {
            if (tail != null) tail.next = new_node;
            new_node.prev = tail;
            tail = new_node;
        }

        // node no meio da lista
        if (node != null) {
            node.prev.next = new_node;
            node.prev = new_node;
        }

        num_elements++;
    }

    @Override
    public E remove(int index) throws ArrayIndexOutOfBoundsException {
        Node<E> node = getNode(index);
        if (node == null) throw new ArrayIndexOutOfBoundsException(index);

        return removeNode(node);
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        Node<E> node = head;

        while (node != null) {
            if (Objects.equals(o, node)) return i;
            node = node.next;
            i++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int i = num_elements - 1;
        Node<E> node = tail;

        while (node != null) {
            if (Objects.equals(o, node)) return i;
            node = node.prev;
            i--;
        }

        return -1;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        c.forEach(element -> add(element));
        
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        @SuppressWarnings("unchecked")
        E[] arr = (E[]) c.toArray();
        
        for (int i = c.size() - 1; i >= 0; i--) {
            add(index, arr[i]);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAll'");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public ListIterator<E> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subList'");
    }
}
