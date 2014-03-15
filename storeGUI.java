package VideoStore;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class storeGUI extends JFrame
{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private JLabel name, amout, rent;
	private JTextField nameTF, widthTF, areaTF;
	private JButton find, exitB;
	
	//Button handlers:
//	private CalculateButtonHandler cbHandler;
	private ExitButtonHandler ebHandler;
	
	public storeGUI() {
		//Instantiate the labels:
		name = new JLabel("Enter the movie name: ", SwingConstants.LEFT);
			
			//Text fields next:
		nameTF = new JTextField();
		
		find = new JButton("Find");
		exitB = new JButton("Exit");
			
		setTitle("Video Store");
		Container pane = getContentPane();		
		pane.setLayout(new GridLayout(5, 2));
			
		pane.add(name);
		pane.add(nameTF);
			
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public class ExitButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
//	public static void main(String[] args)
//	{
//		storeGUI rectObj = new storeGUI();
//	}
//	
}
