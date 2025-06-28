/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEWS;

import API.FacturaApi;
import API.PacienteApi;
import API.TokenAPI;
import Models.Factura;
import Models.Paciente;
import java.awt.BorderLayout;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class FacturaViews extends javax.swing.JPanel {
     private JPanel contentPanel;
     //metodo de limpiar 
     private void limpiarCampos() {
          if  (jComboBoxPaciente.getItemCount() > 0){
     jComboBoxPaciente.setSelectedIndex (-1); 
    }
       txtfecha.setText("");
       txtmonto.setText("");
      
   }
    public FacturaViews(JPanel contentPanel) {
        this.contentPanel = contentPanel;   
        initComponents();
        
        cargarPacientes();
         SetDate ();
       verFacturas();
     //copia los datos de la tabla a los txt
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            //verifica que no se este ajustando un doble evento
            if (!e.getValueIsAdjusting()&& jTable1.getSelectedRow()!= -1){
            int selectedRow = jTable1.getSelectedRow();
            
            //Obtener valores de la tabla y mostrarlos en los campos
            int idFactura = (int) Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString()); 
          int idPaciente = Integer.parseInt(jTable1.getValueAt(selectedRow, 1).toString()); //devuelve el valor de una celda
            String FechaEmision = jTable1.getValueAt(selectedRow, 3).toString();
            String Total = jTable1.getValueAt(selectedRow, 4).toString();
           
            
            //actualizan con los datos seleccionados  de la tabla
seleccionarComboPorId(jComboBoxPaciente, idPaciente);
            txtfecha.setText(FechaEmision);
            txtmonto.setText(Total);
           
            jComboBoxPaciente.repaint();
                       
            }
        });  
    }

 private void cargarPacientes() {
    try {
        PacienteApi pacienteApi = new PacienteApi();
        List<Paciente> pacientes = pacienteApi.getAllPacientes();
        jComboBoxPaciente.removeAllItems();
        for (Paciente p : pacientes) {
            jComboBoxPaciente.addItem(p);
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + ex.getMessage());
    }
 }
 
 private void seleccionarComboPorId(JComboBox<Paciente> combo, int idPaciente ) {
    for (int i = 0; i < combo.getItemCount(); i++) {
       Paciente p = combo.getItemAt(i);
        if (p.getIdPaciente() == idPaciente) {
            combo.setSelectedIndex(i);
            break;
        }
    }
}
   
    @SuppressWarnings("unchecked")
    
     private void SetDate() {
  LocalDate now = LocalDate.now();
  int year = now.getYear();
  int dia = now.getDayOfMonth();
  int month = now.getMonthValue();
  String [] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
  fecha.setText("Hoy es " +dia+ " de " + meses[month -1]+ " de " +year); 
}
private void llenarTablaConFacturas(List<Factura> facturas) {
    try {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // limpia la tabla
               
        for (Factura f : facturas) {            
     
            model.addRow(new Object[]{
                f.getIdFactura(),
                f.getIdPaciente(),
                f.getPacienteNombre(),
                f.getFechaEmision(),
                f.getTotal(),
               
            });
         }

    } catch (Exception e) {
     e.printStackTrace();
    }
}
//TOKEN
private void verFacturas() {
   try {
        String token = TokenAPI.getToken(); // Genera nuevo token cada vez
        FacturaApi api = new FacturaApi();
        List<Factura> facturas = api.getAllFacturas();
        llenarTablaConFacturas(facturas);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + e.getMessage());
    }
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        BtnActualizar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        txtmonto = new javax.swing.JTextField();
        jComboBoxPaciente = new javax.swing.JComboBox();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        fecha.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fecha.setText("Hoy  es {dayname}  {day} de {month} de {year}");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factura111.png"))); // NOI18N
        jLabel2.setText("FACTURA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 170));

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

        jButton1.setText("Detalle Factura");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 475, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(152, 152, 152))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGuardar)
                    .addComponent(BtnActualizar)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnLimpiar)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 1110, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Factura", "ID Paciente", "N.Paciente", "F.Emicion", "Monto"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1110, 250));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Paciente");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Fecha");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Monto");

        jComboBoxPaciente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "seleccionar un paciente" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jComboBoxPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(94, 94, 94))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 1110, 170));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
    try {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una factura de la tabla.");
            return;
        }

        int idFactura = (int) jTable1.getValueAt(selectedRow, 0);

        // Repetir validaciones igual que en agregar
        Paciente paciente = (Paciente) jComboBoxPaciente.getSelectedItem();
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente.");
            return;
        }
            

  String fechaTexto = txtfecha.getText().trim();
DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate fecha;

try {
    fecha = LocalDate.parse(fechaTexto, formato);
} catch (DateTimeParseException e) {
    JOptionPane.showMessageDialog(this, "La fecha debe estar en formato dd/MM/yyyy.");
    return;
}

     //el total
       double monto = Double.parseDouble(txtmonto.getText().trim());
       
       try {
    monto = Double.parseDouble(txtmonto.getText().trim());
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "El monto debe ser un número válido.");
    return; // Detiene el proceso si hay error
}

        // Crear factura actualizada
        Factura actualizada = new Factura();
        actualizada.setIdFactura(idFactura);
        actualizada.setIdPaciente(paciente.getIdPaciente());
        actualizada.setFechaEmision(fechaTexto);
        actualizada.setTotal(monto);

        FacturaApi api = new FacturaApi();
        api.updateFactura(actualizada);

        JOptionPane.showMessageDialog(this, "Factura actualizada correctamente.");
        limpiarCampos();
        verFacturas();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al actualizar factura.");
    }

    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una Factura para eliminar.");
            return;
        }

        int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que deseas eliminar esta Factura?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                FacturaApi api = new FacturaApi();
                boolean resultado = api.deleteFactura(id);
                if (resultado) {
                    JOptionPane.showMessageDialog(this, "Facura eliminada correctamente.");
                    verFacturas(); // Refresca la tabla
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar Factura.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
try {
        // Validar paciente
        Paciente paciente = (Paciente) jComboBoxPaciente.getSelectedItem();
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente.");
            return;
        }

        // Validar fecha
      String fechaTexto = txtfecha.getText().trim();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fecha;

    try {
        fecha = LocalDate.parse(fechaTexto, formato);
    } catch (DateTimeParseException e) {
        JOptionPane.showMessageDialog(this, "La fecha debe tener el formato dd/MM/yyyy.");
        return;
    }

        // Validar monto
     double monto;
    try {
        monto = Double.parseDouble(txtmonto.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El monto debe ser un número válido.");
        return;
    }
        // Crear factura
        Factura f = new Factura();
        f.setIdPaciente(paciente.getIdPaciente());
        f.setFechaEmision(fechaTexto);
        f.setTotal(monto);

        // Guardar
        FacturaApi api = new FacturaApi();
        api.addFactura(f);

        JOptionPane.showMessageDialog(this, "Factura agregada correctamente.");
        limpiarCampos();
        verFacturas();

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al agregar factura.");
    }
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        limpiarCampos ();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
    JOptionPane.showMessageDialog(this, "Primero selecciona una factura de la tabla.");
    return;
}
        int idFactura = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());
        //Abre el panel 
        
    DetalleFacturaViews detallePanel = new DetalleFacturaViews(idFactura,  contentPanel);
    
    
      contentPanel.removeAll();
      contentPanel.add(detallePanel, BorderLayout.CENTER);
      contentPanel.revalidate();
      contentPanel.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBoxPaciente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtmonto;
    // End of variables declaration//GEN-END:variables
}
