package spacePong;

import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class MiddlePanel extends JPanel {
	
	private static JLabel gameLabel; 
	private static JLabel finalScoreLabel; 
	private static Timer timer; 
	
	private static long startTime;
	private static long currentTime;
	private static int elapsedTime;
	private static int time; 
	
	private static Icon spacePongLogo;
	private static Icon gameOverLogo;
	private static Icon paused;
	
	private AudioInputStream audioStream; 
	
	private static Clip mainThemeClip;
	private static Clip gameOverClip;
	private static Clip blepClip; 
	private static Clip ufoShipClip; 
	private static Clip speedUpClip; 
	private static Clip magicPotClip;
	
	private static ArrayList<Clip> clips = new ArrayList<>();  
	
	private static int paddlePositionX; 
	private static int paddlePositionY; 
	
	private static int ballPositionX; 
	private static int ballPositionY; 
	
	private static double ballVelocityX; 
	private static double ballVelocityY;
	
	private static double levelStartBallVelocityX; 
	private static double levelStartBallVelocityY; 
		
	private StellarObject starLabel1;
	private StellarObject starLabel2;
	private StellarObject starLabel3;
	private StellarObject ufoShipLabel;
	private StellarObject meteoriteLabel;
	private StellarObject magicPotLabel;
	
	private static StellarObject[] stellarObjects = new StellarObject[6]; 
			
	public MiddlePanel() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		
		setLayout(null);
		setBackground(Color.BLACK);
		setFocusable(true);
		
		KeyHandler handler = new KeyHandler(); 
		addKeyListener(handler);
		TimerHandler timerHandler = new TimerHandler(); 
		setDefaultTime();
		timer = new Timer(20, timerHandler); 
		timer.setInitialDelay(0);
		
		spacePongLogo = getIcon(generateURL("spacePong.png"));
		gameOverLogo =  getIcon(generateURL("gameOver.png"));
		paused = getIcon(generateURL("paused.png"));
		
		setGameLabel();
		setSpacePongLogo(); 
		setFinalScoreLabel();
		
		setStarLabel();
		setUfoShipLabel();
		setMeteoriteLabel();
		setMagicPotLabel(); 
		
		setRandomStellarObjectPositions();
	
		mainThemeClip = generateClip(generateURL("spacePongSoundTrack.wav")); 
		mainThemeClip.open(audioStream); 
		clips.add(mainThemeClip); // 0
		
		blepClip = generateClip(generateURL("blep.wav")); 
		blepClip.open(audioStream); 
		clips.add(blepClip); // 1
		
		gameOverClip = generateClip(generateURL("gameOver.wav")); 
		gameOverClip.open(audioStream); 
		clips.add(gameOverClip); // 2
		
		ufoShipClip = generateClip(generateURL("ufoShipBlep.wav"));
		ufoShipClip.open(audioStream);
		clips.add(ufoShipClip); // 3
		
		speedUpClip = generateClip(generateURL("speedUp.wav"));
		speedUpClip.open(audioStream);
		clips.add(speedUpClip); // 4
		
		magicPotClip = generateClip(generateURL("magicPot.wav"));
		magicPotClip.open(audioStream);
		clips.add(magicPotClip); // 5
		
		setDefaultObjectPositions();
		setDefaultBallVelocity();		
	}
	
	private URL generateURL(String filename) {
		return getClass().getResource(filename);
	}
	
	private Icon getIcon(URL url) {
		return new ImageIcon(url);
	}
	
	private void setGameLabel() {
		  gameLabel = new JLabel(); 
		  gameLabel.setSize(355,195);
		  setCenterAllignment(gameLabel);
		  gameLabel.setLocation(327,203);
		  add(gameLabel);  
	}
	
	private void setFinalScoreLabel() {
		finalScoreLabel = new JLabel();
		finalScoreLabel.setSize(355,50);
		finalScoreLabel.setFont(new Font("Algerian", Font.BOLD, 35));
		finalScoreLabel.setForeground(Color.MAGENTA);
		setCenterAllignment(finalScoreLabel);
		finalScoreLabel.setLocation(327,363);
		add(finalScoreLabel);
	}

	private void setRandomStellarObjectPositions() {
		StellarObject.setboundaryIndexList();
		for (StellarObject stellarObject: stellarObjects) {
			stellarObject.setRandomPosition();
		}
	}
	
	public static void setStellarObjectIcons() {
		for (StellarObject stellarObject: stellarObjects) {
			stellarObject.setStellarObjectIcon();
			}
	}
	
	public static void clearStellarObjectIcons() {
		for (StellarObject stellarObject: stellarObjects) {
			stellarObject.clearStellarObjectIcon();
			}
	}
	
	private void setStarLabel() {
		starLabel1 = new StarObject(generateURL("star.png"), 40, 40); 
		starLabel2 = new StarObject(generateURL("star.png"), 40, 40); 
		starLabel3 = new StarObject(generateURL("star.png"), 40, 40); 
		stellarObjects[0] = starLabel1;
		stellarObjects[1] = starLabel2;
		stellarObjects[2] = starLabel3;
		add(starLabel1);
		add(starLabel2);
		add(starLabel3);
	}
	
	private void setUfoShipLabel() {
		ufoShipLabel = new UfoShipObject(generateURL("ufoship.png"), 40, 40);
		stellarObjects[3] = ufoShipLabel;
		add(ufoShipLabel);  
	}
	 
	private void setMeteoriteLabel() {
		meteoriteLabel = new MeteoriteObject(generateURL("meteorite.png"), 60, 60);
		stellarObjects[4] = meteoriteLabel;
		add(meteoriteLabel);  
	}
	
	private void setMagicPotLabel() {
		magicPotLabel = new MagicPotObject(generateURL("magicPot.gif"), 40, 40);
		stellarObjects[5] = magicPotLabel;
		add(magicPotLabel);  
	}
		
	private void setCenterAllignment(JLabel label) {
		label.setLayout(new BorderLayout());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
	}
	
	public static void setDefaultTime() {
		time = 60000;
	}
	
	public static void timerControl() {
		if (BottomPanel.isButtonSelected()){
			timer.start();
			startTime = System.currentTimeMillis();
		}
		else {
			timer.stop();
			time -= elapsedTime;
		}
	}
	
	public static void setSpacePongLogo() {
		gameLabel.setIcon(spacePongLogo);
	}
		
	public static void setGameOverLogo() {
		gameLabel.setIcon(gameOverLogo);
	}
	
	public static void setPausedLogo() {
		gameLabel.setIcon(paused);
	}
	
	public static void clearLogo() {
		gameLabel.setIcon(null);
	}
	
	private Clip generateClip(URL url) throws UnsupportedAudioFileException,
	IOException, LineUnavailableException  {
		audioStream = AudioSystem.getAudioInputStream(url);
	    return AudioSystem.getClip();
	}
	
	public static void playSound(int ii) {
		clips.get(ii).start();
	}
	
	public static void stopSound(int ii) {
		clips.get(ii).stop();
	}
	
	public static void resetSound(int ii) {
		clips.get(ii).setMicrosecondPosition(0);
	} 
	
	public static void setFinalScoreLabelText(int score) {
		finalScoreLabel.setText(String.format("Score: %03d", score));	
	}
	 
	public static void clearFinalScoreLabelText() {
		finalScoreLabel.setText(null);
	}
	
	@Override 
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		graphics.setColor(Color.YELLOW);
		graphics.fillRect(paddlePositionX, paddlePositionY, 120, 10);
		graphics.drawRect(0, 0, 1009, 620);
		
		graphics.setColor(Color.WHITE);
		graphics.fillOval(ballPositionX-4, ballPositionY-4, 10, 10);	
	}
	
	public void setDefaultObjectPositions() {
		paddlePositionX = 445;
		paddlePositionY = 600;
		
		ballPositionX = 10; 
		ballPositionY = 100; 
		
		repaint();
	} 
	
	public static void setDefaultBallVelocity() {
		ballVelocityX = 16;  
		ballVelocityY = 1;	
		
		levelStartBallVelocityX = 16; 
		levelStartBallVelocityY = 1; 
	} 
	
	public static void setLevelStartBallVelocity() {
		ballVelocityX = levelStartBallVelocityX; 
		ballVelocityY = levelStartBallVelocityY; 
	}
	
	public static void alterLevelStartBallVelocity() {
		levelStartBallVelocityX *= 1.2;
		levelStartBallVelocityY *= 1.2;
		setLevelStartBallVelocity(); 
	}
	
	public static void velocityTest() {
		if (ballVelocityY < 1 && ballVelocityY > 0) {
			ballVelocityY = 1; 
		}
		else if (ballVelocityY > -1 && ballVelocityY < 0)
			ballVelocityY = -1;
		if (ballVelocityX < 1 && ballVelocityX > 0) {
			ballVelocityX = 1; 
		}
		else if (ballVelocityX > -1 && ballVelocityX < 0)
			ballVelocityX = -1;
	}
	
	
	public static void alterBallVelocity(double ii) {
		ballVelocityX *= ii; 
		ballVelocityY *= ii;
		
		velocityTest(); 
	}
	 
	private void updateBallPosition() { 
		ballPositionX += ballVelocityX; 
		ballPositionY += ballVelocityY;
	}
	
	private void updateBallVelocity() {
		ballVelocityY += 9.8*0.07;
	}
	
	private void checkVelocityDirectionNegation() {
		if (ballPositionX + 5 >= 1007) {
			ballPositionX = 1002; 
			ballVelocityX *= -1; 
		}
		else if (ballPositionX - 4 <= 2) {
			ballPositionX = 6; 
			ballVelocityX *= -1; 
		}
		if (ballPositionY - 4 <= 2) {
			ballPositionY = 6; 
			ballVelocityY *= -0.6;   
			velocityTest();
		}
		else if (ballPositionX >= paddlePositionX && ballPositionX <= paddlePositionX + 119) {
			if (ballPositionY + 5 >= 590 && ballPositionY + 5 <= 589 + ballVelocityY) {
				if (ballPositionY - ballVelocityY < 590) {
					ballPositionY = 590; 
				}
				
				ballVelocityY *= -1; 
				TopPanel.increaseScore(1);	
			}
		}
		if (ballPositionY > 604) {
			newLive();		            }
	}
	
	public void newLive() {
		if (BottomPanel.isButtonSelected()) {
			BottomPanel.pressButton();
		}
		clearLogo();
		TopPanel.alterLives(-1);
		setDefaultObjectPositions();
		setRandomStellarObjectPositions();
		setLevelStartBallVelocity(); 
	}
	
	private void newLevel() {
		setDefaultTime();
		TopPanel.setDefaultTimerText();
		TopPanel.setLevelText();
		resetSound(0);
		setDefaultObjectPositions();
		setRandomStellarObjectPositions();
	}
	
	private void triggerStellarObjectEvent() {
		for (StellarObject stellarObject : stellarObjects) {
			int flag = stellarObject.triggerEvent(ballPositionX, ballPositionY);
			if (flag == 1) {
				stellarObject.setRandomPositionWithLoggedIndex();
			}
			else if (flag == -1) {
				stellarObject.setRandomPositionWithLoggedIndex();
				if (TopPanel.getLives() == 1) {
					newLive();
				}
				else {
					TopPanel.alterLives(-1);
				}
			}
		}	
	}
	
	private class TimerHandler implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
		    currentTime = System.currentTimeMillis();
		    elapsedTime = (int) (currentTime-startTime);
		    int sec = (time - elapsedTime)/1000; 
			int milisec = ((time - elapsedTime) % 1000)/100;  
			if (elapsedTime >= time) {
				TopPanel.setTimerText(0,0);
				if (BottomPanel.isButtonSelected()) {
					BottomPanel.pressButton();
				}
				clearLogo(); 
				new java.util.Timer().schedule( 
				        new java.util.TimerTask() {
				            @Override
				            public void run() {
				            	newLevel();
				            }
				        }, 
				        100
				); 
			}
			else {
				TopPanel.setTimerText(sec, milisec);
			}
			updateBallPosition();  
			checkVelocityDirectionNegation();
			updateBallVelocity(); 
			repaint();
			triggerStellarObjectEvent();
		}   
	}
	
	private class KeyHandler extends KeyAdapter {
		@Override 
		public void keyPressed(KeyEvent event) {
			if ((event.getKeyCode() == KeyEvent.VK_RIGHT) && (timer.isRunning())) {
				if (paddlePositionX + 149 <= 1007) {
					paddlePositionX += 30; 
				}
				else {
					paddlePositionX = 888;
				}
			}	
			else if ((event.getKeyCode() == KeyEvent.VK_LEFT) && (timer.isRunning())){
				if (paddlePositionX -30 >= 2) {
					paddlePositionX -= 30; 
				}
				else {
					paddlePositionX = 2; 
				}
			}
			else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
				BottomPanel.pressButton();
			}
		}
	}
}