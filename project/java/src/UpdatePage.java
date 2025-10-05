import java.sql.*;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class UpdatePage extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;
    private static ResultSet myResultSet;
    private static ResultSetMetaData myMetaData;
    
    String[] selectedRowData;

    public UpdatePage() {
        initComponents();
        setVisible(true);
        
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        tableNameLabel.setForeground(Color.WHITE);
        tableNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        targetSelectionText.setForeground(Color.WHITE);
        updateErrorMessage.setForeground(Color.RED);
        updateErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        updateErrorMessage.setVisible(false);
        
        initializeThisMenu();
    }
    
    private void initializeThisMenu(){
        tableNameLabel.setText(tableNameLabel.getText() + EditTableMenu.currentSelectedTable);
        
        DefaultTableModel tModel = (DefaultTableModel) selectedRowTable.getModel();
        
        int rows = EditTableMenu.currentTableModel.getRowCount();
        
        for(int i = 0; i < rows; i++)
            updateSelection.addItem(Integer.toString(i + 1));
        
        for(int i = 0; i < EditTableMenu.currentTableColumns.length; i++)
            tModel.addColumn(EditTableMenu.currentTableColumns[i]);
        
        fillTable();
    }
    
    private void fillTable(){
        DefaultTableModel tModel = (DefaultTableModel) selectedRowTable.getModel();
        
        int selectedRow = Integer.valueOf(updateSelection.getSelectedItem().toString());
        int columnCount = EditTableMenu.currentTableModel.getColumnCount() - 1;
        selectedRowData = new String[columnCount];
        
        tModel.setRowCount(0);
        
        for(int i = 0; i < columnCount; i++){
            selectedRowData[i] = EditTableMenu.currentTableModel.getValueAt(selectedRow - 1, i + 1).toString();
        }
        
        tModel.addRow(selectedRowData);
    }

    private String prepareUpdateQuery(String[] newRowData){
        String tempQuery = "UPDATE " + EditTableMenu.currentSelectedTable + " SET " + EditTableMenu.currentTableColumns[0] + " = ";
        
        if(EditTableMenu.currentColumnTypes[0].equals("INT") || 
           EditTableMenu.currentColumnTypes[0].equals("TINYINT") || 
           EditTableMenu.currentColumnTypes[0].equals("FLOAT") ||
           newRowData[0].equals("NULL"))
        {
            tempQuery = tempQuery.concat(newRowData[0]);
        }
        else{
            tempQuery = tempQuery.concat("\"" + newRowData[0] + "\"");
        }
        
        for(int i = 1; i < newRowData.length; i++){
            tempQuery = tempQuery.concat(", " + EditTableMenu.currentTableColumns[i] + " = ");
            if(EditTableMenu.currentColumnTypes[i].equals("INT") || 
               EditTableMenu.currentColumnTypes[i].equals("TINYINT") || 
               EditTableMenu.currentColumnTypes[i].equals("FLOAT") ||
               newRowData[i].equals("NULL"))
            {
                tempQuery = tempQuery.concat(newRowData[i]);
            }
            else{
                tempQuery = tempQuery.concat("\"" + newRowData[i] + "\"");
            } 
        }
        
        tempQuery = tempQuery.concat(" WHERE " + EditTableMenu.currentTableColumns[0]);
        
        if(selectedRowData[0].equals("NULL"))
            tempQuery = tempQuery + " IS ";
        else
            tempQuery = tempQuery + " = ";
        
        if(EditTableMenu.currentColumnTypes[0].equals("INT") || 
           EditTableMenu.currentColumnTypes[0].equals("TINYINT") || 
           EditTableMenu.currentColumnTypes[0].equals("FLOAT") ||
           selectedRowData[0].equals("NULL"))
        {
            tempQuery = tempQuery.concat(selectedRowData[0]);
        }
        else{
            tempQuery = tempQuery.concat("\"" + selectedRowData[0] + "\"");
        }
        
        for(int i = 1; i < selectedRowData.length; i++){
            tempQuery = tempQuery.concat(" AND " + EditTableMenu.currentTableColumns[i]);
            
            if(selectedRowData[i].equals("NULL"))
                tempQuery = tempQuery + " IS ";
            else
                tempQuery = tempQuery + " = ";
            
            if(EditTableMenu.currentColumnTypes[i].equals("INT") || 
               EditTableMenu.currentColumnTypes[i].equals("TINYINT") || 
               EditTableMenu.currentColumnTypes[i].equals("FLOAT") ||
               selectedRowData[i].equals("NULL"))
            {
                tempQuery = tempQuery.concat(selectedRowData[i]);
            }
            else{
                tempQuery = tempQuery.concat("\"" + selectedRowData[i] + "\"");
            } 
        }
        
        return tempQuery;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tableNameLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        updateSelection = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectedRowTable = new javax.swing.JTable();
        targetSelectionText = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        updateErrorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tableNameLabel.setText("Update ");

        closeButton.setText("Close");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });

        updateSelection.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                updateSelectionPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        selectedRowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(selectedRowTable);

        targetSelectionText.setText("Select to update");

        updateButton.setText("Update");
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });

        updateErrorMessage.setText("Something went wrong!");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateSelection, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(targetSelectionText, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(updateErrorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(closeButton))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(targetSelectionText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateSelection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(updateErrorMessage)
                .addGap(18, 18, 18)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(closeButton)
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

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        EditTableMenu.activeSubWindow = false;
        dispose();
    }//GEN-LAST:event_closeButtonMouseClicked

    private void updateSelectionPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_updateSelectionPopupMenuWillBecomeInvisible
        fillTable();
    }//GEN-LAST:event_updateSelectionPopupMenuWillBecomeInvisible

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
        updateErrorMessage.setVisible(false);
        
        DefaultTableModel tModel = (DefaultTableModel) selectedRowTable.getModel();
        String[] tempRow = new String[selectedRowData.length];
        
        for(int i = 0; i < selectedRowData.length; i++)
            tempRow[i] = tModel.getValueAt(0, i).toString();
        
        String UPDATE_TABLE_QUERY = prepareUpdateQuery(tempRow);
        
        try {
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            myPreparedStatement = myConnection.prepareStatement(UPDATE_TABLE_QUERY);

            myPreparedStatement.executeUpdate();
            
            for(int i = 0; i < tempRow.length; i++)
            EditTableMenu.currentTableRefference.setValueAt(tempRow[i], Integer.valueOf(updateSelection.getSelectedItem().toString()) - 1, i + 1);
        }
        catch (SQLException ex) {
            updateErrorMessage.setVisible(true);
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
    }//GEN-LAST:event_updateButtonMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdatePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable selectedRowTable;
    private javax.swing.JLabel tableNameLabel;
    private javax.swing.JLabel targetSelectionText;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel updateErrorMessage;
    private javax.swing.JComboBox<String> updateSelection;
    // End of variables declaration//GEN-END:variables
}
