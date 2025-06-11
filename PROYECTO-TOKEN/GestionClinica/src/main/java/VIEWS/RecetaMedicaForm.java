package VIEWS;

import API.MedicoBox;
import API.PacienteApi;
import API.PacienteBox;
import API.RecetaMedicaApi;
import API.TokenAPI;
import Models.Medico;
import Models.Paciente;
import Models.RecetaMedica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RecetaMedicaForm extends JFrame {
   
    private PacienteBox pacienteBox;
    private MedicoBox medicoBox;
    private boolean ignorarEventosSeleccion = false;
   
    public RecetaMedicaForm() {
        // jComboBoxNP = new JComboBox<Models.Paciente>();
        // jComboBoxNM = new JComboBox<Models.Medico>();
        pacienteBox = new PacienteBox();
        medicoBox = new MedicoBox();

       // jComboBoxNP = new JComboBox<>();
        //jComboBoxNM = new JComboBox<>();
        txtindicaciones = new JTextField();
        txtfecha = new JTextField();
        
        jTable1 = new JTable();
        fecha = new JLabel();  
      
        
        initComponents();
        
        SetDate();
        cargarPacientesenCombo();
        cargarMedicosenCombo();
        verRecetasmedicas();
        
      jTable1.getSelectionModel().addListSelectionListener(e -> {
            //verifica que no se este ajustando un doble evento
            if (!e.getValueIsAdjusting()&& jTable1.getSelectedRow()!= -1){
            int selectedRow = jTable1.getSelectedRow();
            
            //Obtener valores de la tabla y mostrarlos en los campos
            int idpaciente = Integer.parseInt(jTable1.getValueAt(selectedRow, 1).toString());
            String pacienteNombre = jTable1.getValueAt(selectedRow, 2).toString();
            int idmedico = Integer.parseInt(jTable1.getValueAt(selectedRow, 3).toString());
            String medicoNombre = jTable1.getValueAt(selectedRow, 4).toString();
            String Indicaciones = jTable1.getValueAt(selectedRow, 5).toString();
            String FechaEmision = jTable1.getValueAt(selectedRow, 6).toString();
           
           
            
            //actualizan con los datos seleccionados  de la tabla
        
            txtindicaciones.setText(Indicaciones);
            txtfecha.setText(FechaEmision);
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
        txtindicaciones.setText("");
        txtfecha.setText("");
       
        jComboBoxNP.setSelectedIndex(-1); 
        jComboBoxNM.setSelectedIndex(-1);
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
private void llenarTablaConRecetaMedica(List<RecetaMedica> recetaMedica) {
    try {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // limpia la tabla
        
       
        for (RecetaMedica r : recetaMedica) {
        
      

            model.addRow(new Object[]{
                r.getidReceta(),
                r.getidPaciente(),
                r.getPacienteNombre(),
                r.getidmedico(),
                r.getMedicoNombre(),
                r.getIndicaciones(),
                r.getFechaEmision(),
               
                
               
            });
         }

    } catch (Exception e) {
     e.printStackTrace();
    }
}

//TOKEN
private void verRecetasmedicas() {
   try {
        String token = TokenAPI.getToken(); // Genera nuevo token cada vez
        RecetaMedicaApi api = new RecetaMedicaApi();
        List<RecetaMedica> recetaMedica = api.getAllRecetaMedicas();
        llenarTablaConRecetaMedica(recetaMedica);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + e.getMessage());
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxNP = new javax.swing.JComboBox();
        jComboBoxNM = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtindicaciones = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        Actualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
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
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recetasm.png"))); // NOI18N
        jLabel2.setText("RECETA MEDICA");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 319, Short.MAX_VALUE)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, -1));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NOMBRE PACIENTE");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NOMBRE MEDICO");

        jComboBoxNP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxNP.setToolTipText("");

        jComboBoxNM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("INDICACIONES");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA EMISION");

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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtindicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBoxNP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(txtindicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxNM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 910, 150));

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
                .addContainerGap(477, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Limpiar)
                        .addComponent(jButtonEliminar))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAgregar)
                        .addComponent(Actualizar)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 910, 80));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "ID.PACIENTE", "N.PACIENTE", "ID.MEDICO", "N.MEDICO", "INDICACIONES", "FECHA EMISION"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 870, 270));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            JOptionPane.showMessageDialog(this, "Selecciona una receta para actualizar.");
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
        String indicaciones = txtindicaciones.getText().trim();
        String fechaemision = txtfecha.getText().trim();
       

        if (indicaciones.isEmpty() || fechaemision.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa los campos obligatorios.");
            return;
        }
        
     

        RecetaMedica recetaMedica = new RecetaMedica();
        recetaMedica.setidReceta(id);
        recetaMedica.setidPaciente(idPacienteActualizado);
        recetaMedica.setidmedico(idMedicoActualizado);
        recetaMedica.setIndicaciones(indicaciones);
        recetaMedica.setFechaEmision(fechaemision);
        
        
        
    
        
        RecetaMedicaApi api = new RecetaMedicaApi();
        boolean resultado = api.updateRecetaMedica(recetaMedica); 

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Paciente actualizado correctamente.");
            limpiarCampos();
            verRecetasmedicas();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el paciente.");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
        
    }//GEN-LAST:event_ActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
      int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona una Receta para eliminar.");
        return;
    }

    int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
    
    int confirm = JOptionPane.showConfirmDialog(this,
        "¿Estás seguro de que deseas eliminar esta Receta?",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            RecetaMedicaApi api = new RecetaMedicaApi();
            boolean resultado = api.deleteRecetaMedica(id);
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Receta eliminada correctamente.");
                verRecetasmedicas();
                 limpiarCampos();
            // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la Receta.");
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
        String indicaciones = txtindicaciones.getText().trim();
        String fechaemision = txtfecha.getText().trim();
        
        // Validar campos vacios
        if (indicaciones.isEmpty() || fechaemision.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos obligatorios.");
            return;
        }
        
         
  DateTimeFormatter formatoEsperado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   try {
    LocalDate.parse(fechaemision, formatoEsperado);
} catch (DateTimeParseException e) {
    JOptionPane.showMessageDialog(null, "La fecha debe tener el formato dd/MM/yyyy (ejemplo: 31/05/2000).");
    return;
}
    
   
        // Crear receta
        RecetaMedica r = new RecetaMedica();
        // Asignar los IDs obtenidos de los objetos seleccionados en los combo boxes
        r.setidPaciente(pacienteSeleccionado.getIdPaciente());
        // Asegúrate de que tu clase Medico.java tenga un método getIdMedico() que coincida con el nombre de tu propiedad de ID
        r.setidmedico(medicoSeleccionado.getIdMedico());
        r.setIndicaciones(indicaciones);
        
        r.setFechaEmision(fechaemision); // Asegúrate de que el formato sea correcto
       
       
       
        
     DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
     String fechaHoy = LocalDate.now().format(formatoFecha);

        
        RecetaMedicaApi api = new RecetaMedicaApi();
        
         //verifica si el usuario ya existe 
    List <RecetaMedica> RecetasmedicasExistentes = api.getAllRecetaMedicas();
    boolean recetamedicaxistente = RecetasmedicasExistentes.stream()
            .anyMatch(u -> u.getPacienteNombre().equalsIgnoreCase(indicaciones));
    
    if (recetamedicaxistente){
    JOptionPane.showMessageDialog(this, "La Receta ya está registrado. (verifique el dato en la tabla).");
    return;
    }
    //Guardar y mostrar Resultado
         boolean Resultado = api.addRecetaMedica(r);
         
         if (Resultado) {
             JOptionPane.showMessageDialog(this, " Receta Agregada correctamente.");
             limpiarCampos();
             verRecetasmedicas();
         }else {
             JOptionPane.showMessageDialog(this, "Error al agregar La Rceta. (verifique los datos.)");
         }
    }catch (Exception ex) {
        ex.printStackTrace ();
        JOptionPane.showMessageDialog (this, "Error no se puede agregar: " + ex.getMessage());
    }
    
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
        limpiarCampos ();
    }//GEN-LAST:event_LimpiarActionPerformed

   
    public static void main(String args[]) {
          
        
         

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RecetaMedicaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecetaMedicaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecetaMedicaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecetaMedicaForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecetaMedicaForm().setVisible(true);
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
    private javax.swing.JComboBox jComboBoxNM;
    private javax.swing.JComboBox jComboBoxNP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtindicaciones;
    // End of variables declaration//GEN-END:variables
}
   
