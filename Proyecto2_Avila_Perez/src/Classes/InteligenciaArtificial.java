package Classes;

import interfaz.ColaUi;
import interfaz.GlobalUi;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase InteligenciaArtificial representa la lógica de la simulación del combate entre personajes.
 * Extiende la clase Thread para ejecutarse en un hilo separado y maneja los enfrentamientos
 * entre personajes de Star Wars y Star Trek.
 */
public class InteligenciaArtificial extends Thread {

    // Referencia al Administrador que gestiona las colas y personajes
    private Administrador administrador;
    // Personajes actuales de cada serie
    Personaje personaStarwars;
    Personaje personaStartrek;

    // Tiempo de simulación por defecto (en milisegundos)
    int simulationTime;
    // Generador de números aleatorios para decisiones aleatorias
    Random random = new Random();
    // Semáforo para controlar el acceso concurrente
    public Semaphore mutex;

    // Array para almacenar los ganadores de las rondas
    public String[] winners = new String[500];

    // Puntero al último índice del array de ganadores
    private int winnersPointer = 0;
    // Contadores de victorias para cada serie
    public int starwarsWins = 0;
    public int startrekWins = 0;

    /**
     * Constructor de la clase InteligenciaArtificial.
     * Inicializa el semáforo y el tiempo de simulación.
     */
    public InteligenciaArtificial() {
        this.mutex = Main.mutex;
        // 10 segundos por defecto
        this.simulationTime = 10 * 1000;
    }

