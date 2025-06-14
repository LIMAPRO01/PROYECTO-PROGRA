package Models;


public class Paciente {
    private int idPaciente;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String genero;
    private String direccion;
    private String telefono;
    private String email;
    private String fechaRegistro;
    private String fechaActualizacion;
    private boolean activo;
    private String estado;

        public int getIdPaciente() {
            return idPaciente;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public String getFechaNacimiento() {
            return fechaNacimiento;
        }

        public String getGenero() {
            return genero;
        }

        public String getDireccion() {
            return direccion;
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

        public void setIdPaciente(int idPaciente) {
            this.idPaciente = idPaciente;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public void setFechaNacimiento(String fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
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
    

