package spacePong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopPanel extends JPanel {
	
	private static JLabel timeLabel; 
	private static JLabel levelLabel; 
	private static JLabel scoreLabel; 
	private static JLabel livesLabel; 
	
	private static int level = 1; 
	private static int lives = 3; 
	private static int score = 0; 
	
	 
	public TopPanel() { 
		
		setLayout(new FlowLayout(FlowLayout.CENTER,100,20)); 
		setPreferredSize(new Dimension(1024,60));
		setBackground(Color.BLACK);
		levelLabel = generateJLabel();
		setLevelText(); 
		livesLabel = generateJLabel(); 
		setLivesText(); 
		scoreLabel = generateJLabel(); 
		setScoreText(); 
		timeLabel = generateJLabel();
		setDefaultTimerText();
	}
	
	public static void setDefaultTimerText() {
		timeLabel.setText("01:00:0");
	}
	
	public static void setTimerText(int sec, int milisec) {
		timeLabel.setText(String.format("00:%02d:%d", sec, milisec));
	}
	
	public static void setLevelText() {
		levelLabel.setText(String.format("Level %d", level));
		MiddlePanel.alterLevelStartBallVelocity();
		level++;
	}
	
	private static void setLivesText() {
		String livesText = ""; 
		for (int ii = lives; ii > 0; ii--) {
			livesText += "🖤";
		}
		for (int ii = 10 - lives; ii > 0; ii--) {
			livesText += "    ";
		}
		livesLabel.setText(livesText); 
	}
	
	public static void alterLives(int ii) {
		if (ii == 1) {
			lives += 1;   
		}
		if (ii == -1 && lives > 0) {
			lives -= 1;
		}
		setLivesText();  
		if (lives == 0) {
			MiddlePanel.setGameOverLogo();
			MiddlePanel.setFinalScoreLabelText(score);
			BottomPanel.deactivateButton(true);
			MiddlePanel.clearStellarObjectIcons();
			MiddlePanel.playSound(2);
			MiddlePanel.resetSound(2);
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	resetGame();
			            }
			        }, 
			        2500
			);
		}
	}
	
	public static int getLives() {
		return lives;
	}
	
	private static void setScoreText() {
		scoreLabel.setText(String.format("%03d", score));
	}
	
	public static void increaseScore(int scoreIncrement) {
		score += scoreIncrement; 
		setScoreText(); 
	}
	
	private JLabel generateJLabel() {
		JLabel label = new JLabel(); 
		label.setFont(new Font("Serif", Font.BOLD, 17));
		label.setForeground(Color.CYAN);
		add(label);
		return label; 
	}
	
	private static void resetGame() {
		BottomPanel.deactivateButton(false);
		level = 1; 
		score = 0; 
		lives = 3; 
		MiddlePanel.setDefaultTime();
		setLevelText(); 
		setScoreText(); 
		setLivesText(); 
		setDefaultTimerText(); 
		MiddlePanel.resetSound(0);
		MiddlePanel.setSpacePongLogo();
		MiddlePanel.setDefaultBallVelocity();
		MiddlePanel.clearFinalScoreLabelText();
	}
}     