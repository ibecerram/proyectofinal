package com.ibecerram.data;

/**
 * Clase Excepciones, extiende de Exception para mostrar mensajes dependiendo del error encontrado.
 */
public class Excepciones extends Exception
{
    Alertas alertas = new Alertas();

    /**
     * Excepción ArchivoNoSeleccionado se ejecuta cuando no se eligió ningún archivo.
     */
    public static class ArchivoNoSeleccionado extends Excepciones
    {
        /**
         * Muestra el mensaje al usuario.
         */
        public ArchivoNoSeleccionado()
        {
            alertas.archivoNoSeleccionado();
        }
    }

    /**
     * Excepción ArchivoNoEncontrado se ejecuta cuando el archivo elegido no existe.
     */
    public static class ArchivoNoEncontrado extends Excepciones
    {
        /**
         * Muestra el mensaje al usuario.
         */
        public ArchivoNoEncontrado()
        {
            alertas.archivoNoEncontrado();
        }
    }

    /**
     * Excepción ErrorPython, se ejecuta cuando no se puede realizar la interpretación del archivo de Python.
     */
    public static class ErrorPython extends Excepciones
    {
        /**
         * Muestra el mensaje al usuario.
         */
        public ErrorPython()
        {
            alertas.errorPython();
        }
    }

    /**
     * Excepción ImagenNoCargada, sucede cuando no se cargar la imagen o gif al reproducir el audio.
     */
    public static class ImagenNoCargada extends Excepciones
    {
        /**
         * Muestra el mensaje al usuario.
         */
        public ImagenNoCargada()
        {
            alertas.errorImagenNoCargada();
        }
    }

    /**
     * Excepción EscrituraArchivo, se ejecuta al momento de no encontrar el archivo que servirá de conexión entre el programa y la red neuronal.
     */
    public static class EscrituraArchivo extends Excepciones
    {
        /**
         * Muestra el mensaje al usuario.
         */
        public EscrituraArchivo()
        {
            alertas.errorEscrituraArchivo();
        }
    }

}
