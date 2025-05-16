package DataStructure;

import ModelClasses.Fir;

public class FIRLinkedList {
    private class Node {
        Fir fir;
        Node next;

        Node(Fir fir) {
            this.fir = fir;
            next = null;
        }
    }

    private Node head;

    public FIRLinkedList() {
        head = null;
    }

    public void addFIR(Fir fir) {
        Node newNode = new Node(fir);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public Fir searchFIR(String firNumber) {
        Node current = head;
        while (current != null) {
            if (current.fir.getFirNumber().equals(firNumber)) {
                return current.fir;
            }
            current = current.next;
        }
        return null;
    }

    public void displayAllFIRs() {
        if (head == null) {
            System.out.println("No FIRs in the system.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.println(current.fir);
            System.out.println("------------------------");
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean deleteFIR(String firNumber) {
        if (isEmpty()) {
            return false;
        }

        Node current = head;
        Node previous = null;

        // If head node itself holds the key to be deleted
        if (current != null && current.fir.getFirNumber().equals(firNumber)) {
            head = current.next;
            return true;
        }

        // Search for the key to be deleted
        while (current != null && !current.fir.getFirNumber().equals(firNumber)) {
            previous = current;
            current = current.next;
        }

        // If key was not present in linked list
        if (current == null) {
            return false;
        }

        // Unlink the node from linked list
        previous.next = current.next;
        return true;
    }

    public void deleteFIRsByCriminalId(String criminalId) {
        if (isEmpty()) {
            return;
        }

        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.fir.getCriminal().getId().equals(criminalId)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
            } else {
                previous = current;
            }
            current = current.next;
        }
    }
}
