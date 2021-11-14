# Space-Pong
Java’s Swing API is used (which provides a graphical user interface for java programs) to implement a Space Pong game for Comp132 (Spring21) Term Project. 

Copyright, all rights reserved. 

Creator of the game is Alp Kural.

Java Files of the Project	is listed as :

SpacePongMain.java        Main class of the game (No command line argument)

SpacePongFrame.java	

TopPanel.java	

MiddlePanel.java	

BottomPanel.java	

StellarObject.java	

StarObject.java	

UfoShipObject.java	

MeteoriteObject.java	

MagicPot.java	

Feature	                             Given in Project Manual	         New Implementation

Ball initial horizontal velocity	    4	                               16

Paddle color	                        Black	                           Yellow

Ball color	                          Red	                             White

Game area color                     	White	                           Black

Speed increase per level	            +50%	                           +20%

Play/Pause button	                   JButton	                         JToggleButton

Pressing the Play/Pause button	      Click the button	               Click the button or press space

Stellar object positions	            Fixed	                           Every time when ball collides with a stellar object, stellar object disappears

                                                                       and respawns in a random position
                                                                      
Pause mode	                          The ball reset to it's initial   The game freezes as it is until it is play mode again

                                      position and velocity	              
Extras	

Game icon	               Application icon for the JFrame

Game logo	               Game logo pops out when the game is opened or restarted

Paused logo	             Paused logo pops out when the game is paused

Game over logo	          Game over logo pops out when the game is over 

Game area boundary  	    Game area boundary is presented with yellow rectangle with thickness of 1 pixel 

Magic pot object 	       Magic pot object is implemented for user to survive longer 

Sound effects 	          Exclusive sound effects are implemented for each object event and for the end of the game 

Main Theme Song  	       Main theme song of the game is implemented 

Restart	                 When the game is over, automatically new game restarts after 2.5 seconds (through this time button becomes passive) and 

                         then, waits in the pause mode
Game Specs 	  
Force Field Ratio 	      0,015

Elastic Collision 	      Left-Right boundaries (Vx = -Vx), paddle (Vy = -Vy)

Plastic Collision 	      Top boundary (Vy = -0.6Vy) // to prevent ball’s vertical speed to increase a lot through time 

Game Info	  

Paddle bounce 	          +1 scores 

Star Object Event 	      +2 scores 

Ufo Ship Object Event    -1 live  

Meteorite Object Event   +10% speed 

Magic Pot Object Event   +1 live & -20% speed 
