package Classes;

import interfaz.ColaUi;
import interfaz.GlobalUi;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La clase Administrador se encarga de gestionar las colas de personajes de Star Wars y Star Trek,
 * interactúa con la inteligencia artificial y actualiza la interfaz de usuario.
 */
public class Administrador extends Thread {

    // Contador utilizado para determinar cuándo crear nuevos personajes
    int counter = 0;
    // Controla la ejecución continua del hilo
    boolean running = true;

    // Instancia de InteligenciaArtificial para procesar los combates
    public InteligenciaArtificial ia;
    // Semáforo para controlar el acceso concurrente a recursos compartidos
    public Semaphore mutex;

    // Colas de personajes para Star Wars y Star Trek en diferentes niveles de prioridad
    public Cola starwarsColaNivel1;
    public Cola starwarsColaNivel2;
    public Cola starwarsColaNivel3;
    public Cola startrekColaNivel1;
    public Cola startrekColaNivel2;
    public Cola startrekColaNivel3;
    public Cola starwarsRefuerzo;
    public Cola startrekRefuerzo;

    // Colas de la interfaz de usuario para actualizar la información visual
    public ColaUi colaStarwarsUi1;
    public ColaUi colaStarwarsUi2;
    public ColaUi colaStarwarsUi3;
    public ColaUi colaStarwarsUiRef;

    public ColaUi colaStartrekUi1;
    public ColaUi colaStartrekUi2;
    public ColaUi colaStartrekUi3;
    public ColaUi colaStartrekUiRef;

    // Generador de números aleatorios para probabilidades
    final Random porcentaje = new Random();

    /**
     * Constructor de la clase Administrador.
     * Inicializa las colas, el semáforo y crea los personajes iniciales.
     * También actualiza las colas en la interfaz y establece la instancia de IA.
     */
    public Administrador() {
        creacionColas();
        this.mutex = Main.mutex;

        // Creación de personajes iniciales para ambas series
        for (int i = 0; i < 10; i++) {
            this.createPersonajesIniciales("starwars");
            this.createPersonajesIniciales("startrek");
        }

        updateColasUi();

        // Impresión de las colas en la consola para depuración
        System.out.println("Cola número 1 de Star Trek: " + this.startrekColaNivel1.print());
        System.out.println("Cola número 2 de Star Trek: " + this.startrekColaNivel2.print());
        System.out.println("Cola número 3 de Star Trek: " + this.startrekColaNivel3.print());
        System.out.println("Cola número 1 de Star Wars: " + this.starwarsColaNivel1.print());
        System.out.println("Cola número 2 de Star Wars: " + this.starwarsColaNivel2.print());
        System.out.println("Cola número 3 de Star Wars: " + this.starwarsColaNivel3.print());

        this.ia = Main.ia;
    }

    /**
     * Inicializa las colas de personajes y las referencias a las colas de la interfaz de usuario.
     */
    private void creacionColas() {
        // Inicialización de colas para Star Trek
        this.startrekColaNivel1 = new Cola();
        this.startrekColaNivel2 = new Cola();
        this.startrekColaNivel3 = new Cola();

        // Inicialización de colas para Star Wars
        this.starwarsColaNivel1 = new Cola();
        this.starwarsColaNivel2 = new Cola();
        this.starwarsColaNivel3 = new Cola();

        // Inicialización de colas de refuerzo
        this.starwarsRefuerzo = new Cola();
        this.startrekRefuerzo = new Cola();

        // Referencias a las colas de la interfaz de usuario
        this.colaStarwarsUi1 = GlobalUi.getMainPage().getColaStarwarsUi1();
        this.colaStarwarsUi2 = GlobalUi.getMainPage().getColaStarwarsUi2();
        this.colaStarwarsUi3 = GlobalUi.getMainPage().getColaStarwarsUi3();
        this.colaStarwarsUiRef = GlobalUi.getMainPage().getColaStarwarsUiRef();

        this.colaStartrekUi1 = GlobalUi.getMainPage().getColaStartrekUi1();
        this.colaStartrekUi2 = GlobalUi.getMainPage().getColaStartrekUi2();
        this.colaStartrekUi3 = GlobalUi.getMainPage().getColaStartrekUi3();
        this.colaStartrekUiRef = GlobalUi.getMainPage().getColaStartrekUiRef();
    }

