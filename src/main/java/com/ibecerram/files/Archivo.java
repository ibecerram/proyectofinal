package com.ibecerram.files;

import java.io.FileWriter;

public class Archivo
{
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
            e.printStackTrace();
        }
    }
}
