package admin;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import db_connection.MySQLConnection;

public class Deleteitem {
    public static void main(String[] args) {
        new Deleteitem();
    }

    public Deleteitem() {
        JFrame frame = new JFrame("GoGreen - Delete Item");
        frame.setSize(480, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // Background panel
        JPanel bgPanel = new JPanel();
        bgPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        bgPanel.setBackground(new Color(18, 18, 30));
        bgPanel.setLayout(null);

        // Header label
        JLabel headerLabel = new JLabel("REMOVE PRODUCT", SwingConstants.CENTER);
        headerLabel.setBounds(0, 20, frame.getWidth(), 40);
        headerLabel.setFont(new Font("Arial Black", Font.BOLD, 22));
        headerLabel.setForeground(new Color(0, 255, 200));
        bgPanel.add(headerLabel);

        // Product name label
        JLabel productLabel = new JLabel("Product Name:");
        productLabel.setBounds(50, 100, 120, 30);
        productLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        productLabel.setForeground(new Color(0, 255, 200));
        bgPanel.add(productLabel);

        // Text field
        JTextField tf = new JTextField();
        tf.setBounds(180, 100, 200, 30);
        tf.setFont(new Font("Arial", Font.PLAIN, 16));
        tf.setBackground(new Color(30, 30, 60));
        tf.setForeground(new Color(0, 255, 200));
        tf.setCaretColor(new Color(0, 255, 200));
        tf.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 200), 2));
        bgPanel.add(tf);

        // Buttons
        JButton enterBtn = new JButton("DELETE");
        JButton clearBtn = new JButton("CLEAR");
        JButton homeBtn = new JButton("HOME");

        JButton[] buttons = {enterBtn, clearBtn, homeBtn};
        int[][] positions = {{100, 220}, {220, 220}, {160, 300}};

        for (int i = 0; i < buttons.length; i++) {
            JButton btn = buttons[i];
            btn.setBounds(positions[i][0], positions[i][1], 100, 40);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setForeground(new Color(0, 255, 200));
            btn.setBackground(new Color(30, 30, 60));
            btn.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 200), 2));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Hover effect
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(0, 255, 200));
                    btn.setForeground(new Color(30, 30, 60));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(30, 30, 60));
                    btn.setForeground(new Color(0, 255, 200));
                }
            });

            bgPanel.add(btn);
        }

        // Button actions
        enterBtn.addActionListener(e -> {
            try {
                Connection con = MySQLConnection.DBConn();
                String sql = "DELETE FROM itemlist WHERE Productname = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, tf.getText());
                pst.execute();
                JOptionPane.showMessageDialog(frame, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                tf.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error deleting product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearBtn.addActionListener(e -> tf.setText(""));

        homeBtn.addActionListener(e -> {
            frame.dispose();
            Admin master = new Admin();
            master.Admin();
        });

        frame.add(bgPanel);
        frame.setVisible(true);
    }

    public void Deleteitem() {
        // No-op for compatibility
    }
}
