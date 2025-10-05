import java.sql.*;

public class DBProject {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/travel_agency";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "2912002";
    private static Connection myConnection;
    
    
    
    public static void main(String[] args) {
        try{
            myConnection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            LoginWindow loginWin = new LoginWindow();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
        
        }
    }   
    
    public static Connection passConnection(){
        return myConnection;
    }
}

