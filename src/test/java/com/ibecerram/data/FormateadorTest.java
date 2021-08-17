package com.ibecerram.data;

import junit.framework.TestCase;

public class FormateadorTest extends TestCase
{

    public void testGetNombre()
    {
        String line = "audio.wav|pop.09|55.3";
        Formateador formateador = new Formateador();
        assertEquals("audio.wav", formateador.getNombre(line));
    }

    public void testGetGenero()
    {
        String line = "audio.wav|pop.09|55.3";
        Formateador formateador = new Formateador();
        assertEquals("pop", formateador.getGenero(line));
    }

    public void testGetPorcentaje()
    {
        String line = "audio.wav|pop.09|5";
        Formateador formateador = new Formateador();
        assertEquals(500.0, formateador.getPorcentaje(line));
    }
}