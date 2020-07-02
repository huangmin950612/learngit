package com.example.demo.list;


public class LinkedList<E> {

    private Node<E> first;
    private int size;

    private static class Node<E> {
        Node<E> next;
        E element;

        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E element) {
        add(size, element);
    }

    public void add(int index, E element) {
        checkPostIndex(index);

        if (index == 0) {
            first = new Node<E>(first, element);
        } else {
            Node<E> pre = node(index - 1);
            Node<E> next = pre.next;
            pre.next = new Node<E>(next, element);
        }
        size++;
    }

    private void checkPostIndex(int index) {
        if (!isPostIndex(index)) {
            throw new IndexOutOfBoundsException(": Index:" + index + ",Size:" + size);
        }
    }

    private boolean isPostIndex(int index) {
        return index >= 0 && index <= size;
    }

    public E get(int index) {
        return node(index).element;
    }

    private Node<E> node(int index) {
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(": Index:" + index + ",Size:" + size);
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    public E remove(int index) {
        checkElementIndex(index);
        Node<E> oldNode = first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> pre = node(index - 1);
            oldNode = pre.next;
            pre.next = oldNode.next;
        }
        return oldNode.element;
    }

    public int indexOf(E element) {
        Node<E> x = first;
        int index = 0;
        if (element == null) {
            for (Node<E> i = x; i != null; i = i.next) {
                if (element == i.element) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> i = x; i != null; i = i.next) {
                if (element.equals(i.element)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    public void clear() {
        size = 0;
        first = null;
    }

    public String toString(){
        if(size == 0){
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[");
        Node<E> x = first;
        for (Node<E> i = x; i!=null; i=i.next){
            sb.append(i.element);
            if(i.next == null){
                return sb.append("]").toString();
            }
            sb.append(",");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        System.out.println(linkedList.toString());

        System.out.println(linkedList.remove(2));
        System.out.println(linkedList.toString());
    }


}
