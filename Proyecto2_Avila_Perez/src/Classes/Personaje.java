package Classes;

import java.util.Random;

/**
 * La clase Personaje representa a un personaje del juego,
 * con atributos como id, serie, prioridad, calidad final, fuerza y contador de rondas.
 */
public class Personaje {

    // Identificador único del personaje
    private int id;
    // Cadena de texto opcional para el identificador (no se utiliza en el constructor)
    private String idString;
    // Serie a la que pertenece el personaje (por ejemplo, "starwars" o "startrek")
    private String serie;
    // Nivel de prioridad del personaje en las colas
    private int prioridad;
    // Calidad total del personaje, utilizada en la lógica del juego
    private int calidadFinal;
    // Contador de rondas de inanición, para manejar cambios de prioridad
    private int contadorRondas;
    // Fuerza o puntos de vida del personaje, asignados aleatoriamente
    private int fuerza;

    // Generador de números aleatorios para asignar la fuerza del personaje
    private Random r = new Random();

    /**
     * Constructor de la clase Personaje.
     * Inicializa un personaje con los atributos proporcionados y asigna una fuerza aleatoria.
     *
     * @param id            Identificador único del personaje.
     * @param serie         Serie a la que pertenece el personaje.
     * @param prioridad     Nivel de prioridad del personaje.
     * @param calidadFinal  Calidad total del personaje.
     */
    public Personaje(int id, String serie, int prioridad, int calidadFinal) {
        this.id = id;
        this.serie = serie;
        this.prioridad = prioridad;
        this.calidadFinal = calidadFinal;
        this.contadorRondas = 0;
        // Asigna una fuerza aleatoria entre 400 y 900
        this.fuerza = r.nextInt(900 - 400) + 400;
    }

    // Métodos getter y setter para acceder y modificar los atributos privados

    /**
     * Obtiene el identificador del personaje.
     *
     * @return El id del personaje.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del personaje.
     *
     * @param id El nuevo id del personaje.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la fuerza del personaje.
     *
     * @return La fuerza del personaje.
     */
    public int getFuerza() {
        return this.fuerza;
    }

    /**
     * Obtiene la serie a la que pertenece el personaje.
     *
     * @return La serie del personaje.
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Establece la serie a la que pertenece el personaje.
     *
     * @param serie La serie del personaje.
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * Obtiene el nivel de prioridad del personaje.
     *
     * @return La prioridad del personaje.
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * Establece el nivel de prioridad del personaje.
     *
     * @param prioridad La nueva prioridad del personaje.
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Obtiene la calidad total del personaje.
     *
     * @return La calidad final del personaje.
     */
    public double getCalidadFinal() {
        return calidadFinal;
    }

    /**
     * Establece la calidad total del personaje.
     *
     * @param calidadFinal La nueva calidad final del personaje.
     */
    public void setCalidadFinal(int calidadFinal) {
        this.calidadFinal = calidadFinal;
    }

    /**
     * Obtiene el contador de rondas de inanición del personaje.
     *
     * @return El contador de rondas.
     */
    public int getContadorRondas() {
        return contadorRondas;
    }

    /**
     * Establece el contador de rondas de inanición del personaje.
     *
     * @param contadorRondas El nuevo contador de rondas.
     */
    public void setContadorRondas(int contadorRondas) {
        this.contadorRondas = contadorRondas;
    }

    /**
     * Representación en cadena del objeto Personaje.
     *
     * @return Una cadena que describe el personaje.
     */
    @Override
    public String toString() {
        return "Serie{"
                + "id=" + id
                + ", serie='" + serie + '\''
                + ", prioridad=" + prioridad
                + ", calidadFinal=" + calidadFinal
                + ", contadorRondas=" + contadorRondas
                + '}';
    }
}

