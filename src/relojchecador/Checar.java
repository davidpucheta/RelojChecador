/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojchecador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author davidreyespucheta
 */
public class Checar {

    public static void setChecar(HashMap<String, String> user) throws ClassNotFoundException {

        int i;
        Connection conn = null;
        PreparedStatement st = null;

        //Crear SQL
        try {

            conn = sqlConn.getConnection();
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
                    + " Email,"
                    + " FechaHora) VALUES ("
                    + "?,?,?,?,?,?,?,?,?,GETDATE())";

            //preparar insert
            st = conn.prepareStatement(sql);
            st.setString(1, user.get("nombre"));
            st.setString(2, user.get("evento"));
            st.setString(3, user.get("hostname"));
            st.setString(4, user.get("ip"));
            st.setString(5, user.get("mac"));
            st.setString(6, user.get("clave"));
            st.setString(7, user.get("oficina"));
            st.setString(8, user.get("departamento"));
            st.setString(9, user.get("email"));
            //st.setString(10, LocalDateTime.now().toString());

            //ejecutar Insert
            i = st.executeUpdate();

            //cerrar conn & st            
            st.close();
            conn.close();

            //revisar resultado del insert
            String s;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            if (i > 0) {
                s =     "Se registro la asistencia correctamente\n" + 
                        "Usuario: " + user.get("nombre") + "\n" +
                        "Hostname: " + user.get("hostname") ;
                alert.setTitle("OK");
                alert.setHeaderText("Registro de asistencia");
            } else {
                s = "No se registro la asistencia";
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Registro de asistencia");
            }

            alert.setContentText(s);
            alert.show();

        } catch (SQLException e) {
            System.err.println("Ocurrio una excepción: ");
            System.err.println(e.getMessage());
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException se2) {
                se2.printStackTrace(System.out);
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace(System.out);
            }
        }
    }

    public static ObservableList<Registro> getChecar() throws SQLException {

        Connection con = sqlConn.getConnection();
        
        HashMap<String, String> myUser = Ajustes.getConfig();

        ObservableList<Registro> data = FXCollections.observableArrayList();
        try {
            String SQL =    "SELECT Evento, CONVERT(VARCHAR(19), FechaHora) AS FechaHora FROM RegistroEventos " +
                            "WHERE CONVERT(date, FechaHora)  = CONVERT(date, GETDATE()) " + 
                            "AND ClaveEmpleado = '"+ myUser.get("clave") +"'  ";
            try (ResultSet rs = con.createStatement().executeQuery(SQL)) {
                while (rs.next()) {
                    String ev = rs.getString("Evento");
                    String fh = rs.getString("FechaHora");
                    
                    //Registro reg = new Registro(ev, fh);
                    data.add(new Registro(ev, fh));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Error on Building Data");
        }
        finally {
            con.close();
        }

        //tableview.setItems(data);
        return data;
    }

}
