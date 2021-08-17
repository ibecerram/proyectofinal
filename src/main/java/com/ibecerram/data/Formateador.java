package com.ibecerram.data;

public class Formateador
{
    public String getNombre(String line)
    {
        String str = "";
        String[] split = line.split("\\|");
        str = split[0];
        return str;
    }

    public String getGenero(String line)
    {
        String str = "";
        String[] split = line.split("\\|");
        str = split[1];

        String genero = "";
        genero = str.split("\\.")[0];
        return genero;
    }

    public Double getPorcentaje(String line)
    {
        String str = "";
        double porcentaje = 0.0;
        String[] split = line.split("\\|");
        str = split[2];

        porcentaje = Double.parseDouble(str) * 100.0;
        return porcentaje;
    }
}
