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
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        
        // Create buttons
        JButton button1 = new JButton("Registrar cliente");
        JButton button2 = new JButton("Registrar Servicos");
        JButton button3 = new JButton("Registrar Funcionarios");
        JButton button4 = new JButton("Carros");
        JButton button5 = new JButton("Servicos");
        JButton button6 = new JButton("Funcionarios");
        
        // Set button sizes
        button1.setPreferredSize(new Dimension(180, 30));
        button2.setPreferredSize(new Dimension(180, 30));
        button3.setPreferredSize(new Dimension(180, 30));
        button4.setPreferredSize(new Dimension(180, 30));
        button5.setPreferredSize(new Dimension(180, 30));
        button6.setPreferredSize(new Dimension(180, 30));
        
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
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        
        // Add the panel to the frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        
        frame.setVisible(true);
    }
}