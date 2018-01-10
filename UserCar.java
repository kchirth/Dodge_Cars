package carRace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class UserCar {
	
	private int x;
	private int y;
	private int height;
	private int width;
	private int stepSize;

	public UserCar() {
		x = Lane.WIDTH + 15;
		y = 400;
		height = 130;
		width = (int) Math.round(height/(1.1));
		stepSize = 20;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void reset() {
		x = Lane.WIDTH + 15;
		y = 400;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(CarRacePanel.userCarImage,x,y,null);
		g2d.setColor(Color.GREEN);
	}
	
	public void move(int direction) {
		switch (direction) {
		case 1:					// 1 represents right
			if(x < JCarRace.WIDTH - (width+stepSize)){
				x = x + stepSize;
			}
			break;
		case 2:					// 2 represents left
			if(x >= stepSize){
				x = x - stepSize;
			}
			else if(x>= 0 && x < stepSize){
				x = 0;
			}
			break;			
		case 3:					// 3 represents up
			if(y >= stepSize){
				y = y - stepSize;
			}
			else if (y >= 0 && y < stepSize){
				y = 0;
			}
			break;				
		case 4:					// 4 represents down
			if(y <= JCarRace.HEIGHT - (height+2*stepSize)){
				y = y + stepSize;
			}
			break;
		
		}
	}
	

}
