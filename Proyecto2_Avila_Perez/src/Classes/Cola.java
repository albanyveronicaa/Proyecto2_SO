package Classes;

import javax.swing.JOptionPane;

/**
 * La clase Cola implementa una cola genérica para almacenar objetos de tipo Personaje.
 * Utiliza una estructura enlazada con nodos para mantener el orden FIFO (First In, First Out).
 */
public class Cola {

    // Referencia al primer nodo de la cola
    private Nodo head;
    // Referencia al último nodo de la cola
    private Nodo tail;
    // Tamaño actual de la cola
    private int size;

    /**
     * Constructor de la clase Cola.
     * Inicializa una cola vacía.
     */
    public Cola() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Vacía la cola, eliminando todos sus elementos.
     */
    public void empty() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Encola (agrega) un nuevo personaje al final de la cola.
     *
     * @param persona El objeto Personaje a encolar.
     */
    public void encolar(Personaje persona) {
        Nodo nuevo = new Nodo(persona);
        if (this.isEmpty()) {
            // Si la cola está vacía, head y tail apuntan al nuevo nodo
            head = tail = nuevo;
        } else {
            // Si no, se agrega al final y tail se actualiza
            tail.setNext(nuevo);
            tail = nuevo;
        }
        size++;
    }

    /**
     * Desencola (elimina) el primer elemento de la cola.
     * Si la cola está vacía, muestra un mensaje de error.
     */
    public void desencolar() {
        if (this.isEmpty()) {
            JOptionPane.showMessageDialog(null, "La cola está vacía");
        } else if (size == 1) {
            // Si solo hay un elemento, se vacía la cola
            this.empty();
        } else {
            // El head se mueve al siguiente nodo
            head = head.getNext();
            size--;
        }
    }

    /**
     * Obtiene y elimina el primer elemento de la cola.
     *
     * @return El objeto Personaje que estaba al frente de la cola.
     */
    public Personaje dispatch() {
        Personaje elemento = process();
        desencolar();
        return elemento;
    }

    /**
     * Obtiene (sin eliminar) el primer elemento de la cola.
     *
     * @return El objeto Personaje que está al frente de la cola.
     */
    public Personaje process() {
        return getHead().getElement();
    }

    /**
     * Imprime los elementos de la cola en formato de cadena.
     *
     * @return Una cadena con la información de los personajes en la cola.
     */
    public String print() {
        if (!this.isEmpty()) {
            String printCola = "";
            for (int i = 0; i < size; i++) {
                Nodo actual = head;
                this.desencolar();

                // Construye la representación en cadena del personaje
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

    // Métodos getter y setter para los atributos de la cola

    /**
     * Obtiene el nodo que está al frente de la cola.
     *
     * @return El nodo head de la cola.
     */
    public Nodo getHead() {
        return head;
    }

    /**
     * Establece el nodo que estará al frente de la cola.
     *
     * @param head El nodo que será el nuevo head.
     */
    public void setHead(Nodo head) {
        this.head = head;
    }

    /**
     * Obtiene el nodo que está al final de la cola.
     *
     * @return El nodo tail de la cola.
     */
    public Nodo getTail() {
        return tail;
    }

    /**
     * Establece el nodo que estará al final de la cola.
     *
     * @param tail El nodo que será el nuevo tail.
     */
    public void setTail(Nodo tail) {
        this.tail = tail;
    }

    /**
     * Obtiene el tamaño actual de la cola.
     *
     * @return El número de elementos en la cola.
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece el tamaño de la cola.
     *
     * @param size El nuevo tamaño de la cola.
     */
    public void setSize(int size) {
        this.size = size;
    }
}

