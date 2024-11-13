package Classes;

import interfaz.ColaUi;
import interfaz.GlobalUi;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InteligenciaArtificial extends Thread {

    private Administrador administrador;
    Personaje personaStarwars;
    Personaje personaStartrek;

    int simulationTime;
    Random random = new Random();
    public Semaphore mutex;

    public String[] winners = new String[500];

    private int winnersPointer = 0;
    public int starwarsWins = 0;
    public int startrekWins = 0;

    public InteligenciaArtificial() {
        this.mutex = Main.mutex;
        // 10 segundos por defecto
        this.simulationTime = 10 * 1000;
    }

    @Override
    public void run() {
        try {
            while (true) {

                long auxTime = simulationTime;
                this.mutex.acquire();

                if (this.personaStarwars == null || this.personaStartrek == null) {
                    Main.sistemaOperativo.regresarPersonajesAColas(personaStarwars, personaStartrek);
                    Thread.sleep(auxTime);

                } else {
                    // Decidiendo
                    System.out.println("Estoy decidiendo...");
                    GlobalUi.getMainPage().getStatusLabel().setText("Decidiendo...");
                    //tomar un número random para determinar ganador. 0=star wars, 1=star trek
                    Personaje winner = this.decideWinner();

                    Thread.sleep((long) (auxTime * 0.3));

                    // entero para determinar resultado de la simulación
                    int decision = random.nextInt(100);

                    if (decision <= 40) { //hay ganador
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
                            //sumar contador de carreras ganadas
                            GlobalUi.getMainPage().getStarWarsWinsLabel().setText(Integer.toString(starwarsWins));
                            //decir que gano
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
                        System.out.println("Hubo empate");
                        GlobalUi.getMainPage().getStatusLabel().setText("¡Hubo Empate!");
                        Thread.sleep((long) (auxTime * 0.5));
                        Main.sistemaOperativo.regresarPersonaCola1(personaStarwars);
                        Main.sistemaOperativo.regresarPersonaCola1(personaStartrek);
                    } else { //van a refuerzo
                        System.out.println("Nos vamos a refuerzo");
                        GlobalUi.getMainPage().getStatusLabel().setText("¡Vamos a refuerzo!");
                        Thread.sleep((long) (auxTime * 0.5));
                        //enviar a la cola de refuerzo
                        Main.sistemaOperativo.enviarPersonajesColaRefuerzo(this.personaStarwars, this.personaStartrek);
                    }
                    System.out.println("Esperando...");
                    GlobalUi.getMainPage().getStatusLabel().setText("Esperando...");
                }
                this.administrador.updateColasUi();

                Thread.sleep((long) (auxTime * 0.2));

                GlobalUi.getMainPage().getStarWarsWinnerLabel().setText("");
                GlobalUi.getMainPage().getStarTrekWinnerLabel().setText("");

                this.mutex.release();
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(InteligenciaArtificial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String printWinners() {
        String text = "";
        for (int i = 0; i < winnersPointer; i++) {
            String value = winners[i];
            text += value + ", ";
        }
        return text;
    }

    public void setPersonaStarwars(Personaje nuevoStarwars) {
        this.personaStarwars = nuevoStarwars;
    }

    public void setPersonaStartrek(Personaje nuevoStartrek) {
        this.personaStartrek = nuevoStartrek;
    }

    public void setSimulationTime(int timeSec) {
        this.simulationTime = timeSec * 1000;
    }

    public void setAdministrador() {
        this.administrador = Main.sistemaOperativo;
    }

    public Personaje decideWinner() {

        Personaje winner = null;

        // si la diferencia de fuerza es <= 50, entonces gana el de mejor calidad, si es la misma, gana el que tenga mas fueza
        if (Math.abs(personaStarwars.getFuerza() - personaStartrek.getFuerza()) <= 50) {

            if (this.personaStartrek.getCalidadFinal() < this.personaStarwars.getCalidadFinal()) {
                winner = this.personaStartrek;
            } else if (this.personaStarwars.getCalidadFinal() < this.personaStartrek.getCalidadFinal()) {
                winner = this.personaStarwars;
            } else if (this.personaStartrek.getCalidadFinal() == this.personaStarwars.getCalidadFinal()) { //misma calidad
                winner = this.winnerByHP();
            }

        } else { // la diferencia es mayor a 50 fuerzas, gana el que tenga mas fuerza
            winner = this.winnerByHP();
        }

        return winner;
    }

    public Personaje winnerByHP() {
        Personaje winner = null;

        if (personaStarwars.getFuerza() > personaStartrek.getFuerza()) {
            //star wars tiene mas HP
            winner = this.personaStarwars;
        } else if (personaStarwars.getFuerza() < personaStartrek.getFuerza()) {
            winner = this.personaStartrek;
        } else { // tienen la misma fuerza, se hace al azar
            int num = random.nextInt(2);
            winner = (num == 0) ? this.personaStarwars : this.personaStartrek;
        }

        return winner;
    }

}
