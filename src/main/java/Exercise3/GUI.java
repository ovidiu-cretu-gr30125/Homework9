package Exercise3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI  extends JFrame implements ActionListener{
    JButton enterCodeButton = null;
    JLabel typeAccessCodeLabel = null;
    JLabel doorStatusLabel = null;
    JLabel wrongPinLabel = null;
    JLabel masterKeyLabel = null;
    JButton tryAgainButton = null;
    JTextField pinTextField = null;
    JTextField doorStatusTextField = null;
    JDialog wrongPinDialog = null;
    private int  numberOfWrongPins=0;
    Door door = new Door(DoorStatus.CLOSE);
    DoorLockController dr1 = new DoorLockController(door);
    ControllerInterface ctrl1=dr1;

    /**
     * the constructor of the graphical user interface
     * initialize the frame
     * @throws Exception
     */
    public GUI() throws Exception {
        setSize(350,200);
        getContentPane().setBackground(new java.awt.Color(0, 0, 0, 196));
        setTitle("Safe home access");
        setVisible(true);
        getContentPane().setLayout(null);
        ctrl1.addTenant("1234","User1");
        Initializer();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Initializer() {

        // Initialize type access code label

        this.typeAccessCodeLabel = new JLabel("Type access code: ");
        this.typeAccessCodeLabel.setBounds(5, 5, 120, 25);
        this.typeAccessCodeLabel.setForeground(Color.white);
        this.typeAccessCodeLabel.setVisible(true);
        add(typeAccessCodeLabel);

        // Initialize text filed for the code

        this.pinTextField = new JTextField();
        this.pinTextField.setBounds(125, 5, 180, 25);
        this.pinTextField.setVisible(true);
        add(pinTextField);

        // Initialize enter Code Button

        this.enterCodeButton = new JButton("Enter code");
        this.enterCodeButton.setBounds(5, 40, 300, 30);
        this.enterCodeButton.setBackground(Color.white);
        this.enterCodeButton.setVisible(true);
        this.enterCodeButton.addActionListener(this);
        add(enterCodeButton);

        // Initialize door status label

        this.doorStatusLabel = new JLabel("Door status");
        this.doorStatusLabel.setBounds(5, 80, 100, 25);
        this.doorStatusLabel.setForeground(Color.white);
        this.doorStatusLabel.setVisible(true);
        add(doorStatusLabel);

        // Initialize door status text field fot the status

        this.doorStatusTextField = new JTextField();
        this.doorStatusTextField.setBounds(125, 80, 180, 25);
        this.doorStatusTextField.setVisible(true);
        this.doorStatusTextField.setText(door.getStatus().toString());
        add(doorStatusTextField);


    }
    public void InitializeDialogForWrongPin(){

        //Initialize wrong pin dialog box

        this.wrongPinDialog = new JDialog();
        this.wrongPinDialog.setSize(200,150);
        this.wrongPinDialog.getContentPane().setLayout(null);
        this.wrongPinDialog.getContentPane().setBackground(new java.awt.Color(0, 0, 0, 196));
        this.wrongPinDialog.setLocationRelativeTo(null);
        this.wrongPinDialog.setVisible(true);

        //Initialize wrong pin label for dialog box

        this.wrongPinLabel = new JLabel("Wrong pin!");
        this.wrongPinLabel.setBounds(55,5,100,25);
        this.wrongPinLabel.setForeground(Color.white);
        this.wrongPinLabel.setVisible(true);
        wrongPinDialog.add(wrongPinLabel);

        //Initialize try again button

        this.tryAgainButton = new JButton("Try again");
        this.tryAgainButton.setBounds(40,35,100,25);
        this.tryAgainButton.setVisible(true);
        this.wrongPinDialog.add(tryAgainButton);
        tryAgainButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                wrongPinDialog.setVisible(false);
                    pinTextField.setText("");
            }
        });
    }
    public void InitializeDialogForMasterKey() {

        //Initialize wrong pin dialog box

        this.wrongPinDialog = new JDialog();
        this.wrongPinDialog.setSize(200,150);
        this.wrongPinDialog.getContentPane().setLayout(null);
        this.wrongPinDialog.getContentPane().setBackground(new java.awt.Color(0, 0, 0, 196));
        this.wrongPinDialog.setLocationRelativeTo(null);
        this.wrongPinDialog.setVisible(true);

        //Initialize master key label for dialog box

        this.masterKeyLabel = new JLabel("Enter the master key!");
        this.masterKeyLabel.setForeground(Color.white);
        this.masterKeyLabel.setBounds(30,5,150,35);
        this.masterKeyLabel.setVisible(true);
        this.wrongPinDialog.add(masterKeyLabel);
        this.wrongPinDialog.add(tryAgainButton);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

       if(actionEvent.getSource()==enterCodeButton){
           try {
               ctrl1.enterPin(pinTextField.getText());
               numberOfWrongPins=0;
           } catch (Exception e) {
               numberOfWrongPins++;
               if(numberOfWrongPins<3) {
                   InitializeDialogForWrongPin();
               }
               else {
                    InitializeDialogForMasterKey();
               }
               e.printStackTrace();
           }
           doorStatusTextField.setText(door.getStatus().toString());
       }
    }
}
