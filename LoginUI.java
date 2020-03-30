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
		int count = 0;
		private static JLabel userlabel;
		private static JLabel passlabel;
		private static JFrame userframe;
		private static JButton button;
		private static JPanel userpanel;
		
		public static void main(String[] args)
		{
			userframe = new JFrame();
			userpanel = new JPanel();
			userframe.setSize(250, 250);
			userframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			userframe.add(userpanel);
			userpanel.setLayout(null);
			userlabel = new JLabel("User: ");
			userlabel.setBounds(10, 20, 85, 25);
			userpanel.add(userlabel);
			JTextField userText = new JTextField();
			userText.setBounds(100, 20, 165, 25);
			userpanel.add(userText);
			passlabel = new JLabel("Password: ");
			passlabel.setBounds(10, 50, 85, 25);
			userpanel.add(passlabel);
			JPasswordField passwordText = new JPasswordField();
			passwordText.setBounds(100, 50, 165, 25);
			userpanel.add(passwordText);
			JButton button = new JButton("Login: ");
			button.setBounds(10, 80, 80, 25);
			userpanel.add(button);
			JLabel success = new JLabel(" ");
			success.setBounds(10, 110, 300, 25);
			userpanel.add(success);
			button.addActionListener(new LoginUI());
			userframe.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}