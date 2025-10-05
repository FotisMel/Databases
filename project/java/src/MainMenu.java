import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;

public class MainMenu extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;

    public MainMenu() {
        initComponents();
        setVisible(true);
        
        setSize(800, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        tableNameLabel.setForeground(Color.WHITE);
        tableNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tableNameLabel = new javax.swing.JLabel();
        logWindowButton = new javax.swing.JButton();
        editTableMenuButton = new javax.swing.JButton();
        newITButton = new javax.swing.JButton();
        reservationinfoButton = new javax.swing.JButton();
        branchInfoButton = new javax.swing.JButton();
        branchTripInfoButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tableNameLabel.setText("Main Menu");

        logWindowButton.setText("Log");
        logWindowButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logWindowButtonMouseClicked(evt);
            }
        });

        editTableMenuButton.setText("Insert/Update/Delete");
        editTableMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTableMenuButtonMouseClicked(evt);
            }
        });

        newITButton.setText("Add new IT");
        newITButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newITButtonMouseClicked(evt);
            }
        });

        reservationinfoButton.setText("Customer Reservation Info");
        reservationinfoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservationinfoButtonMouseClicked(evt);
            }
        });

        branchInfoButton.setText("Branch Info");
        branchInfoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                branchInfoButtonMouseClicked(evt);
            }
        });

        branchTripInfoButton.setText("Branch Trip Info");
        branchTripInfoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                branchTripInfoButtonMouseClicked(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reservationinfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newITButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editTableMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logWindowButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(branchInfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(branchTripInfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
                .addGap(124, 124, 124))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logWindowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editTableMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newITButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(branchTripInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reservationinfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(branchInfoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editTableMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTableMenuButtonMouseClicked
        EditTableMenu editTabMen = new EditTableMenu();
        dispose();
    }//GEN-LAST:event_editTableMenuButtonMouseClicked

    private void logWindowButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logWindowButtonMouseClicked
        LogPage logPage = new LogPage();
        dispose();
    }//GEN-LAST:event_logWindowButtonMouseClicked

    private void newITButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newITButtonMouseClicked
        AddITPage addNewITPage = new AddITPage();
        dispose();
    }//GEN-LAST:event_newITButtonMouseClicked

    private void branchTripInfoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_branchTripInfoButtonMouseClicked
        BranchTripInfoPage brInfPage = new BranchTripInfoPage();
        dispose();
    }//GEN-LAST:event_branchTripInfoButtonMouseClicked

    private void reservationinfoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservationinfoButtonMouseClicked
        CustomerReservationPage brInfPage = new CustomerReservationPage();
        dispose();
    }//GEN-LAST:event_reservationinfoButtonMouseClicked

    private void branchInfoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_branchInfoButtonMouseClicked
        BranchInfo brInfPage = new BranchInfo();
        dispose();
    }//GEN-LAST:event_branchInfoButtonMouseClicked

    private void logoutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseClicked
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            String LOGOUT_QUERY = "DELETE FROM current_active_user";
            
            myPreparedStatement = myConnection.prepareStatement(LOGOUT_QUERY);
            myPreparedStatement.executeUpdate();
            
            LoginWindow loginWin = new LoginWindow();
            dispose();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_logoutButtonMouseClicked

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton branchInfoButton;
    private javax.swing.JButton branchTripInfoButton;
    private javax.swing.JButton editTableMenuButton;
    private javax.swing.JButton logWindowButton;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton newITButton;
    private javax.swing.JButton reservationinfoButton;
    private javax.swing.JLabel tableNameLabel;
    // End of variables declaration//GEN-END:variables
}
