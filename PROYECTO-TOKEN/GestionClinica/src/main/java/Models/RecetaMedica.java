/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Mario
 */
public class RecetaMedica {
    private int idReceta;
    private int idPaciente;
    private String pacienteNombre;
    private int idmedico;
    private String medicoNombre;
    private String indicaciones;
    private String fechaEmision;

    
    
    public int getidReceta() {
        return idReceta;
    }

    public void setidReceta(int idreceta) {
        this.idReceta = idreceta;
    }

    public int getidmedico() {
        return idmedico;
    }
    public String getMedicoNombre(){
        return medicoNombre;
    }
    public void setMedicoNombre(String medicoNombre){
        this.medicoNombre = medicoNombre;
    }

    public void setidmedico(int idmedico) {
        this.idmedico = idmedico;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public int getidPaciente() {
        return idPaciente;
    }

    public void setidPaciente(int idpaciente) {
        this.idPaciente = idpaciente;
    }
    
    
    
}
