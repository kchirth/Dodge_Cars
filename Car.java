package carRace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Car {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int dx;		// speed of the car
	protected Color color;

	public Car(int inputX, int inputY, int inputWidth, int inputDX) {
		x = inputX;
		y = inputY;
		width = inputWidth;
		height = (int)Math.round(width/(1.1));
		dx = inputDX;                         
		color = Color.GREEN;
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(CarRacePanel.carImage,x,y,null); 
	}
	
	public boolean detectImpact(UserCar u) {
		int ux = u.getX();
		int uy = u.getY();
		
		int maxX = x + width;
		int maxY = y + height;
		
                // checks for impact
		return((ux >= x && ux <= maxX && uy >= y && uy <= maxY) || (ux + width  >= x && ux + width <= maxX && uy >= y && uy <= maxY) 
				|| (ux >= x && ux <= maxX && uy + height >= y && uy+ height <= maxY) || (ux + width >= x && ux + width <= maxX && uy + height >= y && uy + height <= maxY) );
		
	}
	
	public void move() {
		y = y + dx;
	}
	
	public int getY(){
		return y;
	}

}
