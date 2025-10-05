import java.awt.*;
import java.sql.*;
import java.awt.event.KeyEvent;

public class LoginWindow extends javax.swing.JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    private static PreparedStatement myPreparedStatement;
    private static ResultSet myResultSet;
    private static ResultSetMetaData myMetaData;
    
    public LoginWindow() {
        initComponents();
        setVisible(true);
        
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        mainPanel.setBackground(new Color(666666));
        
        usernameErrorMessage.setVisible(false);
        usernameErrorMessage.setForeground(Color.red);
        
        passwordErrorMessage.setVisible(false);
        passwordErrorMessage.setForeground(Color.red);
        
        loginResult.setForeground(Color.red);
    }
    
    private void loginProcedure(){
        usernameErrorMessage.setVisible(false);
        passwordErrorMessage.setVisible(false);
        
        if(usernameVariable.getText().equals("") || passwordVariable.getText().equals("")){
            if(usernameVariable.getText().equals(""))
                usernameErrorMessage.setVisible(true);
            if(passwordVariable.getText().equals(""))
                passwordErrorMessage.setVisible(true);
        }
        else{
            try{
                myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                String SEARCH_IT_QUERY = 
                        "SELECT wrk_lname, IT_password, IT_AT FROM worker INNER JOIN IT on IT_AT = wrk_AT WHERE wrk_lname = ?"; 
                myPreparedStatement = myConnection.prepareStatement(SEARCH_IT_QUERY);
                myPreparedStatement.setString(1, usernameVariable.getText());
                myResultSet = myPreparedStatement.executeQuery();
                myMetaData = myResultSet.getMetaData();
                
                if(myResultSet.next()){
                    boolean userFoundFlag = false;
                    
                    do{
                        if(myResultSet.getObject(2).toString().equals(passwordVariable.getText())){
                            userFoundFlag = true;
                            break;
                        }
                    }while(myResultSet.next());
                    
                    if(userFoundFlag){
                        String CLEAR_USER = "DELETE FROM current_active_user";
                        String LOGIN_QUERY = "INSERT INTO current_active_user VALUES (" + myResultSet.getObject(3).toString() + ")"; 
                        myPreparedStatement = myConnection.prepareStatement(CLEAR_USER);
                        myPreparedStatement.executeUpdate();
                        myPreparedStatement = myConnection.prepareStatement(LOGIN_QUERY);
                        myPreparedStatement.executeUpdate();
                        
                        MainMenu mainMen = new MainMenu();
                        dispose();
                    }
                    else loginResult.setText("Wrong password for this User");
                }
                else loginResult.setText("There is no user with this Username. Please try again");
            }
            catch(SQLException  ex){
                ex.printStackTrace();
            }
            finally{
                
            }
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        layeredPanel = new javax.swing.JLayeredPane();
        usernameVariable = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        loginResult = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        passwordErrorMessage = new javax.swing.JLabel();
        usernameErrorMessage = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        passwordVariable = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        usernameVariable.setForeground(new java.awt.Color(0, 0, 0));
        usernameVariable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameVariableKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login Window");
        jLabel1.setAutoscrolls(true);

        loginResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginResult.setText(" ");
        loginResult.setAutoscrolls(true);

        loginButton.setText("Login");
        loginButton.setAutoscrolls(true);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });

        passwordErrorMessage.setText("You must enter a Password!");
        passwordErrorMessage.setAutoscrolls(true);

        usernameErrorMessage.setText("You must enter a Username!");
        usernameErrorMessage.setAutoscrolls(true);

        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("Username:");
        usernameLabel.setAutoscrolls(true);

        passwordVariable.setForeground(new java.awt.Color(0, 0, 0));
        passwordVariable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordVariableKeyPressed(evt);
            }
        });

        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password:");
        passwordLabel.setAutoscrolls(true);

        layeredPanel.setLayer(usernameVariable, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(loginResult, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(loginButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(passwordErrorMessage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(usernameErrorMessage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(usernameLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(passwordVariable, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(passwordLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layeredPanelLayout = new javax.swing.GroupLayout(layeredPanel);
        layeredPanel.setLayout(layeredPanelLayout);
        layeredPanelLayout.setHorizontalGroup(
            layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layeredPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layeredPanelLayout.createSequentialGroup()
                                .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(passwordLabel)
                                    .addComponent(usernameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(passwordVariable)
                                    .addComponent(usernameVariable)
                                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameErrorMessage)
                            .addComponent(passwordErrorMessage)))
                    .addComponent(loginResult, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layeredPanelLayout.setVerticalGroup(
            layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameVariable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameErrorMessage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordVariable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordErrorMessage))
                .addGap(34, 34, 34)
                .addComponent(loginResult)
                .addGap(18, 18, 18)
                .addComponent(loginButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addComponent(layeredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(layeredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
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

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        loginProcedure();
    }//GEN-LAST:event_loginButtonMouseClicked

    private void passwordVariableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordVariableKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginProcedure();
        }
    }//GEN-LAST:event_passwordVariableKeyPressed

    private void usernameVariableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameVariableKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginProcedure();
        }
    }//GEN-LAST:event_usernameVariableKeyPressed

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
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginResult;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel passwordErrorMessage;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordVariable;
    private javax.swing.JLabel usernameErrorMessage;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameVariable;
    // End of variables declaration//GEN-END:variables
}
