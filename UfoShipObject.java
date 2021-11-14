package spacePong;

import java.net.URL;

public class UfoShipObject extends StellarObject {
	
	public UfoShipObject(URL url, int width, int height) {
		super(url, width, height); 
	}

	@Override
	public int triggerEvent(int ballPositionX, int ballPositionY) {
		getPanelLocation();
		if (ballPositionX - 4 >= panelPositionX && ballPositionX + 5 <= panelPositionX + 39
			&& ballPositionY - 4 >= panelPositionY && ballPositionY <= panelPositionY + 39){
			MiddlePanel.playSound(3);
			MiddlePanel.resetSound(3);
			return -1;
		}	
		return 0; 
	}
}