import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingConstants;

public class AddITPage extends javax.swing.JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;
    private static ResultSet myResultSet;

    public AddITPage() {
        initComponents();
        setVisible(true);
        
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        tableNameLabel.setForeground(Color.WHITE);
        tableNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setForeground(Color.WHITE);
        nameLabel.setForeground(Color.WHITE);
        lnameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        salaryLabel.setForeground(Color.WHITE);
        branchLabel.setForeground(Color.WHITE);
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        
        initializeBranchList();
    }    
    
    private void initializeBranchList(){
        try{
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            myPreparedStatement = myConnection.prepareStatement("SELECT br_code FROM branch");
            myResultSet = myPreparedStatement.executeQuery();
            
            branch.removeAllItems();
            branch.addItem("Branch");
            while(myResultSet.next())
                branch.addItem(myResultSet.getObject(1).toString());
        }
        catch(SQLException ex){
            
        }
        finally{
            
        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tableNameLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        branch = new javax.swing.JComboBox<>();
        salary = new javax.swing.JSpinner();
        id = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        branchLabel = new javax.swing.JLabel();
        lnameLabel = new javax.swing.JLabel();
        salaryLabel = new javax.swing.JLabel();
        errorMessage = new javax.swing.JLabel();
        returnButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tableNameLabel.setText("Add IT");

        idLabel.setText("ID number:");

        addButton.setText("Add");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });

        branch.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                branchPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        nameLabel.setText("First name:");

        passwordLabel.setText("Password:");

        branchLabel.setText("Branch:");

        lnameLabel.setText("Last name:");

        salaryLabel.setText("Salary:");

        errorMessage.setText("You need to fill in all input fields!");

        returnButton1.setText("Main Menu");
        returnButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(118, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameLabel)
                            .addComponent(lnameLabel)
                            .addComponent(idLabel))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(branchLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(salaryLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(passwordLabel)))
                        .addGap(18, 18, 18)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salary)
                            .addComponent(branch, 0, 75, Short.MAX_VALUE))
                        .addGap(86, 86, 86))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(returnButton1)
                .addGap(218, 218, 218))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(tableNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idLabel)
                    .addComponent(passwordLabel)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel)
                    .addComponent(salaryLabel)
                    .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnameLabel)
                    .addComponent(branchLabel)
                    .addComponent(branch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(errorMessage)
                .addGap(33, 33, 33)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(returnButton1)
                .addGap(22, 22, 22))
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

    private void branchPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_branchPopupMenuWillBecomeInvisible
        if(!branch.getSelectedItem().equals("Branch"))
            branch.removeItem("Branch");
    }//GEN-LAST:event_branchPopupMenuWillBecomeInvisible

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        errorMessage.setVisible(false);
        
        String newID = id.getText();
        String newName = name.getText();
        String newLname =  lname.getText();
        String newPassword =  password.getText();
        float newSalary = Integer.parseInt(salary.getValue().toString());
        int newBranch = 0;
        
        if(!branch.getSelectedItem().toString().equals("Branch"))
            newBranch = Integer.parseInt(branch.getSelectedItem().toString());
        
        if(newID.equals("") || newName.equals("") ||
           newLname.equals("") || newPassword.equals("") ||
           newSalary == 0 || newBranch == 0){
            errorMessage.setVisible(true);
        }
        else{
            String WORKER_INSERT_QUERY = "INSERT INTO worker VALUES (\"" + newID + "\",\"" + newName + "\",\"" + newLname + "\"," + newSalary + "," + newBranch + ")";
            String IT_INSERT_QUERY = "INSERT INTO IT VALUES (\"" + newID + "\",\"" + newPassword + "\",\"" + "2022-01-01" + "\"," + "NULL)";
            
            try{
                myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                myPreparedStatement = myConnection.prepareStatement(WORKER_INSERT_QUERY);
                int test = myPreparedStatement.executeUpdate();
                

                myPreparedStatement = myConnection.prepareStatement(IT_INSERT_QUERY);
                test = myPreparedStatement.executeUpdate();
                
            }
            catch(SQLException ex){

            }
            finally{

            }
        }
    }//GEN-LAST:event_addButtonMouseClicked

    private void returnButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButton1MouseClicked
        MainMenu mainMen = new MainMenu();
        dispose();
    }//GEN-LAST:event_returnButton1MouseClicked

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
            java.util.logging.Logger.getLogger(AddITPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddITPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddITPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddITPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddITPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox<String> branch;
    private javax.swing.JLabel branchLabel;
    private javax.swing.JLabel errorMessage;
    private javax.swing.JTextField id;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField lname;
    private javax.swing.JLabel lnameLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField name;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton returnButton1;
    private javax.swing.JSpinner salary;
    private javax.swing.JLabel salaryLabel;
    private javax.swing.JLabel tableNameLabel;
    // End of variables declaration//GEN-END:variables
}
