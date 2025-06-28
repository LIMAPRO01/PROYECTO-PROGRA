/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEWS;

import API.CitaApi;
import API.DetalleFacturaApi;
import API.MedicamentoApi;
import API.PdfGenerator;
import Models.Cita;
import Models.DetalleFactura;
import Models.Medicamento;
import java.awt.BorderLayout;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class DetalleFacturaViews extends javax.swing.JPanel {
private int idFactura;
private JPanel  contentPanel;
private JLabel lblIDFactura;
    private int selectedDetalleId = -1;
    private DetalleFacturaApi detalleApi;
    
    private List<Medicamento> medicamentos;
    private List<Cita> citas;
    

  
    public DetalleFacturaViews(int idFactura, JPanel contentPanel) {
     this.idFactura = idFactura;
     this.contentPanel = contentPanel;

     
    // buscar el resto de datos dentro
     detalleApi = new DetalleFacturaApi();
     lblIDFactura = new JLabel("Factura ID:");
contentPanel.add(lblIDFactura);
       initComponents();
        cargarDatosCombos();
        cargarDetalles();
        lblIDFactura.setText(String.valueOf(idFactura));
        agregarEventos();
         SetDate();
        
    }

  
     private void cargarDetalles(){
  try {
        List<DetalleFactura> detalles = detalleApi.getAllDetalleFacturas();

        DefaultTableModel model = (DefaultTableModel) tblDetalleFacturas.getModel();
        model.setRowCount(0); // Limpiar tabla

        for (DetalleFactura d : detalles) {
            model.addRow(new Object[]{
                d.getIdDetalle(),
                d.getIdFactura(),
                d.getPacienteNombre(),
                d.getIdCita(),
                d.getMotivoCita(),
                d.getIdMedicamento(),
                d.getMedicamentoNombre(),
                d.getCantidad(),
                d.getSubtotal()
            });
        }
        // Ocultar columnas 1 (idCita) 
        tblDetalleFacturas.getColumnModel().getColumn(3).setMinWidth(0);
        tblDetalleFacturas.getColumnModel().getColumn(3).setMaxWidth(0);
        tblDetalleFacturas.getColumnModel().getColumn(3).setWidth(0);
     
        tblDetalleFacturas.getColumnModel().getColumn(5).setMinWidth(0);
        tblDetalleFacturas.getColumnModel().getColumn(5).setMaxWidth(0);
        tblDetalleFacturas.getColumnModel().getColumn(5).setWidth(0);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + e.getMessage());
    }
}
    
    
    private void agregarEventos() {   
        tblDetalleFacturas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int fila = tblDetalleFacturas.getSelectedRow();
                if (fila >= 0) {

             int idDetalle = (int) tblDetalleFacturas.getValueAt(fila, 0);
             int idFacutra = (int) tblDetalleFacturas.getValueAt(fila, 1);
             String paciente = tblDetalleFacturas.getValueAt(fila, 2).toString();
             int idCita = (int) tblDetalleFacturas.getValueAt(fila, 3); 
             String motivo = tblDetalleFacturas.getValueAt(fila, 4).toString();
             int idMedicamento = (int) tblDetalleFacturas.getValueAt(fila, 5);
             String nombreMedicamento = tblDetalleFacturas.getValueAt(fila, 6).toString();
             String cantidad = tblDetalleFacturas.getValueAt(fila, 7).toString();
             String subtotal = tblDetalleFacturas.getValueAt(fila, 8).toString();
             
    
              seleccionarComboPorCita(idCita);
               seleccionarComboPorMedicamento(idMedicamento);
               txtCantidad.setText(cantidad);
                txtsubtotal.setText(subtotal);
                selectedDetalleId = idDetalle;
         }
     }
});
    }
    
