package VideoStore;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class storeGUI extends JFrame
{
	private static String userName;
	private static String passWord;
	private static FileManager csv = new FileManager();
	static JLabel lblWelcome;
	
	public storeGUI() {
            
		// Create Form Frame
		super("Video Store");
		setLayout(new FlowLayout());
		setSize(679, 385);
		setLocation(500, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.setToolTipText("close");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	JOptionPane.showMessageDialog(null,
            			"See You!");
            	System.exit(0);
            }
        });
        JMenuItem logout = new JMenuItem("Logout");
        logout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
        		getContentPane().removeAll();
        		JOptionPane.showMessageDialog(null,
            			"logged out!");
        				LoginDialog();
        			
        	}
        });
        file.add(logout);
        file.add(exit);
        menuBar.add(file);
        setJMenuBar(menuBar);
        
		lblWelcome = new JLabel("Message", JLabel.LEFT);
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWelcome.setBounds(0, 0, 336, 25);
		getContentPane().add(lblWelcome);
		
		// When Frame Loaded
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				LoginDialog();
			}
		});
					
	}
	
	public static Boolean getLogin() {
		
		Boolean status = false;
		
		try {
			Person person = new Person();
			person = csv.findPerson(userName, passWord, "src/people.csv");
			if(person.getUsername() != null && person.getPassword() != null) {
				if (person.getRole() == "user") {
					lblWelcome.setText("Welcome : " + person.getUsername());
				}
				status = true;
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Username/Password");
			}
             
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static void LoginDialog() {

		JLabel title = new JLabel("Login Username and Password");
		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();
		final JComponent[] inputs = new JComponent[] {
				title,
				new JLabel("Username"),
				username,
				new JLabel("Password"),
				password
		};
		JOptionPane.showMessageDialog(null, inputs, "Login", JOptionPane.PLAIN_MESSAGE);
		
		userName = username.getText();
		passWord =  new String(password.getPassword()); 
		if(!getLogin())
		{
			LoginDialog();
		}
		
	}
}
