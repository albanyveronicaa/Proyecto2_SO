package Classes;


public class Nodo {
    private Nodo next;
    private Personaje element;
    
    public Nodo(Personaje elemento){
        this.next = null;
        this.element = elemento;
    }


    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }


    public Personaje getElement() {
        return element;
    }

    public void setElement(Personaje element) {
        this.element = element;
    }
    
}
