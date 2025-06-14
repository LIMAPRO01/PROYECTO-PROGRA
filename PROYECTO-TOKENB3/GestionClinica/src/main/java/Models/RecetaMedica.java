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
    private int idpaciente;
    private String pacienteNombre;
    private int idmedico;
    private String medicoNombre;
    private String indicaciones;
    private String fechaEmision;

    
    
    public int getIdreceta() {
        return idReceta;
    }

    public void setIdreceta(int idreceta) {
        this.idReceta = idreceta;
    }

    public int getIdmedico() {
        return idmedico;
    }
    public String getMedicoNombre(){
        return medicoNombre;
    }
    public void setMedicoNombre(String medicoNombre){
        this.medicoNombre = medicoNombre;
    }

    public void setIdmedico(int idmedico) {
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

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }
    
    
    
}
