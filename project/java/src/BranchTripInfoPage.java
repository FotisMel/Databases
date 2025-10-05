
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class BranchTripInfoPage extends javax.swing.JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;
    private static ResultSet myResultSet;
    private static ResultSet myResultSet2;
    private static ResultSetMetaData myMetaData;
    
    public BranchTripInfoPage() {
        initComponents();
        setVisible(true);
        
        setSize(1100, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        tripDataTable.setEnabled(false);
        
        text1.setForeground(Color.WHITE);
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text2.setForeground(Color.WHITE);
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        tableNameLabel.setForeground(Color.WHITE);
        tableNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void fillTable(){
        try {
            DefaultTableModel tModel = (DefaultTableModel) tripDataTable.getModel();
            tModel.setColumnCount(0);
            tModel.setRowCount(0);
            
            String[] columnNames = {"br_code","tr_cost","tr_maxseats","reservations","empty seats","Driver lname","Driver name","Guide lname","Guide name","tr_departure","tr_return"};
            
            for(String name : columnNames)
                tModel.addColumn(name);
            
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            String GET_BRANCH_CODES = "SELECT br_code FROM branch";

            myPreparedStatement = myConnection.prepareStatement(GET_BRANCH_CODES);
            myResultSet = myPreparedStatement.executeQuery();
            
            while(myResultSet.next()){
                String CALL_PROCEDURE = "CALL check_branch_trips(" + myResultSet.getObject(1).toString() + ",\"" + formStartDate() + "\",\"" + formEndDate() + "\")";
            
                myPreparedStatement = myConnection.prepareStatement(CALL_PROCEDURE);
                myResultSet2 = myPreparedStatement.executeQuery();
                myMetaData = myResultSet2.getMetaData();
                
                while(myResultSet2.next()){
                    String[] newRow = new String[myMetaData.getColumnCount() + 1];
                    newRow[0] = myResultSet.getObject(1).toString();
                    
                    for(int i = 1; i <= myMetaData.getColumnCount(); i++){
                        if(myResultSet2.getObject(i) == null)
                            newRow[i] = "NULL";
                        else
                            newRow[i] = myResultSet2.getObject(i).toString();
                    }
                    
                    tModel.addRow(newRow);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private String formStartDate(){
        return startYear.getSelectedItem().toString() + "-" + startMonth.getSelectedItem().toString() + "-" + startDay.getSelectedItem().toString() + " 00:00:00";
    }
    
    private String formEndDate(){
        return endYear.getSelectedItem().toString() + "-" + endMonth.getSelectedItem().toString() + "-" + endDay.getSelectedItem().toString() + " 00:00:00";
    }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tripDataTable = new javax.swing.JTable();
        returnButton = new javax.swing.JButton();
        tableNameLabel = new javax.swing.JLabel();
        endMonth = new javax.swing.JComboBox<>();
        startMonth = new javax.swing.JComboBox<>();
        endDay = new javax.swing.JComboBox<>();
        startDay = new javax.swing.JComboBox<>();
        startYear = new javax.swing.JComboBox<>();
        endYear = new javax.swing.JComboBox<>();
        text1 = new javax.swing.JLabel();
        text2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(tripDataTable);

        returnButton.setText("Main Menu");
        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnButtonMouseClicked(evt);
            }
        });

        tableNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tableNameLabel.setText("Branch Trip Info");

        endMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        endMonth.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                endMonthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        startMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        startMonth.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                startMonthPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        startMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startMonthActionPerformed(evt);
            }
        });

        endDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        endDay.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                endDayPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        startDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        startDay.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                startDayPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        startYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024", "2025", " " }));
        startYear.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                startYearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        endYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024", "2025", " " }));
        endYear.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                endYearPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        text1.setText("From:");

        text2.setText("To:");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(317, 317, 317)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(text1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startDay, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startYear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(text2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endDay, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endYear, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(453, 453, 453)
                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text1)
                    .addComponent(text2))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
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

    private void returnButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButtonMouseClicked
        MainMenu mainMen = new MainMenu();
        dispose();
    }//GEN-LAST:event_returnButtonMouseClicked

    private void startMonthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_startMonthPopupMenuWillBecomeInvisible
        startDay.removeAllItems();
        
        if(startMonth.getSelectedItem().toString().equals("4") ||
           startMonth.getSelectedItem().toString().equals("6") ||
           startMonth.getSelectedItem().toString().equals("9") ||
           startMonth.getSelectedItem().toString().equals("11")){
            for(int i = 1; i <= 30; i++)
                startDay.addItem(Integer.toString(i));
        }
        else if(startMonth.getSelectedItem().toString().equals("2")){
            for(int i = 1; i <= 28; i++)
                startDay.addItem(Integer.toString(i));
        }
        else{
            for(int i = 1; i <= 31; i++)
                startDay.addItem(Integer.toString(i));
        } 
        
        fillTable();
    }//GEN-LAST:event_startMonthPopupMenuWillBecomeInvisible

    private void startDayPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_startDayPopupMenuWillBecomeInvisible
        fillTable();
    }//GEN-LAST:event_startDayPopupMenuWillBecomeInvisible

    private void startYearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_startYearPopupMenuWillBecomeInvisible
        fillTable();
    }//GEN-LAST:event_startYearPopupMenuWillBecomeInvisible

    private void endMonthPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_endMonthPopupMenuWillBecomeInvisible
        endDay.removeAllItems();
        
        if(endMonth.getSelectedItem().toString().equals("4") ||
           endMonth.getSelectedItem().toString().equals("6") ||
           endMonth.getSelectedItem().toString().equals("9") ||
           endMonth.getSelectedItem().toString().equals("11")){
            for(int i = 1; i <= 30; i++)
                endDay.addItem(Integer.toString(i));
        }
        else if(startMonth.getSelectedItem().toString().equals("2")){
            for(int i = 1; i <= 28; i++)
                endDay.addItem(Integer.toString(i));
        }
        else{
            for(int i = 1; i <= 31; i++)
                endDay.addItem(Integer.toString(i));
        } 
        
        fillTable();
    }//GEN-LAST:event_endMonthPopupMenuWillBecomeInvisible

    private void endDayPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_endDayPopupMenuWillBecomeInvisible
        fillTable();
    }//GEN-LAST:event_endDayPopupMenuWillBecomeInvisible

    private void endYearPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_endYearPopupMenuWillBecomeInvisible
        fillTable();
    }//GEN-LAST:event_endYearPopupMenuWillBecomeInvisible

    private void startMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startMonthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startMonthActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BranchTripInfoPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> endDay;
    private javax.swing.JComboBox<String> endMonth;
    private javax.swing.JComboBox<String> endYear;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton returnButton;
    private javax.swing.JComboBox<String> startDay;
    private javax.swing.JComboBox<String> startMonth;
    private javax.swing.JComboBox<String> startYear;
    private javax.swing.JLabel tableNameLabel;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel text2;
    private javax.swing.JTable tripDataTable;
    // End of variables declaration//GEN-END:variables
}
