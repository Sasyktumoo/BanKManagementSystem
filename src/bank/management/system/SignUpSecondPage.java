package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bank.management.system.Component_creation_functions.*;
//fix radio button for senior citizen
public class SignUpSecondPage extends JFrame implements ActionListener {

    JComboBox<String> comboBoxNationality, comboBoxCategory, comboBoxIncome, comboBoxEducation, comboBoxOccupation;
    JTextField textFieldTIN, textFieldHKID;
    JRadioButton radioButtonSeniorYes, radioButtonSeniorNo, radioButtonExistingYes, radioButtonExistingNo;
    JButton buttonNext;
    String formNumber;

    SignUpSecondPage(String formNumber){

        super("APPLICATION FORM");

        add(createImageLabel("icon/bank.png", 150, 5, 100, 100));

        add(createLabel("Page 2 :", new Font("Raleway", Font.BOLD, 22), 300, 30, 600, 40));
        add(createLabel("Additional Details", new Font("Raleway", Font.BOLD, 22), 300, 60, 600, 40));

        add(createLabel("Nationality :", new Font("Raleway", Font.BOLD, 18), 100, 120, 200, 30));
        String[] nationalities = {"Mainland Chinese", "Hong Kong", "Pakistan", "Indian", "Other"};
        comboBoxNationality = createComboBox(nationalities, 350, 120, 320, 30);
        add(comboBoxNationality);

        add(createLabel("Category :", new Font("Raleway", Font.BOLD, 18), 100, 170, 100, 30));
        String[] categories = {"Local Resident", "Non-Local Resident"};
        comboBoxCategory = createComboBox(categories, 350, 170, 320, 30);
        add(comboBoxCategory);

        add(createLabel("Income :", new Font("Raleway", Font.BOLD, 18), 100, 220, 100, 30));
        String[] incomeLevels = {"Null", "<1,50,000", "<2,50,000", "5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        comboBoxIncome = createComboBox(incomeLevels, 350, 220, 320, 30);
        add(comboBoxIncome);

        add(createLabel("Education :", new Font("Raleway", Font.BOLD, 18), 100, 270, 150, 30));
        String[] educationLevels = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"};
        comboBoxEducation = createComboBox(educationLevels, 350, 270, 320, 30);
        add(comboBoxEducation);

        add(createLabel("Occupation :", new Font("Raleway", Font.BOLD, 18), 100, 340, 150, 30));
        String[] occupations = {"Salary based", "Self-Employed", "Business", "Student", "Retired", "Other"};
        comboBoxOccupation = createComboBox(occupations, 350, 340, 320, 30);
        add(comboBoxOccupation);

        add(createLabel("TIN Number :", new Font("Raleway", Font.BOLD, 18), 100, 390, 150, 30));
        textFieldTIN = new JTextField();
        textFieldTIN.setFont(new Font("Raleway", Font.BOLD, 18));
        textFieldTIN.setBounds(350, 390, 320, 30);
        add(textFieldTIN);

        add(createLabel("HKID Number :", new Font("Raleway", Font.BOLD, 18), 100, 440, 180, 30));
        textFieldHKID = new JTextField();
        textFieldHKID.setFont(new Font("Raleway", Font.BOLD, 18));
        textFieldHKID.setBounds(350, 440, 320, 30);
        add(textFieldHKID);

        add(createLabel("Senior Citizen :", new Font("Raleway", Font.BOLD, 18), 100, 490, 180, 30));
        radioButtonSeniorYes = createRadioButton("Yes", 350, 490, 100, 30);
        add(radioButtonSeniorYes);
        radioButtonSeniorNo = createRadioButton("No", 460, 490, 100, 30);
        add(radioButtonSeniorNo);

        ButtonGroup senior_citizen_button_group = new ButtonGroup();
        addRadioButtonsToGroup(senior_citizen_button_group, radioButtonSeniorNo, radioButtonSeniorYes);

        add(createLabel("Existing Account :", new Font("Raleway", Font.BOLD, 18), 100, 540, 180, 30));
        radioButtonExistingYes = createRadioButton("Yes", 350, 540, 100, 30);
        add(radioButtonExistingYes);
        radioButtonExistingNo = createRadioButton("No", 460, 540, 100, 30);
        add(radioButtonExistingNo);
        ButtonGroup existing_account_button_group = new ButtonGroup();
        addRadioButtonsToGroup(existing_account_button_group, radioButtonExistingNo, radioButtonExistingYes);

        add(createLabel("Form No : ", new Font("Raleway", Font.BOLD, 14), 660, 10, 100, 30));
        add(createLabel(formNumber, new Font("Raleway", Font.BOLD, 14), 760, 10, 60, 30));

        buttonNext = createActionButton(this, "Next", 570, 640, 100, 30);
        add(buttonNext);

        setLayout(null);
        setSize(850,750);
        setLocation(450,80);
        getContentPane().setBackground(new Color(252, 208, 76));
        setVisible(true);

        setLayout(null);
        setSize(850,750);
        setLocation(450,80);
        getContentPane().setBackground(new Color(252, 208, 76));
        setVisible(true);
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, int width, int height) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setBackground(new Color(252, 208, 76));
        comboBox.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox.setBounds(x, y, width, height);

        return comboBox;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        String nationality = (String) comboBoxNationality.getSelectedItem();
        String category = (String) comboBoxCategory.getSelectedItem();
        String income = (String) comboBoxIncome.getSelectedItem();
        String education = (String) comboBoxEducation.getSelectedItem();
        String occupation = (String) comboBoxOccupation.getSelectedItem();

        String tinNumber = textFieldTIN.getText();
        String hkidNumber = textFieldHKID.getText();

        String seniorCitizen = radioButtonSeniorYes.isSelected() ? "Yes" : "No";
        String existingAccount = radioButtonExistingYes.isSelected() ? "Yes" : "No";

        try {
            if (tinNumber.isEmpty() || hkidNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            } else {
                SQL_connection connection = new SQL_connection();
                String query = "INSERT INTO SignUpSecondPage VALUES('" + formNumber + "', '" + nationality + "', '" + category + "','" + income + "','" + education + "','" + occupation + "','" + tinNumber + "','" + hkidNumber + "','" + seniorCitizen + "','" + existingAccount + "')";
                connection.statement.executeUpdate(query);
                new SignUpFinalPage(formNumber);
                setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new SignUpSecondPage("");
    }
}