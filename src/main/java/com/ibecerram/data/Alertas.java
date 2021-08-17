package com.ibecerram.data;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Clase Alertas que permite la construcción de alertas para mostrar mensajes.
 */
public class Alertas
{
    /**
     * Alerta que muestra un mensaje con las distintas opciones a realizar con un audio: Escucharlo o eliminarlo.
     * @return Tipo de respuesta con su opción.
     */
    public int alertaArchivoSeleccionado()
    {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("INFORMATION");
        alerta.setHeaderText("¿Qué deseas hacer con el audio?");
        alerta.setContentText("Selecciona una opción.");

        ButtonType btnReproducir = new ButtonType("Reproducir");
        ButtonType btnEliminar = new ButtonType("Eliminar");
        ButtonType btnCancelar = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alerta.getButtonTypes().setAll(btnReproducir, btnEliminar, btnCancelar);

        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.get() == btnReproducir)
        {
            return 1;
        }
        else if (resultado.get() == btnEliminar)
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }

    /**
     * Alerta que muestra un mensaje al no haber seleccionado un archivo.
     */
    public void archivoNoSeleccionado()
    {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ERROR");
        alerta.setHeaderText("Archivo no seleccionado.");
        alerta.setContentText("No se seleccionó ningún archivo, \nintenta de nuevo.");
        alerta.showAndWait();
    }

    /**
     * Alerta que muestra un mensaje al no haber encontrado un esperado.
     */
    public void archivoNoEncontrado()
    {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText("Archivo no encontrado");
        alerta.setContentText("No se encontró el archivo, \nintenta de nuevo.");
        alerta.showAndWait();
    }

    /**
     * Alerta que muestra un mensaje debido a un error en el interpretador de Python.
     */
    public void errorPython()
    {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText("Error con Python");
        alerta.setContentText("La red neuronal falló, \nintenta de nuevo.");
        alerta.showAndWait();
    }

    /**
     * Alerta que muestra un mensaje al no mostrar correctamente una imagen.
     */
    public void errorImagenNoCargada()
    {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText("Imagen no encontrada");
        alerta.setContentText("No se puede encontrar la imagen, \nintenta de nuevo.");
        alerta.showAndWait();
    }

    /**
     * Alerta que muestra un mensaje al intentar escribir el en archivo del path.
     */
    public void errorEscrituraArchivo()
    {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText("Escritura en Archivo incorrecta");
        alerta.setContentText("Imposible leer el archivo, \nintenta de nuevo.");
        alerta.showAndWait();
    }

    /**
     * Alerta que muestra un mensaje de error al intentar grabar un audio.
     */
    public void errorAudioCreado()
    {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("ERROR");
        alerta.setHeaderText("No se pudo grabar el audio.");
        alerta.setContentText("Ocurrió un error al grabar el audio, \nintenta de nuevo.");
        alerta.showAndWait();
    }

    /**
     * Alerta que muestra un mensaje al grabar exitosamente un audio.
     */
    public void audioCreado()
    {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("INFORMATION");
        alerta.setHeaderText("¡Audio correcto!");
        alerta.setContentText("El audio se creó en la carpeta inicial.");
        alerta.showAndWait();
    }
}
