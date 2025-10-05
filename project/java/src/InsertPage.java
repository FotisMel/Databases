
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

public class InsertPage extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;
    private static ResultSet myResultSet;
    private static ResultSetMetaData myMetaData;

    public InsertPage() {
        initComponents();
        setVisible(true);
        
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        menuNameLabel.setForeground(Color.WHITE);
        menuNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        errorMessage.setForeground(Color.RED);
        errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        errorMessage.setVisible(false);
        
        initializeThisPage();
    }
    
    private void initializeThisPage(){
        menuNameLabel.setText(menuNameLabel.getText() + EditTableMenu.currentSelectedTable);
        
        DefaultTableModel tModel = (DefaultTableModel) newDataTable.getModel();
        
        for(String columnName : EditTableMenu.currentTableColumns)
            tModel.addColumn(columnName);
        
        tModel.addRow(new String[tModel.getColumnCount()]);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuNameLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        newDataTable = new javax.swing.JTable();
        insertButton = new javax.swing.JButton();
        errorMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        menuNameLabel.setText("Insert into ");

        closeButton.setText("Close");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });

        newDataTable.setRowHeight(30);
        jScrollPane1.setViewportView(newDataTable);

        insertButton.setText("Insert");
        insertButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                insertButtonMouseClicked(evt);
            }
        });

        errorMessage.setText("Something went wrong! Try again.");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(closeButton)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menuNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(errorMessage)
                .addGap(18, 18, 18)
                .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(closeButton)
                .addGap(14, 14, 14))
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

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        EditTableMenu.activeSubWindow = false;
        dispose();
    }//GEN-LAST:event_closeButtonMouseClicked

    private void insertButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertButtonMouseClicked
        errorMessage.setVisible(false);
        
        DefaultTableModel tModel = (DefaultTableModel) newDataTable.getModel();
        
        String[] insertData = new String[tModel.getColumnCount()];
        
        for(int i = 0; i < tModel.getColumnCount(); i++)
            if(tModel.getValueAt(0, i) == null)
                insertData[i] = "";
            else
                insertData[i] = tModel.getValueAt(0, i).toString();
        
        try{
            String INSERT_QUERY = "INSERT INTO " + EditTableMenu.currentSelectedTable + " VALUES (";
            
            for(int i = 0; i < tModel.getColumnCount(); i++){
                if(EditTableMenu.currentColumnTypes[i].equals("INT") ||
                   EditTableMenu.currentColumnTypes[i].equals("TINYINT") ||
                   EditTableMenu.currentColumnTypes[i].equals("FLOAT") ||
                   insertData[i].equals("NULL"))
                    INSERT_QUERY = INSERT_QUERY + insertData[i];
                else
                    INSERT_QUERY = INSERT_QUERY + "\"" + insertData[i] + "\"";
                
                if(i == tModel.getColumnCount() - 1)
                    INSERT_QUERY = INSERT_QUERY + ")";
                else
                    INSERT_QUERY = INSERT_QUERY + ",";
            }
            
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            myPreparedStatement = myConnection.prepareStatement(INSERT_QUERY);
            myPreparedStatement.executeUpdate();
            
            String insertDataForModel[] = new String[insertData.length + 1];
            
            insertDataForModel[0] = Integer.toString(EditTableMenu.currentTableRefference.getRowCount() + 1);
            
            for(int i = 0; i < insertData.length; i++)
                insertDataForModel[i + 1] = insertData[i];
            
            EditTableMenu.currentTableModel.addRow(insertDataForModel);
        }
        catch (SQLException ex) {
            errorMessage.setVisible(true);
        }
        finally{
            
        }
    }//GEN-LAST:event_insertButtonMouseClicked

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel errorMessage;
    private javax.swing.JButton insertButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel menuNameLabel;
    private javax.swing.JTable newDataTable;
    // End of variables declaration//GEN-END:variables
}
