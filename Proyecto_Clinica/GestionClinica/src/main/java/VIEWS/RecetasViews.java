/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEWS;

import API.MedicoApi;
import API.PacienteApi;
import API.PdfGenerator;
import API.RecetaMedicaApi;
import Models.Medico;
import Models.Paciente;
import Models.RecetaMedica;
import java.awt.BorderLayout;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class RecetasViews extends javax.swing.JPanel {
    private JPanel contentPanel;
    
private void limpiarCampos() {
    txtIndicaciones.setText("");
    txtFecha.setText("");
      if  (jComboBoxPacientes.getItemCount() > 0){
     jComboBoxPacientes.setSelectedIndex (-1); 
    }
    if (jComboBoxMedicos.getItemCount() > 0){
    jComboBoxMedicos.setSelectedIndex(-1);
    }
}
   
    public RecetasViews(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        initComponents();
        
         cargarPacientes();
        cargarMedicos();
          // Asociar el clic de la tabla a tu método
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jTable1MouseClicked(evt); // Llama tu lógica
        }
    });
       limpiarCampos();
        cargarRecetas();
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
//Cargar recetas en tabla
private void cargarRecetas() {
    try {
        RecetaMedicaApi api = new RecetaMedicaApi();
        List<RecetaMedica> lista = api.getAllRecetaMedicas();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar tabla

        for (RecetaMedica r : lista) {
            model.addRow(new Object[]{
                r.getIdreceta(),
                r.getPacienteNombre(),
                r.getMedicoNombre(),
                r.getIndicaciones(),
                r.getFechaEmision()
            });
        }

    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar recetas: " + ex.getMessage());
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

private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
 int filaSeleccionada = jTable1.rowAtPoint(evt.getPoint());  // Obtener la fila seleccionada
    if (filaSeleccionada != -1) {  // Asegurarnos de que haya una fila seleccionada
        // Obtener los datos de la fila (ID de receta, paciente, medico, etc.)
        int idReceta = (int) jTable1.getValueAt(filaSeleccionada, 0);  // ID de la receta
        String PacienteNombreCompleto = ((String) jTable1.getValueAt(filaSeleccionada, 1)).trim(); // Nombre paciente
        String MedicoNombreCompleto = ((String) jTable1.getValueAt(filaSeleccionada, 2)).trim(); // Nombre medico
        String indicaciones = ((String) jTable1.getValueAt(filaSeleccionada, 3)).trim(); // Indicaciones
        String fechaEmision = ((String) jTable1.getValueAt(filaSeleccionada, 4)).trim(); // Fecha emisión
        

//seleccionar paciente y médico en los combos
 seleccionarComboPorNombre(jComboBoxPacientes, PacienteNombreCompleto); 
 seleccionarComboPorNombre(jComboBoxMedicos, MedicoNombreCompleto); 
        // campos de Indicaciones y Fecha de emisión
        txtIndicaciones.setText(indicaciones);
        txtFecha.setText(fechaEmision);
        
        jComboBoxPacientes.repaint();
        jComboBoxMedicos.repaint();
    }
}
  private void SetDate() {
  LocalDate now = LocalDate.now();
  int year = now.getYear();
  int dia = now.getDayOfMonth();
  int month = now.getMonthValue();
  String [] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
  fecha1.setText("Hoy es " +dia+ " de " + meses[month -1]+ " de " +year); 
  }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        fecha1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxPacientes = new javax.swing.JComboBox();
        jComboBoxMedicos = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtIndicaciones = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        btnGenerar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        BtnRegistrar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnDetalle = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        fecha1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fecha1.setText("Hoy  es {dayname}  {day} de {month} de {year}");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recetasm.png"))); // NOI18N
        jLabel3.setText("Recetas médicas");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("Paciente:");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("Médico:");

        txtIndicaciones.setColumns(20);
        txtIndicaciones.setRows(5);
        jScrollPane2.setViewportView(txtIndicaciones);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Indicaciones:");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Fecha:");

        btnGenerar.setBackground(new java.awt.Color(242, 242, 242));
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pdf.png"))); // NOI18N
        btnGenerar.setBorder(null);
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFecha)
                            .addComponent(jComboBoxPacientes, 0, 278, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(25, 25, 25)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addComponent(jComboBoxMedicos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62)
                .addComponent(btnGenerar)
                .addGap(22, 22, 22))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jComboBoxPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGenerar))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        BtnRegistrar.setText("Registrar");
        BtnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarActionPerformed(evt);
            }
        });

        BtnActualizar.setText("Actualizar");
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnDetalle.setText("Detalle De Receta");
        BtnDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDetalleActionPerformed(evt);
            }
        });

        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(BtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(410, 410, 410)
                    .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(537, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnActualizar)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnDetalle))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(BtnLimpiar)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Receta", "Paciente", "Medico", "Fecha", "Indicaciones"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarActionPerformed
        // Validar selección de paciente y médico
        Paciente pacienteSeleccionado = (Paciente) jComboBoxPacientes.getSelectedItem();
        Medico medicoSeleccionado = (Medico) jComboBoxMedicos.getSelectedItem();

        if (pacienteSeleccionado == null || medicoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente y un médico.");
            return;
        }

        // Validar indicaciones
        String indicaciones = txtIndicaciones.getText().trim();
        if (indicaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese las indicaciones.");
            return;
        }

        // Validar fecha (formato yyyy-MM-dd)
        String fechaTexto = txtFecha.getText().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(fechaTexto);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener formato dd/MM/yyyy.");
            return;
        }

        // Crear objeto RecetaMedica
        RecetaMedica receta = new RecetaMedica();
        receta.setIdpaciente(pacienteSeleccionado.getIdPaciente());
        receta.setPacienteNombre(pacienteSeleccionado.getNombre());
        receta.setIdmedico(medicoSeleccionado.getIdMedico());
        receta.setMedicoNombre(medicoSeleccionado.getNombre());
        receta.setIndicaciones(indicaciones);
        receta.setFechaEmision(fechaTexto);

        // Enviar al API
        try {
            RecetaMedicaApi api = new RecetaMedicaApi();
            boolean exito = api.addRecetaMedica(receta);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Receta registrada exitosamente.");
                cargarRecetas(); // actualizar la tabla
                limpiarCampos();        // método para limpiar inputs
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar la receta.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: " + ex.getMessage());
        }
    }//GEN-LAST:event_BtnRegistrarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una receta de la tabla");
            return;
        }

        try {
            // Obtener ID de receta desde la tabla
            int idReceta = Integer.parseInt(jTable1.getValueAt(filaSeleccionada, 0).toString());

            // Obtener paciente y médico seleccionados
            Paciente pacienteSeleccionado = (Paciente) jComboBoxPacientes.getSelectedItem();
            Medico medicoSeleccionado = (Medico) jComboBoxMedicos.getSelectedItem();

            if (pacienteSeleccionado == null || medicoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un paciente y un médico");
                return;
            }

            // Validar indicaciones
            String indicaciones = txtIndicaciones.getText().trim();
            if (indicaciones.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese las indicaciones");
                return;
            }

            // Crear el objeto receta
            RecetaMedica receta = new RecetaMedica();
            receta.setIdreceta(idReceta); // importante para actualizar
            receta.setIdpaciente(pacienteSeleccionado.getIdPaciente());
            receta.setIdmedico(medicoSeleccionado.getIdMedico());
            receta.setIndicaciones(indicaciones);
            receta.setFechaEmision(txtFecha.getText()); // puedes validarla si lo necesitas

            // Llamar API para actualizar
            RecetaMedicaApi api = new RecetaMedicaApi();
            boolean exito = api.updateRecetaMedica(receta);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Receta actualizada correctamente");
                cargarRecetas();  // recargar la tabla
                limpiarCampos(); // limpiar
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar la receta");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar receta: " + ex.getMessage());
        }
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una receta para eliminar.");
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que deseas eliminar esta receta médica?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                RecetaMedicaApi api = new RecetaMedicaApi();
                boolean resultado = api.deleteRecetaMedica(id);
                if (resultado) {
                    JOptionPane.showMessageDialog(this, "Receta médica eliminada correctamente.");
                    cargarRecetas(); // Refresca la tabla de recetas
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la receta médica.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la receta médica: " + ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDetalleActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una receta primero.");
            return;
        }

        int idReceta = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
        
    DetalleRecetasViews detallePanel = new DetalleRecetasViews(idReceta, contentPanel);

    // Reemplazar el contenido del panel principal
    contentPanel.removeAll();
    contentPanel.add(detallePanel, BorderLayout.CENTER);
    contentPanel.revalidate();
    contentPanel.repaint();
;
    }//GEN-LAST:event_BtnDetalleActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
  int fila = jTable1.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un fila para generar el PDF.");
            return;
        }
    String pacienteNombre = jComboBoxPacientes.getSelectedItem().toString(); 
    String medicoNombre = jComboBoxMedicos.getSelectedItem().toString();
    String indicaciones = txtIndicaciones.getText();
    String fechaEmision = txtFecha.getText(); 

    String nombreArchivo = "Receta_" + pacienteNombre.replace(" ", "_") + ".pdf";

    PdfGenerator.generarRecetaPdf(pacienteNombre, medicoNombre, fechaEmision, indicaciones, nombreArchivo);

    JOptionPane.showMessageDialog(this, "Receta generada con éxito en: " + nombreArchivo);
    }//GEN-LAST:event_btnGenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnDetalle;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JLabel fecha1;
    private javax.swing.JComboBox jComboBoxMedicos;
    private javax.swing.JComboBox jComboBoxPacientes;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextArea txtIndicaciones;
    // End of variables declaration//GEN-END:variables
}
