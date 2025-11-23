package admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import db_connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Edititem {
    public static void main(String[] args) {
        new Edititem();
    }

    public Edititem() {
        JFrame frame = new JFrame("GoGreen - Edit Item");
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel bgPanel = new JPanel();
        bgPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        bgPanel.setBackground(new Color(18, 18, 30));
        bgPanel.setLayout(null);

        JLabel headerLabel = new JLabel("UPDATE PRODUCT DETAILS", SwingConstants.CENTER);
        headerLabel.setBounds(0, 20, frame.getWidth(), 40);
        headerLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        headerLabel.setForeground(new Color(0, 255, 200));
        bgPanel.add(headerLabel);

        // Labels
        JLabel lblProduct = createLabel("Product Name:", 50, 80);
        JLabel lblDescription = createLabel("Description:", 50, 130);
        JLabel lblQuantity = createLabel("Quantity:", 50, 200);
        JLabel lblPrice = createLabel("Price:", 50, 250);
        JLabel lblProductId = createLabel("Product ID:", 50, 300);

        // Text fields
        JTextField tfProduct = new JTextField();
        JTextArea tfDescription = new JTextArea();
        JTextField tfQuantity = new JTextField();
        JTextField tfPrice = new JTextField();
        JTextField tfProductId = new JTextField();

        styleInput(tfProduct, 160, 80, 200, 30);
        styleInput(tfDescription, 160, 130, 200, 50);
        styleInput(tfQuantity, 160, 200, 200, 30);
        styleInput(tfPrice, 160, 250, 200, 30);
        styleInput(tfProductId, 160, 300, 200, 30);

        bgPanel.add(lblProduct);
        bgPanel.add(lblDescription);
        bgPanel.add(lblQuantity);
        bgPanel.add(lblPrice);
        bgPanel.add(lblProductId);

        bgPanel.add(tfProduct);
        bgPanel.add(tfDescription);
        bgPanel.add(tfQuantity);
        bgPanel.add(tfPrice);
        bgPanel.add(tfProductId);

        // Buttons
        JButton btnUpdate = new JButton("UPDATE");
        JButton btnClear = new JButton("CLEAR");
        JButton btnHome = new JButton("HOME");

        styleButton(btnUpdate, 60, 370);
        styleButton(btnClear, 200, 370);
        styleButton(btnHome, 340, 370);

        bgPanel.add(btnUpdate);
        bgPanel.add(btnClear);
        bgPanel.add(btnHome);

        // Button actions
        btnUpdate.addActionListener(e -> {
            try {
                Connection con = MySQLConnection.DBConn();
                String sql = "UPDATE itemlist SET Productname=?, Description=?, Price=?, Quantity=? WHERE Id=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, tfProduct.getText());
                pst.setString(2, tfDescription.getText());
                pst.setString(3, tfPrice.getText());
                pst.setString(4, tfQuantity.getText());
                pst.setString(5, tfProductId.getText());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error updating product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnClear.addActionListener(e -> {
            tfProduct.setText("");
            tfDescription.setText("");
            tfQuantity.setText("");
            tfPrice.setText("");
            tfProductId.setText("");
        });

        btnHome.addActionListener(e -> {
            frame.dispose();
            Admin master = new Admin();
            master.Admin();
        });

        frame.add(bgPanel);
        frame.setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 120, 30);
        label.setFont(new Font("Verdana", Font.BOLD, 15));
        label.setForeground(new Color(0, 255, 200));
        return label;
    }

    private void styleInput(JComponent comp, int x, int y, int w, int h) {
        comp.setBounds(x, y, w, h);
        comp.setBackground(new Color(30, 30, 60));
        comp.setForeground(new Color(0, 255, 200));
        comp.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 200), 2));
        if (comp instanceof JTextField) {
            ((JTextField) comp).setCaretColor(new Color(0, 255, 200));
        } else if (comp instanceof JTextArea) {
            ((JTextArea) comp).setCaretColor(new Color(0, 255, 200));
        }
    }

    private void styleButton(JButton btn, int x, int y) {
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

    public void Edititem() {
        // No-op for compatibility
    }
}
