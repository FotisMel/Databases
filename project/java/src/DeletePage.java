import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class DeletePage extends javax.swing.JFrame {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;
    private static ResultSet myResultSet;
    private static ResultSetMetaData myMetaData;
    
    String GET_TABLE_QUERY = "SELECT * FROM ";
    
    public DeletePage() {
        initComponents();
        setVisible(true);
        
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        tableNameLabel.setForeground(Color.WHITE);
        tableNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        tableNameLabel2.setForeground(Color.WHITE);
        tableNameLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        targetSelectionText.setForeground(Color.WHITE);
        
        initializeThisMenu();
    }
    
    private void initializeThisMenu(){
        String selectedTable = EditTableMenu.currentSelectedTable;
        
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            myPreparedStatement = myConnection.prepareStatement(GET_TABLE_QUERY.concat(selectedTable));
            myResultSet = myPreparedStatement.executeQuery();
            
            tableNameLabel2.setText(selectedTable);
            
            int counter = 1;
            
            while(myResultSet.next()){
                deleteSelection.addItem(String.valueOf(counter));
                counter++;
            }
            
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
    
    private String prepareDeleteQuery(String[] selectedRow, int columns){
        String tempQuery = "DELETE FROM " + EditTableMenu.currentSelectedTable + " WHERE " + EditTableMenu.currentTableColumns[0];
        
        if(selectedRow[0].equals("NULL"))
            tempQuery = tempQuery + " IS ";
        else
            tempQuery = tempQuery + " = ";
        
        if(EditTableMenu.currentColumnTypes[0].equals("INT") || 
           EditTableMenu.currentColumnTypes[0].equals("TINYINT") || 
           EditTableMenu.currentColumnTypes[0].equals("FLOAT") ||
           selectedRow[0].equals("NULL"))
        {
            tempQuery = tempQuery.concat(selectedRow[0]);
        }
        else{
            tempQuery = tempQuery.concat("\"" + selectedRow[0] + "\"");
        }
        
        for(int i = 1; i < columns; i++){
            tempQuery = tempQuery.concat(" AND " + EditTableMenu.currentTableColumns[i]);
            
            if(selectedRow[i].equals("NULL"))
                tempQuery = tempQuery + " IS ";
            else
                tempQuery = tempQuery + " = ";
            
            if(EditTableMenu.currentColumnTypes[i].equals("INT") || 
               EditTableMenu.currentColumnTypes[i].equals("TINYINT") || 
               EditTableMenu.currentColumnTypes[i].equals("FLOAT") ||
               selectedRow[i].equals("NULL"))
            {
                tempQuery = tempQuery.concat(selectedRow[i]);
            }
            else{
                tempQuery = tempQuery.concat("\"" + selectedRow[i] + "\"");
            } 
        }
        
        return tempQuery;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();
        targetSelectionText = new javax.swing.JLabel();
        deleteSelection = new javax.swing.JComboBox<>();
        deleteButton = new javax.swing.JButton();
        tableNameLabel = new javax.swing.JLabel();
        tableNameLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        closeButton.setText("Close");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });

        targetSelectionText.setText("Select to delete");

        deleteButton.setText("Delete");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
        });

        tableNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tableNameLabel.setText("Delete from");

        tableNameLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tableNameLabel2.setText("Placeholder Text");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(closeButton))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tableNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(targetSelectionText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(targetSelectionText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        EditTableMenu.activeSubWindow = false;
        dispose();
    }//GEN-LAST:event_closeButtonMouseClicked

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        JTable myTable = EditTableMenu.currentTableRefference;
        int columnCount = myTable.getColumnCount() - 1;
        String[] rowData = new String[columnCount];
        
        for(int i = 0; i < columnCount; i++){
            rowData[i] = myTable.getValueAt(deleteSelection.getSelectedIndex(), i + 1).toString();
        }
        
        String DELETE_ROW_QUERY = prepareDeleteQuery(rowData, columnCount);
        
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            myPreparedStatement = myConnection.prepareStatement(DELETE_ROW_QUERY);
            
            myPreparedStatement.executeUpdate();
            EditTableMenu.currentTableModel.removeRow(deleteSelection.getSelectedIndex());
            deleteSelection.removeItem(deleteSelection.getSelectedItem().toString());
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
    }//GEN-LAST:event_deleteButtonMouseClicked

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
            java.util.logging.Logger.getLogger(DeletePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeletePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeletePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeletePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeletePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> deleteSelection;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel tableNameLabel;
    private javax.swing.JLabel tableNameLabel2;
    private javax.swing.JLabel targetSelectionText;
    // End of variables declaration//GEN-END:variables
}
