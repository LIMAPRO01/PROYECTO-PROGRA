
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models;

/**
 *
 * @author Mario
 */


public class Usuario {
    private int idusuario;
    private String usuario;
    private String contraseña;
    private String rol;
    private Integer idmedico;
    private String fechaRegistro;
    private boolean activo;
    
    public Usuario() {
    // Constructor vacío necesario para instancias sin argumentos
}

    public Usuario (String Usuario, String nombre, String rol, boolean activo){
        this.usuario = Usuario;
        this.rol = rol ;
        this.activo = activo;
        
    }
    
    public int getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(int idusuario) {
        this.idusuario = idusuario;
    }
    
    public String getNombreUsuario() {
        return usuario;
    }

    public void setNombreUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdmedico() {
        return idmedico;
    }

    public void setIdmedico(Integer idmedico) {
        this.idmedico = idmedico;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setVisible(boolean b) {
       
    }

    public Object getFechaRegistroString() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
