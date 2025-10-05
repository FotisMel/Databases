import java.awt.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EditTableMenu extends javax.swing.JFrame {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;
    private static ResultSet myResultSet;
    private static ResultSetMetaData myMetaData;
    
    String GET_TABLE_NAMES_QUERY = "SHOW TABLES"; 
    String GET_TABLE_QUERY = "SELECT * FROM ";
    
    private final String[] actions = {"Action", "Insert", "Update", "Delete"};
    
    public static JTable currentTableRefference;
    public static String[] currentColumnTypes;
    public static String currentSelectedTable;
    public static String[] currentTableColumns;
    public static DefaultTableModel currentTableModel;
    public static boolean activeSubWindow = false;
    
    
    
    public EditTableMenu() {
        initComponents();
        setVisible(true);
        
        setSize(1100, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        tableScrollPanel.setVisible(false);
        
        tableData.setEnabled(false);
        
        actionSelectionText.setForeground(Color.WHITE);
        tableSelectionText.setForeground(Color.WHITE);
        normalText1.setForeground(Color.WHITE);
        
        actionSelectionErrorMessage.setForeground(Color.RED);
        tableSelectionErrorMessage.setForeground(Color.RED);
        actionSelectionErrorMessage.setVisible(false);
        tableSelectionErrorMessage.setVisible(false);
        
        initializeThisMenu();
    }

    private void initializeThisMenu(){
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            myPreparedStatement = myConnection.prepareStatement(GET_TABLE_NAMES_QUERY);
            myResultSet = myPreparedStatement.executeQuery();
            
            for(String action : actions)
                actionSelection.addItem(action);
            
            tableSelection.addItem("Table");
            while(myResultSet.next())
                tableSelection.addItem(myResultSet.getObject(1).toString());
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            if(myConnection != null)
                try{
                    myConnection.close();
                } catch(SQLException ex){}
            
            if(myPreparedStatement != null)
                try{
                    myPreparedStatement.close();
                } catch(SQLException ex){}
            
            if(myResultSet != null)
                try{
                    myResultSet.close();
                } catch(SQLException ex){}
        }
    }
    
    private void fillTable(){
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            myPreparedStatement = myConnection.prepareStatement(GET_TABLE_QUERY.concat(tableSelection.getSelectedItem().toString()));
            myResultSet = myPreparedStatement.executeQuery();
            myMetaData = myResultSet.getMetaData();
            
            DefaultTableModel tModel = (DefaultTableModel) tableData.getModel();
            int newColumnCount = myMetaData.getColumnCount();
            currentTableColumns = new String[newColumnCount];
            currentColumnTypes = new String[newColumnCount];
            currentTableModel = tModel;
            int counter = 1;
            
            tModel.setRowCount(0);
            tModel.setColumnCount(0);
            
            tModel.addColumn("index");
            for(int i = 1; i <= newColumnCount; i++){
                tModel.addColumn(myMetaData.getColumnName(i));
                currentTableColumns[i - 1] = myMetaData.getColumnName(i);
                currentColumnTypes[i - 1] = myMetaData.getColumnTypeName(i);
            }
                
            while(myResultSet.next()){
                String[] newRowData = new String[newColumnCount + 1];
                
                newRowData[0] = String.valueOf(counter);
                counter++;
                
                for(int i = 1; i <= newColumnCount; i++)
                    if(myResultSet.getObject(i) == null)
                        newRowData[i] = "NULL";
                    else
                        newRowData[i] = myResultSet.getObject(i).toString();
                
                tModel.addRow(newRowData);
            }
            
            currentTableRefference = tableData;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally{
            if(myConnection != null)
                try{
                    myConnection.close();
                } catch(SQLException ex){}
            
            if(myPreparedStatement != null)
                try{
                    myPreparedStatement.close();
                } catch(SQLException ex){}
            
            if(myResultSet != null)
                try{
                    myResultSet.close();
                } catch(SQLException ex){}
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        actionSelectionText = new javax.swing.JLabel();
        tableSelection = new javax.swing.JComboBox<>();
        actionSelection = new javax.swing.JComboBox<>();
        confirmButton = new javax.swing.JButton();
        normalText1 = new javax.swing.JLabel();
        tableSelectionText = new javax.swing.JLabel();
        actionSelectionErrorMessage = new javax.swing.JLabel();
        tableSelectionErrorMessage = new javax.swing.JLabel();
        tableScrollPanel = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        returnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        actionSelectionText.setText("Select action:");

        tableSelection.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                tableSelectionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        actionSelection.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                actionSelectionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        confirmButton.setText("Confirm");
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                confirmButtonMouseClicked(evt);
            }
        });

        normalText1.setText("Into");

        tableSelectionText.setText("Select table:");

        actionSelectionErrorMessage.setText("You must select an action!");

        tableSelectionErrorMessage.setText("You must select a table!");

        jLayeredPane2.setLayer(actionSelectionText, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(tableSelection, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(actionSelection, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(confirmButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(normalText1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(tableSelectionText, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(actionSelectionErrorMessage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(tableSelectionErrorMessage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(actionSelection, 0, 110, Short.MAX_VALUE)
                    .addComponent(actionSelectionText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(normalText1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tableSelectionText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableSelection, 0, 109, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(actionSelectionErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableSelectionErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actionSelectionText)
                    .addComponent(tableSelectionText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(actionSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(normalText1)
                    .addComponent(tableSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(actionSelectionErrorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableSelectionErrorMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableScrollPanel.setViewportView(tableData);

        returnButton.setText("Main Menu");
        returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(tableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(501, 501, 501)
                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(120, Short.MAX_VALUE)
                        .addComponent(tableScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(returnButton)
                .addGap(32, 32, 32))
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

    private void actionSelectionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_actionSelectionPopupMenuWillBecomeInvisible
        if(!actionSelection.getSelectedItem().equals("Action"))
            actionSelection.removeItem("Action");
    }//GEN-LAST:event_actionSelectionPopupMenuWillBecomeInvisible

    private void tableSelectionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_tableSelectionPopupMenuWillBecomeInvisible
        if(!tableSelection.getSelectedItem().equals("Table")){
            tableSelection.removeItem("Table");
            
            tableScrollPanel.setVisible(true);
            
            currentSelectedTable = tableSelection.getSelectedItem().toString();
            fillTable();
        }
    }//GEN-LAST:event_tableSelectionPopupMenuWillBecomeInvisible

    private void returnButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButtonMouseClicked
        MainMenu mainMen = new MainMenu();
        dispose();
    }//GEN-LAST:event_returnButtonMouseClicked

    private void confirmButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseClicked
        actionSelectionErrorMessage.setVisible(false);
        tableSelectionErrorMessage.setVisible(false);
        
        if(actionSelection.getSelectedItem().equals("Action") || tableSelection.getSelectedItem().equals("Table")){
            if(actionSelection.getSelectedItem().equals("Action"))
                actionSelectionErrorMessage.setVisible(true);
            
            if(tableSelection.getSelectedItem().equals("Table"))
                tableSelectionErrorMessage.setVisible(true);
        }
        else{
            if(actionSelection.getSelectedItem().equals("Insert") && activeSubWindow == false){
                InsertPage inPage = new InsertPage();
                activeSubWindow = true;
            }
            if(actionSelection.getSelectedItem().equals("Update") && activeSubWindow == false){
                UpdatePage upPage = new UpdatePage();
                activeSubWindow = true;
            }
            if(actionSelection.getSelectedItem().equals("Delete") && activeSubWindow == false){
                DeletePage delPage = new DeletePage();
                activeSubWindow = true;
            }
        }
    }//GEN-LAST:event_confirmButtonMouseClicked

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
            java.util.logging.Logger.getLogger(EditTableMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditTableMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditTableMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditTableMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditTableMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> actionSelection;
    private javax.swing.JLabel actionSelectionErrorMessage;
    private javax.swing.JLabel actionSelectionText;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel normalText1;
    private javax.swing.JButton returnButton;
    private javax.swing.JTable tableData;
    private javax.swing.JScrollPane tableScrollPanel;
    private javax.swing.JComboBox<String> tableSelection;
    private javax.swing.JLabel tableSelectionErrorMessage;
    private javax.swing.JLabel tableSelectionText;
    // End of variables declaration//GEN-END:variables
}
