package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import static bank.management.system.Component_creation_functions.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pin;
    JLabel balanceLabel;
    JButton backButton;

    BalanceEnquiry(String pin){
        //setLookAndFeel("Nimbus");
        this.pin = pin;

        JLabel backgroundLabel = createImageLabel("icon/atm_machine_frame.png", 0, 0, 1550, 830);
        add(backgroundLabel);

        JLabel titleLabel = createLabel("Your Current Balance is HKD: ", new Font("System", Font.BOLD, 16), 430, 180, 700, 35, Color.WHITE);
        backgroundLabel.add(titleLabel);

        balanceLabel = createLabel("", new Font("System", Font.BOLD, 16), 430, 220, 400, 35, Color.WHITE);
        backgroundLabel.add(balanceLabel);

        backButton = createActionButton(this, "Back", 700, 406, 150, 35);
        backgroundLabel.add(backButton);

        int balance = 0;
        try {
            SQL_connection c = new SQL_connection();
            ResultSet resultSet = c.statement.executeQuery("Select * from bank where pin = '"+pin+"'");
            while (resultSet.next()) {
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        balanceLabel.setText(String.valueOf(balance));

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new MainMenu(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}