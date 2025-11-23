package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Login.MainScreen;

/*
  Fixed and modernized Admin Login (frontend only)
  - Components are added to the actual visible content pane (GradientPanel)
  - Keeps original logic intact
  - Uses SwingUtilities.invokeLater for safe Swing startup
*/

class GradientPanel extends JPanel {
    private final Color top = new Color(8, 25, 40);
    private final Color bottom = new Color(2, 150, 110);

    GradientPanel() {
        setOpaque(true);
        setLayout(null); // absolute positioning like your original code
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, top, 0, getHeight(), bottom);
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}

class RoundedField extends JTextField {
    RoundedField() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(255, 255, 255, 220));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }
}

class RoundedPasswordField extends JPasswordField {
    RoundedPasswordField() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setForeground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(255, 255, 255, 220));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }
}

class ModernButton extends JButton {
    private final Color base = new Color(0, 145, 110);
    private final Color hover = new Color(0, 185, 140);

    ModernButton(String text) {
        super(text);
        setFocusPainted(false);
        setForeground(Color.white);
        setFont(new Font("Segoe UI", Font.BOLD, 13));
        setContentAreaFilled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        setBackground(base);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hover);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(base);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
        super.paintComponent(g);
        g2.dispose();
    }
}

class LoginFrame extends JFrame implements ActionListener {

    // UI components
    private final GradientPanel content = new GradientPanel();
    private final JLabel userLabel = new JLabel("USERNAME");
    private final JLabel passwordLabel = new JLabel("PASSWORD");
    private final RoundedField userTextField = new RoundedField();
    private final RoundedPasswordField passwordField = new RoundedPasswordField();
    private final JButton loginButton = new ModernButton("LOGIN");
    private final JButton resetButton = new ModernButton("RESET");
    private final JButton homeButton = new ModernButton("HOME");
    private final JCheckBox showPassword = new JCheckBox("Show Password");

    // store default echo char
    private char defaultEcho;

    LoginFrame() {
        initFrame();
        layoutComponents();
        addActionEvent();
    }

    private void initFrame() {
        setTitle("GoGreen - Admin Login");
        setSize(450, 420);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // set the gradient panel as content pane and use null layout to preserve positioning
        setContentPane(content);
    }

    private void layoutComponents() {
        // Title
        JLabel title = new JLabel("Green Energy Ltd");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBounds(95, 25, 300, 36);
        content.add(title);

        // Labels
        userLabel.setBounds(70, 110, 100, 28);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        userLabel.setForeground(Color.WHITE);

        passwordLabel.setBounds(70, 160, 120, 28);
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setForeground(Color.WHITE);

        // Fields
        userTextField.setBounds(190, 110, 180, 34);
        passwordField.setBounds(190, 160, 180, 34);

        // Show password checkbox
        showPassword.setBounds(190, 204, 140, 22);
        showPassword.setOpaque(false);
        showPassword.setForeground(Color.WHITE);
        showPassword.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Buttons
        loginButton.setBounds(60, 250, 120, 44);
        resetButton.setBounds(190, 250, 120, 44);
        homeButton.setBounds(320, 250, 80, 44);

        // add to visible content pane
        content.add(userLabel);
        content.add(passwordLabel);
        content.add(userTextField);
        content.add(passwordField);
        content.add(showPassword);
        content.add(loginButton);
        content.add(resetButton);
        content.add(homeButton);

        // store default echo char after password field created
        defaultEcho = passwordField.getEchoChar();
    }

    private void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        homeButton.addActionListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == loginButton) {
            String userText = userTextField.getText();
            String pwdText = String.valueOf(passwordField.getPassword());

            if (userText.equalsIgnoreCase("Admin") && pwdText.equalsIgnoreCase("Admin3187")) {
                dispose();
                Admin a = new Admin();
                a.Admin();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }

        if (src == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }

        if (src == homeButton) {
            dispose();
            MainScreen master = new MainScreen();
            master.MainScreen();
        }

        if (src == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar(defaultEcho);
            }
        }
    }
}

public class Login1 {

    // keep original main behavior but safer with invokeLater
    public static void main(String[] a) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }

    // method used by other classes to open this login (keeps original name)
    public void Login1() {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
