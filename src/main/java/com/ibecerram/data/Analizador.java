package com.ibecerram.data;

import com.ibecerram.files.Archivo;
import com.ibecerram.files.Canciones;
import jep.Interpreter;
import jep.SharedInterpreter;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Clase Analizador, permite la comunicación con los archivos necesarios de Python.
 */
public class Analizador
{
    private final String pathCSV = "";
    private ArrayList<Canciones> listaCanciones = new ArrayList<>();
    private Formateador formateador = new Formateador();
    private String pathTemporal = "";

    /**
     * Método que permite entrenar la red neuronal en el script entrenarRed.py
     */
    public void entrenarRedNeuronal()
    {
        try
        {
            Interpreter interpreter = new SharedInterpreter();
            interpreter.runScript("entrenarRed.py");
            Object object = interpreter.getValue("xPrueba");
            System.out.println(object);
            interpreter.close();
            /*Archivo archivo = new Archivo();
            archivo.escribirArchivo("mipath");*/
        }
        catch (Exception e)
        {
            new Excepciones.ErrorPython();
        }
    }

    /**
     * Método que permite analizar un audio de entrada, usando el archivo analizarAudio.py
     * @param path Ubicación del archivo
     */
    public void analizarAudio(String path)
    {
        Interpreter interpreter = null;
        try
        {
            Archivo archivo = new Archivo();
            archivo.escribirArchivo(path);
            pathTemporal = path;

            interpreter = new SharedInterpreter();
            interpreter.runScript("analizarAudio.py");
            Object object = interpreter.getValue("xPrueba");
            interpreter.close();
            System.out.println(object);

            /*Archivo archivo = new Archivo();
            archivo.escribirArchivo("mipath");*/
        }
        catch (Exception e)
        {
            new Excepciones.ErrorPython();
        }
    }

    /**
     * Método que permite utilizar la red neuronal para analizar canciones, utilizando usarRed.py
     */
    public void usarRedNeuronal()
    {
        try
        {
            Interpreter interpreter = new SharedInterpreter();
            interpreter.runScript("usarRed.py");
            String object = (String) interpreter.getValue("retorno");
            interpreter.close();

            String nombre = formateador.getNombre(object);
            String genero = formateador.getGenero(object);
            Double porcentaje = formateador.getPorcentaje(object);

            listaCanciones.add(new Canciones(this.pathTemporal, nombre, genero, porcentaje));

            //System.out.println(object);

        }
        catch (Exception e)
        {
            new Excepciones.ErrorPython();
        }
    }

    public ArrayList<Canciones> getListaCanciones()
    {
        return this.listaCanciones;
    }
}
