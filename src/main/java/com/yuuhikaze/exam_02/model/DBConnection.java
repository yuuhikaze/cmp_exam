package com.yuuhikaze.exam_02.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.flywaydb.core.Flyway;

public class DBConnection {

    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    public DBConnection(String className, String connectionChain, String user, String passsword) {
        performMigration(connectionChain, user, passsword);
        try {
            Class.forName(className);
            this.connection = DriverManager.getConnection(connectionChain, user, passsword);
            this.connection.setAutoCommit(true);
        } catch (SQLException | ClassNotFoundException var6) {
            System.out.println(var6.getMessage());
        }
    }

    public void performMigration(String connectionChain, String user, String password) {
        Flyway flyway =
            Flyway.configure()
                .dataSource(
                    connectionChain + "?createDatabaseIfNotExist=true",
                    user,
                    password)
                .load();
        flyway.migrate();
    }

    public void executeQuery(String query) throws SQLException {
        this.statement = this.connection.createStatement();
        this.resultSet = this.statement.executeQuery(query);
    }

    public int executeInstruction(String instruction) throws SQLException {
        int rows = 0;
        this.statement = this.connection.createStatement();
        rows = this.statement.executeUpdate(instruction);
        return rows;
    }

    public void closeResult() {
        try {
            this.resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeStatement() {
        try {
            this.statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            if (this.resultSet != null) {
                this.closeResult();
            }
            if (this.statement != null) {
                this.closeStatement();
            }
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
