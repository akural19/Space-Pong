package spacePong;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class SpacePongFrame extends JFrame {
	
	private final TopPanel topPanel; 
	private final MiddlePanel midPanel; 
	private final BottomPanel botPanel; 

	public SpacePongFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		super("Space Pong"); 
		
		setLayout(new BorderLayout());
		topPanel = new TopPanel(); 
		midPanel = new MiddlePanel(); 
		botPanel = new BottomPanel(); 
				
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);
	}
}