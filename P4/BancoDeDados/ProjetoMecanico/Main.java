package ProjetoMecanico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> mainScreen());
    }

    private static void mainScreen() {
        JFrame frame = new JFrame("Exemplo de JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 600);
        
        // Create a panel 
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50)); 

        // Create title label
        JLabel titleLabel = new JLabel("Mecanica do dimas");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Create buttons
        JButton button1 = new JButton("Carros");
        JButton button2 = new JButton("Servicos");
        JButton button3 = new JButton("Funcionarios");
        
        // Set button sizes
        button1.setPreferredSize(new Dimension(200, 50));
        button2.setPreferredSize(new Dimension(200, 50));
        button3.setPreferredSize(new Dimension(200, 50));
        
        // Add ActionListener to button1
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CarrosCrud(frame);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ServicosCrud(frame);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionariosCrud(frame);
            }
        });


        panel.add(titleLabel);
        // Add buttons to the panel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        
        // Add the panel to the frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
}