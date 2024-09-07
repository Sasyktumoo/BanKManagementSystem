package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import static bank.management.system.Component_creation_functions.*;

public class FastCash extends JFrame implements ActionListener {

    JButton withdraw100Button, withdraw500Button, withdraw1000Button, withdraw2000Button, withdraw5000Button, withdraw10000Button, backButton;
    String accountPin;
    Color defaultButtonColor = new Color(65, 125, 128);

    FastCash(String pin) {
        this.accountPin = pin;

        JLabel backgroundLabel = createImageLabel("icon/atm_machine_frame.png", 0, 0, 1550, 830);
        add(backgroundLabel);

        JLabel titleLabel = createLabel("SELECT WITHDRAWAL AMOUNT", new Font("System", Font.BOLD, 23), 445, 180, 700, 35, Color.WHITE);
        backgroundLabel.add(titleLabel);

        withdraw100Button = createActionButton(this, "Rs. 100", 410, 274, 150, 35, defaultButtonColor);
        backgroundLabel.add(withdraw100Button);

        withdraw500Button = createActionButton(this, "Rs. 500", 700, 274, 150, 35, defaultButtonColor);
        backgroundLabel.add(withdraw500Button);

        withdraw1000Button = createActionButton(this, "Rs. 1000", 410, 318, 150, 35, defaultButtonColor);
        backgroundLabel.add(withdraw1000Button);

        withdraw2000Button = createActionButton(this, "Rs. 2000", 700, 318, 150, 35, defaultButtonColor);
        backgroundLabel.add(withdraw2000Button);

        withdraw5000Button = createActionButton(this, "Rs. 5000", 410, 362, 150, 35, defaultButtonColor);
        backgroundLabel.add(withdraw5000Button);

        withdraw10000Button = createActionButton(this, "Rs. 10000", 700, 362, 150, 35, defaultButtonColor);
        backgroundLabel.add(withdraw10000Button);

        backButton = createActionButton(this, "BACK", 700, 406, 150, 35, defaultButtonColor);
        backgroundLabel.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            setVisible(false);
            new MainMenu(accountPin);
        } else {
            String amount = ((JButton) e.getSource()).getText().substring(4);
            SQL_connection connection = new SQL_connection();
            Date currentDate = new Date();
            try {
                ResultSet resultSet = connection.statement.executeQuery("select * from bank where pin = '" + accountPin + "'");
                int currentBalance = 0;
                while (resultSet.next()) {
                    if (resultSet.getString("type").equals("Deposit")) {
                        currentBalance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        currentBalance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }

                if (e.getSource() != backButton && currentBalance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                connection.statement.executeUpdate("insert into bank values('"+accountPin+"', '"+currentDate+"', 'withdrawal', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            setVisible(false);
            new MainMenu(accountPin);
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}