package com.ibecerram.files;

import com.ibecerram.data.Excepciones;

import java.io.FileWriter;

/**
 * Clase que me permite escribir el path del archivo que se quiere analizar.
 */
public class Archivo
{
    /**
     * Método que me permite escribir dentro de pathMusic.txt el archivo a analizar.
     * @param variable Path completo del archivo de audio .wav
     */
    public void escribirArchivo(String variable)
    {
        try
        {
            FileWriter fileWriter = new FileWriter("pathMusic.txt");
            fileWriter.write(variable);
            fileWriter.close();
        }
        catch (Exception e)
        {
            new Excepciones.EscrituraArchivo();
        }
    }
}
