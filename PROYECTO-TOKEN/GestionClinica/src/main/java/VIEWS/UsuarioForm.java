/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEWS;

import API.UsuarioApi;
import Models.Usuario;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class UsuarioForm extends javax.swing.JFrame {

   
    //metodo para limpiar campos
    private void limpiarForm() {

        txtUsuario.setText("");
        txtContra.setText("");
        txtRol.setText("");
        txtIDmedico.setText("");
    jCheckBoxActivo.setSelected(false); // Desmarca el checkbox
    }
    
    public UsuarioForm() {
        initComponents();
        cargarUsuarios();
        //copia los datos de la tabla a los txt
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            //verifica que no se este ajustando un doble evento
            if (!e.getValueIsAdjusting()&& jTable1.getSelectedRow()!= -1){
            int selectedRow = jTable1.getSelectedRow();
            
            //Obtener valores de la tabla y mostrarlos en los campos
            String Usuario = jTable1.getValueAt(selectedRow, 1).toString(); //devuelve el valor de una celda
            String Password = jTable1.getValueAt(selectedRow, 2).toString();
            String Rol = jTable1.getValueAt(selectedRow, 3).toString();
            String IDmedico = jTable1.getValueAt(selectedRow, 4) != null ? jTable1.getValueAt(selectedRow, 4).toString(): "";
            String Activo = jTable1.getValueAt(selectedRow, 6).toString();
            
            //actualizan con los datos seleccionados  de la tabla
            txtUsuario.setText(Usuario);
            txtContra.setText(Password);
            txtRol.setText(Rol);
            txtIDmedico.setText(IDmedico);
            jCheckBoxActivo.setSelected(Activo.equalsIgnoreCase("si"));
            }
        });
        SetDate();
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
  
  private void cargarUsuarios() {
      try{   
          UsuarioApi api = new UsuarioApi(); //clase que conecta con la Api
          List<Usuario> usuarios = api.getAllUsuarios();
          
          DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
          model.setRowCount(0); //Limpia la tabla antes de cargar
 
       
        for (Usuario u : usuarios) {
             
        
   
              model.addRow(new Object []{
                  u.getIdUsuario(),
                  u.getNombreUsuario(),
                  u.getContraseña(),
                  u.getRol(),
                  u.getIdmedico(),
                  u.getFechaRegistro(),
                  u.isActivo() ? "si" : "No"
              

              });
         }
          } catch (IOException e){
          JOptionPane.showMessageDialog(this, "Error al cargar los datos" +  e.getMessage());
      }
      
  }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonMenu = new javax.swing.JButton();
        jButtonLogout = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtContra = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtRol = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIDmedico = new javax.swing.JTextField();
        jCheckBoxActivo = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jButtonAgregar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.default.focusedBorderColor"));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelMenu.setBackground(new java.awt.Color(13, 71, 171));
        jPanelMenu.setPreferredSize(new java.awt.Dimension(270, 641));

        fecha.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        fecha.setForeground(new java.awt.Color(204, 255, 255));
        fecha.setText("Hoy  es {dayname}  {day} de {month} de {year}");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 204));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/small.png"))); // NOI18N
        jLabel5.setText("Clínica Médica");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/RegistroUsuarios.png"))); // NOI18N
        jLabel2.setText("Usuarios");

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

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMenuLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                        .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLogout)))
                .addGap(20, 20, 20))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuLayout.createSequentialGroup()
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelMenuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLogout))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Usuario");

        jLabel3.setText("Contraseña");

        jLabel4.setText("Rol");

        jLabel6.setText("ID Médico");

        jCheckBoxActivo.setText("Activo");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(jLabel4)
                        .addGap(33, 33, 33)
                        .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(690, 690, 690)
                        .addComponent(jCheckBoxActivo))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26)
                        .addComponent(txtIDmedico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRol, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))))
                .addGap(10, 10, 10)
                .addComponent(jCheckBoxActivo)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3))
                    .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtIDmedico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jButtonAgregar.setText("Guardar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Usuario", "Usuario", "Contraseña", "Rol", "ID Médico", "Fecha de Registro", "Activo"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1008, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 1120));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMenuActionPerformed
       Mainframe menu = new Mainframe ();
       menu.setLocationRelativeTo(null);
       menu.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButtonMenuActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed

