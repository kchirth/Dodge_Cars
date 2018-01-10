package carRace;
import java.util.ArrayList;
import java.awt.Graphics;

public class Lane {

	protected ArrayList<Car> cars = new ArrayList<Car>();
	protected int xPosition;
	protected final static int WIDTH = 150;
	protected int speed;
	protected boolean generateCar;
	
	
	public Lane(int position, int inputSpeed) {
		this.xPosition = position;
			
		speed = inputSpeed;
		generateCar = false;
	}
	
	public void paintComponent(Graphics g) {
		
                // paint each car in the lane
		for(int i=0;i<cars.size();i++){
			Car c = cars.get(i);
			c.paintComponent(g);
		}
	}
	
	public void reset(){
		for(int i=0;i < cars.size();i++){
			cars.remove(i);
			i--;
		}
	}
	
	public void step() {
		if(generateCar == true){
			Car c = new Car(xPosition+15,-130,130,speed);
			cars.add(c);
		}
		
		// move cars
		for(int i = 0; i < cars.size(); i++){
			Car c = cars.get(i);
			c.move();
                        
                        // remove cars if they are off the screen.=
                        if(c.getY() > 600) {
                            cars.remove(i);
                            i--;
                        }
		}
		
		generateCar = false;
	}
	
	public boolean detectImpact(UserCar u) {
                // check impact for each car
                for (Car c : cars) {
                    if(c.detectImpact(u)){
                        return true;
                    }
                }
		return false;
	}

}
