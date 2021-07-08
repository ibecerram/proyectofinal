package com.ibecerram;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FxMediaPlayer extends Application {
    File selectedFile;
    MediaPlayer mplayer;
    Slider musicSlider;

    public FxMediaPlayer() {
        musicSlider = new Slider();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane() {{
            Label filenameLabel = new Label("");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Audio Files", "*.wav", "*.mp3"));

            VBox vbox = new VBox() {{
                getChildren().add(filenameLabel);
                HBox hbox = new HBox() {{

                    Button playButton = new Button("Play");
                    Button pauseButton = new Button("Pause");

                    playButton.setOnAction(e -> {
                        mplayer.play();
                    });
                    pauseButton.setOnAction(e -> {
                        mplayer.pause();
                    });

                    Button stopButton = new Button("Stop") {{
                        setOnAction(e -> {
                            mplayer.stop();
                        });
                    }};

                    getChildren().addAll(playButton, pauseButton, stopButton);
                }};
                getChildren().add(hbox);
            }};
            setCenter(vbox);

            MenuBar menubar = new MenuBar() {{
                Menu menu = new Menu("File") {{
                    MenuItem selectMenuItem = new MenuItem("Select") {{
                        setOnAction(e -> {
                            selectedFile = fileChooser.showOpenDialog(primaryStage);
                            if (selectedFile != null) {
                                Media media = null;

                                Pattern spacePattern = Pattern.compile(" ");
                                String url = ("file://" + selectedFile.getAbsolutePath());
                                Matcher matcher = spacePattern.matcher(url);
                                url = matcher.replaceAll("\\ ");

                                System.out.println(url);
                                media = new Media(url);

                                mplayer = new MediaPlayer(media);

                                musicSlider.setMin(0);
                                musicSlider.setMax(mplayer.getStopTime().toSeconds());
                            }
                        });
                    }};
                    MenuItem pauseMenuItem = new MenuItem("Pause");
                    MenuItem playMenuItem = new MenuItem("Play");
                    MenuItem stopMenuItem = new MenuItem("Stop");

                    getItems().addAll(selectMenuItem, playMenuItem, pauseMenuItem, stopMenuItem);
                }};
                getMenus().add(menu);
            }};
            setTop(menubar);

            setBottom(musicSlider);
        }};

        new Thread(new Runnable() {
            @Override
            public void run() {
                double currentTime = mplayer.getCurrentTime().toSeconds();
                musicSlider.setValue(currentTime);
                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scene scene = new Scene(root, 400, 100);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}