package spacePong;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JLabel;

public abstract class StellarObject extends JLabel{
	
	private Random random; 	 
	private static ArrayList<Integer> boundaryIndexList = new ArrayList<>();
	private int[] array1 = {91, 271, 451, 631, 811, 950};
	private int[] array2 = {0  ,180, 360, 540, 720, 900};
	
	private int loggedIndex; 
	private Icon icon; 
	
	protected static int panelPositionX; 
	protected static int panelPositionY; 
	
	public StellarObject(URL url, int width, int height) {
		
		random = new Random();	
		icon = BottomPanel.generateIcon(url, width, height);
		setSize(width, height);
	}
	
	public void setStellarObjectIcon() {
		setIcon(icon);
	}
	
	public void clearStellarObjectIcon() {
		setIcon(null);
	}
	
	private int generateRandomIndex(int min, int max) {
		return random.nextInt(max-min) + min; 
	}
	
	private int generateRandomIndex(int max) {
		return generateRandomIndex(0, max);
	}
	
	public static void setboundaryIndexList() {
		Integer[] array = {0, 1, 2, 3, 4, 5};
		for (Integer ii : array) {
			boundaryIndexList.add(ii);
		} 
	}
	
	public void setRandomPosition() {
		int index = generateRandomIndex(boundaryIndexList.size());
		loggedIndex = boundaryIndexList.get(index);
		boundaryIndexList.remove(index);
		setLocation(generateRandomIndex(array2[loggedIndex], array1[loggedIndex]),
				generateRandomIndex(300));		
	}
	
	public void setRandomPositionWithLoggedIndex() {
		setLocation(generateRandomIndex(array2[loggedIndex], array1[loggedIndex]),
				generateRandomIndex(300));	
	}
	
	protected void getPanelLocation() {
		panelPositionX = getLocation().x;
		panelPositionY = getLocation().y;
	}
	
	public abstract int triggerEvent(int ballPositionX, int ballPositionY);
}