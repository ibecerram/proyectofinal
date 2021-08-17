package com.ibecerram.files;

/**
 * Clase Canciones, permite la obtención de los datos del audio para su análisis.
 */
public class Canciones
{
    private String path;
    private String nombre;
    private String genero;
    private double porcentaje;

    /**
     * Constructor de Canciones.
     * @param path Ubicación del archivo.
     * @param nombre Nombre de la canción.
     * @param genero Género de la canción.
     * @param porcentaje Porcentaje de la canción.
     */
    public Canciones(String path, String nombre, String genero, Double porcentaje)
    {
        this.path = path;
        this.nombre = nombre;
        this.genero = genero;
        this.porcentaje = porcentaje;
    }

    /**
     * Método que me permite obtener el path de la canción.
     * @return Path de la canción.
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Método que me permite obtener el nombre de la canción.
     * @return Nombre de la canción.
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Método que me permite obtener el género de la canción.
     * @return Género de la canción.
     */
    public String getGenero()
    {
        return genero;
    }

    /**
     * Método que me permite obtener el porcentaje de coincidencia de la canción.
     * @return Porcentaje de coincidencia de la canción.
     */
    public double getPorcentaje()
    {
        return porcentaje;
    }
}
