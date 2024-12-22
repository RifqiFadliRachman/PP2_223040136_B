import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class OrderForm extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtID, txtCustomerName, txtFoodName, txtQuantity, txtStatus, txtDate;
    private JButton btnInsert, btnUpdate, btnDelete, btnSelect;

    public OrderForm() {
        setTitle("Sistem Pengelolaan Pesanan Makanan");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Nama Pelanggan", "Nama Makanan", "Jumlah", "Status", "Tanggal"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Form Inputs
        JPanel panel = new JPanel(new GridLayout(2, 6, 5, 5));
        txtID = new JTextField();
        txtCustomerName = new JTextField();
        txtFoodName = new JTextField();
        txtQuantity = new JTextField();
        txtStatus = new JTextField();
        txtDate = new JTextField();
        panel.add(new JLabel("ID Pesanan"));
        panel.add(txtID);
        panel.add(new JLabel("Nama Pelanggan"));
        panel.add(txtCustomerName);
        panel.add(new JLabel("Nama Makanan"));
        panel.add(txtFoodName);
        panel.add(new JLabel("Jumlah"));
        panel.add(txtQuantity);
        panel.add(new JLabel("Status"));
        panel.add(txtStatus);
        panel.add(new JLabel("Tanggal (YYYY-MM-DD)"));
        panel.add(txtDate);
        add(panel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel();
        btnInsert = new JButton("Tambah");
        btnUpdate = new JButton("Ubah");
        btnDelete = new JButton("Hapus");
        btnSelect = new JButton("Tampilkan");
        buttonPanel.add(btnInsert);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSelect);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        btnInsert.addActionListener(e -> insertOrder());
        btnUpdate.addActionListener(e -> updateOrder());
        btnDelete.addActionListener(e -> deleteOrder());
        btnSelect.addActionListener(e -> selectOrders());

        setVisible(true);
    }

    private void insertOrder() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Orders (customer_name, food_name, quantity, order_status, order_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtCustomerName.getText());
            stmt.setString(2, txtFoodName.getText());
            stmt.setInt(3, Integer.parseInt(txtQuantity.getText()));
            stmt.setString(4, txtStatus.getText());
            stmt.setDate(5, Date.valueOf(txtDate.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pesanan berhasil ditambahkan!");
            selectOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateOrder() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE Orders SET customer_name = ?, food_name = ?, quantity = ?, order_status = ?, order_date = ? WHERE order_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtCustomerName.getText());
            stmt.setString(2, txtFoodName.getText());
            stmt.setInt(3, Integer.parseInt(txtQuantity.getText()));
            stmt.setString(4, txtStatus.getText());
            stmt.setDate(5, Date.valueOf(txtDate.getText()));
            stmt.setInt(6, Integer.parseInt(txtID.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pesanan berhasil diubah!");
            selectOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteOrder() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM Orders WHERE order_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(txtID.getText()));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Pesanan berhasil dihapus!");
            selectOrders();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void selectOrders() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Orders";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            model.setRowCount(0); // Clear existing data
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("order_id"),
                        rs.getString("customer_name"),
                        rs.getString("food_name"),
                        rs.getInt("quantity"),
                        rs.getString("order_status"),
                        rs.getDate("order_date")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new OrderForm();
    }
}
