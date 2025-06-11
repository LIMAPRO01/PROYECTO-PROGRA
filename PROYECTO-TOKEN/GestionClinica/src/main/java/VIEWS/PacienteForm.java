/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEWS;
import API.PacienteApi;
import API.TokenAPI;
import Models.Paciente;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**                                                                             
 *
 * @author Administrador
 */
public class PacienteForm extends javax.swing.JFrame {
  private void limpiarCampos() {
    txtNombre.setText("");
    txtApellido.setText("");
    txtNacimiento.setText("");
    txtGenero.setText("");
    txtDireccion.setText("");
    txtTelefono.setText("");
    txtEmail.setText("");
    jCheckBoxActivo.setSelected(false);  
    txtbuscar.setText("");
}
    public PacienteForm() {
        initComponents();
        SetDate ();
       verPacientes();

   //copia los datos de la tabla a los txt
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            //verifica que no se este ajustando un doble evento
            if (!e.getValueIsAdjusting()&& jTable1.getSelectedRow()!= -1){
            int selectedRow = jTable1.getSelectedRow();
            
            //Obtener valores de la tabla y mostrarlos en los campos
            String Nombre = jTable1.getValueAt(selectedRow, 1).toString(); //devuelve el valor de una celda
            String Apellido = jTable1.getValueAt(selectedRow, 2).toString();
            String Nacimiento = jTable1.getValueAt(selectedRow, 3).toString();
            String Genero = jTable1.getValueAt(selectedRow, 4).toString();
            String Direccion = jTable1.getValueAt(selectedRow, 5).toString();
            String Telefono = jTable1.getValueAt(selectedRow, 6).toString();
            String Email = jTable1.getValueAt(selectedRow, 7).toString();
            String Estado = jTable1.getValueAt(selectedRow, 8).toString();
            jCheckBoxActivo.setSelected(Estado.equalsIgnoreCase("Activo"));
            
            //actualizan con los datos seleccionados  de la tabla
            txtNombre.setText(Nombre);
            txtApellido.setText(Apellido);
            txtNacimiento.setText(Nacimiento);
            txtGenero.setText(Genero);
            txtDireccion.setText(Direccion);
            txtTelefono.setText(Telefono);
            txtEmail.setText(Email);
            
            
            }
        });  
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
      
private void llenarTablaConPacientes(List<Paciente> pacientes) {
    try {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // limpia la tabla
        
       
        for (Paciente p : pacientes) {
        
      

            model.addRow(new Object[]{
                p.getIdPaciente(),
                p.getNombre(),
                p.getApellido(),
                p.getFechaNacimiento(),
                p.getGenero(),
                p.getDireccion(),
                p.getTelefono(),
                p.getEmail(),
                p.getFechaRegistro(),
                p.getFechaActualizacion(),
                p.isActivo() ? "Activo" : "Inactivo"
            });
         }

    } catch (Exception e) {
     e.printStackTrace();
    }
}

