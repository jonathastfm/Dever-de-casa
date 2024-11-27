package ProjetoMecanico;

import javax.swing.*;
import java.awt.*;

public class CarrosCrud {
    public CarrosCrud(JFrame parent) {
        JFrame frame = new JFrame("Carros CRUD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("ID");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel nameLabel = new JLabel("Nome");
        nameLabel.setBounds(10, 50, 80, 25);
        panel.add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 50, 165, 25);
        panel.add(nameText);
    }
}



