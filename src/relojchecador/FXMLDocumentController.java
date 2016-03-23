/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojchecador;

import java.net.SocketException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.control.ComboBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author davidreyespucheta
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Text clock;
    @FXML
    private Text date;
    @FXML
    private ComboBox<String> comboRegistrar;
    @FXML
    private TextField usuarioTextField;
    @FXML
    private TextField claveTextField;
    @FXML
    private TextField departamentoTextField;
    @FXML
    private TextField oficinaTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TabPane tabPane;
    @FXML
    private TableView<Registro> tabla;
    @FXML
    private TableColumn<Registro, String> eventoCol;
    @FXML
    private TableColumn<Registro, String> fechaHoraCol;
    @FXML
    private Button checarBtn;

    private void updateTable() {
        try {
            eventoCol.setCellValueFactory(new PropertyValueFactory<>("evento"));
            fechaHoraCol.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));

            ObservableList<Registro> data = Checar.getChecar();
            tabla.getItems().setAll(data);

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE',' dd 'de' MMMM 'del' yyyy")));
        bindToTime();

        updateTable();

        HashMap<String, String> myUser = Ajustes.getConfig();

        usuarioTextField.setText(myUser.get("usuario"));
        claveTextField.setText(myUser.get("clave"));
        departamentoTextField.setText(myUser.get("departamento"));
        oficinaTextField.setText(myUser.get("oficina"));
        emailTextField.setText(myUser.get("email"));

    }

    @FXML
    protected void checarBtnClick(ActionEvent e) throws SocketException, ClassNotFoundException {
        checarBtn.setDisable(true);

        //Evento de Click 
        String evento = comboRegistrar.getValue();
        HashMap<String, String> myUser = Ajustes.getConfig();
        HashMap<String, String> myNetwork = ResolverNombreHost.getHostname();

        HashMap<String, String> dataChecar = new HashMap<>();

        dataChecar.put("nombre", myUser.get("usuario"));
        dataChecar.put("evento", evento);
        dataChecar.put("hostname", myNetwork.get("hostname"));
        dataChecar.put("ip", myNetwork.get("ip"));
        dataChecar.put("mac", myNetwork.get("mac"));
        dataChecar.put("clave", myUser.get("clave"));
        dataChecar.put("oficina", myUser.get("oficina"));
        dataChecar.put("departamento", myUser.get("departamento"));
        dataChecar.put("email", myUser.get("email"));

        Checar.setChecar(dataChecar);
        updateTable();

        checarBtn.setDisable(false);
    }

    @FXML
    protected void guardarConfig(ActionEvent e) {
        String usuario = usuarioTextField.getText();
        String clave = claveTextField.getText();
        String email = emailTextField.getText();
        String departamento = departamentoTextField.getText();
        String oficina = oficinaTextField.getText();

        Ajustes.setConfig(usuario, email, clave, departamento, oficina);

        tabPane.getSelectionModel().selectFirst();
    }

    // Reloj Digital
    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {

                    LocalDateTime currentTime = LocalDateTime.now();
                    Locale l = new Locale("es", "MX");
                    String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("hh:mm:ss a", l));

                    //Actualizar label del Reloj
                    clock.setText(formattedTime);

                    //si el dia avanza entonces    
                    if ("00:00:00 AM".equals(formattedTime)) {
                        date.setText(currentTime.format(DateTimeFormatter.ofPattern("EEEE',' dd 'de' MMMM 'del' yyyy")));
                    }
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}


/*Codigo para una alerta modal
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Revisar Conexión");
        alert.setHeaderText("Sin conexión");
        String s ="La aplicación no se puede conectar al servidor de checado.\nPor favor revise su conexión a Internet.";
        alert.setContentText(s);
        alert.show();
 */
