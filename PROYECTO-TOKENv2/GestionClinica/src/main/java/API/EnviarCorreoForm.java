/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.mail.MessagingException;
/**
 *
 * @author Mario
 */
public class EnviarCorreoForm extends JFrame{

    private JTextField txtCorreo;
    private JButton btnSeleccionarReceta;
    private JButton btnSeleccionarFactura;
    private JButton btnEnviarCorreo;
    private JLabel lblReceta;
    private JLabel lblFactura;

    private File archivoReceta;
    private File archivoFactura;

    public EnviarCorreoForm() {
        setTitle("Enviar Receta y Factura por Correo");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblCorreo = new JLabel("Correo destinatario:");
        lblCorreo.setBounds(20, 20, 150, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(170, 20, 200, 25);
        add(txtCorreo);

        btnSeleccionarReceta = new JButton("Seleccionar Receta PDF");
        btnSeleccionarReceta.setBounds(20, 60, 180, 30);
        add(btnSeleccionarReceta);

        lblReceta = new JLabel("Ningún archivo seleccionado");
        lblReceta.setBounds(210, 60, 180, 30);
        add(lblReceta);

        btnSeleccionarFactura = new JButton("Seleccionar Factura PDF");
        btnSeleccionarFactura.setBounds(20, 100, 180, 30);
        add(btnSeleccionarFactura);

        lblFactura = new JLabel("Ningún archivo seleccionado");
        lblFactura.setBounds(210, 100, 180, 30);
        add(lblFactura);

        btnEnviarCorreo = new JButton("Enviar Correo");
        btnEnviarCorreo.setBounds(130, 150, 120, 30);
        add(btnEnviarCorreo);

        // Acción para seleccionar archivo receta
        btnSeleccionarReceta.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                archivoReceta = fileChooser.getSelectedFile();
                lblReceta.setText(archivoReceta.getName());
            }
        });

        // Acción para seleccionar archivo factura
        btnSeleccionarFactura.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                archivoFactura = fileChooser.getSelectedFile();
                lblFactura.setText(archivoFactura.getName());
            }
        });

        // Acción para enviar correo
        btnEnviarCorreo.addActionListener(e -> {
            String destinatario = txtCorreo.getText().trim();
            if (destinatario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un correo destinatario.");
                return;
            }
            if (archivoReceta == null || archivoFactura == null) {
                JOptionPane.showMessageDialog(this, "Seleccione ambos archivos PDF.");
                return;
            }

            // Aquí configuras tus credenciales SMTP
            String smtpHost = "smtp.gmail.com";
            String smtpPort = "587";
            String username = "clinicaregional.mensajeria@gmail.com"; // Cambia por tu correo
            String password = "rtdk lwpg hvgl xcrp";   // Cambia por tu contraseña de aplicación

            EmailSender emailSender = new EmailSender(username, password, smtpHost, smtpPort);

            String subject = "Receta Médica y Factura";
            String body = "Adjuntamos su receta médica y factura. ¡Gracias por su visita!";

            new Thread(() -> {
                try {
                    emailSender.sendEmail(destinatario, subject, body, archivoReceta);
                    emailSender.sendEmail(destinatario, subject, body, archivoFactura);
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, "Correo enviado correctamente.");
                    });
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(this, "Error al enviar correo: " + ex.getMessage());
                    });
                }
            }).start();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EnviarCorreoForm().setVisible(true);
        });
    }
    
}
