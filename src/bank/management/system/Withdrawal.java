package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

import static bank.management.system.Component_creation_functions.*;

public class Withdrawal extends JFrame implements ActionListener {

    String pinNumber;
    TextField amountTextField;

    JButton withdrawButton, backButton;
    Withdrawal(String pin){

        this.pinNumber = pin;

        JLabel backgroundLabel = createImageLabel("icon/atm_machine_frame.png", 0, 0, 1550, 830);
        add(backgroundLabel);

        JLabel maxWithdrawalLabel = createLabel("MAXIMUM WITHDRAWAL IS 10,000 HKD", new Font("System", Font.BOLD, 16), 460, 180, 700, 35, Color.WHITE);
        backgroundLabel.add(maxWithdrawalLabel);

        JLabel enterAmountLabel = createLabel("PLEASE ENTER YOUR AMOUNT", new Font("System", Font.BOLD, 16), 460, 220, 400, 35, Color.WHITE);
        backgroundLabel.add(enterAmountLabel);

        amountTextField = createTextField(460, 260, 320, 25, new Color(65, 125, 129), Color.WHITE, 22);
        backgroundLabel.add(amountTextField);

        withdrawButton = createActionButton(this, "WITHDRAW", 700, 362, 150, 35);
        withdrawButton.setBackground(new Color(65,125,128));
        backgroundLabel.add(withdrawButton);

        backButton = createActionButton(this, "BACK", 700, 406, 150, 35);
        backButton.setBackground(new Color(65,125,128));
        backgroundLabel.add(backButton);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == withdrawButton) {
            try {
                String amount = amountTextField.getText();
                Date currentDate = new Date();
                if (amountTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to withdraw");
                } else {
                    SQL_connection connection = new SQL_connection();
                    ResultSet resultSet = connection.statement.executeQuery("select * from bank where pin = '" + pinNumber + "'");
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insuffient Balance");
                        return;
                    }

                    connection.statement.executeUpdate("insert into Bank values('"+pinNumber+"', '"+currentDate+"', 'Withdrawl', '"+amount+"' )");
                    JOptionPane.showMessageDialog(null, "HKD" + amount + " Debited Successfully");
                    setVisible(false);
                    new MainMenu(pinNumber);

                }
            } catch (Exception exception) {

            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new MainMenu(pinNumber);
        }
    }

    public static void main(String[] args) {
        new Withdrawal("");
    }
}