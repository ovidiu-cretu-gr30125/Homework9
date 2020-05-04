package Exercise4;

import javax.swing.*;
import java.awt.*;

public class View {

    private JFrame frame;
    private JButton temperatureSensorButton;
    private JButton humiditySensorButton;
    private JButton pressureSensorButton;
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel pressureLabel;
    private JTextField temperatureSensorTextField;
    private JTextField humiditySensorTextField;
    private JTextField pressureSensorTextFiled;

    public View(String title){
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        temperatureLabel = new JLabel("Temperature");
        temperatureLabel.setBounds(5,80,50,25);
        temperatureLabel.setVisible(true);
    }

}