try {
    // Obtener valores desde campos de texto
    String nombreUsuario = txtUsuario.getText().trim();
    String contraseña = txtContra.getText().trim();
    String rol = txtRol.getText().trim();
    String idMedicoTexto = txtIDmedico.getText().trim();

    // Validar campos vacíos
    if (nombreUsuario.isEmpty() || contraseña.isEmpty() || rol.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.");
        return;
    }
    
    //Validar los roles permitidos
   String [] rolesValidos  =  {"Administrador", "Recepcionista", "Medico"};
   boolean rolvalido = Arrays.stream(rolesValidos)
           .anyMatch(r -> r.equalsIgnoreCase(rol));
           
           if (!rolvalido){
               JOptionPane.showMessageDialog(this, "Ingrese un rol válido. Administrador, Recepcionista o Médico.");
               return;
           }

    Usuario usuario = new Usuario();
    usuario.setNombreUsuario(nombreUsuario);
    usuario.setContraseña(contraseña);
    usuario.setRol(rol);

 
    // Validar y asignar ID médico si no está vacío
    if (!idMedicoTexto.isEmpty()) {
        try {
            usuario.setIdmedico(Integer.parseInt(idMedicoTexto));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID de médico debe ser un número válido.");
            return;
        }
    } else {
        usuario.setIdmedico(null);
    }

    // Asignar estado del checkbox
    usuario.setActivo(jCheckBoxActivo.isSelected());

    // Agregar usuario usando la API
    UsuarioApi api = new UsuarioApi();
    
    //verifica si el usuario ya existe 
    List <Usuario> usuariosExistentes = api.getAllUsuarios();
    
    boolean usuarioRepetido = usuariosExistentes.stream()
            .anyMatch(u -> u.getNombreUsuario().equalsIgnoreCase(nombreUsuario));
    
    if (usuarioRepetido){
    JOptionPane.showMessageDialog(this, "El nombre de usuario ya está en uso. Intente con otro.");
    return;
    }
    //llamada de la api add
    api.addUsuario(usuario);
     
    limpiarForm ();
    // Refrescar lista de usuarios
    cargarUsuarios();

    JOptionPane.showMessageDialog(this, "Usuario agregado correctamente.");

} catch (Exception ex) {
    ex.printStackTrace(); // Para depuración
    JOptionPane.showMessageDialog(this, "Error al agregar usuario.");
}

    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
    //obtiene el indice de la fila seleccionada en la tabla
        int selectedRow = jTable1.getSelectedRow();
        //si no hay seleccion devuelve -1
        if (selectedRow == -1){
        JOptionPane.showMessageDialog(this, "Selecciona un usuario a modificar");
        return;
        }
        //bloque para manejar errores 
        try{
            //Obtine el id de la fila seleccionada
        int idUsuario = (int)jTable1.getValueAt(selectedRow,0);
        //lee los nuevos datos //trim elimina espacios
        String nombreUsuario = txtUsuario.getText().trim();
        String Contraseña = txtContra.getText().trim();
        String rol = txtRol.getText().trim();
        String idMedicoTexto = txtIDmedico.getText().trim();
        
         //verifica los campos esten completos
        if (nombreUsuario.isEmpty() || Contraseña.isEmpty()||rol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "por favor complete todos los campos.");
            return;
            }
        
        //crea un nuevo objeto usuario con el id original y nuevos datos se envia y actualiza
        Usuario usuario  = new Usuario ();
        usuario.setIdUsuario(idUsuario);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContraseña(Contraseña);
        usuario.setRol(rol);
        usuario.setActivo(jCheckBoxActivo.isSelected());


    // Validar y asignar ID médico si no está vacío
    if (!idMedicoTexto.isEmpty()) {
        try {
        usuario.setIdmedico(Integer.parseInt(idMedicoTexto));
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "ID de médico debe ser un número válido.");
        return;
        }
    } else {
        usuario.setIdmedico(null);
    }
        
        //llama la funcion modificar el usuario 
        UsuarioApi api = new UsuarioApi();
        boolean Resultado = api.updateUsuario(usuario);

        if (Resultado){
        JOptionPane.showMessageDialog(this,"Usuario modificado correctamente.");
        limpiarForm(); //limpia los campos de texto 
        cargarUsuarios(); //Actualiza tabla
        
        }else{
        JOptionPane.showMessageDialog(this,"Error al modificar usuario.");
        }
        }catch (Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Ocurrio un error al modificar el usuario.");
        }
      
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
     //obtiene el indice de la fila seleccionada en la tabla
    int filaSeleccionada = jTable1.getSelectedRow();
//no hay fila seleccionada devuelve -1
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione el Usuario a eliminar.");
        return;
    }
      // Obtener ID del alumno desde la tabla
    int idUsuario = (int) jTable1.getValueAt(filaSeleccionada, 0);

    //confirmacion para eliminar  el registro
    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este Usuario?", "Confirmar", 
    JOptionPane.YES_NO_OPTION);
    
    if (confirmacion != JOptionPane.YES_OPTION) return;
//llama metodo eliminar alumno
    //llama la funcion modificar el usuario 
    try{
        UsuarioApi api = new UsuarioApi();
        boolean Resultado = api.deleteUsuario(idUsuario);
 
        if (Resultado) {
            JOptionPane.showMessageDialog(this, "El usuario ha sido eliminado.");
            cargarUsuarios(); //Actualización de la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar usuario.");
        }
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error de conexión al intentar eliminar.");
    }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
   limpiarForm();
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
     Login login = new Login ();
     login.setLocationRelativeTo(null);
     login.setVisible(true);
     this.dispose();
    }//GEN-LAST:event_jButtonLogoutActionPerformed

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
            java.util.logging.Logger.getLogger(UsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuarioForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
 
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuarioForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fecha;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonMenu;
    private javax.swing.JCheckBox jCheckBoxActivo;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtContra;
    private javax.swing.JTextField txtIDmedico;
    private javax.swing.JTextField txtRol;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
