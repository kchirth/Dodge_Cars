package carRace;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Gameover {

	protected Rectangle replayButton = new Rectangle(JCarRace.WIDTH/2 - 60,150,150,50);
	protected Rectangle quitButton = new Rectangle(JCarRace.WIDTH/2 - 60,250,150,50);
        protected Rectangle hallButton = new Rectangle(JCarRace.WIDTH/2 - 60,330,150,50);
	private static Image gameoverImage;
	
	Gameover() {
		
		// load background image
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("gameover_background.png");
		try {
			gameoverImage = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paintComponent(Graphics g){
            
		Graphics2D g2d = (Graphics2D) g;
		String endTime = CarRacePanel.userName + ": " + Double.toString(CarRacePanel.elapsedSeconds) + "s";
		
		g.drawImage(gameoverImage,0,0,null);
		
		 // print name and score at the top
		Font fnt0 = new Font("arial", Font.BOLD,40);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString(endTime, 0, 100);
		
		// draw re-play button
		Font fnt1 = new Font("arial", Font.BOLD, 25);
		g.setFont(fnt1);
		g.drawString("Replay?", replayButton.x + 20, replayButton.y + 30);
		g2d.draw(replayButton);
		
		// draw quit button
		g.drawString("Quit", quitButton.x + 35, quitButton.y + 30);
		g2d.draw(quitButton);
                
                // draw hall of fame button
                g.drawString("Hall of Fame", hallButton.x + 2, hallButton.y + 30);
		g2d.draw(hallButton);
	}

}
