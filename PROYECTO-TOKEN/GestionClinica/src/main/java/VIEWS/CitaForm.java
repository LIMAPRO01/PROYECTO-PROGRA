
package VIEWS;

import API.CitaApi;
import API.MedicoApi;
import API.PacienteApi;
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
import Models.ComboItem;


public class CitaForm extends javax.swing.JFrame {

    private void limpiarCampos() {
        txtMC.setText("");
        txtbuscar.setText("");
        jCheckBoxActivo4.setSelected(false);
    }

    public CitaForm() {
        initComponents();
        SetDate();
        verCitas(); 
         // ✅ Inicialización manual si no lo hiciste desde el diseñador
        JComboPaciente = new JComboBox<>();
        JComboMedico = new JComboBox<>();
        cargarPacientesYMedicos();

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = jTable1.getSelectedRow();
            if (!e.getValueIsAdjusting() && selectedRow != -1) {
                
                String fechaCita = jTable1.getValueAt(selectedRow, 5).toString();
                String motivo = jTable1.getValueAt(selectedRow, 6).toString();
                String estado = jTable1.getValueAt(selectedRow, 7).toString();

                txtFC.setText(fechaCita);
                txtMC.setText(motivo);
                jCheckBoxActivo4.setSelected(estado.equalsIgnoreCase("Programada"));
            }
        });
    }
       private void cargarPacientesYMedicos() {
       try {
            PacienteApi pacienteApi = new PacienteApi();
            MedicoApi medicoApi = new MedicoApi();

            List<Paciente> pacientes = pacienteApi.getAllPacientes();
            List<Medico> medicos = medicoApi.getAllMedicos();

            // ✅ Cargar pacientes
          ComboItem itemPaciente = (ComboItem) JComboPaciente.getSelectedItem();
if (itemPaciente != null) {
    String nombrePaciente = itemPaciente.getValue();
    int idPaciente = itemPaciente.getId();
} else {
    System.out.println("⚠️ No hay paciente seleccionado aún.");

 
            }

            // ✅ Cargar médicos
            ComboItem itemMedico = (ComboItem) JComboMedico.getSelectedItem();
if (itemMedico != null) {
    String nombreMedico = itemMedico.getValue();
    int idMedico = itemMedico.getId();
} else {
    System.out.println("⚠️ No hay médico seleccionado aún.");
}


        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar pacientes o médicos.");
        }
    }

    private void SetDate() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int dia = now.getDayOfMonth();
        int month = now.getMonthValue();
        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        fecha.setText("Hoy es " + dia + " de " + meses[month - 1] + " de " + year);
    }

    private void llenarTablaConPacientes(List<Cita> citas) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            for (Cita c : citas) {
                model.addRow(new Object[]{
                    c.getIdCita(),
                    c.getIdPaciente(),
                    c.getPacienteNombre(),
                    c.getIdMedico(),
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


//TOKEN
private void verCitas() {
   try {
        String token = TokenAPI.getToken(); // Genera nuevo token cada vez
        CitaApi api = new CitaApi();
        List<Cita> citas = api.getAllCitas();
        llenarTablaConPacientes(citas);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + e.getMessage());
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
        jPanel9 = new javax.swing.JPanel();
        BtnActualizar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        BtnBuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jCheckBoxActivo4 = new javax.swing.JCheckBox();
        txtFC = new javax.swing.JTextField();
        txtMC = new javax.swing.JTextField();
        JComboPaciente = new javax.swing.JComboBox<>();
        JComboMedico = new javax.swing.JComboBox<>();
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

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 170));

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));

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

        BtnGuardar.setText("Agregar");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(BtnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
                .addComponent(BtnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGuardar)
                    .addComponent(BtnActualizar)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnLimpiar))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnBuscar)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 920, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel31.setText("Nombre pasiente");

        jLabel32.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel32.setText("Nombre medico");

        jLabel33.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel33.setText("F. Cita:");

        jLabel37.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel37.setText("Motivo Cita");

        jCheckBoxActivo4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jCheckBoxActivo4.setText("Estado");
        jCheckBoxActivo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActivo4ActionPerformed(evt);
            }
        });

        JComboPaciente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        JComboMedico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JComboPaciente, 0, 167, Short.MAX_VALUE)
                    .addComponent(JComboMedico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(49, 49, 49)
                        .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(txtMC, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                        .addComponent(jCheckBoxActivo4)
                        .addGap(77, 77, 77))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33)
                    .addComponent(txtFC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JComboPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxActivo4)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel37)
                            .addComponent(JComboMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 173, 920, 160));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID cita", "ID Paciente", "N.Paciente", "ID Medico", "N.Medico", "F.Cita", "Motivo cita", "Estado", "F. Registro"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 920, 230));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jCheckBoxActivo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxActivo4ActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
try {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona u   na cita para actualizar.");
            return;
        }

        // Obtener el ID de la cita desde la tabla
        int idCita = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());

        // Obtener objetos seleccionados del JComboBox
        ComboItem p = (ComboItem) JComboPaciente.getSelectedItem();
