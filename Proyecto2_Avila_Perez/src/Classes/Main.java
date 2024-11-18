package Classes;

import interfaz.GlobalUi;
import java.util.concurrent.Semaphore;

/**
 * Clase principal que inicializa el sistema y lanza los hilos de ejecución.
 */
public class Main {

    // Semáforo utilizado para controlar el acceso concurrente a recursos compartidos
    public static Semaphore mutex = new Semaphore(1);
    // Instancia de InteligenciaArtificial que maneja los enfrentamientos
    public static InteligenciaArtificial ia = new InteligenciaArtificial();
    // Instancia de Administrador que gestiona las colas y personajes
    public static Administrador sistemaOperativo = new Administrador();

    /**
     * Método principal que inicia la ejecución del programa.
     *
     * @param args los argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Establece la referencia del administrador en la inteligencia artificial
        ia.setAdministrador();

        // Inicia los hilos de ejecución del administrador y la inteligencia artificial
        sistemaOperativo.start();
        ia.start();

        // Abre la interfaz gráfica de usuario principal
        GlobalUi.openMainPage();
    }

}

