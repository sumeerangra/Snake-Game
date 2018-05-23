package snakeGame;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
 
public enum GameSound {
   MAINGAMESOUND("new.wav"),  
   GAMEOVER("over.wav"),         
   BEEP("beep.wav");      
   
   public Clip clip;
   
   GameSound(String soundFileName) {
      try {
         
         URL url = this.getClass().getClassLoader().getResource(soundFileName);

         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         
         clip = AudioSystem.getClip();
         
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }
   
   public void stopIt() {
	   if (clip.isRunning())
	         clip.stop(); 
   }
   
   
   public void play() {
         if (clip.isRunning())
         clip.stop();   
         clip.setFramePosition(0); 
         clip.start();     
      
   }
   

   static void init() {
      values(); 
   }
}