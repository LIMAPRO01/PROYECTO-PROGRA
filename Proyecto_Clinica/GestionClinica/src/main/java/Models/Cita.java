package Models;

public class Cita {
    private int idCita;
    private int idPaciente;
    private String pacienteNombre;
    private int idmedico;
    private String medicoNombre;
    private String fechaCita;
    private String motivo;
    private String estado;
    
    private String fechaRegistro;

   
         public int getIdCita() {
            return idCita;
        }

        public int getidPaciente() {
            return idPaciente;
        }

        public String getPacienteNombre() {
            return pacienteNombre;
        }

        public int getidmedico() {
            return idmedico;
        }

        public String getMedicoNombre() {
            return medicoNombre;
        }

        public String getFechaCita() {
            return fechaCita;
        }

        public String getMotivo() {
            return motivo;
        }

       

        public String getFechaRegistro() {
            return fechaRegistro;
        }
        
        public void setIdCita(int idCita) {
            this.idCita = idCita;
        }
        public void setIdPaciente(int idPaciente){
            this.idPaciente = idPaciente;
        }

        public void setPacienteNombre(String pacienteNombre) {
            this.pacienteNombre = pacienteNombre;
        }

        public void setidmedico(int idmedico) {
            this.idmedico = idmedico;
        }

        public void setMedicoNombre(String medicoNombre) {
            this.medicoNombre = medicoNombre;
        }

        public void setFechaCita(String fechaCita) {
            this.fechaCita = fechaCita;
        }

        public void setMotivo(String motivo) {
            this.motivo = motivo;
        }

     

        public void setFechaRegistro(String fechaRegistro) {
            this.fechaRegistro = fechaRegistro;
        }

   
        
        public String getEstado() { return estado; } 
        public void setEstado(String estado) { this.estado = estado; } 

    
           @Override
    public String toString() {
        return idCita + " - " + motivo;
    }
}

