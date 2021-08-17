package com.ibecerram.data;

import com.ibecerram.files.Archivo;
import com.ibecerram.files.Canciones;
import jep.Interpreter;
import jep.SharedInterpreter;

import java.util.ArrayList;

public class Analizador
{
    private final String pathCSV = "";
    private ArrayList<Canciones> listaCanciones = new ArrayList<>();
    private Formateador formateador = new Formateador();
    private String pathTemporal = "";

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

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
            e.printStackTrace();
        }
    }

    public ArrayList<Canciones> getListaCanciones()
    {
        return this.listaCanciones;
    }
}
