package carRace;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import carRace.CarRacePanel;

public class JCarRace extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static CarRacePanel animationPanel;
	public static final int WIDTH = 450;
	public static final int HEIGHT = 600;
	 
	public JCarRace(String title) {
		super(title);
		setSize(WIDTH,HEIGHT);
		this.setResizable(false);
                
		animationPanel=new CarRacePanel();
		getContentPane().add(animationPanel,BorderLayout.CENTER);
	}


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JCarRace jCarRace = new JCarRace("Racing Game");
		jCarRace.setVisible(true);   
	}

}
