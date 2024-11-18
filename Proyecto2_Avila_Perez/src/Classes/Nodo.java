package Classes;

/**
 * La clase Nodo representa un nodo en una estructura enlazada,
 * utilizada para la implementación de una cola de personajes.
 * Cada nodo almacena un objeto de tipo Personaje y una referencia al siguiente nodo.
 */
public class Nodo {

    // Referencia al siguiente nodo en la estructura
    private Nodo next;
    // Elemento de tipo Personaje almacenado en el nodo
    private Personaje element;

    /**
     * Constructor de la clase Nodo.
     * Inicializa un nodo con el elemento proporcionado y sin referencia al siguiente nodo.
     *
     * @param elemento El objeto Personaje que se almacenará en el nodo.
     */
    public Nodo(Personaje elemento) {
        this.next = null;
        this.element = elemento;
    }

    /**
     * Obtiene el nodo siguiente en la estructura enlazada.
     *
     * @return El nodo siguiente.
     */
    public Nodo getNext() {
        return next;
    }

    /**
     * Establece el nodo siguiente en la estructura enlazada.
     *
     * @param next El nodo que será el siguiente.
     */
    public void setNext(Nodo next) {
        this.next = next;
    }

    /**
     * Obtiene el elemento Personaje almacenado en el nodo.
     *
     * @return El objeto Personaje almacenado.
     */
    public Personaje getElement() {
        return element;
    }

    /**
     * Establece el elemento Personaje almacenado en el nodo.
     *
     * @param element El objeto Personaje que se almacenará.
     */
    public void setElement(Personaje element) {
        this.element = element;
    }

}
