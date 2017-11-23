/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegopelotas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author MariFer
 */
public class PelotasLocas extends JFrame {

    JPanel panelPrecentacion;
    JLabel fondoPresentacion;
    JPanel paneljuego;
    JLabel fondojuego;

    JLabel mat[][];
    int matriz[][];
    Random aleatorio;
    Fondo p;
    int puntos, clicks, contClicks, cont = 99;

    public PelotasLocas() {

        initComponents();
        btnReiniciar.setVisible(false);

        this.setBounds(0, 0, 516, 575);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel de inicio      
//        panelPrecentacion = new JPanel();
//        panelPrecentacion.setBounds(0, 0, this.getWidth(), this.getHeight());
//        panelPrecentacion.setVisible(true);
        //Fondo de inicio
        fondoPresentacion = new JLabel();
        fondoPresentacion.setBounds(0, 0, 516, 501);
        fondoPresentacion.setIcon(new ImageIcon("imagenes/fondo.jpg"));
        fondoPresentacion.setVisible(true);

//        //Panel de juego
//        paneljuego = new JPanel();
//        paneljuego.setBounds(0, 0, this.getWidth(), this.getHeight());
//        paneljuego.setVisible(true);
        //Fondo de juego
        fondojuego = new JLabel();
        fondojuego.setBounds(0, 0, 516, 501);
        fondojuego.setIcon(new ImageIcon("imagenes/fondo1.jpg"));
        fondojuego.setVisible(true);

        this.add(fondojuego, -1);
//        this.add(paneljuego);

        this.add(fondoPresentacion, 0);
//        this.add(panelPrecentacion);

        juego();
        this.setVisible(true);

        //txtFondo.setVisible(false);
    }

