package com.ibecerram;

import com.ibecerram.data.Alertas;
import com.ibecerram.data.Excepciones;
import com.ibecerram.data.HiloGrabacionAudio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.lang.annotation.Target;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Clase ControllerGrabarAudio tiene los componentes para la grabación de un audio.
 */
public class ControllerGrabarAudio implements Initializable
{
    @FXML
    private Button btnPlay;

    @FXML
    private ImageView imageView;

    private Alertas alertas = new Alertas();

    /**
     * Graba un audio en formato .wav durante 15 segundos.
     */
    public void grabarAudioSistema()
    {
        Stage stage = (Stage) imageView.getScene().getWindow();
        try
        {
            AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
            DataLine.Info dataInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(dataInfo);



            targetDataLine.open();
            targetDataLine.start();

            HiloGrabacionAudio hiloGrabacionAudio = new HiloGrabacionAudio(targetDataLine);
            hiloGrabacionAudio.start();

            TimeUnit.SECONDS.sleep(15);
            hiloGrabacionAudio.interrupt();
            targetDataLine.stop();
            targetDataLine.close();
            alertas.audioCreado();
        }
        catch (Exception e)
        {
            new Excepciones.ErrorCreacionAudio();
        }

        stage.close();
    }

    /**
     * Agrega una imagen de señalización de audio.
     */
    public void recordingImage()
    {
        try
        {
            Image image = new Image(this.getClass().getResource("/recording.png").toString());
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            imageView.setVisible(true);
        }
        catch (Exception e)
        {
            new Excepciones.ImagenNoCargada();
        }


    }

    /**
     * Inicializa el entorno gráfico de la Interfaz Grabar Audio.
     * @param url URL
     * @param resourceBundle ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        recordingImage();
    }
}
