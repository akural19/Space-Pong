package spacePong;

import java.net.URL;

public class MagicPotObject extends StellarObject {
	
	public MagicPotObject(URL url, int width, int height) {
		super(url, width, height); 
	}

	@Override
	public int triggerEvent(int ballPositionX, int ballPositionY) {
		getPanelLocation();
		if (ballPositionX - 4 >= panelPositionX && ballPositionX + 5 <= panelPositionX + 39
			&& ballPositionY - 4 >= panelPositionY && ballPositionY <= panelPositionY + 39){
			MiddlePanel.playSound(5);
			MiddlePanel.resetSound(5);
			TopPanel.alterLives(1);
			MiddlePanel.alterBallVelocity(0.8);
			return 1;
		}	
		return 0; 
	}
}