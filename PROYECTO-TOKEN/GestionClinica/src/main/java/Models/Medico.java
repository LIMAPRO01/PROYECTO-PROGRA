/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Mario
 */
public class Medico {

    private int idmedico;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
    private String email;
    private String fechaRegistro;
    private String fechaActualizacion;
    private boolean activo;
    private String estado;

    // Getters y setters
     public int getIdMedico() {
            return idmedico;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public String getEspecialidad() {
            return especialidad;
        }

      
        public String getTelefono() {
            return telefono;
        }

        public String getEmail() {
            return email;
        }

        public String getFechaRegistro() {
            return fechaRegistro;
        }

        public String getFechaActualizacion() {
            return fechaActualizacion;
        }

        public boolean isActivo() {
            return activo;
        }

        public void setIdMedico(int idmedico) {
            this.idmedico = idmedico;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public void setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
        }

      
        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFechaRegistro(String fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }

        public void setFechaActualizacion(String fechaActualizacion) {
            this.fechaActualizacion = fechaActualizacion;
        }

        public void setActivo(boolean activo) {
            this.activo = activo;
        }
        
        public String getEstado() { return estado; } 
        public void setEstado(String estado) { this.estado = estado; } 
        
       
       @Override
public String toString() {
    return nombre + " " + apellido ; // Esto es lo que se mostrará en el JComboBox
}
    }
    

