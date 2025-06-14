/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEWS;

import API.CitaApi;
import API.MedicoBox;
import API.PacienteBox;
import API.TokenAPI;
import Models.Cita;
import Models.Medico;
import Models.Paciente;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows
 */
public class CitaForm extends javax.swing.JFrame {
 private PacienteBox pacienteBox;
    private MedicoBox medicoBox;
    private boolean ignorarEventosSeleccion = false;
    /**
     * Creates new form CitaForm
     */
    public CitaForm() {
        pacienteBox = new PacienteBox();
        medicoBox = new MedicoBox();
        
        initComponents();
        SetDate();
        cargarPacientesenCombo();
        cargarMedicosenCombo();
        verCita();
        
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            
            if (!e.getValueIsAdjusting()&& jTable1.getSelectedRow()!= -1){
            int selectedRow = jTable1.getSelectedRow();
            
            int idpaciente = Integer.parseInt(jTable1.getValueAt(selectedRow, 1).toString());
            String pacienteNombre = jTable1.getValueAt(selectedRow, 2).toString();
            int idmedico = Integer.parseInt(jTable1.getValueAt(selectedRow, 3).toString());
            String medicoNombre = jTable1.getValueAt(selectedRow, 4).toString();
            String fechaCita = jTable1.getValueAt(selectedRow, 5).toString();
            String motivo = jTable1.getValueAt(selectedRow, 6).toString();
            String estado = jTable1.getValueAt(selectedRow, 7).toString();
            String fechaRegistro = jTable1.getValueAt(selectedRow, 8).toString();
            jCheckBoxProgramada.setSelected(estado.equalsIgnoreCase("Programada"));
                        txtMotivoCita.setText(motivo);
            txtFechaCita.setText(fechaCita);
               // --- NEW FIX: Select the correct object in the JComboBox by ID ---
                ignorarEventosSeleccion = true; // Prevents triggering selection listeners during programmatic selection
               try {
    // Para jComboBoxNP (Paciente)
    for (int i = 0; i < jComboBoxNP.getItemCount(); i++) {
        Models.Paciente p = (Models.Paciente) jComboBoxNP.getItemAt(i);
        if (p != null && p.getIdPaciente() == idpaciente) { // idpaciente viene de la tabla
            jComboBoxNP.setSelectedItem(p); // Selecciona el OBJETO Paciente
            break;
        }
                    }

                  // Para jComboBoxNM (Medico)
    for (int i = 0; i < jComboBoxNM.getItemCount(); i++) {
        Models.Medico m = (Models.Medico) jComboBoxNM.getItemAt(i);
        if (m != null && m.getIdMedico() == idmedico) { // idmedico viene de la tabla (asumiendo getIdMedico())
            jComboBoxNM.setSelectedItem(m); // Selecciona el OBJETO Medico
            break;
        }
                    }
                } finally {
                    ignorarEventosSeleccion = false;
                }
                // --- END NEW FIX ---
            
               }
        });  
    }
       @SuppressWarnings("unchecked")  
       
        private void limpiarCampos() {
        txtMotivoCita.setText("");
        txtFechaCita.setText("");
        jCheckBoxProgramada.setSelected(false); 
        jComboBoxNP.setSelectedIndex(-1); 
        jComboBoxNM.setSelectedIndex(-1);
        jTable1.clearSelection();
    }
  private void SetDate() {
  LocalDate now = LocalDate.now();
  int year = now.getYear();
  int dia = now.getDayOfMonth();
  int month = now.getMonthValue();
  String [] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
  fecha.setText("Hoy es " +dia+ " de " + meses[month -1]+ " de " +year); 
}
          private void cargarPacientesenCombo() {
        try {
            List<Models.Paciente> pacientes = pacienteBox.getAllPacientes();
            // You had DefaultComboBoxModel<Models.Paciente> model = new DefaultComboBoxModel<>();
            // This is correct. Now you just need to SET this model to the JComboBox.

            DefaultComboBoxModel<Models.Paciente> model = new DefaultComboBoxModel<>(); // This is correct
            for (Models.Paciente p : pacientes) {
                model.addElement(p); // Add the actual Paciente object
            }

            // --- CRITICAL FIX: SET THE MODEL TO THE JCOMBOBOX ---
            if (jComboBoxNP == null) { // This check was useful, but now you need to set the model
                System.out.println("jComboBoxNP no está inicializado en cargarPacientesenCombo. Esto es un problema.");
                // Potentially throw an error or handle initialization here if initComponents() isn't reliable
                return; // Exit to prevent NullPointerException
            }
            jComboBoxNP.setModel(model); // <--- YOU WERE MISSING THIS LINE!
            // --- END CRITICAL FIX ---

            jComboBoxNP.setSelectedIndex(-1); // Opcional: seleccionar ningún elemento por defecto
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar Pacientes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }   
    }

    private void cargarMedicosenCombo() {
       try {
            List<Models.Medico> medicos = medicoBox.getAllMedicos();
            DefaultComboBoxModel<Models.Medico> model = new DefaultComboBoxModel<>(); // This is correct

            for (Models.Medico m : medicos) {
                model.addElement(m); // Add the actual Medico object
            }

            // --- CRITICAL FIX: SET THE MODEL TO THE JCOMBOBOX ---
            if (jComboBoxNM == null) {
                 System.out.println("jComboBoxNM no está inicializado en cargarMedicosenCombo. Esto es un problema.");
                 return; // Exit to prevent NullPointerException
            }
            jComboBoxNM.setModel(model); // <--- YOU WERE MISSING THIS LINE!
            // --- END CRITICAL FIX ---

            jComboBoxNM.setSelectedIndex(-1); // Opcional: seleccionar ningún elemento por defecto
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar Médicos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } 
    private void llenarTablaConCita(List<Cita> cita) {
    try {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // limpia la tabla
        
       
        for (Cita c : cita) {
        
      

            model.addRow(new Object[]{
                c.getIdCita(),
                c.getidPaciente(),
                c.getPacienteNombre(),
                c.getidmedico(),
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
   private void seleccionarComboPorNombre(JComboBox<ComboItem> comboBox, String nombreBuscado) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            if (comboBox.getItemAt(i).toString().equalsIgnoreCase(nombreBuscado)) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    // Clase interna para usar en los combos
    public class ComboItem {
        private int id;
        private String nombre;

        public ComboItem(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonMenu = new javax.swing.JButton();
        jButtonLogout = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxNP = new javax.swing.JComboBox();
        jComboBoxNM = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtMotivoCita = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFechaCita = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        Actualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        jCheckBoxProgramada = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(13, 71, 171));

        fecha.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fecha.setForeground(new java.awt.Color(204, 255, 255));
        fecha.setText("Hoy  es {dayname}  {day} de {month} de {year}");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 204));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small.png"))); // NOI18N
        jLabel5.setText("Clínica Médica");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calendario.png"))); // NOI18N
        jLabel2.setText("CITAS");

        jButtonMenu.setBackground(new java.awt.Color(13, 71, 171));
        jButtonMenu.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtonMenu.setForeground(new java.awt.Color(204, 255, 255));
        jButtonMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu.png"))); // NOI18N
        jButtonMenu.setText("Menú");
        jButtonMenu.setBorder(null);
        jButtonMenu.setBorderPainted(false);
        jButtonMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonMenu.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMenuActionPerformed(evt);
            }
        });

        jButtonLogout.setBackground(new java.awt.Color(13, 71, 171));
        jButtonLogout.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonLogout.setForeground(new java.awt.Color(204, 255, 255));
        jButtonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sale.png"))); // NOI18N
        jButtonLogout.setText("Cerrar sesión");
        jButtonLogout.setBorder(null);
        jButtonLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonLogout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonLogout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLogout)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLogout))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 170));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NOMBRE PACIENTE");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NOMBRE MEDICO");

        jComboBoxNP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxNP.setToolTipText("");

        jComboBoxNM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("MOTIVO DE CITA");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA CITA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxNP, 0, 181, Short.MAX_VALUE)
                    .addComponent(jComboBoxNM, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMotivoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(txtFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxNP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtMotivoCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxNM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 910, 150));

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

        jCheckBoxProgramada.setText("ESTADO");
        jCheckBoxProgramada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxProgramadaActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                .addComponent(jCheckBoxProgramada)
                .addGap(138, 138, 138))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Limpiar)
                        .addComponent(jButtonEliminar)
                        .addComponent(jCheckBoxProgramada))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAgregar)
                        .addComponent(Actualizar)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 910, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ID.PACIENTE", "N.PACIENTE", "ID.MEDICO", "N.MEDICO", "FECHA CITA", "MOTIVO", "ESTADO", "FECHA REGISTRO"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 910, 220));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuActionPerformed
        Mainframe menu = new Mainframe ();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonMenuActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        Login login = new Login ();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        try {
            int selectedRow = jTable1.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una Cita para actualizar.");
                return;
            }
            Paciente pacienteSeleccionado = (Paciente) jComboBoxNP.getSelectedItem();
            if (pacienteSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un Paciente.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int idPacienteActualizado = pacienteSeleccionado.getIdPaciente(); // ¡Obtenemos el ID del objeto seleccionado!

            Medico medicoSeleccionado = (Medico) jComboBoxNM.getSelectedItem();
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
            Paciente pacienteSeleccionado = (Paciente) jComboBoxNP.getSelectedItem();
            if (pacienteSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un Paciente.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener el objeto Medico seleccionado del jComboBoxNM
            // Asegúrate de que jComboBoxNM esté declarado como JComboBox<Models.Medico>
            Medico medicoSeleccionado = (Medico) jComboBoxNM.getSelectedItem();
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
            boolean esProgramada = jCheckBoxProgramada.isSelected();
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

    private void jCheckBoxProgramadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxProgramadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxProgramadaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CitaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CitaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CitaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CitaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CitaForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonMenu;
    private javax.swing.JCheckBox jCheckBoxProgramada;
    private javax.swing.JComboBox jComboBoxNM;
    private javax.swing.JComboBox jComboBoxNP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
