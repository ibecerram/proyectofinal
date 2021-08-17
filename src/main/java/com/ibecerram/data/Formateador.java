package com.ibecerram.data;

/**
 * Clase Formateador, permite obtener la entrada resultante y transformarla en datos válidos.
 */
public class Formateador
{
    /**
     * Permite obtener el nombre de la canción analizada de la red neuronal.
     * @param line Resultado obtenido por la red neuronal
     * @return Nombre de la canción.
     */
    public String getNombre(String line)
    {
        String str = "";
        String[] split = line.split("\\|");
        str = split[0];
        return str;
    }

    /**
     * Permite obtener el género de la canción analizada de la red neuronal.
     * @param line Resultado obtenido por la red neuronal
     * @return Género de la canción.
     */
    public String getGenero(String line)
    {
        String str = "";
        String[] split = line.split("\\|");
        str = split[1];

        String genero = "";
        genero = str.split("\\.")[0];
        return genero;
    }

    /**
     * Permite obtener el porcentaje de clasificación de la canción analizada de la red neuronal.
     * @param line Resultado obtenido por la red neuronal
     * @return Porcentaje de la canción con el género asignado.
     */
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
