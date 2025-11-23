package admin;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db_connection.MySQLConnection;

public class Orders {

    // Custom cell renderer for wrapping text in JTable
    static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
            setFont(new Font("Verdana", Font.PLAIN, 12));
            setForeground(Color.WHITE);
            setBackground(new Color(50, 50, 50));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            if (isSelected) {
                setBackground(new Color(0, 153, 153));
                setForeground(Color.WHITE);
            } else {
                setBackground(row % 2 == 0 ? new Color(45, 45, 45) : new Color(60, 60, 60));
                setForeground(Color.WHITE);
            }
            return this;
        }
    }

    // Dynamically adjust row heights based on content
    private static void adjustRowHeights(JTable table) {
        for (int row = 0; row < table.getRowCount(); row++) {
            int maxHeight = 0;
            for (int column = 0; column < table.getColumnCount(); column++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                int height = comp.getPreferredSize().height;
                maxHeight = Math.max(height, maxHeight);
            }
            if (table.getRowHeight(row) != maxHeight) {
                table.setRowHeight(row, maxHeight);
            }
        }
    }

    public static void View22() {
        JFrame frame = new JFrame("GoGreen - Orders");
        frame.setSize(750, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(34, 34, 34));
        frame.setLayout(new BorderLayout(10, 10));

        // Header
        JLabel header = new JLabel("ORDERS LIST", SwingConstants.CENTER);
        header.setFont(new Font("Verdana", Font.BOLD, 24));
        header.setForeground(new Color(0, 204, 204));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(header, BorderLayout.NORTH);

        // Table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Payment Date");
        model.addColumn("Product Name");
        model.addColumn("Price");
        model.addColumn("Quantity");
        model.addColumn("Order ID");
        model.addColumn("Total Price");

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Table header
        table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(0, 102, 102));
        table.getTableHeader().setForeground(Color.WHITE);

        // Apply text area renderer to all columns
        TextAreaRenderer textRenderer = new TextAreaRenderer();
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(textRenderer);
        }

        // Set column widths
        int[] widths = {40, 110, 200, 80, 80, 150, 100};
        for (int i = 0; i < widths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 204, 204), 2));
        scrollPane.getViewport().setBackground(new Color(34, 34, 34));
        scrollPane.setBackground(new Color(34, 34, 34));
        frame.add(scrollPane, BorderLayout.CENTER);

        // Load data from database
        try (Connection con = MySQLConnection.DBConn();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM orderedlist")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("id"),
                        rs.getString("PaymentDate"),
                        rs.getString("ProductName"),
                        rs.getString("Price"),
                        rs.getString("Quantity"),
                        rs.getString("OrderId"),
                        rs.getString("TotalPrice")
                });
            }
            // Adjust row heights dynamically after all rows are added
            adjustRowHeights(table);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error loading orders: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Home Button
        JButton btnHome = new JButton("HOME");
        btnHome.setPreferredSize(new Dimension(120, 35));
        btnHome.setBackground(new Color(0, 153, 153));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFont(new Font("Verdana", Font.BOLD, 14));
        btnHome.setFocusPainted(false);
        btnHome.addActionListener(e -> {
            frame.dispose();
            Admin master = new Admin();
            master.Admin();
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(34, 34, 34));
        bottomPanel.add(btnHome);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Orders::View22);
    }
}
