package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import Bean.LoginBean;
import Util.DBConnector;

public class LoginDao {

    // Validate user credentials using a stored procedure
    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        try (Connection connection = DBConnector.getConnection();
             CallableStatement callableStatement = connection.prepareCall("{CALL ValidateLogin(?, ?, ?)}")) {

            // Set input parameters
            callableStatement.setString(1, loginBean.getUsername());
            callableStatement.setString(2, loginBean.getPassword());
            // Register the output parameter for the status
            callableStatement.registerOutParameter(3, java.sql.Types.BOOLEAN);

            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve the result
            status = callableStatement.getBoolean(3);

        } catch (SQLException e) {
            // Handle SQL exception
            printSQLException(e);
        }
        return status;
    }

    // Print SQL exception details
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());

                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
