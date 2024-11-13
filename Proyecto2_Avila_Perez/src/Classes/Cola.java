package Classes;

import javax.swing.JOptionPane;

public class Cola {

    private Nodo head;
    private Nodo tail;
    private int size;

    public Cola() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void empty() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void encolar(Personaje persona) { //queue
        Nodo nuevo = new Nodo(persona);
        if (this.isEmpty()) {
            head = tail = nuevo;
        } else {
            tail.setNext(nuevo);
            tail = nuevo;
        }
        size++;
    }

    public void desencolar() {
        if (this.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La cola está vacia");
        } else if (size == 1) {
            this.empty();
        } else {
            head = head.getNext();
            size--;
        }
    }
    
    public Personaje dispatch(){
        Personaje elemento = process();
        desencolar();
        return elemento;
    }
    
    public Personaje process(){
        return getHead().getElement();
    }

    public String print() {
        if (!this.isEmpty()) {
            String printCola = "";
            for (int i = 0; i < size; i++) {
                Nodo actual = head;
                this.desencolar();
                
                if (actual.getElement().getSerie().equals("starwars")) {
                    printCola += "Star Wars - " + actual.getElement().getId() + ", ";
                } else if (actual.getElement().getSerie().equals("startrek")) {
                    printCola += "Star Trek - " + actual.getElement().getId() + ", ";
                }
                this.encolar(actual.getElement());
            }
            return printCola;
        }
        return null;
    }
    
    public Nodo getHead() {
        return head;
    }

    public void setHead(Nodo head) {
        this.head = head;
    }

    public Nodo getTail() {
        return tail;
    }

    public void setTail(Nodo tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
