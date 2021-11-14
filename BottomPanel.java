package spacePong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Icon; 
import javax.swing.ImageIcon; 

public class BottomPanel extends JPanel {
	
	private static JToggleButton playButton;  
	private static ImageIcon gameLogo; 
	
	private Icon iconPlay; 
	private Icon iconPause;
	 
	public BottomPanel() {
		
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(1024,50));
		iconPlay = generateIcon(generateURL("playButton.png"),25,25); 
		iconPause = generateIcon(generateURL("pauseButton.png"),25,25);
		
		gameLogo = generateIcon(generateURL("gameLogo.jpg"),50,50);
		
		playButton = new JToggleButton(" Play ", iconPlay); 
		playButton.setFont(new Font("Verdana", Font.BOLD, 12));
		playButton.setPreferredSize(new Dimension(110,40));
		playButton.setFocusable(false);
		playButton.setSelectedIcon(iconPause);
		playButton.setForeground(Color.WHITE);
		playButton.setBackground(Color.DARK_GRAY);
		playButton.setContentAreaFilled(false);
		add(playButton); 
		
		ButtonHandler handler = new ButtonHandler(); 
		playButton.addItemListener(handler);
	} 
	
	public static void pressButton() {
		playButton.setSelected(!isButtonSelected());
	}
	
	public static boolean isButtonSelected() {
		return playButton.isSelected(); 
	}
	
	public static void deactivateButton(boolean val) {
		playButton.setEnabled(!val);
	}
	
	private URL generateURL(String filename) {
		return getClass().getResource(filename);
	}
	
	public static ImageIcon generateIcon(URL url, int width, int height) {
		ImageIcon imageIcon = new ImageIcon(url);
		Image image = imageIcon.getImage(); 
		image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT); 
		return new ImageIcon(image);  
	}
	
	public static Image getGameLogo() {
		return gameLogo.getImage();
	}
	
	private class ButtonHandler implements ItemListener {
		@Override 
		public void itemStateChanged(ItemEvent event) {
			if (playButton.isEnabled()) { 
				MiddlePanel.timerControl();
				if (isButtonSelected()) {
					MiddlePanel.clearLogo();
					MiddlePanel.setStellarObjectIcons(); 
					playButton.setText(" Pause");
					MiddlePanel.playSound(0);
				}
				else {
					MiddlePanel.setPausedLogo();
					playButton.setText(" Play ");
					MiddlePanel.stopSound(0);				
				}
			}
			else {
				playButton.setSelected(false);
			}
		}
	}
}