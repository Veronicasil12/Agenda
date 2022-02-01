package edu.uam.controlador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionBaseDatos {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(ConexionBaseDatos.class.getName());
    }

    private final String connectionString;
    private final String user;
    private final String password;
    private Connection connection;

   
    public ConexionBaseDatos(String connectionString, String user, String password) {
        this.connectionString = connectionString;
        this.user = user;
        this.password = password;
    }

    
    public boolean open() {
        if (isOpen()) {
            return true;
        }

        try {
            connection = DriverManager.getConnection(connectionString, user, password);
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to open the database connection.", e);
            return false;
        }
    }

    
    public boolean isOpen() {
        if (connection == null) {
            return false;
        }

        try {
            return !connection.isClosed();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to check if the database connection is open.", e);
            return false;
        }
    }

    
    public boolean close() {
        if (!isOpen()) {
            return true;
        }

        try {
            connection.close();
            connection = null;
            return true;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to close the database connection.", e);
            return false;
        }
    }

  
    public boolean execute(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.execute(sql);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the statement.", e);
            return false;
        }
    }

    
    public int[] executeBatch(String... sql) {
        try {
            Statement statement = connection.createStatement();
            for (String sqlLine : sql) {
                statement.addBatch(sqlLine);
            }
            return statement.executeBatch();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the statement.", e);
            return new int[0];
        }
    }

    
    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the query.", e);
            return null;
        }
    }

    
    public List<Integer> executePrepared(String sqlFormat, Object... items) {
        try {
            PreparedStatement statement = connection.prepareStatement(sqlFormat, Statement.RETURN_GENERATED_KEYS);

            int itemCount = items.length;

            for (int i = 0; i < itemCount; i++) {
                statement.setObject(i + 1, items[i]);
            }

            statement.execute();

            List<Integer> generatedIds = new ArrayList<>();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                generatedIds.add(generatedKeys.getInt(1));
            }

            return generatedIds;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the prepared statement.", e);
            return new ArrayList<>();
        }
    }

    
    public int[] executePreparedBatch(String sqlFormat, List<Object[]> batch) {
        try {
            PreparedStatement statement = connection.prepareStatement(sqlFormat);
            for (Object[] items : batch) {
                int itemCount = items.length;

                for (int i = 0; i < itemCount; i++) {
                    statement.setObject(i + 1, items[i]);
                }

                statement.addBatch();
            }
            return statement.executeBatch();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while trying to execute the prepared statement batch.", e);
            return new int[0];
        }
    }
}