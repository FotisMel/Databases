
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class BranchInfo extends javax.swing.JFrame {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;
    private static ResultSet myResultSet;
    private static ResultSetMetaData myMetaData;

    public BranchInfo() {
        initComponents();
        setVisible(true);
        
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        tableNameLabel.setForeground(Color.WHITE);
        tableNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        text1.setForeground(Color.WHITE);
        
        initializeBranchList();
        
        fillBranchInfo();
        fillWorkerInfo();
        fillTotalSalaries();
    }

    private void initializeBranchList(){
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            myPreparedStatement = myConnection.prepareStatement("SELECT br_code FROM branch");
            myResultSet = myPreparedStatement.executeQuery();
            
            while(myResultSet.next())
                branchSelection.addItem(myResultSet.getObject(1).toString());
        }
        catch (SQLException ex) {
            
        }
        finally{
            
        }
    }
    
    private void fillBranchInfo(){
        String GET_BRANCH_INFO_QUERY = 
                "SELECT br_code, br_street, br_num, br_city, wrk_name, wrk_lname, SUM(Reservations)  AS 'Reservations', SUM(Profit) " +
                "FROM( " +
                "	SELECT br_code, br_street, br_num, br_city, wrk_name, wrk_lname, COUNT(*) AS 'Reservations', COUNT(*) * tr_cost AS 'Profit' " +
                "	FROM branch " +
                "	LEFT JOIN manages ON br_code = mng_br_code " +
                "	LEFT JOIN admin ON mng_adm_AT = adm_AT " +
                "	LEFT JOIN worker ON adm_AT = wrk_AT " +
                "	LEFT JOIN trip ON br_code = tr_br_code " +
                "	LEFT JOIN reservation ON tr_id = res_tr_id " +
                "	GROUP BY tr_id " +
                ") AS temp " +
                "WHERE br_code = " + branchSelection.getSelectedItem().toString();
        
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            myPreparedStatement = myConnection.prepareStatement(GET_BRANCH_INFO_QUERY);
            myResultSet = myPreparedStatement.executeQuery();
            myMetaData = myResultSet.getMetaData();
            
            DefaultTableModel tModel = (DefaultTableModel) branchInfo.getModel();
            tModel.setColumnCount(0);
            tModel.setRowCount(0);
            
            String[] branchArr = new String[myMetaData.getColumnCount()];
            
            for(int i = 1; i <= myMetaData.getColumnCount(); i++)
                tModel.addColumn(myMetaData.getColumnName(i));
            
            myResultSet.next();
            
            for(int i = 0; i < myMetaData.getColumnCount(); i++){
                if(myResultSet.getObject(i + 1) == null)
                    branchArr[i] = "NULL";
                else
                    branchArr[i] = myResultSet.getObject(i + 1).toString();
            }
            
            tModel.addRow(branchArr);
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
            
    private void fillWorkerInfo(){
        String GET_BRANCH_WORKER_INFO_QUERY = 
                "SELECT br_code, wrk_name, wrk_lname, wrk_salary " +
                "FROM branch " +
                "INNER JOIN worker ON br_code = wrk_br_code " +
                "WHERE br_code = " + branchSelection.getSelectedItem().toString();
        
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            myPreparedStatement = myConnection.prepareStatement(GET_BRANCH_WORKER_INFO_QUERY);
            myResultSet = myPreparedStatement.executeQuery();
            myMetaData = myResultSet.getMetaData();
            
            DefaultTableModel tModel = (DefaultTableModel) workerInfo.getModel();
            tModel.setColumnCount(0);
            tModel.setRowCount(0);
            
            for(int i = 1; i <= myMetaData.getColumnCount(); i++)
                tModel.addColumn(myMetaData.getColumnName(i));
            
            while(myResultSet.next()){
                String[] workerArr = new String[myMetaData.getColumnCount()];
                
                for(int i = 0; i < myMetaData.getColumnCount(); i++){
                    if(myResultSet.getObject(i + 1) == null)
                        workerArr[i] = "NULL";
                    else
                        workerArr[i] = myResultSet.getObject(i + 1).toString();
                }
                    
                tModel.addRow(workerArr);
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
                    
    private void fillTotalSalaries(){
        String GET_TOTAL_SALARIES_QUERY = 
                "SELECT SUM(wrk_salary) AS 'Total Salary' " +
                "FROM branch " +
                "INNER JOIN worker ON br_code = wrk_br_code " +
                "WHERE br_code = " + branchSelection.getSelectedItem().toString();
        
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            myPreparedStatement = myConnection.prepareStatement(GET_TOTAL_SALARIES_QUERY);
            myResultSet = myPreparedStatement.executeQuery();
            myMetaData = myResultSet.getMetaData();
            
            DefaultTableModel tModel = (DefaultTableModel) totalSalaries.getModel();
            tModel.setColumnCount(0);
            tModel.setRowCount(0);
            
            String[] salaryArr = new String[myMetaData.getColumnCount()];
            
            myResultSet.next();
            for(int i = 0; i < myMetaData.getColumnCount(); i++){
                if(myResultSet.getObject(i + 1) == null)
                    salaryArr[i] = "NULL";
                else
                    salaryArr[i] = myResultSet.getObject(i + 1).toString();
            }
            
            tModel.addColumn(myMetaData.getColumnName(1));
            tModel.addRow(salaryArr);
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tableNameLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        branchInfo = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        workerInfo = new javax.swing.JTable();
        branchSelection = new javax.swing.JComboBox<>();
        text1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        totalSalaries = new javax.swing.JTable();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tableNameLabel.setText("Branch Info");

        branchInfo.setRowHeight(25);
        jScrollPane1.setViewportView(branchInfo);

        jScrollPane2.setViewportView(workerInfo);

        branchSelection.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                branchSelectionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        text1.setText("Select branch:");

        jScrollPane3.setViewportView(totalSalaries);

        closeButton.setText("Main Menu");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(branchSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 65, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(text1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(branchSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeButton)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void branchSelectionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_branchSelectionPopupMenuWillBecomeInvisible
        fillBranchInfo();
        fillWorkerInfo();
        fillTotalSalaries();
    }//GEN-LAST:event_branchSelectionPopupMenuWillBecomeInvisible

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        MainMenu mainMen = new MainMenu();
        dispose();
    }//GEN-LAST:event_closeButtonMouseClicked

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
            java.util.logging.Logger.getLogger(BranchInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BranchInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BranchInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BranchInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BranchInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable branchInfo;
    private javax.swing.JComboBox<String> branchSelection;
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel tableNameLabel;
    private javax.swing.JLabel text1;
    private javax.swing.JTable totalSalaries;
    private javax.swing.JTable workerInfo;
    // End of variables declaration//GEN-END:variables
}
