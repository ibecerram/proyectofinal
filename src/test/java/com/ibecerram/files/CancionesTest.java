package com.ibecerram.files;

import junit.framework.TestCase;

public class CancionesTest extends TestCase
{

    public void testGetPath()
    {
        Canciones song = new Canciones("src/audio.wav", "my way", "jazz", 44.3);
        assertEquals("src/audio.wav", song.getPath());
    }

    public void testGetNombre()
    {
        Canciones song = new Canciones("src/audio.wav", "my way", "jazz", 44.3);
        assertEquals("my way", song.getNombre());
    }

    public void testGetGenero()
    {
        Canciones song = new Canciones("src/audio.wav", "my way", "jazz", 44.3);
        assertEquals("jazz", song.getGenero());
    }

    public void testGetPorcentaje()
    {
        Canciones song = new Canciones("src/audio.wav", "my way", "jazz", 44.3);
        assertEquals(44.3, song.getPorcentaje());
    }
}