package org.speakeasy.grapevine.flock.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database connection class & utilities
 *
 * @author speakeasy
 */
public class SQLiteJDBC {

    public String sDriver = "org.sqlite.JDBC";
    public String sUrl = null;
    public int iTimeout = 30;
    public Connection conn = null;
    public Statement statement = null;

    public SQLiteJDBC() {
        ;
    }

    public SQLiteJDBC(File cwd) throws Exception {
        sUrl = cwd.getAbsolutePath() + "/grapevineflocks.sqlite";
        load(sDriver, sUrl);
    }

    public SQLiteJDBC(String sUrlToLoad) throws Exception {
        this.sUrl = sUrlToLoad;
        load(sDriver, sUrl);
    }

    public void load(String sDriverVar, String sUrlVar) throws Exception {
        setDriver(sDriverVar);
        setUrl(sUrlVar);
        setConnection();
        setStatement();
    }

    private void setDriver(String sDriverVar) {
        sDriver = sDriverVar;
    }

    private void setUrl(String sUrlVar) {
        sUrl = sUrlVar;
    }

    public void setConnection() throws Exception {
        Class.forName(sDriver);
        conn = DriverManager.getConnection("jdbc:sqlite:" + sUrl);
    }

    public Connection getConnection() {
        return conn;
    }

    public void setStatement() throws Exception {
        if (conn == null) {
            setConnection();
        }
        statement = conn.createStatement();
        statement.setQueryTimeout(iTimeout);  // set timeout to 30 sec.
    }

    public Statement getStatement() {
        return statement;
    }

    public void executeStmt(String instruction) throws SQLException {
        statement.executeUpdate(instruction);
    }

    // processes an array of instructions e.g. a set of SQL command strings passed from a file
    //NB you should ensure you either handle empty lines in files by either removing them or parsing them out 
    // since they will generate spurious SQLExceptions when they are encountered during the iteration....
    public void executeStmt(String[] instructionSet) throws SQLException {
        for (int i = 0; i < instructionSet.length; i++) {
            executeStmt(instructionSet[i]);
        }
    }

    public ResultSet executeQry(String instruction) throws SQLException {
        return statement.executeQuery(instruction);
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (Exception ignore) {
        }
    }

}
