package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

import static bank.management.system.Component_creation_functions.*;
import static javax.swing.UIManager.setLookAndFeel;

public class SignUp extends JFrame implements ActionListener {

    Random ran = new Random();
    long transform =(ran.nextLong() % 9000L) +1000L;
    String application_num = " " + Math.abs(transform);


    private JButton next_page_button;
    private JRadioButton maleRadioButton, femaleRadioButton, marriedRadioButton, unmarriedRadioButton, otherRadioButton;

    private JTextField nameTextField, fatherNameTextField, emailTextField, addressTextField, cityTextField, stateTextField, pinCodeTextField;
    private JDateChooser dateOfBirthChooser;

    public SignUp() {
        super("APPLICATION FORM");
        initializeLookAndFeel();
        initializeComponents();
        getContentPane().setBackground(new Color(222, 255, 228));
        setLayout(null);
        setSize(850, 800);
        setLocation(360, 40);
        setVisible(true);
    }

    private void initializeLookAndFeel() {
        try {
            setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to set the desired Look and Feel", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initializeComponents() {

        add(createImageLabel("icon/bank.png", 25, 10, 100, 100));

        add(createLabel("APPLICATION FORM NO."+ application_num, new Font("Raleway", Font.BOLD, 38), 160, 20, 600, 40));
        add(createLabel("Page 1", new Font("Raleway", Font.BOLD, 22), 330, 70, 600, 30));
        add(createLabel("Personal Details", new Font("Raleway", Font.BOLD, 22), 290, 90, 600, 30));

        add(createLabel("Name :", new Font("Raleway", Font.BOLD, 20), 100, 190, 100, 30));
        nameTextField = createTextField(300, 190, 400, 30);
        add(nameTextField);

        add(createLabel("Father's Name :", new Font("Raleway", Font.BOLD, 20), 100, 240, 200, 30));
        fatherNameTextField = createTextField(300, 240, 400, 30);
        add(fatherNameTextField);

        add(createLabel("Gender", new Font("Raleway", Font.BOLD, 20), 100, 290, 200, 30));
        maleRadioButton = createRadioButton("Male", 300, 290, 60, 30);
        add(maleRadioButton);

        femaleRadioButton = createRadioButton("Female", 450, 290, 90, 30);
        add(femaleRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        addRadioButtonsToGroup(genderGroup, maleRadioButton, femaleRadioButton);

        add(createLabel("Date of Birth", new Font("Raleway", Font.BOLD, 20), 100, 340, 200, 30));
        dateOfBirthChooser = new JDateChooser();
        dateOfBirthChooser.setForeground(new Color(105, 105, 105));
        dateOfBirthChooser.setBounds(300, 340, 400, 30);
        add(dateOfBirthChooser);

        add(createLabel("Marital Status :", new Font("Raleway", Font.BOLD, 20), 100, 440, 200, 30));
        marriedRadioButton = createRadioButton("Married", 300, 440, 100, 30);
        unmarriedRadioButton = createRadioButton("Unmarried", 450, 440, 100, 30);
        otherRadioButton = createRadioButton("Other", 635, 440, 100, 30);
        ButtonGroup maritalStatusGroup = new ButtonGroup();
        addRadioButtonsToGroup(maritalStatusGroup, marriedRadioButton, unmarriedRadioButton, otherRadioButton);

        add(createLabel("Email address :", new Font("Raleway", Font.BOLD, 20), 100, 390, 200, 30));
        emailTextField = createTextField(300, 390, 400, 30);
        add(emailTextField);

        add(createLabel("Address :", new Font("Raleway", Font.BOLD, 20), 100, 490, 200, 30));
        addressTextField = createTextField(300, 490, 400, 30);
        add(addressTextField);

        add(createLabel("City :", new Font("Raleway", Font.BOLD, 20), 100, 540, 200, 30));
        cityTextField = createTextField(300, 540, 400, 30);
        add(cityTextField);

        add(createLabel("Pin Code :", new Font("Raleway", Font.BOLD, 20), 100, 590, 200, 30));
        pinCodeTextField = createTextField(300, 590, 400, 30);
        add(pinCodeTextField);

        add(createLabel("State :", new Font("Raleway", Font.BOLD, 20), 100, 640, 200, 30));
        stateTextField = createTextField(300, 640, 400, 30);
        add(stateTextField);


        next_page_button = createActionButton(this, "next", 620, 710, 80, 30);
        add(next_page_button);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String formNumber = application_num;
        String applicantName = nameTextField.getText();
        String fatherName = fatherNameTextField.getText();
        String dateOfBirth = ((JTextField) dateOfBirthChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (maleRadioButton.isSelected()) {
            gender = "Male";
        } else if (femaleRadioButton.isSelected()) {
            gender = "Female";
        }
        String emailAddress = emailTextField.getText();
        String maritalStatus = null;
        if (marriedRadioButton.isSelected()) {
            maritalStatus = "Married";
        } else if (unmarriedRadioButton.isSelected()) {
            maritalStatus = "Unmarried";
        } else if (otherRadioButton.isSelected()) {
            maritalStatus = "Other";
        }

        String homeAddress = addressTextField.getText();
        String cityName = cityTextField.getText();
        String pinCode = pinCodeTextField.getText();
        String stateName = stateTextField.getText();

        try {
            if (applicantName.equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the required fields.");
            } else {
                SQL_connection connection = new SQL_connection();
                String query = "insert into SignUp values('" + formNumber + "', '" + applicantName + "', '" + fatherName + "', '" + dateOfBirth + "', '" + gender + "', '" + emailAddress + "', '" + maritalStatus + "', '" + homeAddress + "', '" + cityName + "', '" + pinCode + "', '" + stateName + "')";
                connection.statement.executeUpdate(query);
                new SignUpSecondPage(formNumber);
                //new SignUpFinalPage(formNumber);
                setVisible(false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }




}