
package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static bank.management.system.Component_creation_functions.*;

public class SignUpFinalPage extends JFrame implements ActionListener {

    JRadioButton savingsAccountRadioButton, fixedDepositRadioButton, currentAccountRadioButton, recurringDepositRadioButton;
    JButton submitButton, cancelButton;
    JCheckBox atmCardCheckBox, internetBankingCheckBox, mobileBankingCheckBox, emailAlertsCheckBox, chequeBookCheckBox, eStatementCheckBox, termsCheckBox;
    String formNumber;

    SignUpFinalPage(String formNumber) {

        this.formNumber = formNumber;

        add(createImageLabel("icon/bank.png", 150, 5, 100, 100));

        add(createLabel("Page 3:", new Font("Raleway", Font.BOLD, 22), 280, 40, 400, 40));
        add(createLabel("Account Details", new Font("Raleway", Font.BOLD, 22), 280, 70, 400, 40));
        add(createLabel("Account Type:", new Font("Raleway", Font.BOLD, 18), 100, 140, 200, 30));

        savingsAccountRadioButton = createRadioButton("Saving Account", 100, 180, 150, 30);
        add(savingsAccountRadioButton);
        fixedDepositRadioButton = createRadioButton("Fixed Deposit Account", 350, 180, 300, 30);
        add(fixedDepositRadioButton);
        currentAccountRadioButton = createRadioButton("Current Account", 100, 220, 250, 30);
        add(currentAccountRadioButton);
        recurringDepositRadioButton = createRadioButton("Recurring Deposit Account", 350, 220, 250, 30);
        add(recurringDepositRadioButton);

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(savingsAccountRadioButton);
        accountTypeGroup.add(fixedDepositRadioButton);
        accountTypeGroup.add(currentAccountRadioButton);
        accountTypeGroup.add(recurringDepositRadioButton);

        add(createLabel("Card Number:", new Font("Raleway", Font.BOLD, 18), 100, 300, 200, 30));
        add(createLabel("(Your 16-digit Card Number)", new Font("Raleway", Font.BOLD, 12), 100, 330, 200, 20));
        add(createLabel("XXXX-XXXX-XXXX-4841", new Font("Raleway", Font.BOLD, 18), 330, 300, 250, 30));
        add(createLabel("(It would appear on atm card/cheque Book and Statements)", new Font("Raleway", Font.BOLD, 12), 330, 330, 500, 20));
        add(createLabel("PIN:", new Font("Raleway", Font.BOLD, 18), 100, 370, 200, 30));
        add(createLabel("XXXX", new Font("Raleway", Font.BOLD, 18), 330, 370, 200, 30));
        add(createLabel("(4-digit Password)", new Font("Raleway", Font.BOLD, 12), 100, 400, 200, 20));
        add(createLabel("Services Required:", new Font("Raleway", Font.BOLD, 18), 100, 450, 200, 30));

        createCheckBox("ATM CARD", 100, 500, 200, 30);
        createCheckBox("Internet Banking", 350, 500, 200, 30);
        createCheckBox("Mobile Banking", 100, 550, 200, 30);
        createCheckBox("EMAIL Alerts", 350, 550, 200, 30);
        createCheckBox("Cheque Book", 100, 600, 200, 30);
        createCheckBox("E-Statement", 350, 600, 200, 30);

        termsCheckBox = createCheckBox("I hereby declare that the above entered details correct to the best of my knowledge.", 100, 680, 600, 20, new Font("Raleway", Font.BOLD, 10));
        termsCheckBox.setSelected(true);
        add(termsCheckBox);

        add(createLabel("Form No : ", new Font("Raleway", Font.BOLD, 14), 700, 10, 100, 30));
        add(createLabel(formNumber, new Font("Raleway", Font.BOLD, 14), 760, 10, 60, 30));

        submitButton = createActionButton(this, "Submit", 250, 720, 100, 30);
        add(submitButton);

        cancelButton = createActionButton(this, "Cancel", 420, 720, 100, 30);
        add(cancelButton);

        getContentPane().setBackground(new Color(215,252,252));
        setSize(850,800);
        setLayout(null);
        setLocation(400,20);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String accountType = determineAccountType();
        String cardNumber = generateCardNumber();
        String pin = generatePin();
        String facilities = determineSelectedFacilities();

        try {
            if (e.getSource() == submitButton) {
                if (accountType.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select an account type.");
                } else {
                    SQL_connection dbConnection = new SQL_connection();
                    executeDatabaseUpdate(dbConnection, formNumber, accountType, cardNumber, pin, facilities);
                    JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\nPin: " + pin);
                    new Deposit(pin);
                    setVisible(false);
                }
            } else if (e.getSource() == cancelButton) {
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void executeDatabaseUpdate(SQL_connection dbConnection, String formNumber, String accountType, String cardNumber, String pin, String facilities) throws Exception {
        String query1 = "insert into SignUpFinalPage values ('%s', '%s', '%s', '%s', '%s')";
        String query2 = "insert into Login values ('%s', '%s', '%s')";

        String formattedQuery1 = String.format(query1, formNumber, accountType, cardNumber, pin, facilities);
        String formattedQuery2 = String.format(query2, formNumber, cardNumber, pin);

        dbConnection.statement.executeUpdate(formattedQuery1);
        dbConnection.statement.executeUpdate(formattedQuery2);
    }

    private String determineAccountType() {
        if (savingsAccountRadioButton.isSelected()) return "Saving Account";
        if (fixedDepositRadioButton.isSelected()) return "Fixed Deposit Account";
        if (currentAccountRadioButton.isSelected()) return "Current Account";
        if (recurringDepositRadioButton.isSelected()) return "Recurring Deposit Account";
        return "";
    }

    private String generateCardNumber() {
        Random random = new Random();
        long card_number_generated = Math.abs((random.nextLong() % 90000000L) + 1409963000000000L);
        return String.valueOf(card_number_generated);
    }

    private String generatePin() {
        Random random = new Random();
        long pin_number_generated = Math.abs((random.nextLong() % 9000L) + 1000L);
        return String.valueOf(pin_number_generated);
    }

    private String determineSelectedFacilities() {
        StringBuilder facilities = new StringBuilder();
        if (atmCardCheckBox.isSelected()) facilities.append("ATM CARD ");
        if (internetBankingCheckBox.isSelected()) facilities.append("Internet Banking ");
        if (mobileBankingCheckBox.isSelected()) facilities.append("Mobile Banking ");
        if (emailAlertsCheckBox.isSelected()) facilities.append("EMAIL Alerts ");
        if (chequeBookCheckBox.isSelected()) facilities.append("Cheque Book ");
        if (eStatementCheckBox.isSelected()) facilities.append("E-Statement ");
        return facilities.toString().trim();
    }
    public void createCheckBox(String text, int x, int y, int width, int height) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setFont(new Font("Raleway", Font.BOLD, 16));
        checkBox.setBackground(new Color(215, 252, 252));
        checkBox.setBounds(x, y, width, height);

        if (text.equals("ATM CARD")) {
            atmCardCheckBox = checkBox;
        } else if (text.equals("Internet Banking")) {
            internetBankingCheckBox = checkBox;
        } else if (text.equals("Mobile Banking")) {
            mobileBankingCheckBox = checkBox;
        } else if (text.equals("EMAIL Alerts")) {
            emailAlertsCheckBox = checkBox;
        } else if (text.equals("Cheque Book")) {
            chequeBookCheckBox = checkBox;
        } else if (text.equals("E-Statement")) {
            eStatementCheckBox = checkBox;
        }

        add(checkBox);
    }
    public static JCheckBox createCheckBox(String text, int x, int y, int width, int height, Font font) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setFont(font);
        checkBox.setBounds(x, y, width, height);
        checkBox.setBackground(new Color(215, 252, 252));
        return checkBox;
    }
    public static void main(String[] args) {
        new SignUpFinalPage("");
    }
}



