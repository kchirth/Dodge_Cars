package carRace;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	protected Rectangle playButton = new Rectangle(JCarRace.WIDTH/2 - 60,150,100,50);
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(CarRacePanel.backgroundImage,0,0,null);
                
                // draw title 
		Font fnt0 = new Font("arial", Font.BOLD,50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("CAR RACE GAME", 10, 100);
		
                // draw play button
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 19, playButton.y + 30);
		g2d.draw(playButton);
	}

}
