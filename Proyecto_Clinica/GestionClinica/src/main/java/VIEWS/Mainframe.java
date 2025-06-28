/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEWS;
import Models.Usuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Mainframe extends javax.swing.JFrame {
   private JPanel contentPanel;
    private boolean menuVisible = true;
    private Usuario usuario;

    public Mainframe(Usuario usuario) {
        this.usuario = usuario;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Sistema de Gestión Clínica");
        PanelContenido.setLayout(new BorderLayout());
        Principal panelPrincipal = new Principal();
        cargarPanel(panelPrincipal);
        //panel inicial 
     btnMENU.addActionListener(e -> {
    toggleMenu(!menuVisible); // Alterna visibilidad
});
      

    if (usuario.getRol().equalsIgnoreCase("Recepcionista")) {
        btnUsuarios.setVisible(false);
    } else if (usuario.getRol().equalsIgnoreCase("Medico")) {
        btnUsuarios.setEnabled(false);
        btnMedicamentos.setEnabled(false);
        btnFacturas.setEnabled(false);
        btnPacientes.setEnabled(false); // Solo lectura, podrías bloquear acciones
    }
  
    }
    
        // Método para cambiar el contenido
    private void cargarPanel(JPanel panel) {
         PanelContenido.removeAll();
        
        PanelContenido.add(panel, BorderLayout.CENTER);
        PanelContenido.revalidate();
        PanelContenido.repaint();
    }

private void ocultarMenu() {
    PanelMenu.setVisible(false);
    menuVisible = false;
}

private void mostrarMenu() {
    PanelMenu.setVisible(true);
    menuVisible = true;
}

private void toggleMenu(boolean mostrar) {
    PanelMenu.setVisible(mostrar);
    PanelContenido.revalidate(); // <-- Recalcula el layout
    PanelContenido.repaint(); 
    menuVisible = mostrar;
}

    
 


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButtoncerrarS = new javax.swing.JButton();
        btnMENU = new javax.swing.JButton();
        PanelContenido = new javax.swing.JPanel();
        PanelMenu = new javax.swing.JPanel();
        btnPacientes = new javax.swing.JButton();
        btnMedicos = new javax.swing.JButton();
        btnCitas = new javax.swing.JButton();
        btnRecetas = new javax.swing.JButton();
        btnFacturas = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnMedicamentos = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btncorreo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setPreferredSize(new java.awt.Dimension(738, 150));

        jLabel2.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Control/Clínica Médica");

        jButtoncerrarS.setBackground(new java.awt.Color(0, 153, 153));
        jButtoncerrarS.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButtoncerrarS.setForeground(new java.awt.Color(51, 51, 51));
        jButtoncerrarS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sale.png"))); // NOI18N
        jButtoncerrarS.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(0, 0, 0)));
        jButtoncerrarS.setBorderPainted(false);
        jButtoncerrarS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtoncerrarS.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButtoncerrarS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtoncerrarS.setVerifyInputWhenFocusTarget(false);
        jButtoncerrarS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtoncerrarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoncerrarSActionPerformed(evt);
            }
        });

        btnMENU.setBackground(new java.awt.Color(102, 102, 102));
        btnMENU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/barramenu.png"))); // NOI18N
        btnMENU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMENUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnMENU)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtoncerrarS, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtoncerrarS, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(btnMENU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelContenido.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelContenidoLayout = new javax.swing.GroupLayout(PanelContenido);
        PanelContenido.setLayout(PanelContenidoLayout);
        PanelContenidoLayout.setHorizontalGroup(
            PanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 859, Short.MAX_VALUE)
        );
        PanelContenidoLayout.setVerticalGroup(
            PanelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelContenido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanelContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        PanelMenu.setBackground(new java.awt.Color(51, 51, 51));

        btnPacientes.setBackground(new java.awt.Color(51, 51, 51));
        btnPacientes.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnPacientes.setForeground(new java.awt.Color(255, 255, 255));
        btnPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/contacto.png"))); // NOI18N
        btnPacientes.setText("Pacientes");
        btnPacientes.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(51, 51, 51)));
        btnPacientes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacientesActionPerformed(evt);
            }
        });

        btnMedicos.setBackground(new java.awt.Color(51, 51, 51));
        btnMedicos.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnMedicos.setForeground(new java.awt.Color(255, 255, 255));
        btnMedicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/people.png"))); // NOI18N
        btnMedicos.setText("Médicos");
        btnMedicos.setAlignmentX(12.0F);
        btnMedicos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(51, 51, 51)));
        btnMedicos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMedicos.setPreferredSize(new java.awt.Dimension(131, 48));
        btnMedicos.setRequestFocusEnabled(false);
        btnMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicosActionPerformed(evt);
            }
        });

        btnCitas.setBackground(new java.awt.Color(51, 51, 51));
        btnCitas.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnCitas.setForeground(new java.awt.Color(255, 255, 255));
        btnCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cita.png"))); // NOI18N
        btnCitas.setText("Citas");
        btnCitas.setToolTipText("");
        btnCitas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(51, 51, 51)));
        btnCitas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnCitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCitasActionPerformed(evt);
            }
        });

        btnRecetas.setBackground(new java.awt.Color(51, 51, 51));
        btnRecetas.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnRecetas.setForeground(new java.awt.Color(255, 255, 255));
        btnRecetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reporte.png"))); // NOI18N
        btnRecetas.setText("Recetas");
        btnRecetas.setToolTipText("");
        btnRecetas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(51, 51, 51)));
        btnRecetas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnRecetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecetasActionPerformed(evt);
            }
        });

        btnFacturas.setBackground(new java.awt.Color(51, 51, 51));
        btnFacturas.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnFacturas.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/file.png"))); // NOI18N
        btnFacturas.setText("Facturas");
        btnFacturas.setToolTipText("");
        btnFacturas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(51, 51, 51)));
        btnFacturas.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturasActionPerformed(evt);
            }
        });

        btnUsuarios.setBackground(new java.awt.Color(51, 51, 51));
        btnUsuarios.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/contacto.png"))); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setToolTipText("");
        btnUsuarios.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(51, 51, 51)));
        btnUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnMedicamentos.setBackground(new java.awt.Color(51, 51, 51));
        btnMedicamentos.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnMedicamentos.setForeground(new java.awt.Color(255, 255, 255));
        btnMedicamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medicina.png"))); // NOI18N
        btnMedicamentos.setText("Medicamentos");
        btnMedicamentos.setToolTipText("");
        btnMedicamentos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(51, 51, 51)));
        btnMedicamentos.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnMedicamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicamentosActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logosmall.png"))); // NOI18N
        jLabel3.setText("Clínica Médica");
        jLabel3.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btncorreo.setBackground(new java.awt.Color(51, 51, 51));
        btncorreo.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btncorreo.setForeground(new java.awt.Color(255, 255, 255));
        btncorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comunicacion.png"))); // NOI18N
        btncorreo.setText("Enviar Correo");
        btncorreo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 12, 1, 1, new java.awt.Color(51, 51, 51)));
        btncorreo.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btncorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncorreoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMenuLayout = new javax.swing.GroupLayout(PanelMenu);
        PanelMenu.setLayout(PanelMenuLayout);
        PanelMenuLayout.setHorizontalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCitas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMedicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRecetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnFacturas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUsuarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMedicamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btncorreo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelMenuLayout.setVerticalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCitas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRecetas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMedicamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btncorreo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMedicamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicamentosActionPerformed
    cargarPanel(new MedicamentoViews(PanelContenido));
        toggleMenu(false);
    }//GEN-LAST:event_btnMedicamentosActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        cargarPanel(new UsuariosViews());
        toggleMenu(false);
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturasActionPerformed
        cargarPanel(new FacturaViews(PanelContenido));
        toggleMenu(false);
    }//GEN-LAST:event_btnFacturasActionPerformed

    private void btnRecetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecetasActionPerformed
   cargarPanel(new RecetasViews(PanelContenido));
        toggleMenu(false);
    }//GEN-LAST:event_btnRecetasActionPerformed

    private void btnCitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCitasActionPerformed
        cargarPanel(new CitasViews());
        toggleMenu(false);
    }//GEN-LAST:event_btnCitasActionPerformed

    private void btnMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicosActionPerformed
        cargarPanel(new Medicos());
        toggleMenu(false);
    }//GEN-LAST:event_btnMedicosActionPerformed

    private void btnPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacientesActionPerformed
    cargarPanel(new Pacientes());
    toggleMenu(false); 
    }//GEN-LAST:event_btnPacientesActionPerformed

    private void btnMENUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMENUActionPerformed
   toggleMenu(true); 
    }//GEN-LAST:event_btnMENUActionPerformed

    private void jButtoncerrarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoncerrarSActionPerformed
        Login login = new Login ();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtoncerrarSActionPerformed

    private void btncorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncorreoActionPerformed
        EnvioCorreos envio = new EnvioCorreos (this);
        envio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btncorreoActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelContenido;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JButton btnCitas;
    private javax.swing.JButton btnFacturas;
    private javax.swing.JButton btnMENU;
    private javax.swing.JButton btnMedicamentos;
    private javax.swing.JButton btnMedicos;
    private javax.swing.JButton btnPacientes;
    private javax.swing.JButton btnRecetas;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btncorreo;
    private javax.swing.JButton jButtoncerrarS;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables

}
