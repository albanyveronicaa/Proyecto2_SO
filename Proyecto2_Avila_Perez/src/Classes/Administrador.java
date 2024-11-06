package Classes;

import interfaz.ColaUi;
import interfaz.GlobalUi;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador extends Thread {

    int counter = 0;
    boolean running = true;

    public InteligenciaArtificial ia;
    public Semaphore mutex;

    public Cola starwarsColaNivel1;
    public Cola starwarsColaNivel2;
    public Cola starwarsColaNivel3;
    public Cola startrekColaNivel1;
    public Cola startrekColaNivel2;
    public Cola startrekColaNivel3;
    public Cola starwarsRefuerzo;
    public Cola startrekRefuerzo;

    // colas de la interfaz
    public ColaUi colaStarwarsUi1;
    public ColaUi colaStarwarsUi2;
    public ColaUi colaStarwarsUi3;
    public ColaUi colaStarwarsUiRef;

    public ColaUi colaStartrekUi1;
    public ColaUi colaStartrekUi2;
    public ColaUi colaStartrekUi3;
    public ColaUi colaStartrekUiRef;

    final Random porcentaje = new Random();

    public Administrador() {
        creacionColas();
        this.mutex = Main.mutex;
        for (int i = 0; i < 10; i++) {
            this.createPersonajesIniciales("starwars");
            this.createPersonajesIniciales("startrek");

        }
        updateColasUi();
        System.out.println("Cola número 1 de Star Trek: " + this.startrekColaNivel1.print());
        System.out.println("Cola número 2 de Star Trek: " + this.startrekColaNivel2.print());
        System.out.println("Cola número 3 de Star Trek: " + this.startrekColaNivel3.print());
        System.out.println("Cola número 1 de Star Wars: " + this.starwarsColaNivel1.print());
        System.out.println("Cola número 2 de Star Wars: " + this.starwarsColaNivel2.print());
        System.out.println("Cola número 3 de Star Wars: " + this.starwarsColaNivel3.print());
        this.ia = Main.ia;

    }

    private void creacionColas() {
        this.startrekColaNivel1 = new Cola();
        this.startrekColaNivel2 = new Cola();
        this.startrekColaNivel3 = new Cola();
        this.starwarsColaNivel1 = new Cola();
        this.starwarsColaNivel2 = new Cola();
        this.starwarsColaNivel3 = new Cola();
        this.starwarsRefuerzo = new Cola();
        this.startrekRefuerzo = new Cola();

        this.colaStarwarsUi1 = GlobalUi.getMainPage().getColaStarwarsUi1();
        this.colaStarwarsUi2 = GlobalUi.getMainPage().getColaStarwarsUi2();
        this.colaStarwarsUi3 = GlobalUi.getMainPage().getColaStarwarsUi3();
        this.colaStarwarsUiRef = GlobalUi.getMainPage().getColaStarwarsUiRef();

        this.colaStartrekUi1 = GlobalUi.getMainPage().getColaStartrekUi1();
        this.colaStartrekUi2 = GlobalUi.getMainPage().getColaStartrekUi2();
        this.colaStartrekUi3 = GlobalUi.getMainPage().getColaStartrekUi3();
        this.colaStartrekUiRef = GlobalUi.getMainPage().getColaStartrekUiRef();
    }

    @Override
    public void run() {
        try {
            while (this.running) {
                this.mutex.acquire();
                this.setCounter(this.counter + 1);

                this.desencolarRefuerzoPersonaje(starwarsRefuerzo);
                this.desencolarRefuerzoPersonaje(startrekRefuerzo);

                if (this.counter >= 2) {
                    int result = porcentaje.nextInt(100);

                    this.addPersonaje("starwars", result);
                    this.addPersonaje("startrek", result);
                    this.setCounter(0);
                }

                this.sumarContadorCambiarPrioridad(starwarsColaNivel2);
                this.sumarContadorCambiarPrioridad(starwarsColaNivel3);
                this.sumarContadorCambiarPrioridad(startrekColaNivel2);
                this.sumarContadorCambiarPrioridad(startrekColaNivel3);

                updateColasUi();

                // obtener los personajes
                Personaje starwars = this.getPersonaColas(starwarsColaNivel1, starwarsColaNivel2, starwarsColaNivel3);
                Personaje startrek = this.getPersonaColas(startrekColaNivel1, startrekColaNivel2, startrekColaNivel3);

                // pasarle a la ia los personajes
                ia.setPersonaStarwars(starwars);
                ia.setPersonaStartrek(startrek);

                //actualizar ids y personajes en interfaz
                GlobalUi.getMainPage().getUiStarWarsId().setText(("Star Wars - " + starwars.getId()));
                GlobalUi.getMainPage().getUiStarTrekId().setText(("Star Trek - " + startrek.getId()));
                GlobalUi.getMainPage().setPersonajesImgsUi();
               
                // actualizar HP y calidad en interfaz
                GlobalUi.getMainPage().getCalidadStarWarsUi().setText(Integer.toString((int) starwars.getCalidadFinal()));
                GlobalUi.getMainPage().getCalidadStarTrekUi().setText(Integer.toString((int) startrek.getCalidadFinal()));
                GlobalUi.getMainPage().getStarWarsHP().setText(Integer.toString(starwars.getFuerza()));
                GlobalUi.getMainPage().getStarTrekHP().setText(Integer.toString(startrek.getFuerza()));

                // setear en 0 el contador de inanicion de cada personaje
                if (starwars != null) {
                    starwars.setContadorRondas(0);
                }
                if (startrek != null) {
                    startrek.setContadorRondas(0);
                }

                updateColasUi();
                this.mutex.release();
                Thread.sleep(500);

            }

        } catch (InterruptedException e) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void addPersonaje(String serie, int result) { // todo agregar personaje a su cola de prioridad

        int prioridadFinal = calidadFinal();
        if (result <= 80) {
            if (serie.equals("starwars")) {
                Personaje starwars = this.crearPersonaje(result, serie, prioridadFinal, prioridadFinal);

                this.ponerPersonajeEnSuCola(starwars);

            } else if (serie.equals("startrek")) {
                Personaje startrek = this.crearPersonaje(result, serie, prioridadFinal, prioridadFinal);
                this.ponerPersonajeEnSuCola(startrek);
            }
        }
    }

    private void createPersonajesIniciales(String serie) {
        int prioridadFinal = calidadFinal();

        int result = porcentaje.nextInt(100);

        if (serie.equals("starwars")) {

            Personaje starwars = this.crearPersonaje(result, serie, prioridadFinal, prioridadFinal);

            if (starwars.getPrioridad() == 1) {
                this.starwarsColaNivel1.encolar(starwars);
            } else if (starwars.getPrioridad() == 2) {
                this.starwarsColaNivel2.encolar(starwars);
            } else if (starwars.getPrioridad() == 3) {
                this.starwarsColaNivel3.encolar(starwars);
            }
        } else if (serie.equals("startrek")) {
            Personaje startrek = this.crearPersonaje(result, serie, prioridadFinal, prioridadFinal);
            if (startrek.getPrioridad() == 1) {
                this.startrekColaNivel1.encolar(startrek);
            } else if (startrek.getPrioridad() == 2) {
                this.startrekColaNivel2.encolar(startrek);
            } else if (startrek.getPrioridad() == 3) {
                this.startrekColaNivel3.encolar(startrek);
            }
        }
    }

    private void desencolarRefuerzoPersonaje(Cola refuerzo) {
        if (refuerzo.isEmpty()) {
            return;
        } else {
            int result = porcentaje.nextInt(100);
            if (result <= 40) {
                Personaje personaje = refuerzo.dispatch();
                personaje.setPrioridad(1);
                this.regresarPersonaCola1(personaje);
            } else {
                Personaje personaje = refuerzo.dispatch();
                refuerzo.encolar(personaje);
            }
        }
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Personaje crearPersonaje(int id, String serie, int prioridad, int calidadFinal) {
        return new Personaje(id, serie, prioridad, calidadFinal);

    }

    public void ponerPersonajeEnSuCola(Personaje personaje) {
        if (personaje.getSerie().equals("starwars")) {
            if (personaje.getPrioridad() == 1) {
                this.starwarsColaNivel1.encolar(personaje);
            } else if (personaje.getPrioridad() == 2) {
                this.starwarsColaNivel2.encolar(personaje);
            } else {
                this.starwarsColaNivel3.encolar(personaje);
            }
        } else if (personaje.getSerie().equals("startrek")) {
            if (personaje.getPrioridad() == 1) {
                this.startrekColaNivel1.encolar(personaje);
            } else if (personaje.getPrioridad() == 2) {
                this.startrekColaNivel2.encolar(personaje);
            } else {
                this.startrekColaNivel3.encolar(personaje);
            }
        }
    }

    public void regresarPersonaCola1(Personaje serie) {
        if (serie.getSerie().equals("starwars")) {
            this.starwarsColaNivel1.encolar(serie);
        } else if (serie.getSerie().equals("startrek")) {
            this.startrekColaNivel1.encolar(serie);
        }
    }

    private void sumarContadorCambiarPrioridad(Cola cola) {
        int longitud = cola.getSize();
        int i = 0;

        while (i < longitud) {
            Personaje personaje = cola.dispatch();
            personaje.setContadorRondas(personaje.getContadorRondas() + 1);

            if (personaje.getContadorRondas() >= 8) {
                if (personaje.getPrioridad() > 1) {
                    personaje.setPrioridad(personaje.getPrioridad() - 1);
                    if (personaje.getSerie().equals("starwars")) {
                        if (personaje.getPrioridad() == 1) {
                            this.starwarsColaNivel1.encolar(personaje);
                        } else if (personaje.getPrioridad() == 2) {
                            this.starwarsColaNivel2.encolar(personaje);
                        } else if (personaje.getPrioridad() == 3) {
                            this.starwarsColaNivel3.encolar(personaje);
                        }
                    } else if (personaje.getSerie().equals("startrek")) {
                        if (personaje.getPrioridad() == 1) {
                            this.startrekColaNivel1.encolar(personaje);
                        } else if (personaje.getPrioridad() == 2) {
                            this.startrekColaNivel2.encolar(personaje);
                        } else if (personaje.getPrioridad() == 3) {
                            this.startrekColaNivel3.encolar(personaje);
                        }
                    }
                } else {
                    cola.encolar(personaje);
                }
                personaje.setContadorRondas(1);
            } else {
                cola.encolar(personaje);
            }
            i++;
        }
    }

    public void enviarPersonajesColaRefuerzo(Personaje war, Personaje trek) {
        if (war != null) {
            this.starwarsRefuerzo.encolar(war);
        }
        if (trek != null) {
            this.startrekRefuerzo.encolar(trek);
        }
    }

    private Personaje getPersonaColas(Cola cola1, Cola cola2, Cola cola3) {

        if (!cola1.isEmpty()) {
            return cola1.dispatch();
        } else if (!cola2.isEmpty()) {
            return cola2.dispatch();
        } else if (!cola3.isEmpty()) {
            return cola3.dispatch();
        }
        return null;
    }

    public void updateColasUi() {
        this.colaStarwarsUi1.updateUiQueue(starwarsColaNivel1);
        this.colaStarwarsUi2.updateUiQueue(starwarsColaNivel2);
        this.colaStarwarsUi3.updateUiQueue(starwarsColaNivel3);
        this.colaStarwarsUiRef.updateUiQueue(starwarsRefuerzo);

        this.colaStartrekUi1.updateUiQueue(startrekColaNivel1);
        this.colaStartrekUi2.updateUiQueue(startrekColaNivel2);
        this.colaStartrekUi3.updateUiQueue(startrekColaNivel3);
        this.colaStartrekUiRef.updateUiQueue(startrekRefuerzo);
    }

    public void regresarPersonajesAColas(Personaje starwars, Personaje startrek) {
        if (starwars != null) {
            if (starwars.getPrioridad() == 1) {
                this.starwarsColaNivel1.encolar(starwars);
            } else if (starwars.getPrioridad() == 2) {
                this.starwarsColaNivel2.encolar(starwars);
            } else if (starwars.getPrioridad() == 3) {
                this.starwarsColaNivel3.encolar(starwars);
            }
        }
        if (startrek != null) {
            if (startrek.getPrioridad() == 1) {
                this.startrekColaNivel1.encolar(startrek);
            } else if (startrek.getPrioridad() == 2) {
                this.startrekColaNivel2.encolar(startrek);
            } else if (startrek.getPrioridad() == 3) {
                this.startrekColaNivel3.encolar(startrek);
            }
        }
    }

    public int calidadFinal() {
        int calidadCarroceria = 0;
        int prioridadRandom = porcentaje.nextInt(100);
        if (prioridadRandom <= 60) {
            calidadCarroceria = 1;
        }
        int calidadChasis = 0;
        prioridadRandom = porcentaje.nextInt(100);
        if (prioridadRandom <= 70) {
            calidadChasis = 1;
        }

        int calidadMotor = 0;
        prioridadRandom = porcentaje.nextInt(100);
        if (prioridadRandom <= 50) {
            calidadMotor = 1;
        }

        int calidadRueda = 0;
        prioridadRandom = porcentaje.nextInt(100);
        if (prioridadRandom <= 40) {
            calidadRueda = 1;
        }
        int prioridadFinal = 0;
        int sumaCalidad = calidadCarroceria + calidadRueda + calidadMotor + calidadChasis;
        if (sumaCalidad >= 3) {
            prioridadFinal = 1;
        } else if (sumaCalidad == 2) {
            prioridadFinal = 2;
        } else {
            prioridadFinal = 3;
        }
        return prioridadFinal;
    }

}
