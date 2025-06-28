/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEWS;

import API.CitaApi;
import API.MedicoApi;
import API.PacienteApi;
import API.PdfGenerator;
import API.TokenAPI;
import Models.Cita;
import Models.Medico;
import Models.Paciente;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CitasViews extends javax.swing.JPanel {
private void limpiarCampos() {
    txtMotivoCita.setText("");
    txtFechaCita.setText("");
      if  (jComboBoxPacientes.getItemCount() > 0){
     jComboBoxPacientes.setSelectedIndex (-1); 
    }
    if (jComboBoxMedicos.getItemCount() > 0){
    jComboBoxMedicos.setSelectedIndex(-1);
    }
    jCheckBoxEstado.setSelected(false); // Desmarca el checkbox
}
   
    public CitasViews() {
        initComponents();
        cargarPacientes();
        cargarMedicos();
        verCita();
                 // Asociar el clic de la tabla a tu método
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt); // Llama tu lógica
        }
    });
         limpiarCampos();
         SetDate();
    }
    
     private void cargarPacientes() {
    try {
        PacienteApi pacienteApi = new PacienteApi();
        List<Paciente> pacientes = pacienteApi.getAllPacientes();
        jComboBoxPacientes.removeAllItems();
        for (Paciente p : pacientes) {
            jComboBoxPacientes.addItem(p);
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + ex.getMessage());
    }
}

