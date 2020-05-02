package Exercise3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI  extends JFrame implements ActionListener{
    JButton enterCodeButton = null;
    JLabel label1 = null;
    JLabel label2 =null;
    JTextField textField1 = null;
    JTextField textField2 = null;


    public GUI() {
        setSize(350,200);
        getContentPane().setBackground(new java.awt.Color(0, 0, 0, 196));
        setTitle("Safe home access");
        setVisible(true);
        getContentPane().setLayout(null);
        Initializer();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        // Create enterCodeButton
    public void Initializer(){

        // Initialize label1

        label1 = new JLabel("Type access code: ");
        label1.setBounds(5,5,120,25);
        label1.setForeground(Color.white);
        label1.setVisible(true);
        add(label1);

        // Initialize text filed for the code

        textField1 = new JTextField();
        textField1.setBounds(125,5,180,25);
        textField1.setVisible(true);
        add(textField1);

        // Initialize Code Button

       enterCodeButton = new JButton("Enter code");
       enterCodeButton.setBounds(5,40,300,30);
       enterCodeButton.setBackground(Color.white);
       enterCodeButton.setVisible(true);
       add(enterCodeButton);

       // Initialize label2

        label2 = new JLabel("Door status");
        label2.setBounds(5,80,100,25);
        label2.setForeground(Color.white);
        label2.setVisible(true);
        add(label2);

        // Initialize text field fot the status

        textField2 = new JTextField();
        textField2.setBounds(125,80,180,25);
        textField2.setVisible(true);
        add(textField2);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
