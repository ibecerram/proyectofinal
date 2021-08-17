package com.ibecerram;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Clase principal de la aplicación, permite lanzar la app.
 *
 */
public class App extends Application
{
    /**
     * Lanza la app para su despliegue.
     * @param args
     */
    public static void main( String[] args )
    {
        launch(args);
    }

    /**
     * Inicializa la apertura de la interfaz.
     * @param primaryStage Escenario principal.
     * @throws Exception Exception por error.
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:src/main/resources/InterfazApp.fxml"));
        primaryStage.setTitle("Analizador Audio");
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
