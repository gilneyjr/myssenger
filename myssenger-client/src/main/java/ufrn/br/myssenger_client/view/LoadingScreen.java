package ufrn.br.myssenger_client.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingScreen extends JPanel {
	private static final long serialVersionUID = -8336473888760844311L;
	public LoadingScreen() {
		super(new GridBagLayout());
		JLabel label = new JLabel("Loading...");
		Font font = label.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.SIZE, Integer.valueOf(18));
		label.setFont(font.deriveFont(attributes));
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = gbc.weighty = 1;
		gbc.gridx = gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		
		this.add(label);
	}
}
