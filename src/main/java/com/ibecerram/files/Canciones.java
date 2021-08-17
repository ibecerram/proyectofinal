package com.ibecerram.files;

public class Canciones
{
    private String path;
    private String nombre;
    private String genero;
    private double porcentaje;

    public Canciones(String path, String nombre, String genero, Double porcentaje)
    {
        this.path = path;
        this.nombre = nombre;
        this.genero = genero;
        this.porcentaje = porcentaje;
    }

    public void getInfo()
    {
        System.out.println(path);
        System.out.println(nombre);
        System.out.println(genero);
        System.out.println(porcentaje);
    }

    public String getPath()
    {
        return path;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getGenero()
    {
        return genero;
    }

    public double getPorcentaje()
    {
        return porcentaje;
    }
}
