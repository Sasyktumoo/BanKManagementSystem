package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import static bank.management.system.Component_creation_functions.*;

public class Login extends JFrame implements ActionListener {

    private JTextField cardNumberField;
    private JPasswordField pinField;

    private JButton signInButton, clearButton, signUpButton;

    public Login() {

        super("Bank Management System");
        setLookAndFeel("Nimbus");
        initializeUI();
        setLayout(null);
        setSize(850, 480);
        setLocation(450, 200);
        setVisible(true);
    }

    private void initializeUI() {
        add(createImageLabel("icon/bank.png", 350, 10, 100, 100));
        add(createImageLabel("icon/card.png", 630, 350, 100, 100));

        add(createLabel("WELCOME TO ATM", new Font("AvantGarde", Font.BOLD, 38), 230, 125, 450, 40, Color.WHITE));

        add(createLabel("Card No:", new Font("Ralway", Font.BOLD, 28), 150, 190, 230, 30, Color.WHITE));
        cardNumberField = createTextField(325, 190, 230, 30);
        add(cardNumberField);

        add(createLabel("PIN:", new Font("Ralway", Font.BOLD, 28), 150, 250, 230, 30, Color.WHITE));
        pinField = createPasswordField(325, 250, 230, 30);
        add(pinField);


        signInButton = createActionButton(this, "SIGN IN", 300, 300, 100, 30);
        add(signInButton);

        clearButton = createActionButton(this, "CLEAR", 430, 300, 100, 30);
        add(clearButton);

        signUpButton = createActionButton(this, "SIGN UP", 300, 350, 230, 30);
        add(signUpButton);
        add(createImageLabel("icon/bank_application_background.png", 0, 0, 850, 480));

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signInButton) {

                SQL_connection c = new SQL_connection();
                String cardno = cardNumberField.getText();
                String pin = new String(pinField.getPassword());
                String q = "select * from login where card_number = '" + cardno + "' and pin = '" + pin + "'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    setVisible(false);
                    new MainMenu(pin);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (e.getSource() == clearButton) {
                cardNumberField.setText("");
                pinField.setText("");
            } else if (e.getSource() == signUpButton) {
                new SignUp();
                setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}