package admin;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import db_connection.MySQLConnection;

public class AddItem {
    public static void main(String[] args) {
        new AddItem();
    }

    public AddItem() {
        JFrame frame = new JFrame("GoGreen - Add Item");
        frame.setSize(500, 450);
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
        JLabel headerLabel = new JLabel("ADD PRODUCT", SwingConstants.CENTER);
        headerLabel.setBounds(0, 20, frame.getWidth(), 40);
        headerLabel.setFont(new Font("Arial Black", Font.BOLD, 22));
        headerLabel.setForeground(new Color(0, 255, 200));
        bgPanel.add(headerLabel);

        // Labels and input fields
        JLabel lblProduct = new JLabel("Product Name:");
        lblProduct.setBounds(50, 90, 120, 30);
        lblProduct.setFont(new Font("Verdana", Font.BOLD, 16));
        lblProduct.setForeground(new Color(0, 255, 200));
        bgPanel.add(lblProduct);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(50, 140, 120, 30);
        lblDescription.setFont(new Font("Verdana", Font.BOLD, 16));
        lblDescription.setForeground(new Color(0, 255, 200));
        bgPanel.add(lblDescription);

        JLabel lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(50, 200, 120, 30);
        lblQuantity.setFont(new Font("Verdana", Font.BOLD, 16));
        lblQuantity.setForeground(new Color(0, 255, 200));
        bgPanel.add(lblQuantity);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(50, 250, 120, 30);
        lblPrice.setFont(new Font("Verdana", Font.BOLD, 16));
        lblPrice.setForeground(new Color(0, 255, 200));
        bgPanel.add(lblPrice);

        JTextField tfProduct = new JTextField();
        tfProduct.setBounds(180, 90, 200, 30);
        styleInput(tfProduct);

        JTextArea tfDescription = new JTextArea();
        tfDescription.setBounds(180, 140, 200, 50);
        styleInput(tfDescription);

        JTextField tfQuantity = new JTextField();
        tfQuantity.setBounds(180, 200, 200, 30);
        styleInput(tfQuantity);

        JTextField tfPrice = new JTextField();
        tfPrice.setBounds(180, 250, 200, 30);
        styleInput(tfPrice);

        bgPanel.add(tfProduct);
        bgPanel.add(tfDescription);
        bgPanel.add(tfQuantity);
        bgPanel.add(tfPrice);

        // Buttons
        JButton addBtn = new JButton("ADD");
        JButton clearBtn = new JButton("CLEAR");
        JButton homeBtn = new JButton("HOME");

        addButtonStyle(addBtn, 100, 320);
        addButtonStyle(clearBtn, 220, 320);
        addButtonStyle(homeBtn, 160, 370);

        bgPanel.add(addBtn);
        bgPanel.add(clearBtn);
        bgPanel.add(homeBtn);

        // Button actions
        addBtn.addActionListener(e -> {
            try {
                Connection con = MySQLConnection.DBConn();

                // Create table if not exists
                Statement st = con.createStatement();
                st.executeUpdate(
                        "CREATE TABLE IF NOT EXISTS itemlist (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "Productname VARCHAR(255)," +
                        "Description TEXT," +
                        "Quantity INT," +
                        "Price INT)"
                );

                String sql = "INSERT INTO itemlist(Productname, Description, Quantity, Price) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, tfProduct.getText());
                pst.setString(2, tfDescription.getText());
                pst.setString(3, tfQuantity.getText());
                pst.setString(4, tfPrice.getText());
                pst.execute();

                JOptionPane.showMessageDialog(frame, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error adding product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearBtn.addActionListener(e -> {
            tfProduct.setText("");
            tfDescription.setText("");
            tfQuantity.setText("");
            tfPrice.setText("");
        });

        homeBtn.addActionListener(e -> {
            frame.dispose();
            Admin master = new Admin();
            master.Admin();
        });

        frame.add(bgPanel);
        frame.setVisible(true);
    }

    // Helper to style input fields
    private void styleInput(JComponent comp) {
        comp.setBackground(new Color(30, 30, 60));
        comp.setForeground(new Color(0, 255, 200));
        if (comp instanceof JTextField) {
            ((JTextField) comp).setCaretColor(new Color(0, 255, 200));
        } else if (comp instanceof JTextArea) {
            ((JTextArea) comp).setCaretColor(new Color(0, 255, 200));
        }
        comp.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 200), 2));
    }

    // Helper to style buttons
    private void addButtonStyle(JButton btn, int x, int y) {
        btn.setBounds(x, y, 100, 40);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(new Color(0, 255, 200));
        btn.setBackground(new Color(30, 30, 60));
        btn.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 200), 2));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
    }

    public void AddItem() {
        // No-op for compatibility
    }
}
