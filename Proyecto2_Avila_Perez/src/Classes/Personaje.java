
package Classes;

import java.util.Random;

public class Personaje {
    private int id;
    private String idString;
    private String serie;
    private int prioridad;
    private int calidadFinal;
    private int contadorRondas;
    private int caballosFuerza;

    private Random r = new Random();

    public Personaje(int id, String serie, int prioridad, int calidadFinal) {
        this.id = id;
        this.serie = serie;
        this.prioridad = prioridad;
        this.calidadFinal = calidadFinal;
        this.contadorRondas = 0;
        this.caballosFuerza = r.nextInt(900 - 400) + 400;

    }


    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCaballosFuerza(){
        return this.caballosFuerza;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public double getCalidadFinal() {
        return calidadFinal;
    }

    public void setCalidadFinal(int calidadFinal) {
        this.calidadFinal = calidadFinal;
    }

    public int getContadorRondas() {
        return contadorRondas;
    }

    public void setContadorRondas(int contadorRondas) {
        this.contadorRondas = contadorRondas;
    }
    @Override
    public String toString() {
        return "Serie{" +
                "id=" + id +
                ", serie='" + serie + '\'' +
                ", prioridad=" + prioridad +
                ", calidadFinal=" + calidadFinal +
                ", contadorRondas=" + contadorRondas +
                '}';
    }
}
