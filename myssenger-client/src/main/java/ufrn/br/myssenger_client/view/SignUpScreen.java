package ufrn.br.myssenger_client.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import ufrn.br.myssenger_client.controller.ClientController;

public class SignUpScreen extends JPanel {
	private static final long serialVersionUID = 601336510664019697L;
	
	private JTextField text1;
	private JPasswordField text2;
	private JPasswordField text3;
	private JLabel error;
	private MainView client; 
	private ClientController service;
	
	public SignUpScreen(MainView client, ClientController service) {
		super(new GridBagLayout());
		this.client = client;
		this.service = service;
		
		Insets insets = new Insets(3, 6, 3, 6);
		
		JLabel label1 = new JLabel("Username");
		GridBagConstraints gbcLabel1 = new GridBagConstraints();
		gbcLabel1.gridx = 0;
		gbcLabel1.gridy = 0;
		gbcLabel1.gridwidth = 1;
		gbcLabel1.gridheight = 1;
		gbcLabel1.insets = insets;
		gbcLabel1.anchor = GridBagConstraints.WEST;
		
		text1 = new JTextField();
		GridBagConstraints gbcText1 = new GridBagConstraints();
		gbcText1.gridx = 0;
		gbcText1.gridy = 1;
		gbcText1.gridwidth = 2;
		gbcText1.gridheight = 1;
		gbcText1.insets = insets;
		gbcText1.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel label2 = new JLabel("Password");
		GridBagConstraints gbcLabel2 = new GridBagConstraints();
		gbcLabel2.gridx = 0;
		gbcLabel2.gridy = 2;
		gbcLabel2.gridwidth = 1;
		gbcLabel2.gridheight = 1;
		gbcLabel2.insets = insets;
		gbcLabel2.anchor = GridBagConstraints.WEST;
		
		text2 = new JPasswordField();
		GridBagConstraints gbcText2 = new GridBagConstraints();
		gbcText2.gridx = 0;
		gbcText2.gridy = 3;
		gbcText2.gridwidth = 2;
		gbcText2.gridheight = 1;
		gbcText2.insets = insets;
		gbcText2.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel label3 = new JLabel("Confirm Password");
		GridBagConstraints gbcLabel3 = new GridBagConstraints();
		gbcLabel3.gridx = 0;
		gbcLabel3.gridy = 4;
		gbcLabel3.gridwidth = 1;
		gbcLabel3.gridheight = 1;
		gbcLabel3.insets = insets;
		gbcLabel3.anchor = GridBagConstraints.WEST;
		
		text3 = new JPasswordField();
		GridBagConstraints gbcText3 = new GridBagConstraints();
		gbcText3.gridx = 0;
		gbcText3.gridy = 5;
		gbcText3.gridwidth = 2;
		gbcText3.gridheight = 1;
		gbcText3.insets = insets;
		gbcText3.fill = GridBagConstraints.HORIZONTAL;
		
		JButton signUp = new JButton("Sign Up");
		signUp.addActionListener(this.new SignUpButtonListener());
		GridBagConstraints gbcSignUp = new GridBagConstraints();
		gbcSignUp.gridx = 1;
		gbcSignUp.gridy = 6;
		gbcSignUp.gridwidth = 1;
		gbcSignUp.gridheight = 1;
		gbcSignUp.anchor = GridBagConstraints.EAST;
		gbcSignUp.insets = insets;
		
		JButton back = new JButton("Back");
		back.addActionListener(this.new BackListener());
		GridBagConstraints gbcBack = new GridBagConstraints();
		gbcBack.gridx = 0;
		gbcBack.gridy = 6;
		gbcBack.gridwidth = 1;
		gbcBack.gridheight = 1;
		gbcBack.anchor = GridBagConstraints.WEST;
		gbcBack.insets = insets;
		
		error = new JLabel("A label");
		error.setForeground(Color.red);
		error.setVisible(false);
		GridBagConstraints gbcError = new GridBagConstraints();
		gbcError.gridx = 0;
		gbcError.gridy = 7;
		gbcError.gridwidth = 2;
		gbcError.gridheight = 1;
		gbcError.anchor = GridBagConstraints.EAST;
		gbcError.insets = insets;
		
		this.add(label1, gbcLabel1);
		this.add(text1, gbcText1);
		this.add(label2, gbcLabel2);
		this.add(text2, gbcText2);
		this.add(label3, gbcLabel3);
		this.add(text3, gbcText3);
		this.add(signUp, gbcSignUp);
		this.add(back, gbcBack);
		this.add(error, gbcError);
	}
	
	public void resetFields() {
		text1.setText("");
		text2.setText("");
		text3.setText("");
		error.setVisible(false);
	}
	
	public class SignUpButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = text1.getText().trim();
			String password = new String(text2.getPassword()).trim();
			String confirm = new String(text3.getPassword()).trim();
			
			if(username.isEmpty()) {
				error.setText("Username field is empty!");
				error.setVisible(true);
				return;
			}
			if(password.isEmpty()) {
				error.setText("Password field is empty!");
				error.setVisible(true);
				return;
			}
			if(confirm.isEmpty()) {
				error.setText("Confirm Password field is empty!");
				error.setVisible(true);
				return;
			}
			
			if(confirm.equals(password)) {
				client.setLoadingScreen();
				new Worker(username, password).execute();
			}
			else {
				error.setText("Confirmation doesn't match password!");
			}
		}
	}
	
	public class Worker extends SwingWorker<Void, Void> {
		private String username;
		private String password;
		
		public Worker(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}

		@Override
		protected Void doInBackground() throws Exception {
			service.signUp(username, password);
			client.setSignInScreen();
			JOptionPane.showMessageDialog(client.getWindow(),
					"User successfully signed up!",
				    "Success",
				    JOptionPane.PLAIN_MESSAGE);
			return null;
		}
	}
	
	public class BackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			client.setSignInScreen();
			resetFields();
		}
	}
}