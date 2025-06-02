/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author aritz
 */
public class BD {

    // Referencia a un objeto de la interface java.sql.Connection 
    private static Connection conn;

    public static Connection getConexion() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Map<String, String> mapa = System.getenv();
                String host = mapa.getOrDefault("DB_HOST", "localhost");
                String port = mapa.getOrDefault("DB_PORT", "3306");
                String dbName = mapa.getOrDefault("DB_NAME", "bdcargador");
                String dbUser = mapa.getOrDefault("DB_USER", "root");
                String dbPassword = mapa.getOrDefault("DB_PASSWORD", "root");
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdactivityxx", "root", "root");
                conn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbName, dbUser, dbPassword);
                System.out.println("Se ha conectado.");
            } catch (ClassNotFoundException ex1) {
                System.out.println("No se ha conectado: " + ex1);
            } catch (SQLException ex2) {
                System.out.println("No se ha conectado:" + ex2);
            }
        }
        return conn;
    }

    public static void destroy() {
        System.out.println("Cerrando conexion...");
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar la conexion");
            System.out.println(ex.getMessage());
        }
    }
}
