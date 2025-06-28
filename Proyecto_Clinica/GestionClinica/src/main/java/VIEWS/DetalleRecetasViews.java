/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package VIEWS;

import API.DetalleRecetaApi;
import API.MedicamentoApi;
import Models.DetalleReceta;
import Models.Medicamento;
import java.awt.BorderLayout;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class DetalleRecetasViews extends javax.swing.JPanel {
  private int idReceta;
      private JPanel contentPanel;
   
    public DetalleRecetasViews(int idReceta, JPanel contentPanel) {
          this.idReceta = idReceta;
          this.contentPanel = contentPanel;
        initComponents();
      
        cargarMedicamentos();// puedes usar el idReceta si lo necesitas aquí
        cargarDetallesReceta();
           LBlIDReceta.setText(String.valueOf(idReceta));
           
           //el listener  
           jTable1.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()){
            cargarDatosdeTabla();
        }
    });          
          
           SetDate();
    }
 
    private void cargarDetallesReceta() {
    try {
        DetalleRecetaApi api = new DetalleRecetaApi(); // clase de API
        List<DetalleReceta> detalles = api.getAllDetalleRecetas(idReceta);

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpia la tabla

        for (DetalleReceta d : detalles) {
            model.addRow(new Object[]{
                d.getIdDetalle(),
                d.getIdReceta(),
                d.getMedicamentoNombre(),
                d.getCantidad(),
                d.getDosis()
            });
        }
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + ex.getMessage());
        ex.printStackTrace();
    }
}
    
    
    private void cargarMedicamentos() {
    MedicamentoApi medicamentoApi = new MedicamentoApi();
 try {
    List<Medicamento> medicamento = medicamentoApi.getAllMedicamentos();
    
    jComboBoxMedicamento.removeAllItems(); //Limpia combo antes de agregar los nuevos

    for (Medicamento med : medicamento) {
        jComboBoxMedicamento.addItem(med); //añadir el medicamento al combo
    }
     } catch (IOException ex) {
       ex.printStackTrace(); // Muestra el error completo en consola 
       JOptionPane.showMessageDialog(null, "Error al cargar medicamentos");
     }
  }
    
    //Método auxiliar para buscar el medicamento
    private Medicamento buscarMedicamentoNombre(String Nombre){
        for (int i = 0; i <jComboBoxMedicamento.getItemCount(); i ++){
            Medicamento med = (Medicamento) jComboBoxMedicamento.getItemAt(i);
            if  (med.getNombre().equals(Nombre)) return med;
        }
        return null;
    }
    
     private void cargarDatosdeTabla (){
int filaSeleccionada = jTable1.getSelectedRow();
if (filaSeleccionada != -1){
    
    int idDetalle = (int) jTable1.getValueAt(filaSeleccionada, 0);
    int idReceta = (int) jTable1.getValueAt(filaSeleccionada, 1);
    String Nombre = jTable1.getValueAt(filaSeleccionada,2).toString();
      int cantidad = (int)jTable1.getValueAt(filaSeleccionada,3);
    String dosis = jTable1.getValueAt(filaSeleccionada, 4).toString();
  
 
    
            
    txtCantidad.setText(String.valueOf(cantidad));
    txtDosis.setText(dosis);
    
        for (int i = 0; i <jComboBoxMedicamento.getItemCount(); i ++){
            Medicamento m = (Medicamento) jComboBoxMedicamento.getItemAt(i);
            if  (m.getNombre().equals(Nombre)) {
            jComboBoxMedicamento.setSelectedIndex(i);
            break;
            }
        }
}
     }
     private void limpiarCampos(){
          txtCantidad.setText("");
    txtDosis.setText("");
    jComboBoxMedicamento.setSelectedIndex(0);
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
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        LBlIDReceta = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxMedicamento = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        txtDosis = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        BtnRegistrar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        fecha.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fecha.setText("Hoy  es {dayname}  {day} de {month} de {year}");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recetasm.png"))); // NOI18N
        jLabel3.setText("Detalle de Recetas");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnRegresar)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRegresar)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID ", "Receta", "Medicamento", "Cantidad", "Dosis"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Receta:");

        LBlIDReceta.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("Medicamento:");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("Dosis:");

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setText("Cantidad:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBlIDReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(152, 152, 152)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(txtDosis))
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LBlIDReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxMedicamento, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))))
                        .addGap(75, 75, 75))))
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

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

        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(BtnRegistrar)
                .addGap(27, 27, 27)
                .addComponent(BtnActualizar)
                .addGap(35, 35, 35)
                .addComponent(BtnEliminar)
                .addGap(42, 42, 42)
                .addComponent(BtnLimpiar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnRegistrar)
                    .addComponent(BtnActualizar)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnLimpiar))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
             contentPanel.removeAll();
        contentPanel.add(new RecetasViews(contentPanel), BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarActionPerformed
        //Obtenemos el combo seleccionado
        try {
            Medicamento medSeleccionado = (Medicamento) jComboBoxMedicamento.getSelectedItem();
            if (medSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un medicamento.");
                return;
            }
            String cantidadTexto = txtCantidad.getText().trim();

            // Verifica que no esté vacío y que solo tenga números
            if (cantidadTexto.isEmpty() || !cantidadTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.");
                return;
            }
            // Validar campo dosis
            String dosis = txtDosis.getText().trim();
            if (dosis.isEmpty() || !dosis.matches("^[0-9]+(mg|MG|Mg|mG)$")) {
                JOptionPane.showMessageDialog(this, "La dosis debe tener formato como '500mg'.");
                return;
            }
            //Creat objetos detalleReceta y llenar los datos
            DetalleReceta detalle = new DetalleReceta ();
            detalle.setIdReceta(idReceta);
            detalle.setIdMedicamento(medSeleccionado.getIdMedicamento());
            detalle.setMedicamentoNombre(medSeleccionado.getNombre());
            detalle.setCantidad(Integer.parseInt(txtCantidad.getText()));
            detalle.setDosis(txtDosis.getText());

            DetalleRecetaApi api = new DetalleRecetaApi();
            boolean Resultado = api.addDetalleReceta(detalle);
            if (Resultado) {
                cargarDetallesReceta ();
                limpiarCampos();

                JOptionPane.showMessageDialog(this, "El detalle ha sido agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo resgistrar el detalle.");
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar detalle" + e.getMessage());
        }
    }//GEN-LAST:event_BtnRegistrarActionPerformed

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(this, "Seleccione un detalle para actualizar.");
            return;
        }

        try {
            int idDetalle = (int)jTable1.getValueAt(fila, 0);
            Medicamento medSeleccionado = (Medicamento)jComboBoxMedicamento.getSelectedItem();
            String cantidadTexto = txtCantidad.getText().trim();

            // Verifica que no esté vacío y que solo tenga números
            if (cantidadTexto.isEmpty() || !cantidadTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.");
                return;
            }
            // Validar campo dosis
            String dosis = txtDosis.getText().trim();
            if (dosis.isEmpty() || !dosis.matches("^[0-9]+(mg|MG|Mg|mG)$")) {
                JOptionPane.showMessageDialog(this, "La dosis debe tener formato como '500mg'.");
                return;
            }
            DetalleReceta detalle = new DetalleReceta ();
            detalle.setIdDetalle(idDetalle);
            detalle.setIdReceta(idReceta); //ya esta en disponible en el form
            detalle.setIdMedicamento(medSeleccionado.getIdMedicamento());
            detalle.setMedicamentoNombre(medSeleccionado.getNombre());
            detalle.setCantidad(Integer.parseInt(txtCantidad.getText()));
            detalle.setDosis(txtDosis.getText());

            DetalleRecetaApi api = new DetalleRecetaApi();
            boolean actualizado = api.updateDetalleReceta(detalle);
            if (actualizado) {
                cargarDetallesReceta ();
                limpiarCampos();

                JOptionPane.showMessageDialog(this, "El detalle ha sido actualizado correctamente.");
            }else {
                JOptionPane.showMessageDialog(this, "Error al actualizar detalles.");
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar" + e.getMessage());
        }
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un detalle para eliminar.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este detalle?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            int idDetalle = (int) jTable1.getValueAt(fila, 0);
            DetalleRecetaApi api = new DetalleRecetaApi();
            boolean eliminado = api.deleteDetalleReceta(idDetalle);
            if (eliminado) {
                cargarDetallesReceta();
                limpiarCampos();

                JOptionPane.showMessageDialog(this, "Detalle eliminado correctamente.");

            }else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el detalle.");

            }
        }catch (Exception e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar." + e.getMessage());
        }
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_BtnLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JLabel LBlIDReceta;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel fecha;
    private javax.swing.JComboBox jComboBoxMedicamento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDosis;
    // End of variables declaration//GEN-END:variables
}
