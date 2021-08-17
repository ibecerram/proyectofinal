package com.ibecerram;

import jep.Interpreter;
import jep.SharedInterpreter;

public class Analizador
{
    private final String pathCSV = "";

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

            interpreter = new SharedInterpreter();
            interpreter.runScript("analizarAudio.py");
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

    public void usarRedNeuronal()
    {
        try
        {
            Interpreter interpreter = new SharedInterpreter();
            interpreter.runScript("usarRed.py");
            Object object = interpreter.getValue("retorno");
            System.out.println(object);
            interpreter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
