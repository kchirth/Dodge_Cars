package carRace;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import carRace.CarRacePanel.STATE;

public class MouseInput implements MouseListener {

	public MouseInput() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// public Rectangle playButton = new Rectangle(JCarRace.WIDTH/2 - 60,150,100,50);
		int mx = e.getX();
		int my = e.getY();
		
		// Play Button
		if(CarRacePanel.State == STATE.MENU){
			if(mx >= JCarRace.WIDTH/2 - 60 && mx <= JCarRace.WIDTH/2 + 40){
				if(my >= 150 && my <= 200){
					// pressed play button
					CarRacePanel.State  = CarRacePanel.State.GAME;
					CarRacePanel.change = 1;
				}
			}
		}
		else if(CarRacePanel.State == STATE.GAMEOVER){
			// replay button pressed
			if(mx >= JCarRace.WIDTH/2 - 60 && mx <= JCarRace.WIDTH/2 + 90){
				if(my >= 150 && my <= 200){
					
					CarRacePanel.reset();
					
					CarRacePanel.State  = CarRacePanel.State.GAME;
					CarRacePanel.change = 1;
					
				}
			}
			// quit button pressed
			if(mx >= JCarRace.WIDTH/2 - 60 && mx <= JCarRace.WIDTH/2 + 90){
				if(my >= 250 && my <= 300){
					System.exit(0);
				}
			}
                        
                        // hall of fame button pressed
                        if(mx >= JCarRace.WIDTH/2 - 60 && mx <= JCarRace.WIDTH/2 + 90){
                                if(my >= 330 && my <= 380){
                                    CarRacePanel.State = STATE.HallOfFame;
                                }
                        }
			
		}
                else if(CarRacePanel.State == STATE.HallOfFame){
                    // back button pressed
                    if(mx >= 5 && mx <= 55){
                        if(my >= 5 && my <= 55){
                            CarRacePanel.State = STATE.GAMEOVER;
                        }
                    }
                }
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
