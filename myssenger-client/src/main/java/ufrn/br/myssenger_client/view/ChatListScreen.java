package ufrn.br.myssenger_client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class ChatListScreen extends JPanel {
	private static final long serialVersionUID = 8203866994494764953L;
	private Box chats;
	
	public ChatListScreen() {
		super(new BorderLayout());
		JScrollPane scroll = new JScrollPane();
		JPanel head = new JPanel();
		chats = Box.createVerticalBox();
		
		// Scroll settings
		scroll.setViewportView(chats);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		for(int i = 1; i <= 10; i++) {
			chats.add(new ChatComponent("Friend "+ i));
			chats.add(new Sep());
		}
		
		// Main settings
		this.add(scroll, BorderLayout.CENTER);
		this.add(head, BorderLayout.NORTH);
	}
	
	public static class Sep extends JSeparator {
		private static final long serialVersionUID = 1675491389505391770L;
		
		@Override
		public Dimension getMaximumSize() {
			return new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height);
		}
	}
	
	public static class ChatComponent extends JPanel {
		private static final long serialVersionUID = -9115430775308099621L;
		private static Color DEFAULT_COLOR = new Color(240,240,240);
		
		public ChatComponent(String friendname) {
			super(new FlowLayout(FlowLayout.LEFT));
			JLabel label = new JLabel(friendname);
		
			this.add(label);
			this.setBackground(DEFAULT_COLOR);
			//this.addMouseListener(l);
		}
	}
}
