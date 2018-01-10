package carRace;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarRacePanel extends JPanel implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1L;
	private Timer updateTimer;
	private Graphics2D g2d;
	protected static Lane[] lanes;
	protected static UserCar userCar;
	private int millisecond;
	private int random;
	private static Menu menu;
	private static Gameover gameover;
        protected static HallOfFame hallOfFame;
	private JTextField textField;
	protected static int change;
	protected static String userName;
	protected static Image backgroundImage;
	protected static Image userCarImage;
	protected static Image carImage;
	private static long tStart;
	private long tStop;
	private long tDelta;
	protected static double elapsedSeconds;
	private static final int TIMERSPEED=30;
	
	public static enum STATE {
		MENU, 
		GAME,
		GAMEOVER,
                HallOfFame
	};
	
	public static STATE State = STATE.MENU;
	
	public CarRacePanel() {
		updateTimer = new Timer(TIMERSPEED,this);
		updateTimer.start();
		tStart = System.currentTimeMillis();
		
		// load background image
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("3lane.PNG");
		try {
			backgroundImage = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// load car image
		input = classLoader.getResourceAsStream("cars_new.png");
		try {
			carImage = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// load user car image
		input = classLoader.getResourceAsStream("usercar_new.png");
		try {
			userCarImage = ImageIO.read(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		menu = new Menu();
		gameover = new Gameover();
                hallOfFame = new HallOfFame();
		// add key listener and mouselistener
		this.addKeyListener(this);
		this.setFocusable(true);
		this.addMouseListener(new MouseInput());
		
		//create text field for player to put username
		textField = new JTextField(10);
		textField.setText("Default");
		this.add(textField, BorderLayout.NORTH);
		change = 0;
		
		userCar = new UserCar();
		
                // initialize lanes
		lanes = new Lane[3];
		lanes[0] = new Lane(0,2);
		lanes[1] = new Lane(Lane.WIDTH,3);
		lanes[2] = new Lane(2*(Lane.WIDTH),4);
		
		// set a random lane to start with a car
		random = (int)(Math.random()*3);
		lanes[random].generateCar = true;
                        
	}
	
	public static void reset(){
            
                // grab start time
		tStart = System.currentTimeMillis();
		
                // reset each lane when starting new game
		lanes[0].reset();
		lanes[1].reset();
		lanes[2].reset();
		
		userCar.reset();
	}
	
	public void regenerateCars(){
		tStop = System.currentTimeMillis();
                
                // makes game harder as time goes on
		if(millisecond > 4000 && tStop-tStart < 30000){
			millisecond = 0;
			
			random = (int)(Math.random()*3);
			lanes[random].generateCar = true;
		}
		else if(millisecond > 3000 && tStop-tStart < 60000){
			millisecond = 0;
			
			random = (int)(Math.random()*3);
			lanes[random].generateCar = true;
		}
		else if(millisecond > 2000 && tStop-tStart >= 60000){
			millisecond = 0;
			
			random = (int)(Math.random()*3);
			lanes[random].generateCar = true;
		}
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// removes TextField when coming from the menu
		if(change == 1 && State == STATE.GAME){
			userName = textField.getText();
			this.remove(textField);
			change = 0;
		}
		else if(State == STATE.GAME){
            
                        // draw background
			g.drawImage(backgroundImage,0,0,null);
			
			userCar.paintComponent(g);
			
                        // add new cars
			regenerateCars();

			// draw and move cars in the lanes
			for(int i = 0; i<3; i++){
				lanes[i].step();
				lanes[i].paintComponent(g);
			}
			
			// check for impacts
			for(int i = 0; i < 3; i++) {
				if(lanes[i].detectImpact(userCar)) {
					// calculate time elapsed
					tStop = System.currentTimeMillis();
					tDelta = tStop - tStart;
					elapsedSeconds = tDelta/1000.00;
					
                                        // set to gameover screen
					g2d.clearRect(0, 0, this.getSize().width, this.getSize().height);
					State = STATE.GAMEOVER;
                                        
                                        // put user score in database
                                        try {
                                            hallOfFame.updateDataBase();
                                        } catch (Exception ex) {
                                            Logger.getLogger(MouseInput.class.getName()).log(Level.SEVERE, null, ex);
                                        }
				}
			}
		}
		else if(State == STATE.MENU){
			menu.paintComponent(g);
		}
		else if(State == STATE.GAMEOVER){
			gameover.paintComponent(g);
		}
                else if(State == STATE.HallOfFame){
                    try {
                        hallOfFame.paintComponent(g);
                    } catch (SQLException ex) {
                        Logger.getLogger(CarRacePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                }
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		millisecond += TIMERSPEED;          // millisecond is used to determine when to spawn a new car
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// empty
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// user input
		if(State == STATE.GAME){
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				userCar.move(1);
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT){
				userCar.move(2);
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP){
				userCar.move(3);
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN){
				userCar.move(4);
			}	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// empty
	}

}
