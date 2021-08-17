package com.ibecerram;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerApp implements Initializable
{
    @FXML
    private Button btnAgregarAudio;

    @FXML
    private ListView<File> listViewAudios = new ListView();
    private ArrayList<File> listaAudios = new ArrayList<>();

    private FileChooser fileChooserAudio = new FileChooser();

    @FXML
    public void agregarAudio()
    {
        File fileAudio = null;
        Stage stage = new Stage();
        try
        {
            fileAudio = fileChooserAudio.showOpenDialog(stage);
            if(fileAudio != null)
            {
                listViewAudios.getItems().add(fileAudio);
                listaAudios.add(fileAudio);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private MediaPlayer mediaPlayer;
    @FXML
    public void reproducirAudio(File file)
    {
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();

    }

    @FXML
    private Button btnAudioPause;

    @FXML
    private Button btnAudioStop;

    @FXML
    private Button btnAnalizar;

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
    public void analizarAudio()
    {
        for(File file : listaAudios)
        {
            Analizador analizador = new Analizador();
            analizador.analizarAudio(file.getAbsolutePath());
            analizador.usarRedNeuronal();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        listViewAudios.setCellFactory(new Callback<ListView<File>, ListCell<File>>()
        {
            @Override
            public ListCell<File> call(ListView<File> fileListView)
            {
                return new ListCell<>()
                {
                    @Override
                    protected void updateItem(File file, boolean empty)
                    {
                        super.updateItem(file, empty);
                        setText(file == null || empty ? null : file.getName());
                    }
                };
            }
        });
    }
}