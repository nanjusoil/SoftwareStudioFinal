package main.java;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlay {
	
	private static AudioInputStream tmpStream;
	private static Clip tmp;
	
	public static Clip getMusic( String path ){
		
		try {
			tmpStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
			tmp = AudioSystem.getClip();
			tmp.open(tmpStream);
		}catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
		return tmp;
		
	}

}
