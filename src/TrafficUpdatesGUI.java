import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficUpdatesGUI extends JFrame {
    private JButton[] buttons = null;

    public static void main(String[] args) {
        TrafficUpdatesGUI t = new TrafficUpdatesGUI();
        t.setVisible(true);
    }

    public TrafficUpdatesGUI() {
        setTitle("SuedTirol Traffic Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        String[] categories = GetInformation.getCategories();
        buttons = new JButton[categories.length + 1];
        setSize(600, (1 + categories.length) * 100);
        for (int i = 0; i < buttons.length; i++) {
            JButton button;
            if (i < categories.length) {
                button = new JButton(categories[i]);
            } else {
                button = new JButton("Alle Informationen");
            }
            button.setBounds(350, i * 100, 200, 50);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new CategoryGUI(button.getText()).setVisible(true);;
                }
            });
            JSeparator s = new JSeparator();
            s.setBounds(350, i * 100 + 75, 200, 2);
            add(s);
            add(button);
        }
    }
}

