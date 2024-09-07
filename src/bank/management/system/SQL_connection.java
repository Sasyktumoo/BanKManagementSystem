package bank.management.system;

import java.sql.*;

public class SQL_connection {
    Connection connection;
    Statement statement;
    public SQL_connection(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSystem","root","opaopa228");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}