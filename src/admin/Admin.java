package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Login.MainScreen;

public class Admin {
    public static void main(String[] args) {
        new Master();
    }

    public void Admin() {
        new Master();
    }
}

class Master {
    JFrame frame = new JFrame("GoGreen - Admin Dashboard");

    JButton addButton = new JButton("ADD ITEM");
    JButton editButton = new JButton("EDIT ITEM");
    JButton viewButton = new JButton("VIEW ITEMS");
    JButton deleteButton = new JButton("DELETE ITEM");
    JButton ordersButton = new JButton("VIEW ORDERS");
    JButton logoutButton = new JButton("LOGOUT");

    Master() {
        initUI();
        configureButtons();
    }

    private void initUI() {
        // Frame setup
        frame.setSize(480, 520);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        // Background panel (dark theme)
        JPanel bgPanel = new JPanel();
        bgPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        bgPanel.setBackground(new Color(18, 18, 30));
        bgPanel.setLayout(null);

        // Header label
        JLabel headerLabel = new JLabel("GREEN ENERGY LTD", SwingConstants.CENTER);
        headerLabel.setBounds(0, 20, frame.getWidth(), 40);
        headerLabel.setFont(new Font("Arial Black", Font.BOLD, 22));
        headerLabel.setForeground(new Color(0, 255, 200));
        bgPanel.add(headerLabel);

        // Glowing neon-style buttons
        JButton[] buttons = {addButton, viewButton, editButton, deleteButton, ordersButton, logoutButton};
        String[] btnTexts = {"ADD ITEM", "VIEW ITEMS", "EDIT ITEM", "DELETE ITEM", "VIEW ORDERS", "LOGOUT"};

        int btnWidth = 220, btnHeight = 50;
        int startY = 90, spacing = 60;

        for (int i = 0; i < buttons.length; i++) {
            JButton btn = buttons[i];
            btn.setBounds((frame.getWidth() - btnWidth) / 2, startY + spacing * i, btnWidth, btnHeight);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
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

        frame.add(bgPanel);
        frame.setVisible(true);
    }

    private void configureButtons() {
        addButton.addActionListener(e -> {
            frame.dispose();
            AddItem ai = new AddItem();
            ai.AddItem();
        });

        editButton.addActionListener(e -> {
            frame.dispose();
            Edititem ei = new Edititem();
            ei.Edititem();
        });

        viewButton.addActionListener(e -> {
            frame.dispose();
            View v = new View();
            v.View11();
        });

        deleteButton.addActionListener(e -> {
            frame.dispose();
            Deleteitem di = new Deleteitem();
            di.Deleteitem();
        });

        ordersButton.addActionListener(e -> {
            frame.dispose();
            Orders o = new Orders();
            o.View22();
        });

        logoutButton.addActionListener(e -> {
            frame.dispose();
            MainScreen ms = new MainScreen();
            ms.MainScreen();
        });
    }
}
