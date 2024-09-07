package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Component_creation_functions {
    public static JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Raleway", Font.BOLD, 14));
        return textField;
    }

    public static TextField createTextField(int x, int y, int width, int height, Color background_color, Color foreground_color, int font_size) {
        TextField textField = new TextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(background_color);
        textField.setForeground(foreground_color);
        textField.setFont(new Font("Raleway", Font.BOLD, font_size));
        return textField;
    }
    public static JPasswordField createPasswordField(int x, int y, int width, int height) {
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(x, y, width, height);
        passwordField.setFont(new Font("Arial", Font.BOLD, 14));
        return passwordField;
    }
    public static JPasswordField createPasswordField(int x, int y, int width, int height, Font font, Color background_color, Color foreground_color) {
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(x, y, width, height);
        passwordField.setBackground(background_color);
        passwordField.setForeground(foreground_color);
        passwordField.setFont(font);
        return passwordField;
    }
    public static JLabel createLabel(String text, Font font, int x, int y, int width, int height, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;
    }
    public static JLabel createLabel(String text, Font font, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setBounds(x, y, width, height);
        return label;
    }
    public static JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        return label;
    }
    public static JButton createActionButton(ActionListener listener, String text, int x, int y, int width, int height) {
        JButton action_button = new JButton(text);
        action_button.setFont(new Font("Raleway", Font.BOLD, 14));
        action_button.setBackground(Color.BLACK);
        action_button.setForeground(Color.WHITE);
        action_button.setBounds(x, y, width, height);
        action_button.addActionListener(listener);
        return action_button;
    }

    public static JButton createActionButton(ActionListener listener, String text, int x, int y, int width, int height, Color color) {
        JButton action_button = new JButton(text);
        action_button.setFont(new Font("Raleway", Font.BOLD, 14));
        action_button.setBackground(color);
        action_button.setForeground(Color.WHITE);
        action_button.setBounds(x, y, width, height);
        action_button.addActionListener(listener);
        return action_button;
    }
    public static JButton createActionButtonDefaultFont(ActionListener listener, String text, int x, int y, int width, int height, Color color) {
        JButton action_button = new JButton(text);
        action_button.setBackground(color);
        action_button.setForeground(Color.WHITE);
        action_button.setBounds(x, y, width, height);
        action_button.addActionListener(listener);
        return action_button;
    }

    static void setLookAndFeel(String lookAndFeelName) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeelName.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    System.out.println(lookAndFeelName + " Look and Feel set successfully.");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Could not set " + lookAndFeelName + " Look and Feel.");
            e.printStackTrace();
        }
    }
    public static JLabel createImageLabel(String path, int x, int y, int width, int height) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(path));
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(x, y, width, height);
        return label;
    }
    public static JRadioButton createRadioButton(String text, int x, int y, int width, int height) {
        JRadioButton button = new JRadioButton(text);
        button.setFont(new Font("Raleway", Font.BOLD, 14));
        button.setBackground(new Color(222, 255, 228));
        button.setBounds(x, y, width, height);
        return button;
    }

    public static void addRadioButtonsToGroup(ButtonGroup group, JRadioButton... buttons) {
        for (JRadioButton button : buttons) {
            group.add(button);
        }
    }
}
