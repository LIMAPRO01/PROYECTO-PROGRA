/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionclinica;
import Models.Paciente;
import Models.Medico;
import Models.Usuario;
import Models.Cita;
import Models.DetalleReceta;
import Models.Factura;
import Models.Medicamento;
import Models.MovimientoStock;
import Models.RecetaMedica;
import API.UsuarioApi;
import API.PacienteApi;
import API.MedicoApi;
import API.CitaApi;
import API.DetalleRecetaApi;
import API.FacturaApi;
import API.MedicamentoApi;
import API.MovimientoStockApi;
import API.RecetaMedicaApi;
import VIEWS.Login;
import VIEWS.PacienteForm;
import VIEWS.UsuarioForm;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.swing.SwingUtilities;
/**
 *
 * @author Mario
 */
public class GestionClinica {
   
    public static void main(String[] args) {
        
         Login login = new Login ();
         login.setLocationRelativeTo(null);
        login.setVisible(true);
        
        
          //UsuarioForm usuario = new UsuarioForm ();
      // usuario.setVisible(true);
        
       
        
       // PacienteForm paciente = new PacienteForm ();
        //paciente.setVisible(true);
        
        
        
      /*  PacienteApi pacienteapi = new PacienteApi();
        MedicoApi medicoapi =  new MedicoApi();
        UsuarioApi usuarioApi = new UsuarioApi();
        CitaApi citaApi = new CitaApi();
        DetalleRecetaApi detalleRecetaApi = new DetalleRecetaApi();
        FacturaApi facturaApi = new FacturaApi();
        MedicamentoApi medicamentoApi = new MedicamentoApi();
        MovimientoStockApi movimientoStockApi = new MovimientoStockApi();
        RecetaMedicaApi recetaMedicaApi = new RecetaMedicaApi();
       
        
  
        try {
            List<Paciente> pacientes = pacienteapi.getAllPacientes();
            System.out.println(" Lista de pacientes:");

            for (Paciente p : pacientes) {
                System.out.println("ID: " + p.getIdPaciente());
                System.out.println("Nombre: " + p.getNombre() + " " + p.getApellido());
                System.out.println("Fecha nacimiento: " + p.getFechaNacimiento());
                System.out.println("Direccion: " + p.getDireccion());
                System.out.println("Telefono: " + p.getTelefono());
                System.out.println("Email: " + p.getEmail());
                System.out.println("Activo: " + p.isActivo());
                System.out.println("--------------------------");
            }

        } catch (IOException e) {
            System.out.println("❌ Error al obtener los pacientes:");
            e.printStackTrace();
        }
        try {
            List<Medico> medicos = medicoapi.getAllMedicos();
            System.out.println("📋 Lista de médicos:");

            for (Medico m : medicos) {
                System.out.println("ID: " + m.getIdmedico());
                System.out.println("Nombre: " + m.getNombre() + " " + m.getApellido());
                System.out.println("Especialidad: " + m.getEspecialidad());
                System.out.println("Teléfono: " + m.getTelefono());
                System.out.println("Email: " + m.getEmail());
                System.out.println("Fecha de registro: " + m.getFechaRegistro());
                System.out.println("Fecha de actualización: " + m.getFechaActualizacion());
                System.out.println("Activo: " + m.isActivo());
                System.out.println("Estado: " + m.getEstado());
                System.out.println("--------------------------");
            }

        } catch (IOException e) {
            System.out.println("❌ Error al obtener los médicos:");
            e.printStackTrace();
    }
        try {
    List<Usuario> usuarios = usuarioApi.getAllUsuarios();
    System.out.println("📋 Lista de usuarios:");

    for (Usuario u : usuarios) {
        System.out.println("ID: " + u.getIdUsuario());
        System.out.println("Usuario: " + u.getNombreUsuario());
        System.out.println("Contraseña: " + u.getContraseña()); // Opcional mostrar
        System.out.println("Rol: " + u.getRol());
        System.out.println("ID Médico asignado: " + u.getIdmedico());
        System.out.println("Fecha de registro: " + u.getFechaRegistro());
        System.out.println("Activo: " + u.isActivo());
        System.out.println("--------------------------");
    }

} catch (IOException e) {
    System.out.println("❌ Error al obtener los usuarios:");
    e.printStackTrace();
}
    try {
            List<Cita> citas = citaApi.getAllCitas();
            System.out.println("📋 Lista de citas:");
            for (Cita c : citas) {
                System.out.println("ID Cita: " + c.getIdCita());
                System.out.println("Paciente: " + c.getPacienteNombre());
                System.out.println("Médico: " + c.getMedicoNombre());
                System.out.println("Fecha Cita: " + c.getFechaCita());
                System.out.println("Motivo: " + c.getMotivo());
                System.out.println("Estado: " + c.getEstado());
                System.out.println("--------------------------");
            }
        } catch (IOException e) {
            System.out.println("❌ Error al obtener las citas:");
            e.printStackTrace();
        }

        try {
            List<DetalleReceta> detalles = detalleRecetaApi.getAllDetalleRecetas();
            System.out.println("📋 Lista de detalles de receta:");
            for (DetalleReceta d : detalles) {
                System.out.println("ID Detalle: " + d.getIdDetalle());
                System.out.println("ID Receta: " + d.getIdReceta());
                System.out.println("Medicamento: " + d.getMedicamentoNombre());
                System.out.println("Cantidad: " + d.getCantidad());
                System.out.println("Dosis: " + d.getDosis());
                System.out.println("--------------------------");
            }
        } catch (IOException e) {
            System.out.println("❌ Error al obtener los detalles de receta:");
            e.printStackTrace();
        }

        try {
            List<Factura> facturas = facturaApi.getAllFacturas();
            System.out.println("📋 Lista de facturas:");
            for (Factura f : facturas) {
                System.out.println("ID Factura: " + f.getIdFactura());
                System.out.println("Paciente: " + f.getPacienteNombre());
                System.out.println("Fecha Emisión: " + f.getFechaEmision());
                System.out.println("Total: " + f.getTotal());
                System.out.println("--------------------------");
            }
        } catch (IOException e) {
            System.out.println("❌ Error al obtener las facturas:");
            e.printStackTrace();
        }

        try {
            List<Medicamento> medicamentos = medicamentoApi.getAllMedicamentos();
            System.out.println("📋 Lista de medicamentos:");
            for (Medicamento m : medicamentos) {
                System.out.println("ID Medicamento: " + m.getIdMedicamento());
                System.out.println("Nombre: " + m.getNombre());
                System.out.println("Descripción: " + m.getDescripcion());
                System.out.println("Precio: " + m.getPrecio());
                System.out.println("Stock: " + m.getStock());
                System.out.println("--------------------------");
            }
        } catch (IOException e) {
            System.out.println("❌ Error al obtener los medicamentos:");
            e.printStackTrace();
        }

        try {
            List<MovimientoStock> movimientos = movimientoStockApi.getAllMovimientoStock();
            System.out.println("📋 Lista de movimientos de stock:");
            for (MovimientoStock ms : movimientos) {
                System.out.println("ID Movimiento: " + ms.getIdmovimiento());
                System.out.println("ID Medicamento: " + ms.getIdmedicamento());
                System.out.println("Tipo Movimiento: " + ms.getTipoMovimiento());
                System.out.println("Cantidad: " + ms.getCantidad());
                System.out.println("Fecha Movimiento: " + ms.getFechaMovimiento());
                System.out.println("Descripción: " + ms.getDescripcion());
                System.out.println("--------------------------");
            }
        } catch (IOException e) {
            System.out.println("❌ Error al obtener los movimientos de stock:");
            e.printStackTrace();
        }

        try {
            List<RecetaMedica> recetas = recetaMedicaApi.getAllRecetaMedicas();
            System.out.println("📋 Lista de recetas médicas:");
            for (RecetaMedica r : recetas) {
                System.out.println("ID Receta: " + r.getIdreceta());
                System.out.println("Paciente: " + r.getPacienteNombre());
                System.out.println("ID Médico: " + r.getIdmedico());
                System.out.println("Indicaciones: " + r.getIndicaciones());
                System.out.println("Fecha Emisión: " + r.getFechaEmision());
                System.out.println("--------------------------");
            }
        } catch (IOException e) {
            System.out.println("❌ Error al obtener las recetas médicas:");
            e.printStackTrace();
        }*/
      
       
       
       
    }     
        
}
   

        
    