//TOKEN
private void verPacientes() {
   try {
        String token = TokenAPI.getToken(); // Genera nuevo token cada vez
        PacienteApi api = new PacienteApi();
        List<Paciente> pacientes = api.getAllPacientes();
        llenarTablaConPacientes(pacientes);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + e.getMessage());
    }
}

 

    
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
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jCheckBoxActivo = new javax.swing.JCheckBox();
        txtApellido = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        txtNacimiento = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        Actualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonAgregar = new javax.swing.JButton();
        Limpiar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pacientes.png"))); // NOI18N
        jLabel2.setText("Pacientes");

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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Apellido:");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("F. Nacimiento:");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("Género:");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Dirección");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("Teléfono:");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("Email:");

        jCheckBoxActivo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jCheckBoxActivo.setText("Estado");
        jCheckBoxActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(35, 35, 35)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jCheckBoxActivo)
                        .addGap(45, 45, 45))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jCheckBoxActivo))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9))
                                .addGap(34, 34, 34))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
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

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButtonAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Actualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonBuscar)
                .addGap(42, 42, 42)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAgregar)
                    .addComponent(Actualizar)
                    .addComponent(jButtonEliminar)
                    .addComponent(Limpiar)
                    .addComponent(jButtonBuscar)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Apellido", "F. Nacimiento", "Género", "Dirección", "Telefono", "Email", "F. Registro", "F. Actualización", "Estado"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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

    private void jCheckBoxActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxActivoActionPerformed

    private void LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimpiarActionPerformed
     limpiarCampos ();
    }//GEN-LAST:event_LimpiarActionPerformed

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
    try {
        // Obtener y validar campos
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String fechaNacimiento = txtNacimiento.getText().trim(); // Formato esperado: dd/MM/yyyy
        String genero = txtGenero.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        // Validar campos vacios
        if (nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento.isEmpty()|| genero.isEmpty() || direccion.isEmpty()|| telefono.isEmpty()|| email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos obligatorios.");
            return;
        }
        
         
  DateTimeFormatter formatoEsperado = DateTimeFormatter.ofPattern("dd/MM/yyyy");
   try {
    LocalDate.parse(fechaNacimiento, formatoEsperado);
} catch (DateTimeParseException e) {
    JOptionPane.showMessageDialog(null, "La fecha debe tener el formato dd/MM/yyyy (ejemplo: 31/05/2000).");
    return;
}
    
   
        // Crear paciente
        Paciente p = new Paciente();
        
        p.setNombre(nombre);
        p.setApellido(apellido);
        p.setFechaNacimiento(fechaNacimiento); // Asegúrate de que el formato sea correcto
        p.setGenero(genero);
        p.setDireccion(direccion);
        p.setTelefono(telefono);
        p.setEmail(email);
        p.setActivo(jCheckBoxActivo.isSelected());
       
        
     DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
     String fechaHoy = LocalDate.now().format(formatoFecha);
    p.setFechaRegistro(fechaHoy);                           
    p.setFechaActualizacion(fechaHoy);
    p.setEstado("Activo");
        
        PacienteApi api = new PacienteApi();
        
         //verifica si el usuario ya existe 
    List <Paciente> pacientesExistentes = api.getAllPacientes();
    boolean pacienteexistente = pacientesExistentes.stream()
            .anyMatch(u -> u.getNombre().equalsIgnoreCase(nombre));
    
    if (pacienteexistente){
    JOptionPane.showMessageDialog(this, "El paciente ya está registrado. (verifique el dato en la tabla).");
    return;
    }
    //Guardar y mostrar Resultado
         boolean Resultado = api.addPaciente(p);
         
         if (Resultado) {
             JOptionPane.showMessageDialog(this, " Paciente Agregado correctamente.");
             limpiarCampos();
             verPacientes();
         }else {
             JOptionPane.showMessageDialog(this, "Error al agregar el paciente. (verifique los datos.)");
         }
    }catch (Exception ex) {
        ex.printStackTrace ();
        JOptionPane.showMessageDialog (this, "Error no se puede agregar: " + ex.getMessage());
    }
    }//GEN-LAST:event_jButtonAgregarActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
          try {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un paciente para actualizar.");
            return;
        }

        // Obtener el ID del paciente desde la tabla
        int id = Integer.parseInt(jTable1.getValueAt(selectedRow, 0).toString());

        // Obtener datos actualizados de los campos
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String fechaNacimiento = txtNacimiento.getText().trim();
        String genero = txtGenero.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento.isEmpty() || genero.isEmpty() || direccion.isEmpty()|| telefono.isEmpty()|| email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa los campos obligatorios.");
            return;
        }
       DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
     String fechaHoy = LocalDate.now().format(formatoFecha);

        Paciente paciente = new Paciente();
        paciente.setIdPaciente(id);
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setGenero(genero);
        paciente.setDireccion(direccion);
        paciente.setTelefono(telefono);
        paciente.setEmail(email);
        paciente.setActivo(jCheckBoxActivo.isSelected());
        paciente.setFechaActualizacion(fechaHoy);
        
    
        
        PacienteApi api = new PacienteApi();
        boolean resultado = api.updatePaciente(paciente); 

        if (resultado) {
            JOptionPane.showMessageDialog(this, "Paciente actualizado correctamente.");
            limpiarCampos();
            verPacientes();
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
            PacienteApi api = new PacienteApi();
            boolean resultado = api.deletePaciente(id);
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente.");
                verPacientes(); // Refresca la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el paciente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
       String dato = txtbuscar.getText().trim(); //Captura
    if (dato.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ingresa un dato para buscar. (ej. no.Telefonico o Nombre y Apellido).");
        return;
    }

    try {
        PacienteApi api = new PacienteApi(); 
        List<Paciente> pacientes = api.buscarPacientes(dato); //Api
        
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
    }//GEN-LAST:event_jButtonBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(PacienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PacienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PacienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PacienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PacienteForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Limpiar;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonMenu;
    private javax.swing.JCheckBox jCheckBoxActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
