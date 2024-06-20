import javax.swing.*;
import java.awt.*;

public class CategoryGUI extends JFrame {
	public CategoryGUI(String categoryName) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Traffic Information - " + categoryName);
		setSize(400, 600);
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.insets = new Insets(5, 0, 5, 0);
		String[] information = GetInformation.getInformationByCategoryName(categoryName);
		for (int i = 0; i < information.length; i++) {
			JTextArea t = new JTextArea(information[i]);
			t.setEditable(false);
			t.setLineWrap(true);
			t.setWrapStyleWord(true);
			t.setColumns(30);
			JScrollPane textScrollPane = new JScrollPane(t);
			textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			panel.add(textScrollPane, gbc);
			JSeparator s = new JSeparator();
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panel.add(s, gbc);
		}
		panel.setPreferredSize(new Dimension(400, information.length * 100));
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		setVisible(true);
	}
}
