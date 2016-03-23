/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojchecador;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidreyespucheta
 */
public class sqlConn {
    
    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        
        try {
            //cargar driver JDBC
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //Establecer conx a DB
            String url = "jdbc:jtds:sqlserver://200.52.131.119:1467;databaseName=HW_TimeChecker";
            //inciar conx
            conn = DriverManager.getConnection(url, "david.reyes", "1y+3[jQ$xQ]");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(sqlConn.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return conn;
    }
    
    //Ya lo movi de aqui a Clase Checar---
    /*public static void connectSql() throws ClassNotFoundException {
        
        try {
            //cargar driver JDBC
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            //Establecer conx a DB
            String url = "jdbc:jtds:sqlserver://200.52.131.119:1467;databaseName=HW_TimeChecker";
            int i;
            
            //Crear SQL
            try (Connection conn = DriverManager.getConnection(url, "david.reyes", "1y+3[jQ$xQ]")) {
                //Crear SQL
                String sql = "INSERT INTO RegistroEventos ("
                        + " Nombre,"
                        + " Evento,"
                        + " Hostname,"
                        + " IPAddress,"
                        + " MacAddress,"
                        + " ClaveEmpleado,"
                        + " Oficina,"
                        + " Departamento,"
                        + " FechaHora) VALUES ("
                        + "?,?,?,?,?,?,?,?,?)";
                //preparar insert
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, "David Reyes");
                st.setString(2, "Entrada");
                st.setString(3, "MacBook Air de David ");
                st.setString(4, "192.168.1.100");
                st.setString(5, "12-23-45-67-89");
                st.setString(6, "21");
                st.setString(7, "Xalapa");
                st.setString(8, "Innovación");
                st.setString(9, LocalDateTime.now().toString());
                //ejecutar Insert
                i = st.executeUpdate();
                //Cerrar conx
            }

            //revisar resultado del insert
            String s;
            Alert alert = new Alert(AlertType.INFORMATION);

            if (i > 0) {
                s = "Se registro la asistencia";
                alert.setTitle("OK");
                alert.setHeaderText("Checar = OK");
            } else {
                s = "No se registro la asistencia";
                alert.setAlertType(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Checar != ok");
            }

            alert.setContentText(s);
            alert.show();

        } catch (SQLException e) {
            System.err.println("Ocurrio una excepción: ");
            System.err.println(e.getMessage());
        }

    }*/

}
