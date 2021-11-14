# Space-Pong
Java’s Swing API is used (which provides a graphical user interface for java programs) to implement a Space Pong game for Comp132 (Spring21) Term Project. 

Copyright, all rights reserved. 

Source code of this project belongs to Alp Kural, and the project is implemented for educational purposes at Koc University. 

Java Files of the Project	is listed as :

-SpacePongMain.java:   Main class of the game (No command line argument)

-SpacePongFrame.java   -TopPanel.java   -MiddlePanel.java   -BottomPanel.java   -StellarObject.java   -StarObject.java   -UfoShipObject.java   -MeteoriteObject.java   -MagicPot.java	

Game Specs 	  

Force Field Ratio:   0,015

Elastic Collision:   Left-Right boundaries (Vx = -Vx), paddle (Vy = -Vy)

Plastic Collision:   Top boundary (Vy = -0.6Vy) // to prevent ball’s vertical speed to increase a lot through time 

Game Info	  

Paddle bounce:   +1 scores 

Star Object Event:   +2 scores 

Ufo Ship Object Event:   -1 live  

Meteorite Object Event:   +10% speed 

Magic Pot Object Event:   +1 live & -20% speed 
