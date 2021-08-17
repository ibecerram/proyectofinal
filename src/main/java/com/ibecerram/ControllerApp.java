package com.ibecerram;

import com.ibecerram.data.Analizador;
import com.ibecerram.files.Canciones;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    private ArrayList<Canciones> listaCanciones = new ArrayList<>();

    private FileChooser fileChooserAudio = new FileChooser();

    @FXML
    private TableView tableViewCanciones;

    @FXML
    private BarChart barChartGeneros;

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

    @FXML
    private Button btnAudioPause;

    @FXML
    private Button btnAudioStop;

    @FXML
    private Button btnAnalizar;

    @FXML
    public void analizarAudio()
    {
        Analizador analizador = new Analizador();
        for(File file : listaAudios)
        {
            analizador.analizarAudio(file.getAbsolutePath());
            analizador.usarRedNeuronal();
        }

        this.listaCanciones.addAll(analizador.getListaCanciones());

        for(Canciones canciones : listaCanciones)
        {
            canciones.getInfo();
            System.out.println("------------");
        }

        agregarCanciones();
        reiniciarListView();
    }

    public void reiniciarListView()
    {
        this.listViewAudios.getItems().clear();
        this.listaAudios.clear();
    }


    public void agregarCanciones()
    {
        this.tableViewCanciones.getItems().clear();
        for(Canciones canciones : listaCanciones)
        {
            tableViewCanciones.getItems().add(canciones);
        }
        this.setContadorGeneros();
    }

    public void setContadorGeneros()
    {
        this.barChartGeneros.getXAxis().setLabel("Genero");
        this.barChartGeneros.getYAxis().setLabel("Cantidad");

        XYChart.Series series = new XYChart.Series();
        series.setName("Generos Contabilizados");
        series.getData().add(new XYChart.Data("Blues", contarGenero("blues")));
        series.getData().add(new XYChart.Data("Classical", contarGenero("classical")));
        series.getData().add(new XYChart.Data("Blues", contarGenero("blues")));
        series.getData().add(new XYChart.Data("Country", contarGenero("country")));
        series.getData().add(new XYChart.Data("Disco", contarGenero("disco")));
        series.getData().add(new XYChart.Data("Hiphop", contarGenero("hiphop")));
        series.getData().add(new XYChart.Data("Jazz", contarGenero("jazz")));
        series.getData().add(new XYChart.Data("Metal", contarGenero("metal")));
        series.getData().add(new XYChart.Data("Pop", contarGenero("pop")));
        series.getData().add(new XYChart.Data("Reggae", contarGenero("reggae")));
        series.getData().add(new XYChart.Data("Rock", contarGenero("rock")));

        barChartGeneros.getData().addAll(series);

    }

    public int contarGenero(String genero)
    {
        int contador = 0;

        for(Canciones canciones : listaCanciones)
        {
            if(canciones.getGenero().contains(genero))
            {
                contador++;
            }
        }

        return contador;
    }



    @FXML
    private VBox vBoxReproducirAudio;

    @FXML
    private ImageView imageViewCancion = new ImageView();

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnStop;

    private File file = null;

    private MediaPlayer mediaPlayer;


    public void agregarAudio(String path)
    {
        this.file = new File(path);
        Media media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        agregarImagen();
    }

    public void agregarImagen()
    {
        try
        {
            Image image = new Image(this.getClass().getResource("/soundeffect.gif").toString());
            imageViewCancion.setImage(image);
            imageViewCancion.setPreserveRatio(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void audioExit()
    {
        vBoxReproducirAudio.setVisible(false);
        imageViewCancion.setImage(null);
        audioStop();
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






    private Alertas alertas = new Alertas();

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

        listViewAudios.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(mouseEvent.getClickCount() == 2)
                {
                    if(listViewAudios.getSelectionModel().getSelectedItems() != null && !listViewAudios.getSelectionModel().getSelectedItems().isEmpty())
                    {
                        int opcion = alertas.alertaArchivoSeleccionado();
                        if(opcion == 1)
                        {
                            int index = listViewAudios.getSelectionModel().getSelectedIndex();
                            agregarAudio(listViewAudios.getItems().get(index).getAbsolutePath());
                            vBoxReproducirAudio.setVisible(true);
                        }
                        else if(opcion == 2)
                        {
                            int index = listViewAudios.getSelectionModel().getSelectedIndex();
                            listViewAudios.getItems().remove(index);
                        }
                    }
                }
            }
        });

        tableViewCanciones.getColumns().clear();
        TableColumn<Canciones, String> columnaPath = new TableColumn<>("Ubicacion");
        TableColumn<Canciones, String> columnaNombre = new TableColumn<>("Titulo");
        TableColumn<Canciones, String> columnaGenero = new TableColumn<>("Género");
        TableColumn<Canciones, Double> columnaPorcentaje = new TableColumn<>("Coincidencia");

        columnaPath.setMinWidth(200);
        columnaNombre.setMinWidth(120);
        columnaGenero.setMinWidth(120);
        columnaPorcentaje.setMinWidth(120);

        columnaPath.setCellValueFactory(new PropertyValueFactory<Canciones, String>("path"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Canciones, String>("nombre"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<Canciones, String>("genero"));
        columnaPorcentaje.setCellValueFactory(new PropertyValueFactory<Canciones, Double>("porcentaje"));

        tableViewCanciones.getColumns().addAll(columnaPath, columnaNombre, columnaGenero, columnaPorcentaje);

    }
}