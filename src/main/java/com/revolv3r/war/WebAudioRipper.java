package com.revolv3r.war;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class WebAudioRipper {

  private static final int BUFFER_SIZE = 2048;

    public static void main(String[] args) {

      //parameters: [0] - url
      //[1] - length

      //ripAudioFromStreamUrl(args[0], new Long(args[1]));
      try
      {
        ripAudioFromStreamUrl(new URL("http://54.77.136.103:8000/resonance"), 1000L); //2hours
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
    }

    private static void ripAudioFromStreamUrl(URL aLocation, Long aLength)
    {
      try
      {
        URLConnection connection = aLocation.openConnection();
        InputStream inStream = connection.getInputStream();

        File dest = new File("c://users//williamsm//desktop//mp3_ripper//test_file.mpeg");

        OutputStream outStream = new FileOutputStream(dest);
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;

        System.out.println("Now recording " + aLocation.toString());

        float i = 0;
        while ((length = inStream.read(buffer)) > 0 && i<=aLength) { //1 hour??
          outStream.write(buffer, 0, length);
          i++;
          String strOutput = "=";
          Float positionDone = (i / aLength.floatValue()) * 100;

          for (Long j=1L; j<positionDone.intValue()/10;j++){strOutput+="=";}
          StringUtils.rightPad(strOutput, 10, " ");
          System.out.print("|"+strOutput+"        |\r");
          System.out.flush();
        }

        outStream.close();
    }
    catch (Exception ex)
    {
    Logger.getLogger(WebAudioRipper.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
