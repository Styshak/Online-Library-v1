/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.styshak.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Sergey
 */
public class DBConnection {

    private static InitialContext ic;
    private static Connection con;

    private DBConnection() {

    }

    public static Connection getConnection() {
        try {
            ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/Library");
            if (con == null) {
                con = ds.getConnection();
            }
        } catch (NamingException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
