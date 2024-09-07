package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static bank.management.system.Component_creation_functions.*;


public class PinChange extends JFrame implements ActionListener {
    JButton changeButton, backButton;
    JPasswordField newPinField, confirmPinField;
    String currentPin;
    Color backgroundColor = new Color(65, 125, 128);
    Font passwordFont = new Font("Raleway", Font.BOLD, 22);
    PinChange(String pin){

        currentPin = pin;
        Color buttonColor = new Color(65, 125, 128);
        Font labelFont = new Font("System", Font.BOLD, 16);


        JLabel backgroundLabel = createImageLabel("icon/atm_machine_frame.png", 0, 0, 1550, 830);
        add(backgroundLabel);


        JLabel headerLabel = createLabel("CHANGE YOUR PIN", labelFont, 430, 180, 400, 35, Color.WHITE);
        backgroundLabel.add(headerLabel);

        JLabel newPinLabel = createLabel("New PIN:", labelFont, 430, 220, 150, 35, Color.WHITE);
        backgroundLabel.add(newPinLabel);

        JLabel confirmPinLabel = createLabel("Re-Enter New PIN:", labelFont, 430, 250, 400, 35, Color.WHITE);
        backgroundLabel.add(confirmPinLabel);


        newPinField = createPasswordField(600, 220, 180, 25, passwordFont, backgroundColor, Color.WHITE);
        backgroundLabel.add(newPinField);

        confirmPinField = createPasswordField(600, 255, 180, 25, passwordFont, backgroundColor, Color.WHITE);
        backgroundLabel.add(confirmPinField);


        changeButton = createActionButton(this, "CHANGE", 700, 362, 150, 35, buttonColor);
        backgroundLabel.add(changeButton);

        backButton = createActionButton(this, "BACK", 700, 406, 150, 35, buttonColor);
        backgroundLabel.add(backButton);



        setSize(1550,1080);
        setLayout(null);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try{

            String enteredNewPin = newPinField.getText();
            String reEnteredPin = confirmPinField.getText();

            if (!enteredNewPin.equals(reEnteredPin)){
                JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                return;
            }
            if (e.getSource()==changeButton){
                if (newPinField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Enter New PIN");
                    return;
                }
                if (confirmPinField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Re-Enter New PIN");
                    return;
                }

                SQL_connection databaseConnection = new SQL_connection();
                String updateBankPin = "update Bank set pin = '"+enteredNewPin+"' where pin = '"+currentPin+"'";
                String updateLoginPin = "update Login set pin = '"+enteredNewPin+"' where pin = '"+currentPin+"'";
                String updateSignupPin = "update SignUpFinalPage set pin = '"+enteredNewPin+"' where pin = '"+currentPin+"'";

                databaseConnection.statement.executeUpdate(updateBankPin);
                databaseConnection.statement.executeUpdate(updateLoginPin);
                databaseConnection.statement.executeUpdate(updateSignupPin);

                JOptionPane.showMessageDialog(null,"PIN changed successfully");
                setVisible(false);
                new MainMenu(enteredNewPin);

            } else if (e.getSource()==backButton) {
                new MainMenu(currentPin);
                setVisible(false);
            }


        }catch (Exception E){
            E.printStackTrace();
        }



    }

    public static void main(String[] args) {
        new PinChange("");
    }
}