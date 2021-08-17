package com.ibecerram;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEscucharAudio implements Initializable
{
    @FXML
    private Button btnPlay;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnStop;

    private File file = null;

    private MediaPlayer mediaPlayer;

    public void reproducirAudio(String path)
    {
        this.file = new File(path);

    }

    public void agregarAudio(String path)
    {
        this.file = new File(path);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(false);
    }

    @FXML
    public void audioStop()
    {
        mediaPlayer.stop();
    }

    @FXML
    public void audioPause()
    {
        mediaPlayer.pause();
    }

    @FXML
    public void audioPlay()
    {
        mediaPlayer.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
}
