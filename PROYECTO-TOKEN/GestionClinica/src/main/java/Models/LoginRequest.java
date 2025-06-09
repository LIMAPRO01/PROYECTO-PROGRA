/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Administrador
 */
public class LoginRequest {
        private String nombreUsuario;
    private String contraseña;

     // GET obtiene (leer el valor)
    public String getNombreUsuario() { return nombreUsuario; }
    public String getContraseña() { return contraseña; }

    // SET modifica, cambia (escribir el valor)
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
}

