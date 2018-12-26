/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import modeloqytetet.MetodoSalirCarcel;
import modeloqytetet.Qytetet;

/**
 *
 * @author juliu
 */
public class controladorQytetet extends javax.swing.JFrame {

    /**
     * Creates new form controladorQytetet
     */
    
    Qytetet modeloQytetet = Qytetet.getInstance();
    public controladorQytetet() {
        initComponents();
    }
    
    public void actualizarControlador(Qytetet juego){
        this.vistaQytetet1.actualizarQytetet(juego);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbSalirCarcelPagando = new javax.swing.JButton();
        jbSalirCarcelDado = new javax.swing.JButton();
        jbJugar = new javax.swing.JButton();
        jbComprar = new javax.swing.JButton();
        jbSiguienteJugador = new javax.swing.JButton();
        jbAplicarSorpresa = new javax.swing.JButton();
        jbNoComprar = new javax.swing.JButton();
        vistaQytetet1 = new GUIQytetet.VistaQytetet();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbSalirCarcelPagando.setText("Salir Carcel Pagando");
        jbSalirCarcelPagando.setToolTipText("");
        jbSalirCarcelPagando.setActionCommand("SCP");
        jbSalirCarcelPagando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirCarcelPagandoActionPerformed(evt);
            }
        });

        jbSalirCarcelDado.setText("Salir Carcel Tirando");
        jbSalirCarcelDado.setActionCommand("SCT");
        jbSalirCarcelDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirCarcelDadoActionPerformed(evt);
            }
        });

        jbJugar.setText("Jugar");
        jbJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbJugarActionPerformed(evt);
            }
        });

        jbComprar.setText("Comprar casilla");
        jbComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbComprarActionPerformed(evt);
            }
        });

        jbSiguienteJugador.setText("Pasar turno");
        jbSiguienteJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteJugadorActionPerformed(evt);
            }
        });

        jbAplicarSorpresa.setText("Aplicar Sorpresa");
        jbAplicarSorpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAplicarSorpresaActionPerformed(evt);
            }
        });

        jbNoComprar.setText("No comprar");
        jbNoComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbNoComprarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jbSalirCarcelDado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jbSalirCarcelPagando))
                .addGap(49, 49, 49)
                .addComponent(jbJugar)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbComprar)
                        .addGap(48, 48, 48)
                        .addComponent(jbSiguienteJugador)
                        .addGap(45, 45, 45)
                        .addComponent(jbAplicarSorpresa))
                    .addComponent(jbNoComprar))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(vistaQytetet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vistaQytetet1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalirCarcelPagando)
                    .addComponent(jbJugar)
                    .addComponent(jbComprar)
                    .addComponent(jbSiguienteJugador)
                    .addComponent(jbAplicarSorpresa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSalirCarcelDado)
                    .addComponent(jbNoComprar))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbJugarActionPerformed
        // TODO add your handling code here:
        boolean tienePropietario = modeloQytetet.jugar();
        actualizarControlador(modeloQytetet);
        this.jbJugar.setEnabled(false);
        if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == modeloqytetet.TipoCasilla.CALLE){
            if(tienePropietario){
                this.jbComprar.setEnabled(false);
                this.jbNoComprar.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Propiedad comprada no puedes comprarla");
            }else{
                this.jbComprar.setEnabled(true);
                this.jbNoComprar.setEnabled(true);
                this.jbSiguienteJugador.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Puedes comprar la propiedad");
            }
        }else if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == modeloqytetet.TipoCasilla.SORPRESA){
            this.jbAplicarSorpresa.setEnabled(true);
            if (modeloQytetet.getJugadorActual().getEncarcelado()){
                JOptionPane.showMessageDialog(this, "Estas encarcelado");
                //this.jbSiguienteJugador.setEnabled(true);
            }
        }else if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == modeloqytetet.TipoCasilla.IMPUESTO){
            JOptionPane.showMessageDialog(this, "Pagas impuesto");
        }else if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == modeloqytetet.TipoCasilla.JUEZ){
            JOptionPane.showMessageDialog(this, "Vas a la carcel");
            this.jbSiguienteJugador.setEnabled(true);
        }else if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == modeloqytetet.TipoCasilla.SALIDA){
            JOptionPane.showMessageDialog(this, "Cobras una buena pasta");
        }else if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == modeloqytetet.TipoCasilla.CARCEL){
            this.jbSiguienteJugador.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Estas en la carcel de paso");
        }else if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo() == modeloqytetet.TipoCasilla.PARKING){
            this.jbSiguienteJugador.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Estas en el parking, zona de descanso");
        }
    }//GEN-LAST:event_jbJugarActionPerformed

    private void jbSalirCarcelPagandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirCarcelPagandoActionPerformed
        // TODO add your handling code here:
        boolean resultado = modeloQytetet.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        if(resultado){
            JOptionPane.showMessageDialog(this, "Sales de la cárcel");
            this.jbJugar.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(this, "NO sales de la carcel");
            this.jbSiguienteJugador.setEnabled(true);
        }
        this.vistaQytetet1.actualizarQytetet(modeloQytetet);
    }//GEN-LAST:event_jbSalirCarcelPagandoActionPerformed

    private void jbSalirCarcelDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirCarcelDadoActionPerformed
        // TODO add your handling code here:
        boolean resultado = modeloQytetet.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        if(resultado){
            JOptionPane.showMessageDialog(this, "Sales de la cárcel tirando dado");
        }else{
            JOptionPane.showMessageDialog(this, "No sales de la cárcel valor demasiado bajo UAHAHAHAHAHAHA");
        }
        this.vistaQytetet1.actualizarQytetet(modeloQytetet);
    }//GEN-LAST:event_jbSalirCarcelDadoActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        // TODO add your handling code here:
        modeloQytetet.comprarTituloPropiedad();
        this.jbComprar.setEnabled(false);
        this.jbNoComprar.setEnabled(false);
        this.jbJugar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(true);
        this.vistaQytetet1.actualizarQytetet(modeloQytetet);
    }//GEN-LAST:event_jbComprarActionPerformed

    private void jbNoComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbNoComprarActionPerformed
        // TODO add your handling code here:
        this.jbNoComprar.setEnabled(false);
        this.jbJugar.setEnabled(false);
        this.jbComprar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(true);
        this.vistaQytetet1.actualizarQytetet(modeloQytetet);
    }//GEN-LAST:event_jbNoComprarActionPerformed

    private void jbSiguienteJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteJugadorActionPerformed
        // TODO add your handling code here:
        modeloQytetet.siguienteJugador();
       boolean bancarrota = modeloQytetet.getJugadorActual().getSaldo() <= 0;
       if(bancarrota){
           this.jbSiguienteJugador.setEnabled(false);
           JOptionPane.showMessageDialog(this, "JUEGO TERMINADO EL ULTIMO JUGADOR HA ENTRADO EN BANCARROTA");
           JOptionPane.showMessageDialog(this, modeloQytetet.obtenerRanking());
       }
        this.vistaQytetet1.actualizarQytetet(modeloQytetet);
        this.jbJugar.setEnabled(true);
    }//GEN-LAST:event_jbSiguienteJugadorActionPerformed

    private void jbAplicarSorpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAplicarSorpresaActionPerformed
        modeloQytetet.aplicarSorpresa();
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(true);
        // TODO add your handling code here:
        this.vistaQytetet1.actualizarQytetet(modeloQytetet);
    }//GEN-LAST:event_jbAplicarSorpresaActionPerformed
    
    
    /**
     * @param args the command line arguments
     */
    public void habilitarComenzarTurno(){
        
        this.jbComprar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(false);
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbNoComprar.setEnabled(false);
        if(modeloQytetet.getJugadorActual().getEncarcelado()){
            this.jbSalirCarcelPagando.setEnabled(true);
            this.jbSalirCarcelDado.setEnabled(true);
        }
        else {
            this.jbJugar.setEnabled(true);
        }
    }
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
            java.util.logging.Logger.getLogger(controladorQytetet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(controladorQytetet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(controladorQytetet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(controladorQytetet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        Qytetet juego = Qytetet.getInstance();
        /* Create and display the form */
        controladorQytetet controladorQytetet = new controladorQytetet();       
        CapturaNombreJugadores capturaNombres = new CapturaNombreJugadores(controladorQytetet, true);
        ArrayList<String> nombres = capturaNombres.obtenerNombres();      
        juego.inicializarJuego(nombres);
        Dado.createInstance(controladorQytetet);
        controladorQytetet.habilitarComenzarTurno();
        
        controladorQytetet.actualizarControlador(juego);
        
        controladorQytetet.setVisible(true);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAplicarSorpresa;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbJugar;
    private javax.swing.JButton jbNoComprar;
    private javax.swing.JButton jbSalirCarcelDado;
    private javax.swing.JButton jbSalirCarcelPagando;
    private javax.swing.JButton jbSiguienteJugador;
    private GUIQytetet.VistaQytetet vistaQytetet1;
    // End of variables declaration//GEN-END:variables
}
