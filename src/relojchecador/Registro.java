/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojchecador;

public class Registro {

    private String evento ;
    private String fechaHora;

    /**
     * Default constructor.
     */
    public Registro() {
    }

    /**
     * Constructor
     *
     * @param evento
     * @param fechaHora
     */
    public Registro(String evento, String fechaHora) {
        this.evento = evento;
        this.fechaHora = fechaHora;

    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    

}