    /**
     * Método principal del hilo que ejecuta el ciclo del juego.
     * Controla la creación de personajes, actualización de colas y la interacción con la IA.
     */
    @Override
    public void run() {
        try {
            while (this.running) {
                // Adquisición del semáforo para acceso exclusivo
                this.mutex.acquire();
                this.setCounter(this.counter + 1);

                // Desencola personajes de refuerzo si es posible
                this.desencolarRefuerzoPersonaje(starwarsRefuerzo);
                this.desencolarRefuerzoPersonaje(startrekRefuerzo);

                // Cada 2 iteraciones, intenta agregar nuevos personajes
                if (this.counter >= 2) {
                    int result = porcentaje.nextInt(100);

                    this.addPersonaje("starwars", result);
                    this.addPersonaje("startrek", result);
                    this.setCounter(0);
                }

                // Actualiza los contadores de inanición y cambia prioridades si es necesario
                this.sumarContadorCambiarPrioridad(starwarsColaNivel2);
                this.sumarContadorCambiarPrioridad(starwarsColaNivel3);
                this.sumarContadorCambiarPrioridad(startrekColaNivel2);
                this.sumarContadorCambiarPrioridad(startrekColaNivel3);

                updateColasUi();

                // Obtiene los personajes para el enfrentamiento
                Personaje starwars = this.getPersonaColas(starwarsColaNivel1, starwarsColaNivel2, starwarsColaNivel3);
                Personaje startrek = this.getPersonaColas(startrekColaNivel1, startrekColaNivel2, startrekColaNivel3);

                // Pasa los personajes a la inteligencia artificial
                ia.setPersonaStarwars(starwars);
                ia.setPersonaStartrek(startrek);

                // Actualiza la interfaz de usuario con los personajes actuales
                GlobalUi.getMainPage().getUiStarWarsId().setText(("Star Wars - " + starwars.getId()));
                GlobalUi.getMainPage().getUiStarTrekId().setText(("Star Trek - " + startrek.getId()));
                GlobalUi.getMainPage().setPersonajesImgsUi();

                // Actualiza HP y calidad en la interfaz
                GlobalUi.getMainPage().getCalidadStarWarsUi().setText(Integer.toString((int) starwars.getCalidadFinal()));
                GlobalUi.getMainPage().getCalidadStarTrekUi().setText(Integer.toString((int) startrek.getCalidadFinal()));
                GlobalUi.getMainPage().getStarWarsHP().setText(Integer.toString(starwars.getFuerza()));
                GlobalUi.getMainPage().getStarTrekHP().setText(Integer.toString(startrek.getFuerza()));

                // Reinicia el contador de rondas de inanición para los personajes en combate
                if (starwars != null) {
                    starwars.setContadorRondas(0);
                }
                if (startrek != null) {
                    startrek.setContadorRondas(0);
                }

                updateColasUi();

                // Liberación del semáforo
                this.mutex.release();
                Thread.sleep(500);

            }
        } catch (InterruptedException e) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Agrega un nuevo personaje a su cola de prioridad correspondiente según la serie y el resultado aleatorio.
     *
     * @param serie  Serie del personaje ("starwars" o "startrek").
     * @param result Resultado aleatorio para determinar la creación del personaje.
     */
    private void addPersonaje(String serie, int result) {
        // Calcula la prioridad final basada en atributos
        int prioridadFinal = calidadFinal();

        // Con una probabilidad del 80%, crea y agrega el personaje
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

    /**
     * Crea los personajes iniciales para una serie específica y los coloca en sus colas de prioridad.
     *
     * @param serie Serie para la cual se crearán los personajes iniciales.
     */
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

    /**
     * Desencola un personaje de la cola de refuerzo con una probabilidad del 40% y lo coloca en la cola de prioridad 1.
     *
     * @param refuerzo Cola de refuerzo de la cual se intentará desencolar un personaje.
     */
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

    /**
     * Establece el valor del contador interno.
     *
     * @param counter Nuevo valor para el contador.
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Crea una instancia de Personaje con los atributos especificados.
     *
     * @param id           Identificador único del personaje.
     * @param serie        Serie a la que pertenece el personaje.
     * @param prioridad    Nivel de prioridad del personaje en las colas.
     * @param calidadFinal Valor que representa la calidad total del personaje.
     * @return Instancia de Personaje creada.
     */
    public Personaje crearPersonaje(int id, String serie, int prioridad, int calidadFinal) {
        return new Personaje(id, serie, prioridad, calidadFinal);
    }

    /**
     * Coloca un personaje en su cola correspondiente según su serie y prioridad.
     *
     * @param personaje Instancia de Personaje a encolar.
     */
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

    /**
     * Regresa un personaje a la cola de prioridad 1 de su serie.
     *
     * @param personaje Instancia de Personaje a regresar.
     */
    public void regresarPersonaCola1(Personaje personaje) {
        if (personaje.getSerie().equals("starwars")) {
            this.starwarsColaNivel1.encolar(personaje);
        } else if (personaje.getSerie().equals("startrek")) {
            this.startrekColaNivel1.encolar(personaje);
        }
    }

    /**
     * Incrementa el contador de inanición de cada personaje en la cola y ajusta su prioridad si ha esperado más de 8 rondas.
     *
     * @param cola Cola cuyos personajes serán evaluados.
     */
    private void sumarContadorCambiarPrioridad(Cola cola) {
        int longitud = cola.getSize();
        int i = 0;

        while (i < longitud) {
            Personaje personaje = cola.dispatch();
            personaje.setContadorRondas(personaje.getContadorRondas() + 1);

            if (personaje.getContadorRondas() >= 8) {
                if (personaje.getPrioridad() > 1) {
                    // Disminuye la prioridad del personaje
                    personaje.setPrioridad(personaje.getPrioridad() - 1);
                    // Lo coloca en la cola correspondiente según su nueva prioridad
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
                    // Si ya está en la prioridad más alta, lo regresa a la misma cola
                    cola.encolar(personaje);
                }
                personaje.setContadorRondas(1);
            } else {
                // Si no ha alcanzado 8 rondas de espera, lo regresa a la cola
                cola.encolar(personaje);
            }
            i++;
        }
    }

    /**
     * Envía personajes derrotados a sus respectivas colas de refuerzo.
     *
     * @param war  Personaje de Star Wars a enviar a refuerzo.
     * @param trek Personaje de Star Trek a enviar a refuerzo.
     */
    public void enviarPersonajesColaRefuerzo(Personaje war, Personaje trek) {
        if (war != null) {
            this.starwarsRefuerzo.encolar(war);
        }
        if (trek != null) {
            this.startrekRefuerzo.encolar(trek);
        }
    }

    /**
     * Obtiene un personaje de las colas de prioridad, dando preferencia a las colas de mayor prioridad.
     *
     * @param cola1 Cola de prioridad 1.
     * @param cola2 Cola de prioridad 2.
     * @param cola3 Cola de prioridad 3.
     * @return Personaje obtenido de las colas.
     */
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

    /**
     * Actualiza las representaciones de las colas en la interfaz de usuario.
     */
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

    /**
     * Regresa los personajes a sus colas de prioridad correspondientes después de un enfrentamiento.
     *
     * @param starwars Personaje de Star Wars a regresar.
     * @param startrek Personaje de Star Trek a regresar.
     */
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

    /**
     * Calcula la prioridad final de un personaje basándose en probabilidades asociadas a sus atributos.
     *
     * @return Entero que representa la prioridad final (1, 2 o 3).
     */
    public int calidadFinal() {
        int calidadHabilidad = 0;
        int prioridadRandom = porcentaje.nextInt(100);
        if (prioridadRandom <= 60) {
            calidadHabilidad = 1;
        }

        int calidadPuntosVida = 0;
        prioridadRandom = porcentaje.nextInt(100);
        if (prioridadRandom <= 70) {
            calidadPuntosVida = 1;
        }

        int calidadFuerza = 0;
        prioridadRandom = porcentaje.nextInt(100);
        if (prioridadRandom <= 50) {
            calidadFuerza = 1;
        }

        int calidadAgilidad = 0;
        prioridadRandom = porcentaje.nextInt(100);
        if (prioridadRandom <= 40) {
            calidadAgilidad = 1;
        }

        int prioridadFinal = 0;

        int sumaCalidad = calidadHabilidad + calidadPuntosVida + calidadFuerza + calidadAgilidad;
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