    /**
     * Método principal del hilo que controla la lógica de los enfrentamientos entre personajes.
     */
    @Override
    public void run() {
        try {
            while (true) {

                long auxTime = simulationTime;
                // Adquiere el semáforo para acceso exclusivo
                this.mutex.acquire();

                if (this.personaStarwars == null || this.personaStartrek == null) {
                    // Si no hay personajes disponibles, los regresa a las colas y espera
                    Main.sistemaOperativo.regresarPersonajesAColas(personaStarwars, personaStartrek);
                    Thread.sleep(auxTime);

                } else {
                    // Decidiendo el ganador del enfrentamiento
                    System.out.println("Estoy decidiendo...");
                    GlobalUi.getMainPage().getStatusLabel().setText("Decidiendo...");
                    // Determina el ganador basado en atributos y probabilidad
                    Personaje winner = this.decideWinner();

                    Thread.sleep((long) (auxTime * 0.3));

                    // Número aleatorio para determinar el resultado de la simulación
                    int decision = random.nextInt(100);

                    if (decision <= 40) { // Hay ganador
                        System.out.println("Ganó alguien");
                        GlobalUi.getMainPage().getStatusLabel().setText("¡Hay un ganador!");
                        String per = winner.getSerie();
                        if (per.equals("starwars")) {
                            winners[winnersPointer] = "Star Wars - " + Integer.toString(winner.getId());
                        } else if (per.equals("startrek")) {
                            winners[winnersPointer] = "Star Trek - " + Integer.toString(winner.getId());
                        }
                        if (winnersPointer < winners.length) {
                            winnersPointer++;
                        }

                        GlobalUi.getMainPage().getWinnersLabel().setText(printWinners());
                        if (winner.getSerie().equals("starwars")) {
                            this.starwarsWins++;
                            // Actualiza el contador de victorias
                            GlobalUi.getMainPage().getStarWarsWinsLabel().setText(Integer.toString(starwarsWins));
                            // Indica en la interfaz que Star Wars ganó
                            GlobalUi.getMainPage().getStarWarsWinnerLabel().setText("¡Ganador!");

                        } else if (winner.getSerie().equals("startrek")) {
                            this.startrekWins++;
                            GlobalUi.getMainPage().getStarTrekWinsLabel().setText(Integer.toString(startrekWins));
                            GlobalUi.getMainPage().getStarTrekWinnerLabel().setText("¡Ganador!");
                        }

                        System.out.println("Star Wars ganadas: " + starwarsWins);
                        System.out.println("Star Trek ganadas: " + startrekWins);
                        Thread.sleep((long) (auxTime * 0.5));

                    } else if (decision <= 27) {
                        // Hubo empate, los personajes regresan a la cola de prioridad 1
                        System.out.println("Hubo empate");
                        GlobalUi.getMainPage().getStatusLabel().setText("¡Hubo Empate!");
                        Thread.sleep((long) (auxTime * 0.5));
                        Main.sistemaOperativo.regresarPersonaCola1(personaStarwars);
                        Main.sistemaOperativo.regresarPersonaCola1(personaStartrek);
                    } else {
                        // Ambos personajes van a la cola de refuerzo
                        System.out.println("Nos vamos a refuerzo");
                        GlobalUi.getMainPage().getStatusLabel().setText("¡Vamos a refuerzo!");
                        Thread.sleep((long) (auxTime * 0.5));
                        // Enviar a la cola de refuerzo
                        Main.sistemaOperativo.enviarPersonajesColaRefuerzo(this.personaStarwars, this.personaStartrek);
                    }
                    System.out.println("Esperando...");
                    GlobalUi.getMainPage().getStatusLabel().setText("Esperando...");
                }
                // Actualiza las colas en la interfaz
                this.administrador.updateColasUi();

                Thread.sleep((long) (auxTime * 0.2));

                // Limpia los indicadores de ganador en la interfaz
                GlobalUi.getMainPage().getStarWarsWinnerLabel().setText("");
                GlobalUi.getMainPage().getStarTrekWinnerLabel().setText("");

                // Libera el semáforo
                this.mutex.release();
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(InteligenciaArtificial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Imprime la lista de ganadores acumulados.
     *
     * @return Cadena con los ganadores separados por comas.
     */
    private String printWinners() {
        String text = "";
        for (int i = 0; i < winnersPointer; i++) {
            String value = winners[i];
            text += value + ", ";
        }
        return text;
    }

    /**
     * Establece el personaje de Star Wars que participará en el enfrentamiento.
     *
     * @param nuevoStarwars Personaje de Star Wars.
     */
    public void setPersonaStarwars(Personaje nuevoStarwars) {
        this.personaStarwars = nuevoStarwars;
    }

    /**
     * Establece el personaje de Star Trek que participará en el enfrentamiento.
     *
     * @param nuevoStartrek Personaje de Star Trek.
     */
    public void setPersonaStartrek(Personaje nuevoStartrek) {
        this.personaStartrek = nuevoStartrek;
    }

    /**
     * Establece el tiempo de simulación en segundos.
     *
     * @param timeSec Tiempo en segundos.
     */
    public void setSimulationTime(int timeSec) {
        this.simulationTime = timeSec * 1000;
    }

    /**
     * Establece la referencia al Administrador del sistema.
     */
    public void setAdministrador() {
        this.administrador = Main.sistemaOperativo;
    }

    /**
     * Decide el ganador del enfrentamiento entre los personajes de Star Wars y Star Trek.
     *
     * @return Personaje ganador del enfrentamiento.
     */
    public Personaje decideWinner() {

        Personaje winner = null;

        // Si la diferencia de fuerza es <= 50, gana el de mejor calidad
        if (Math.abs(personaStarwars.getFuerza() - personaStartrek.getFuerza()) <= 50) {

            if (this.personaStartrek.getCalidadFinal() < this.personaStarwars.getCalidadFinal()) {
                winner = this.personaStartrek;
            } else if (this.personaStarwars.getCalidadFinal() < this.personaStartrek.getCalidadFinal()) {
                winner = this.personaStarwars;
            } else if (this.personaStartrek.getCalidadFinal() == this.personaStarwars.getCalidadFinal()) {
                // Misma calidad, decide por fuerza
                winner = this.winnerByHP();
            }

        } else {
            // La diferencia de fuerza es mayor a 50, gana el que tenga más fuerza
            winner = this.winnerByHP();
        }

        return winner;
    }

    /**
     * Determina el ganador basado en la fuerza (HP) de los personajes.
     *
     * @return Personaje ganador basado en fuerza.
     */
    public Personaje winnerByHP() {
        Personaje winner = null;

        if (personaStarwars.getFuerza() > personaStartrek.getFuerza()) {
            // Star Wars tiene más fuerza
            winner = this.personaStarwars;
        } else if (personaStarwars.getFuerza() < personaStartrek.getFuerza()) {
            // Star Trek tiene más fuerza
            winner = this.personaStartrek;
        } else {
            // Tienen la misma fuerza, se elige al azar
            int num = random.nextInt(2);
            winner = (num == 0) ? this.personaStarwars : this.personaStartrek;
        }

        return winner;
    }

}
