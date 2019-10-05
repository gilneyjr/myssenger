package ufrn.br.myssenger_client.view;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ufrn.br.myssenger_client.controller.ClientController;

public class ChatScreen extends JPanel {
	private static final long serialVersionUID = 9213155293155301226L;
	private Box messages;
	
	public void addMessage(String content, boolean itsMe) {
		messages.add(Box.createVerticalStrut(5));
		messages.add(new MessageComponent(content, itsMe));
	}
	
	public ChatScreen(ClientController service) {
		super(new BorderLayout());
		
		// Instantiating components
		JScrollPane scroll = new JScrollPane();
		Box bottom = Box.createVerticalBox();
		messages = Box.createVerticalBox();
		JTextArea textField = new JTextArea();
		JButton send = new JButton("Send");
		
		// ScrollBar settings
		scroll.setViewportView(messages);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Text input settings
		textField.setLineWrap(true);
		textField.setWrapStyleWord(true);
		
		// ScrollBar settings
		scroll.setViewportView(messages);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		// Text input settings
		textField.setLineWrap(true);
		textField.setWrapStyleWord(true);
		
		// Bottom panel settings
		Box aux = Box.createHorizontalBox();
		aux.add(Box.createHorizontalStrut(5));
		aux.add(textField);
		aux.add(Box.createHorizontalGlue());
		aux.add(Box.createHorizontalStrut(5));
		aux.add(send);
		aux.add(Box.createHorizontalStrut(5));
		bottom.add(Box.createVerticalStrut(5));
		bottom.add(aux);
		bottom.add(Box.createVerticalStrut(5));
		
		// Send action settings
		send.addActionListener(evt -> {
			String msg = textField.getText().trim(); 
			textField.setText("");
			if(!msg.isEmpty()) {
				addMessage(msg, true);
				service.send(msg);
				scroll.updateUI();
			}
		});
		
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(e.isShiftDown())
						textField.append("\n");
					else {
						String msg = textField.getText().trim(); 
						textField.setText("");
						if(!msg.isEmpty()) {
							addMessage(msg, true);
							service.send(msg);
							scroll.updateUI();
						}
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		this.add(scroll, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
	}
}