private void seleccionarComboPorCita(int motivo) {
    for (int i = 0; i < jComboBoxMotivoCita.getItemCount(); i++) {
        Cita c = (Cita) jComboBoxMotivoCita.getItemAt(i);
        if (c.getIdCita()== motivo) {
            jComboBoxMotivoCita.setSelectedIndex(i);
            break;
        }
    }
}
    //Método auxiliar para buscar el medicamento
    private void  seleccionarComboPorMedicamento(int Medicamento){
        for (int i = 0; i <jComboBoxMedicamento.getItemCount(); i ++){
            Medicamento med = (Medicamento) jComboBoxMedicamento.getItemAt(i);
            if  (med.getIdMedicamento() == Medicamento){
         jComboBoxMedicamento.setSelectedIndex(i);
            break;
    }
        }
    }

    private void limpiarCampos() {
         if  (jComboBoxMedicamento.getItemCount() > 0){
     jComboBoxMedicamento.setSelectedIndex (-1); 
    }
             if  (jComboBoxMotivoCita.getItemCount() > 0){
     jComboBoxMotivoCita.setSelectedIndex (-1); 
    }
        txtCantidad.setText("");
        txtsubtotal.setText("");
    }
     private void cargarDatosCombos() {
        //Llamar APIs para cargar listas
        MedicamentoApi medicamentoApi = new MedicamentoApi();
        CitaApi citaApi = new CitaApi();

        try {
            medicamentos = medicamentoApi.getAllMedicamentos();
            citas = citaApi.getAllCita();

            jComboBoxMedicamento.removeAllItems();
            for (Medicamento m : medicamentos) {
                jComboBoxMedicamento.addItem(m);  // el toString() mostrará el nombre
            }

            jComboBoxMotivoCita.removeAllItems();
            for (Cita c : citas) {
                jComboBoxMotivoCita.addItem(c);  // similar
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + ex.getMessage());
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        btnGenerarPdf = new javax.swing.JButton();
        jComboBoxMotivoCita = new javax.swing.JComboBox();
        jComboBoxMedicamento = new javax.swing.JComboBox();
        lblIDfactura = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        BtnEliminar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleFacturas = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        fecha.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fecha.setText("Hoy  es {dayname}  {day} de {month} de {year}");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factura111.png"))); // NOI18N
        jLabel2.setText("DETALLE FACTURA");

        btnRegresar.setBackground(new java.awt.Color(204, 204, 204));
        btnRegresar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(204, 255, 255));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/volver.png"))); // NOI18N
        btnRegresar.setBorder(null);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegresar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addGap(13, 13, 13))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegresar))
                        .addGap(16, 16, 16))))
        );

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("Motivo Cita");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Cantidad");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("Medicamento");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("CONSULTA");

        txtMonto.setEditable(false);
        txtMonto.setText("Q 100");
        txtMonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setText("TOTAL");

        txtsubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsubtotalActionPerformed(evt);
            }
        });

        btnGenerarPdf.setBackground(new java.awt.Color(242, 242, 242));
        btnGenerarPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pdf.png"))); // NOI18N
        btnGenerarPdf.setBorder(null);
        btnGenerarPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPdfActionPerformed(evt);
            }
        });

        jComboBoxMotivoCita.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una opción" }));

        jComboBoxMedicamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un opción" }));
        jComboBoxMedicamento.setToolTipText("");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jComboBoxMotivoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lblIDfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBoxMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(97, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerarPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMotivoCita, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(btnGenerarPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblIDfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)))))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(0, 153, 153));

        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnGuardar.setText("Registrar");
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnGuardar)
                .addGap(52, 52, 52)
                .addComponent(BtnEliminar)
                .addGap(46, 46, 46)
                .addComponent(BtnLimpiar)
                .addGap(394, 394, 394))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnGuardar)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnLimpiar))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tblDetalleFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idDetalle", "ID Factura", "Paciente", "ID Cita", "motivoCita", "ID Medicamento", "medicamentoNombre", "cantidad", "subtotal"
            }
        ));
        jScrollPane1.setViewportView(tblDetalleFacturas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
   contentPanel.removeAll();
   contentPanel.add(new FacturaViews(contentPanel), BorderLayout.CENTER);
   contentPanel.revalidate();
   contentPanel.repaint();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
int selectedRow = tblDetalleFacturas.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona un detalle para eliminar.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este detalle?", "Confirmar", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) return;
    
  int idDetalle = (int) tblDetalleFacturas.getValueAt(selectedRow, 0);
    
        try {
            boolean eliminado = detalleApi.deleteDetalleFactura(selectedDetalleId);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Detalle eliminado correctamente.");
                  limpiarCampos();
                cargarDetalles();
               selectedDetalleId = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar detalle.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor");
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed

  try {
        // Obtener los datos de los campos y combos
        Cita cita = (Cita) jComboBoxMotivoCita.getSelectedItem();
        Medicamento medicamento = (Medicamento) jComboBoxMedicamento.getSelectedItem();
          String textoCantidad = txtCantidad.getText().trim();
System.out.println("Valor de cantidad ingresado: '" + txtCantidad.getText() + "'");

// Validar cantidad antes de parsear
    if (textoCantidad.isEmpty() || !textoCantidad.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Cantidad inválida. Debe ser un número entero positivo.");
        return;
    }
    
    int cantidad = Integer.parseInt(textoCantidad);
        // Validación básica
        if (cita == null || medicamento == null || cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios y la cantidad debe ser mayor a cero.");
            return;
            
        }
        // Construir el detalle
        DetalleFactura detalle = new DetalleFactura();
        detalle.setIdFactura(idFactura); // Este idFactura ya te lo pasan desde FacturaViews
        detalle.setIdCita(cita.getIdCita());
        detalle.setMotivoCita(cita.getMotivo()); // opcional, si se usa en tabla
        detalle.setIdMedicamento(medicamento.getIdMedicamento());
        detalle.setMedicamentoNombre(medicamento.getNombre()); // opcional
        detalle.setCantidad(cantidad);

        // Enviar al backend
        boolean exito = detalleApi.addDetalleFactura(detalle);

       if (exito) {
        JOptionPane.showMessageDialog(this, "Detalle agregado correctamente.");
        cargarDetalles();
        limpiarCampos();
    } else {
        JOptionPane.showMessageDialog(this, "Error al agregar detalle.");
    }

} catch (IOException e) {
    JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: " + e.getMessage());
}

    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
limpiarCampos();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void btnGenerarPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPdfActionPerformed
        int fila = tblDetalleFacturas.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un detalle para generar el PDF.");
            return;
        }

        try {
            // Extraer datos seleccionados
            String factura = lblIDFactura.getText(); // o String.valueOf(idFactura)
            String paciente = tblDetalleFacturas.getValueAt(fila, 2).toString();
            String cita = tblDetalleFacturas.getValueAt(fila, 3).toString(); // ID de cita
            String motivo = tblDetalleFacturas.getValueAt(fila, 4).toString();
            String idMed = tblDetalleFacturas.getValueAt(fila, 5).toString();
            String medicamento = tblDetalleFacturas.getValueAt(fila,6 ).toString();
            String cantidad = tblDetalleFacturas.getValueAt(fila, 7).toString();
            String Total = tblDetalleFacturas.getValueAt(fila, 8).toString();

            // Seleccionar ruta del archivo
            String archivo = "detalle_factura_" + factura + "_detalle_" + tblDetalleFacturas.getValueAt(fila, 0) + ".pdf";

            // Llamar generador PDF
            PdfGenerator.generarDetalleFacturaPdf(
                    factura,
                    paciente,
                    cita,
                    motivo,
                    idMed,
                    medicamento,
                    cantidad,
                    Total,
                    archivo
            );

            JOptionPane.showMessageDialog(this, "PDF generado exitosamente: " + archivo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar PDF: " + ex.getMessage());
        }

    }//GEN-LAST:event_btnGenerarPdfActionPerformed

    private void txtsubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubtotalActionPerformed

    private void txtMontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton btnGenerarPdf;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel fecha;
    private javax.swing.JComboBox jComboBoxMedicamento;
    private javax.swing.JComboBox jComboBoxMotivoCita;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIDfactura;
    private javax.swing.JTable tblDetalleFacturas;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtsubtotal;
    // End of variables declaration//GEN-END:variables
}
