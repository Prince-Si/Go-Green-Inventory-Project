package emp;

import javax.swing.*;
import Login.MainScreen;
import java.awt.*;
import java.awt.event.*;

class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();

    JLabel titleLabel = new JLabel("Employee Login");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JButton homeButton = new JButton("HOME");
    JCheckBox showPassword = new JCheckBox("Show Password");

    LoginFrame() {
        setTitle("GoGreen - Login");
        setSize(450, 400);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container.setLayout(null);
        container.setBackground(new Color(34, 34, 34)); // dark background

        // Title Label
        titleLabel.setBounds(130, 30, 250, 30);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        container.add(titleLabel);

        // Username Label
        userLabel.setBounds(50, 100, 100, 30);
        userLabel.setForeground(Color.WHITE);
        container.add(userLabel);

        // Username TextField
        userTextField.setBounds(160, 100, 200, 30);
        userTextField.setBackground(new Color(64, 64, 64));
        userTextField.setForeground(Color.WHITE);
        userTextField.setCaretColor(Color.WHITE);
        container.add(userTextField);

        // Password Label
        passwordLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setForeground(Color.WHITE);
        container.add(passwordLabel);

        // Password Field
        passwordField.setBounds(160, 150, 200, 30);
        passwordField.setBackground(new Color(64, 64, 64));
        passwordField.setForeground(Color.WHITE);
        passwordField.setCaretColor(Color.WHITE);
        container.add(passwordField);

        // Show Password Checkbox
        showPassword.setBounds(160, 190, 150, 25);
        showPassword.setForeground(Color.WHITE);
        showPassword.setBackground(new Color(34, 34, 34));
        container.add(showPassword);

        // Login Button
        loginButton.setBounds(50, 240, 100, 35);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        container.add(loginButton);

        // Reset Button
        resetButton.setBounds(160, 240, 100, 35);
        resetButton.setBackground(new Color(220, 53, 69));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        container.add(resetButton);

        // Home Button
        homeButton.setBounds(270, 240, 100, 35);
        homeButton.setBackground(new Color(108, 117, 125));
        homeButton.setForeground(Color.WHITE);
        homeButton.setFocusPainted(false);
        container.add(homeButton);

        // Add action listeners
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        homeButton.addActionListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText = userTextField.getText();
            String pwdText = passwordField.getText();

            if (userText.equalsIgnoreCase("Emp1") && pwdText.equalsIgnoreCase("Emp1admin")) {
                dispose();
                Bill billFrame = new Bill();
                billFrame.Bill1();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        } else if (e.getSource() == homeButton) {
            dispose();
            MainScreen master = new MainScreen();
            master.MainScreen();
        } else if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}

public class Logine {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }

    public void Logine() {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
