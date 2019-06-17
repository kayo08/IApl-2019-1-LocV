/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Murilo
 */
public class ViewHome extends javax.swing.JFrame {

    String selected = "Portuguese";

    /**
     * Creates new form viewHome
     */
    public ViewHome(String language) {
        initComponents();
        setLocationRelativeTo(null);
        this.selected = language;
        changeLanguage(selected);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewhome_name = new javax.swing.JLabel();
        viewhome_welcome = new javax.swing.JLabel();
        button_home_vehicle = new javax.swing.JButton();
        button_home_client = new javax.swing.JButton();
        button_home_leasing = new javax.swing.JButton();
        button_home_exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        viewhome_name.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        viewhome_name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewhome_name.setText("Sistema de Locação de Veículos");
        viewhome_name.setAutoscrolls(true);
        viewhome_name.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        viewhome_welcome.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        viewhome_welcome.setText("Bem vindo!");

        button_home_vehicle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        button_home_vehicle.setText("Gerenciar Veículos");
        button_home_vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_home_vehicleActionPerformed(evt);
            }
        });

        button_home_client.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        button_home_client.setText("Gerenciar Clientes");
        button_home_client.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_home_client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_home_clientActionPerformed(evt);
            }
        });

        button_home_leasing.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        button_home_leasing.setText("Gerenciar Locações");
        button_home_leasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_home_leasingActionPerformed(evt);
            }
        });

        button_home_exit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        button_home_exit.setText("Sair");
        button_home_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_home_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(button_home_vehicle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(button_home_client, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36)
                        .addComponent(button_home_leasing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(button_home_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(270, 270, 270))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(viewhome_welcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(226, 226, 226))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(viewhome_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(52, 52, 52)))
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(viewhome_welcome)
                .addGap(31, 31, 31)
                .addComponent(viewhome_name)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_home_vehicle)
                    .addComponent(button_home_client)
                    .addComponent(button_home_leasing))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(button_home_exit)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_home_vehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_home_vehicleActionPerformed

        new ViewVehicle(selected).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_home_vehicleActionPerformed
    private void changeLanguage(String language) {
        switch (language) {
            case "Portuguese":
                viewhome_welcome.setText("Bem vindo!");
                viewhome_name.setText("Sistema de Locação de Veículos");
                button_home_vehicle.setText("Gerenciar Veículos");
                button_home_client.setText("Gerenciar Clientes");
                button_home_leasing.setText("Gerenciar Locações");
                button_home_exit.setText("Sair");
                break;
            case "English":
                viewhome_welcome.setText("Welcome!");
                viewhome_name.setText("Vehicle Leasing System");
                button_home_vehicle.setText("Manage Vehicles");
                button_home_client.setText("Manage Clients");
                button_home_leasing.setText("Manage Leases");
                button_home_exit.setText("Leave");
                break;
            case "Spanish":
                viewhome_welcome.setText("¡bienvenido!");
                viewhome_name.setText("Sistema de Alquiler de Vehículos");
                button_home_vehicle.setText("Gestión de Vehículos");
                button_home_client.setText("Gestión de Clientes");
                button_home_leasing.setText("Gestión de Locaciones");
                button_home_exit.setText("Salir");
                break;
            default:
                viewhome_welcome.setText("Bem vindo!");
                viewhome_name.setText("Sistema de Locação de Veículos");
                button_home_vehicle.setText("Gerenciar Veículos");
                button_home_client.setText("Gerenciar Clientes");
                button_home_leasing.setText("Gerenciar Locações");
                button_home_exit.setText("Sair");
        }

    }
    private void button_home_leasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_home_leasingActionPerformed

        new ViewLeasing(selected).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_home_leasingActionPerformed

    private void button_home_clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_home_clientActionPerformed

        new ViewClient(selected).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_button_home_clientActionPerformed

    private void button_home_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_home_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_button_home_exitActionPerformed

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
            java.util.logging.Logger.getLogger(ViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewHome("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_home_client;
    private javax.swing.JButton button_home_exit;
    private javax.swing.JButton button_home_leasing;
    private javax.swing.JButton button_home_vehicle;
    private javax.swing.JLabel viewhome_name;
    private javax.swing.JLabel viewhome_welcome;
    // End of variables declaration//GEN-END:variables
}
