package spacePong;

import java.net.URL;

public class MeteoriteObject extends StellarObject {

	public MeteoriteObject(URL url, int width, int height) {
		super(url, width, height); 
	}
	
	@Override
	public int triggerEvent(int ballPositionX, int ballPositionY) {
		getPanelLocation();
		if (ballPositionX - 4 >= panelPositionX && ballPositionX + 5 <= panelPositionX + 59
				&& ballPositionY - 4 >= panelPositionY && ballPositionY <= panelPositionY + 59){
			MiddlePanel.alterBallVelocity(1.1);
			MiddlePanel.playSound(4);
			MiddlePanel.resetSound(4);
			return 1; 
		}	
		return 0; 
	}
}