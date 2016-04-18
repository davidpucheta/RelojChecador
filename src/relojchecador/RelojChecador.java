/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relojchecador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author davidreyespucheta
 */
public class RelojChecador extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Stage secondStage = new Stage();

        //Venatana Principal
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(400.00);
        stage.setHeight(400.00);
        stage.show();

        //Ventana WebCam
        Parent secondRoot = FXMLLoader.load(getClass().getResource("/WebCam/FXML.fxml"));
        Scene secondScene = new Scene(secondRoot);
        secondStage.setScene(secondScene);
        secondStage.initStyle(StageStyle.UNDECORATED);
        secondStage.initOwner(stage);
        secondStage.setWidth(400.00);
        secondStage.setHeight(400.00);
        secondStage.setX(stage.getX() + 410.00);
        secondStage.setY(stage.getY());
        secondStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
