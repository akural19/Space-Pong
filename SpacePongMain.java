package spacePong;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class SpacePongMain {

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		SpacePongFrame myFrame = new SpacePongFrame(); 
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(1024, 768);
		myFrame.setLocationRelativeTo(null);
		myFrame.setResizable(false);
		myFrame.setVisible(true);
		myFrame.setIconImage(BottomPanel.getGameLogo());
	}
} 