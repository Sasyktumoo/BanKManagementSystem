package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bank.management.system.Component_creation_functions.*;

public class MainMenu extends JFrame implements ActionListener {
    JButton depositButton, withdrawButton, fastCashButton, miniStatementButton, pinChangeButton, balanceEnquiryButton, exitButton;
    String pin;
    Color backgroundColor = new Color (65, 125, 128);
    MainMenu(String pin){
        this.pin = pin;

        JLabel backgroundLabel = createImageLabel("icon/atm_machine_frame.png", 0, 0, 1550, 830);
        add(backgroundLabel);

        JLabel titleLabel = createLabel("Please Select Your Transaction", new Font("System", Font.BOLD, 25), 430, 180, 600, 35, Color.WHITE);
        backgroundLabel.add(titleLabel);

        depositButton = createActionButtonDefaultFont(this, "DEPOSIT", 410, 274, 150, 35, backgroundColor);
        backgroundLabel.add(depositButton);

        withdrawButton = createActionButtonDefaultFont(this, "CASH WITHDRAWAL", 700, 274, 150, 35, backgroundColor);
        backgroundLabel.add(withdrawButton);

        fastCashButton = createActionButtonDefaultFont(this, "FAST CASH", 410, 318, 150, 35, backgroundColor);
        backgroundLabel.add(fastCashButton);

        miniStatementButton = createActionButtonDefaultFont(this, "MINI STATEMENT", 700, 318, 150, 35, backgroundColor);
        backgroundLabel.add(miniStatementButton);

        pinChangeButton = createActionButtonDefaultFont(this, "PIN CHANGE", 410, 362, 150, 35, backgroundColor);
        backgroundLabel.add(pinChangeButton);

        balanceEnquiryButton = createActionButtonDefaultFont(this, "BALANCE ENQUIRY", 700, 362, 150, 35, backgroundColor);
        backgroundLabel.add(balanceEnquiryButton);

        exitButton = createActionButtonDefaultFont(this, "EXIT", 700, 406, 150, 35, backgroundColor);
        backgroundLabel.add(exitButton);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == depositButton){
            new Deposit(pin);
            setVisible(false);
        } else if (e.getSource() == exitButton){
            System.exit(0);
        } else if (e.getSource() == withdrawButton) {
            new Withdrawal(pin);
            setVisible(false);
        } else if (e.getSource() == balanceEnquiryButton) {
            new BalanceEnquiry(pin);
            setVisible(false);
        } else if (e.getSource() == fastCashButton) {
            new FastCash(pin);
            setVisible(false);
        } else if (e.getSource() == pinChangeButton) {
            new PinChange(pin);
            setVisible(false);
        } else if (e.getSource() == miniStatementButton) {
            new BankStatement(pin);
        }
    }

    public static void main(String[] args) {
        new MainMenu("");
    }
}