    public void juego() {

        //Inicialisamos los componentes
        mat = new JLabel[10][10];
        matriz = new int[10][10];
        aleatorio = new Random();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                mat[i][j] = new JLabel();
                mat[i][j].setBounds(0 + i * 50, 0 + j * 50, 50, 50);
                matriz[i][j] = aleatorio.nextInt(4) + 1;
                mat[i][j].setIcon(new ImageIcon("imagenes/" + matriz[i][j] + ".png"));
                mat[i][j].setVisible(true);
                this.add(mat[i][j], 2);
            }
        }
        mouseClick();

    }

    public void mouseClick() {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < matriz.length; j++) {

//                implementa el interface MouseListener, ha de redefinir 
//                todas las funciones aunque solamente estemos 
//                interesados en algunas de ellas.
//            Para evitar el código inútil, se han creado clases que 
//           terminan con la palabra Adapter, que implementan el 
//                correspondiente interface Listener redefiniendo 
//                        todas las funciones declaradas en el interface 
//                                sin que hagan nada en particular.
                mat[i][j].addMouseListener(new MouseAdapter() {
//                    La clase BotonRaton ya no implementa (implements)
//                            el interface MouseListener, sino que deriva
//         (extends) de la clase MouseAdapter. El código que define la 
//                 clase BotonRaton se escribe en términos de la clase MouseAdapter 
//                 de la siguiente forma
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        for (int k = 0; k < mat.length; k++) {
                            for (int l = 0; l < matriz.length; l++) {
                                if (mat[k][l] == evt.getSource()) {
//                                    getSource si el suceso procede de la acción
//                                            sobre un botón, de un control lista
                                    System.out.println("Posicion (" + k + " " + l + ")");

                                    puntos += evt.getClickCount() * contClicks;
                                    contClicks = 0;
                                    txtPuntuacion.setText("Puntos  " + Integer.toString(puntos));
                                    clicks += evt.getClickCount();
                                    txtClicks.setText("Clicks  " + Integer.toString(clicks));

                                    txtCont.setText("Contador  " + Integer.toString(cont));
                                    recursiva(k, l, matriz[k][l]);

                                }

                            }
                        }

                        for (int j = 0; j < mat.length; j++) {
                            for (int k = 0; k < mat.length; k++) {
                                for (int l = 0; l < matriz.length; l++) {
                                    if (l > 0 && matriz[k][l] == -2) {
                                        matriz[k][l] = matriz[k][l - 1];
                                        matriz[k][l - 1] = -2;

                                    }
                                    mat[k][l].setIcon(new ImageIcon("imagenes/" + matriz[k][l] + ".jpg"));

                                }
                            }
                        }

                        for (int k = 0; k < mat.length; k++) {
                            for (int l = 0; l < matriz.length; l++) {
                                if (matriz[k][l] == -2) {
                                    //mat[k][l].setIcon(new ImageIcon("imagenes/5.jpg"));
                                    mat[k][l].setVisible(false);
                                    // txtFondo.setVisible(true);
                                }
                            }
                        }
                    }
                });

            }
        }

    }

    public void recursiva(int px, int py, int color) {

        if (color != -1) {
            matriz[px][py] = -1;
            contClicks++;

        }
        if (color == -1) {
            matriz[px][py] = -2;
            cont--;
            if (cont == 0) {
                JOptionPane.showMessageDialog(null,"Tuviste: " + Integer.toString(clicks) + " Clicks" 
                        + ", Puntos: " + Integer.toString(puntos) +", Pero has terminado!!   Felicidades !!");
                btnReiniciar.setVisible(false);
                fondoPresentacion.setVisible(true);
                btnInicio.setVisible(true);
                Reiniciar();
            }
        }

        mat[px][py].setIcon(new ImageIcon("imagenes/" + matriz[px][py] + ".png"));

        if (px < 9 && matriz[px + 1][py] == color) {
            recursiva(px + 1, py, color);
        }
        if (px > 0 && matriz[px - 1][py] == color) {
            recursiva(px - 1, py, color);
        }
        if (py < 9 && matriz[px][py + 1] == color) {
            recursiva(px, py + 1, color);
        }
        if (py > 0 && matriz[px][py - 1] == color) {
            recursiva(px, py - 1, color);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPuntuacion = new javax.swing.JLabel();
        txtCont = new javax.swing.JLabel();
        btnReiniciar = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();
        txtClicks = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setSize(new java.awt.Dimension(400, 300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtPuntuacion.setText("00000");

        txtCont.setText("00000");

        btnReiniciar.setText("Reiniciar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        txtClicks.setText("00000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPuntuacion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(txtClicks, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCont, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReiniciar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(505, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPuntuacion)
                    .addComponent(txtCont)
                    .addComponent(btnReiniciar)
                    .addComponent(btnInicio)
                    .addComponent(txtClicks))
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        p = new Fondo();
        //this.add(p, BorderLayout.CENTER);
        //p.repaint();
    }//GEN-LAST:event_formWindowOpened

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        btnReiniciar.setVisible(false);
        fondoPresentacion.setVisible(true);
        btnInicio.setVisible(true);
        Reiniciar();
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        btnReiniciar.setVisible(true);
        fondoPresentacion.setVisible(false);
        btnInicio.setVisible(false);
        Reiniciar();
    }//GEN-LAST:event_btnInicioActionPerformed

    public void Reiniciar() {
        aleatorio = new Random();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                mat[i][j].setBounds(0 + i * 50, 0 + j * 50, 50, 50);
                matriz[i][j] = aleatorio.nextInt(4) + 1;
                mat[i][j].setIcon(new ImageIcon("imagenes/" + matriz[i][j] + ".png"));
                mat[i][j].setVisible(true);
            }
        }

        txtPuntuacion.setText("Puntos  00000");
        txtClicks.setText("Clicks  00000");
        txtCont.setText("Faltan  00000");
        puntos = 0;
        clicks = 0;
        cont = 99;
    }

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
            java.util.logging.Logger.getLogger(PelotasLocas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PelotasLocas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PelotasLocas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PelotasLocas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PelotasLocas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JLabel txtClicks;
    private javax.swing.JLabel txtCont;
    private javax.swing.JLabel txtPuntuacion;
    // End of variables declaration//GEN-END:variables
}