ComboItem m = (ComboItem) JComboMedico.getSelectedItem();

        if (p == null || m == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente y un médico.");
            return;
        }

        // Obtener otros datos del formulario
        String fechaCita = txtFC.getText().trim();
        String motivo = txtMC.getText().trim();
        String estado = jCheckBoxActivo4.isSelected() ? "Programada" : "Cancelada";

        if (fechaCita.isEmpty() || motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos obligatorios.");
            return;
        }

        // Convertir fecha al formato yyyy-MM-dd
        DateTimeFormatter formatoEsperado = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaCita, formatoEsperado);
        String fechaFormateada = fecha.toString(); // yyyy-MM-dd
        String fechaHoy = LocalDate.now().format(formatoEsperado);

        // Crear el objeto Cita
        Cita cita = new Cita();
        cita.setIdCita(idCita);
        cita.setIdPaciente(p.getId());
        cita.setIdMedico(m.getId());
        cita.setFechaCita(fechaFormateada);
        cita.setMotivo(motivo);
        cita.setEstado(estado);
        cita.setFechaRegistro(fechaHoy);
        cita.setPacienteNombre(p.toString()); // Solo para mostrar, opcional
        cita.setMedicoNombre(m.toString());   // Solo para mostrar, opcional

        // Llamar a la API
        CitaApi api = new CitaApi();
        boolean resultado = api.updateCita(cita);

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Cita actualizada correctamente.");
            limpiarCampos();
            verCitas();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la cita.");
        }

    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd-MM-yyyy.");
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un paciente para eliminar.");
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que deseas eliminar este paciente?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                CitaApi api = new CitaApi();
                boolean resultado = api.deleteCita(id);
                if (resultado) {
                    JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente.");
                    verCitas(); // Refresca la tabla
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el paciente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
                                           
    try {
        // Obtener objetos seleccionados de los JComboBox
        ComboItem itemPaciente = (ComboItem) JComboPaciente.getSelectedItem();
ComboItem itemMedico = (ComboItem) JComboMedico.getSelectedItem();

if (itemPaciente == null || itemMedico == null) {
    JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente y un médico.");
    return;
}

int idPaciente = itemPaciente.getId();
int idMedico = itemMedico.getId();
String nombrePaciente = itemPaciente.getValue();
String nombreMedico = itemMedico.getValue();


        if (itemPaciente == null || itemMedico == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente y un médico.");
            return;
        }

        
        String fechaCita = txtFC.getText().trim(); // dd-MM-yyyy
        String motivo = txtMC.getText().trim();

        if (fechaCita.isEmpty() || motivo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.");
            return;
        }

        // Convertir fecha al formato yyyy-MM-dd
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaCita, formatoEntrada);
        String fechaFormateada = fecha.toString(); // yyyy-MM-dd
        String fechaHoy = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Verificar si la cita ya existe (opcional)
        List<Cita> listaCitas = new CitaApi().getAllCitas();
        boolean existente = listaCitas.stream()
            .anyMatch(c -> c.getIdPaciente() == idPaciente && c.getFechaCita().equals(fechaFormateada));

        if (existente) {
            JOptionPane.showMessageDialog(this, "La cita ya está registrada.");
            return;
        }

        // Crear la nueva cita
        Cita c = new Cita();
        c.setIdPaciente(idPaciente);
        c.setIdMedico(idMedico);
        c.setFechaCita(fechaFormateada);
        c.setMotivo(motivo);
        c.setFechaRegistro(fechaHoy);
        c.setEstado("Programada");
        c.setPacienteNombre(itemPaciente.toString());
        c.setMedicoNombre(itemMedico.toString());

        // Guardar la cita
        boolean resultado = new CitaApi().addCita(c);
        if (resultado) {
            JOptionPane.showMessageDialog(this, "Cita agregada correctamente.");
            limpiarCampos();
            verCitas();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar la cita.");
        }

    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd-MM-yyyy.");
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al agregar cita: " + ex.getMessage());
    }


    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        limpiarCampos ();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        String dato = txtbuscar.getText().trim(); //Captura
        if (dato.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un dato para buscar. (ej. no.Telefonico o Nombre y Apellido).");
            return;
        }

        try {
            CitaApi api = new CitaApi();
            List<Cita> pacientes = api.buscarCitas(dato); //Api

            if (pacientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron pacientes con esos datoa.");
                return;
            }else {
                llenarTablaConPacientes(pacientes);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar paciente: " + ex.getMessage());
        }
    }//GEN-LAST:event_BtnBuscarActionPerformed

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
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JComboBox<String> JComboMedico;
    private javax.swing.JComboBox<String> JComboPaciente;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonMenu;
    private javax.swing.JCheckBox jCheckBoxActivo4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtFC;
    private javax.swing.JTextField txtMC;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
