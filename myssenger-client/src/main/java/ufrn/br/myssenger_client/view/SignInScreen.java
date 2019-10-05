package ufrn.br.myssenger_client.view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ufrn.br.myssenger_client.controller.ClientController;


public class SignInScreen extends JPanel {
	private static final long serialVersionUID = 601336510664019697L;
	
	private JTextField text1;
	private JPasswordField text2;
	private MainView client; 
	
	public SignInScreen(MainView client, ClientController service) {
		super(new GridBagLayout());
		this.client = client;
		
		Insets insets = new Insets(3, 6, 3, 6);
		
		JLabel label1 = new JLabel("Username");
		GridBagConstraints gbcLabel1 = new GridBagConstraints();
		gbcLabel1.gridx = 0;
		gbcLabel1.gridy = 0;
		gbcLabel1.gridwidth = 1;
		gbcLabel1.gridheight = 1;
		gbcLabel1.insets = insets;
		
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
		
		text2 = new JPasswordField();
		GridBagConstraints gbcText2 = new GridBagConstraints();
		gbcText2.gridx = 0;
		gbcText2.gridy = 3;
		gbcText2.gridwidth = 2;
		gbcText2.gridheight = 1;
		gbcText2.insets = insets;
		gbcText2.fill = GridBagConstraints.HORIZONTAL;
		
		JButton button = new JButton("Sign In");
		button.addActionListener(this.new SignInButtonListener(service));
		GridBagConstraints gbcButton = new GridBagConstraints();
		gbcButton.gridx = 1;
		gbcButton.gridy = 4;
		gbcButton.gridwidth = 1;
		gbcButton.gridheight = 1;
		gbcButton.anchor = GridBagConstraints.EAST;
		gbcButton.insets = insets;
		
		JLabel signUp = new JLabel("Sign Up");
		Font font = signUp.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		signUp.setFont(font.deriveFont(attributes));
		signUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUp.addMouseListener(new SignUpButtonListener());
		GridBagConstraints gbcSignUp = new GridBagConstraints();
		gbcSignUp.gridx = 0;
		gbcSignUp.gridy = 4;
		gbcSignUp.gridwidth = 1;
		gbcSignUp.gridheight = 1;
		gbcSignUp.anchor = GridBagConstraints.SOUTHWEST;
		gbcSignUp.insets = insets;
		
		this.add(label1, gbcLabel1);
		this.add(text1, gbcText1);
		this.add(label2, gbcLabel2);
		this.add(text2, gbcText2);
		this.add(button, gbcButton);
		this.add(signUp, gbcSignUp);
	}
	
	public void resetFields() {
		text1.setText("");
		text2.setText("");
	}
	
	public class SignInButtonListener implements ActionListener {
		private ClientController service;
		
		public SignInButtonListener(ClientController service) {
			this.service = service;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String username = text1.getText().trim();
			String password = new String(text2.getPassword()).trim();
			
			if(!username.isEmpty() && !password.isEmpty())
				service.signIn(username, password);
		}
	}
	
	public class SignUpButtonListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			client.setSignUpScreen();
			resetFields();
		}
	}
}