private void cargarMedicos() {
    try {
        MedicoApi medicoApi = new MedicoApi();
        List<Medico> medicos = medicoApi.getAllMedicos();
        jComboBoxMedicos.removeAllItems();
        for (Medico m : medicos) {
            jComboBoxMedicos.addItem(m);
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar médicos: " + ex.getMessage());
    }
}
    private void llenarTablaConCita(List<Cita> cita) {
    try {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // limpia la tabla
        
       
        for (Cita c : cita) {      

            model.addRow(new Object[]{
                c.getIdCita(),
                c.getPacienteNombre(),
                c.getMedicoNombre(),
                c.getFechaCita(),
                c.getMotivo(),
                c.getEstado(),
                c.getFechaRegistro(),
                
            });
         }

    } catch (Exception e) {
     e.printStackTrace();
    }
}
    private void seleccionarComboPorNombre(JComboBox combo, String nombre) {
    for (int i = 0; i < combo.getItemCount(); i++) {
        Object item = combo.getItemAt(i);
        if (item.toString().equalsIgnoreCase(nombre)) {
            combo.setSelectedIndex(i);
            break;
        }
    }
}
private void verCita() {
   try {
        String token = TokenAPI.getToken(); // Genera nuevo token cada vez
        CitaApi api = new CitaApi();
        List<Cita> recetaMedica = api.getAllCita();
        llenarTablaConCita(recetaMedica);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar cita: " + e.getMessage());
    }
}
private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
    int filaSeleccionada = jTable1.rowAtPoint(evt.getPoint());
    if (filaSeleccionada != -1) {
        int id = (int) jTable1.getValueAt(filaSeleccionada, 0);
        String pacienteNombre = jTable1.getValueAt(filaSeleccionada, 1).toString();
        String medicoNombre = jTable1.getValueAt(filaSeleccionada, 2).toString();
        String fechaCita = jTable1.getValueAt(filaSeleccionada, 3).toString();
        String motivo = jTable1.getValueAt(filaSeleccionada, 4).toString();
        String estado = jTable1.getValueAt(filaSeleccionada, 5).toString();
        
        txtFechaCita.setText(fechaCita);
        txtMotivoCita.setText(motivo);
        jCheckBoxEstado.setSelected(estado.equalsIgnoreCase("Programada"));

        seleccionarComboPorNombre(jComboBoxPacientes, pacienteNombre);
        seleccionarComboPorNombre(jComboBoxMedicos, medicoNombre);
    }
}
  private void SetDate() {
  LocalDate now = LocalDate.now();
  int year = now.getYear();
  int dia = now.getDayOfMonth();
  int month = now.getMonthValue();
  String [] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
  fecha.setText("Hoy es " +dia+ " de " + meses[month -1]+ " de " +year); 
  }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxMedicos = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtMotivoCita = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFechaCita = new javax.swing.JTextField();
        jComboBoxPacientes = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        Actualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        jCheckBoxEstado = new javax.swing.JCheckBox();
        btnGenerarpdf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        fecha.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fecha.setText("Hoy  es {dayname}  {day} de {month} de {year}");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendario.png"))); // NOI18N
        jLabel2.setText("CITAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Nombre Paciente");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Nombre Médico");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Motivo de cita");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("Fecha de cita");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMotivoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(56, 56, 56)
                .addComponent(jLabel6)
                .addContainerGap(64, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(txtMotivoCita, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jComboBoxPacientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(txtFechaCita))
                .addGap(43, 43, 43))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        Limpiar.setText("Limpiar");
        Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimpiarActionPerformed(evt);
            }
        });

        jCheckBoxEstado.setBackground(new java.awt.Color(0, 153, 153));
        jCheckBoxEstado.setText("ESTADO");
        jCheckBoxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEstadoActionPerformed(evt);
            }
        });

        btnGenerarpdf.setBackground(new java.awt.Color(0, 153, 153));
        btnGenerarpdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pdf.png"))); // NOI18N
        btnGenerarpdf.setBorder(null);
        btnGenerarpdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarpdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButtonAgregar)
                .addGap(28, 28, 28)
                .addComponent(Actualizar)
                .addGap(34, 34, 34)
                .addComponent(jButtonEliminar)
                .addGap(36, 36, 36)
                .addComponent(Limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerarpdf, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jCheckBoxEstado)
                .addGap(36, 36, 36))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Limpiar)
                                .addComponent(jButtonEliminar)
                                .addComponent(jCheckBoxEstado))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonAgregar)
                                .addComponent(Actualizar))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnGenerarpdf)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "PACIENTE", "MEDICO", "FECHA CITA", "MOTIVO", "ESTADO", "FECHA REGISTRO"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1062, 1062, 1062)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        try {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una Cita para actualizar.");
                return;
            }
            Paciente pacienteSeleccionado = (Paciente) jComboBoxPacientes.getSelectedItem();
            if (pacienteSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un Paciente.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idPacienteActualizado = pacienteSeleccionado.getIdPaciente(); // ¡Obtenemos el ID del objeto seleccionado!

            Medico medicoSeleccionado = (Medico) jComboBoxMedicos.getSelectedItem();
            if (medicoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un Médico.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idMedicoActualizado = medicoSeleccionado.getIdMedico();
            // Obtener el ID del paciente desde la tabla
            int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());

            // Obtener datos actualizados de los campos
            String motivo = txtMotivoCita.getText().trim();
            String fechacita = txtFechaCita.getText().trim();

            if (motivo.isEmpty() || fechacita.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor completa los campos obligatorios.");
                return;
            }

            Cita cita = new Cita();

            cita.setIdCita(id);
            cita.setIdPaciente(idPacienteActualizado);
            cita.setidmedico(idMedicoActualizado);
            cita.setMotivo(motivo);
            cita.setFechaCita(fechacita);
            cita.setEstado("Programada");

            CitaApi api = new CitaApi();
            boolean resultado = api.updateCita(cita);

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Cita actualizada correctamente.");
                limpiarCampos();
                verCita();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la Cita.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_ActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una Cita para eliminar.");
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que deseas eliminar esta Cita?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                CitaApi api = new CitaApi();
                boolean resultado = api.deleteCita(id);
                if (resultado) {
                    JOptionPane.showMessageDialog(this, "Cita eliminada correctamente.");
                    verCita();
                    limpiarCampos();
                    // Refresca la tabla
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la Cita.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        try {
            // --- 1. Obtener y Validar las Selecciones de los ComboBox ---

            // Obtener el objeto Paciente seleccionado del jComboBoxNP
            // Asegúrate de que jComboBoxNP esté declarado como JComboBox<Models.Paciente>
            Paciente pacienteSeleccionado = (Paciente) jComboBoxPacientes.getSelectedItem();
            if (pacienteSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un Paciente.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener el objeto Medico seleccionado del jComboBoxNM
            // Asegúrate de que jComboBoxNM esté declarado como JComboBox<Models.Medico>
            Medico medicoSeleccionado = (Medico) jComboBoxMedicos.getSelectedItem();
            if (medicoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un Médico.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener y validar campos
            String motivo = txtMotivoCita.getText().trim();
            String fechacita = txtFechaCita.getText().trim();

            // Validar campos vacios
            if (motivo.isEmpty() || fechacita.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos obligatorios.");
                return;
            }

            DateTimeFormatter formatoEsperado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate.parse(fechacita, formatoEsperado);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "La fecha debe tener el formato dd/MM/yyyy (ejemplo: 31/05/2000).");
                return;
            }

            // Crear receta
            Cita c = new Cita();

            c.setIdPaciente(pacienteSeleccionado.getIdPaciente());
            c.setidmedico(medicoSeleccionado.getIdMedico());
            c.setMotivo(motivo);
            c.setFechaCita(fechacita);
            boolean esProgramada = jCheckBoxEstado.isSelected();
            c.setEstado(esProgramada ? "Programada" : "No Programada");

            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaHoy = LocalDate.now().format(formatoFecha);

            c.setFechaRegistro(fechaHoy);

            CitaApi api = new CitaApi();

            //verifica si el usuario ya existe
            List <Cita> CitasExistentes = api.getAllCita();
            boolean citasexistentes = CitasExistentes.stream()
            .anyMatch(u -> u.getidPaciente() == c.getidPaciente() &&
                u.getidmedico() == c.getidmedico() &&
                u.getFechaCita().equalsIgnoreCase(c.getFechaCita()));

            if (citasexistentes){
                JOptionPane.showMessageDialog(this, "La cita ya está registrado. (verifique el dato en la tabla).");
                return;
            }
            //Guardar y mostrar Resultado
            boolean Resultado = api.addCita(c);

            if (Resultado) {
                JOptionPane.showMessageDialog(this, " Cita Agregada correctamente.");
                limpiarCampos();
                verCita();
            }else {
                JOptionPane.showMessageDialog(this, "Error al agregar La Cita. (verifique los datos.)");
            }
        }catch (Exception ex) {
            ex.printStackTrace ();
            JOptionPane.showMessageDialog (this, "Error no se puede agregar: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiarCampos ();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void jCheckBoxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxEstadoActionPerformed

    private void btnGenerarpdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarpdfActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para generar el PDF.");
            return;
        }
    String pacienteNombre = jComboBoxPacientes.getSelectedItem().toString(); 
    String medicoNombre = jComboBoxMedicos.getSelectedItem().toString();
    String fechaCita = txtFechaCita.getText(); 
    String Motivo = txtMotivoCita.getText();

    String nombreArchivo = "Cita_" + pacienteNombre.replace(" ", "_") + ".pdf";

    PdfGenerator.generarCitaPdf(pacienteNombre, medicoNombre, fechaCita, Motivo, nombreArchivo);

    JOptionPane.showMessageDialog(this, "Cita generada con éxito en: " + nombreArchivo);
    }//GEN-LAST:event_btnGenerarpdfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Limpiar;
    private javax.swing.JButton btnGenerarpdf;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JCheckBox jCheckBoxEstado;
    private javax.swing.JComboBox jComboBoxMedicos;
    private javax.swing.JComboBox jComboBoxPacientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtFechaCita;
    private javax.swing.JTextField txtMotivoCita;
    // End of variables declaration//GEN-END:variables
}
