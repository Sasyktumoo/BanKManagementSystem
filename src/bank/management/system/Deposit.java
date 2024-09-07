package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static bank.management.system.Component_creation_functions.*;

public class Deposit extends JFrame implements ActionListener {
    String accountPin;
    TextField amountTextField;
    JLabel backgroundImageLabel;
    JButton depositButton, backButton;

    Deposit(String pin){

        this.accountPin = pin;

        backgroundImageLabel = createImageLabel("icon/atm_machine_frame.png", 0, 0, 1550, 830);
        add(backgroundImageLabel);

        JLabel depositLabel = createLabel("ENTER AMOUNT YOU WANT TO DEPOSIT", new Font("System", Font.BOLD, 16), 460, 180, 400, 35, Color.WHITE);
        backgroundImageLabel.add(depositLabel);

        amountTextField = createTextField(460, 230, 320, 40, new Color(65, 125, 128), Color.WHITE, 22);
        backgroundImageLabel.add(amountTextField);

        depositButton = createActionButton(this, "DEPOSIT", 700, 362, 150, 35);
        depositButton.setBackground(new Color(65,125,128));
        backgroundImageLabel.add(depositButton);

        backButton = createActionButton(this, "BACK", 700, 406, 150, 35);
        backButton.setBackground(new Color(65,125,128));
        backgroundImageLabel.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = amountTextField.getText();
            Date currentDate = new Date();
            if (e.getSource() == depositButton) {
                if (amountTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    SQL_connection connection = new SQL_connection();
                    connection.statement.executeUpdate("insert into Bank values('"+accountPin+"', '"+currentDate+"','Deposit', '"+amount+"')");
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Deposited Successfully");
                    setVisible(false);
                    new MainMenu(accountPin);
                }
            } else if (e.getSource() == backButton) {
                setVisible(false);
                new MainMenu(accountPin);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}