package Login;

import javax.swing.*;
import admin.Login1;
import emp.Logine;
import java.awt.*;
import java.awt.event.*;

// --------------------- MAIN SCREEN ---------------------
public class MainScreen {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Prop());
    }

    public void MainScreen() {
        new Prop();
    }
}

// --------------------- CUSTOM GRADIENT PANEL ---------------------
class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint gp = new GradientPaint(
                0, 0, new Color(20, 35, 50),
                0, getHeight(), new Color(5, 200, 120)
        );

        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}

// --------------------- CUSTOM ROUNDED BUTTON ---------------------
class ModernButton extends JButton {

    private Color hoverColor = new Color(5, 181, 115);
    private Color normalColor = new Color(0, 150, 90);
    private boolean hovered = false;

    ModernButton(String text) {
        super(text);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 15));
        setBackground(normalColor);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover animation
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                setBackground(normalColor);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Rounded
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

        super.paintComponent(g);
        g2.dispose();
    }
}

// --------------------- MAIN PROP SCREEN ---------------------
class Prop {

    JFrame frame = new JFrame();
    ModernButton adminBtn = new ModernButton("Admin");
    ModernButton empBtn = new ModernButton("Employee");

    Prop() {
        prepareGUI();
        setupComponents();
        addActions();
    }

    public void prepareGUI() {
        frame.setTitle("GoGreen - Login");
        frame.setSize(450, 520);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setContentPane(new GradientPanel());
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void setupComponents() {

        JLabel title = new JLabel("Green Energy Ltd");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setBounds(80, 40, 350, 40);

        JLabel sub = new JLabel("LOGIN AS");
        sub.setForeground(Color.WHITE);
        sub.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sub.setBounds(170, 120, 300, 30);

        adminBtn.setBounds(140, 190, 160, 50);
        empBtn.setBounds(140, 270, 160, 50);

        frame.add(title);
        frame.add(sub);
        frame.add(adminBtn);
        frame.add(empBtn);
    }

    public void addActions() {
        adminBtn.addActionListener(e -> {
            frame.dispose();
            Login1 java = new Login1();
            java.Login1();
        });

        empBtn.addActionListener(e -> {
            frame.dispose();
            Logine emp = new Logine();
            emp.Logine();
        });
    }
}
