/**
 * The following Java code implements java swing and makes a login GUI 
 * which takes in the user name and password of "admin" and tells the user whether 
 * or not the login was successful
 **/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUI implements ActionListener
	{
	
	// declaring all the labels and frames here so that they can be used globally
	
		int count = 0;
		private static JLabel userlabel;
		private static JLabel passlabel;
		private static JFrame userframe;
		
		@SuppressWarnings("unused") // because seeing it is reeeally annoying
		
		private static JButton button;
		private static JPanel userpanel;
		private static JPasswordField passwordText;
		private static JTextField userText;	
		private static JLabel loginlabel;
		
		public static void main(String[] args)
		{
			
			/** 
			 * the main class is used here to create objects, set their names,
			 * their size, and make sure that they are visible to the user
			 **/
			
			userframe = new JFrame();
			userpanel = new JPanel();
			userframe.setSize(300, 200);
			userframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			userframe.add(userpanel);
			userpanel.setLayout(null);
			userlabel = new JLabel("User: ");
			userlabel.setBounds(10, 20, 85, 25);
			userpanel.add(userlabel);
			userText = new JTextField();
			userText.setBounds(100, 20, 165, 25);
			userpanel.add(userText);
			passlabel = new JLabel("Password: ");
			passlabel.setBounds(10, 50, 85, 25);
			userpanel.add(passlabel);
			passwordText = new JPasswordField();
			passwordText.setBounds(100, 50, 165, 25);
			userpanel.add(passwordText);
			JButton button = new JButton("Login");
			button.setBounds(10, 80, 80, 25);
			userpanel.add(button);
			button.addActionListener(new LoginUI());
			userframe.setVisible(true);	
		}
		
		@SuppressWarnings("deprecation") // because it was annoying to see
		@Override
		
		public void actionPerformed(ActionEvent e) {
			String user = userText.getText();
			String password = passwordText.getText();
			
			/* 
			 * The following line is commented out because it could be called out
			 * as a possible security issue 
			*/
			//System.out.println("(" + user + " , " + password + ")");
			
			if(user.equals("admin") && (password.equals("admin")))
			{
				System.out.println("Login verified");
				userframe.dispose();
			}
			
			// pop an error rmessage in case you don't get the user and password right
			
			else
			{	
				loginlabel = new JLabel("Something's not right here...");
				loginlabel.setBounds(50, 70, 185, 105);
				userpanel.add(loginlabel);
				loginlabel.setVisible(true);
				System.out.println("Login not verified");
			}
		}
	}