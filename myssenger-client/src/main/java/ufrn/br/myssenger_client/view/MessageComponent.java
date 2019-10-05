package ufrn.br.myssenger_client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;

public class MessageComponent extends Box {
	private static final long serialVersionUID = 2063337595251844950L;
	private JTextArea msg;
	
	public MessageComponent(String content, boolean itsMe) {
		super(BoxLayout.X_AXIS);
		Color color;
		int beg, end;
		if(itsMe) {
			color = Color.CYAN;
			beg = 35;
			end = 10;
		} else {
			color = Color.LIGHT_GRAY;
			beg = 10;
			end = 35; 
		}
		
		msg = new JTextArea();
		msg.setEditable(false);
		msg.setBackground(color);
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		msg.setText(content);
		msg.setMargin(new Insets(5, 5, 10, 10));
		
		this.add(Box.createHorizontalStrut(beg));
		this.add(msg);
		this.add(Box.createHorizontalStrut(end));
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(Integer.MAX_VALUE, msg.getPreferredSize().height);
	}
}
