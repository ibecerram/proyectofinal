package com.ibecerram.data;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Clase que funge como un Hilo que permite grabar un audio por 15 segundos.
 */
public class HiloGrabacionAudio extends Thread
{
    private TargetDataLine targetDataLine = null;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String name = "";

    /**
     * Constructor que recibe como parámetro la línea de audio.
     * @param targetDataLine Línea de audio del sistema.
     */
    public HiloGrabacionAudio(TargetDataLine targetDataLine)
    {
        this.targetDataLine = (TargetDataLine) targetDataLine;
    }

    /**
     * Empieza a escribir el audio durante 15 segundos.
     */
    @Override
    public void run()
    {
        name = "Audio " + format.format(new Date()) + ".wav";
        AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
        File file = new File(name);
        try
        {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
