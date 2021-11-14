package spacePong;

import java.net.URL;

public class StarObject extends StellarObject {

	public StarObject(URL url, int width, int height) {
		super(url, width, height); 
	}
	
	@Override
	public int triggerEvent(int ballPositionX, int ballPositionY) {
		getPanelLocation();
		if (ballPositionX - 4 >= panelPositionX && ballPositionX + 5 <= panelPositionX + 39
				&& ballPositionY - 4 >= panelPositionY && ballPositionY <= panelPositionY + 39){
			TopPanel.increaseScore(2);
			MiddlePanel.playSound(1);
			MiddlePanel.resetSound(1);
			return 1;
		}
		return 0; 
	}
}