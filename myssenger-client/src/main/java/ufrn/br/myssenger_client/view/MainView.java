package ufrn.br.myssenger_client.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ufrn.br.myssenger_client.controller.ClientController;

public class MainView {
	private JFrame window; 
	private JPanel signInScreen;
	private JPanel signUpScreen;
	private JPanel chatListScreen;
	private JPanel chatScreen;
	private JPanel loadingScreen;
	private ClientController controller;
	
	public void setSignInScreen() {
		window.setContentPane(signInScreen);
		window.revalidate();
	}
	public void setSignUpScreen() {
		window.setContentPane(signUpScreen);
		window.revalidate();
	}
	public void setchatListScreen() {
		window.setContentPane(chatListScreen);
		window.revalidate();
	}
	public void setchatScreen() {
		window.setContentPane(chatScreen);
		window.revalidate();
	}
	public void setLoadingScreen() {
		window.setContentPane(loadingScreen);
		window.revalidate();
	}
	public JFrame getWindow() {
		return this.window;
	}
	
	public MainView(String title) {
		controller = new ClientController();
		window = new JFrame();
		signInScreen = new SignInScreen(this, controller);
		signUpScreen = new SignUpScreen(this, controller);
		chatListScreen = new ChatListScreen();
		chatScreen = new ChatScreen(controller);
		loadingScreen = new LoadingScreen();
		
		// Window settings
		window.setContentPane(signInScreen);
		window.setTitle(title);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setSize(360, 540);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}