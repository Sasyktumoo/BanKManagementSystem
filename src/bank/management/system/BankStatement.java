package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import static bank.management.system.Component_creation_functions.*;

public class BankStatement extends JFrame implements ActionListener {
    String accountPin;
    JButton exitButton;

    BankStatement(String pin) {
        this.accountPin = pin;
        setLookAndFeel("Nimbus");
        getContentPane().setBackground(new Color(255, 204, 204));
        setSize(400, 600);
        setLocation(20, 20);
        setLayout(null);

        JLabel transactionLabel = createLabel("", 20, 140, 400, 200);
        add(transactionLabel);

        JLabel headerLabel = createLabel("TechCoder A.V", 150, 20, 200, 20);
        headerLabel.setFont(new Font("System", Font.BOLD, 15));
        add(headerLabel);

        JLabel cardNumberLabel = createLabel("", 20, 80, 300, 20);
        add(cardNumberLabel);

        JLabel balanceLabel = createLabel("", 20, 400, 300, 20);
        add(balanceLabel);

        try {
            SQL_connection connection = new SQL_connection();
            ResultSet rsCard = connection.statement.executeQuery("select * from login where pin = '" + pin + "'");
            while (rsCard.next()) {
                cardNumberLabel.setText("Card Number:  " + rsCard.getString("card_number").substring(0, 4) + "XXXXXXXX" + rsCard.getString("card_number").substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int balance = 0;
            SQL_connection connection = new SQL_connection();
            ResultSet rsTransactions = connection.statement.executeQuery("select * from bank where pin = '" + pin + "'");
            while (rsTransactions.next()) {
                transactionLabel.setText(transactionLabel.getText() + "<html>" + rsTransactions.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rsTransactions.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rsTransactions.getString("amount") + "<br><br><html>");

                if (rsTransactions.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rsTransactions.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rsTransactions.getString("amount"));
                }
            }
            balanceLabel.setText("Your Total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        exitButton = createActionButton(this, "Exit", 20, 500, 100, 25);
        add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new BankStatement("");
    }
}