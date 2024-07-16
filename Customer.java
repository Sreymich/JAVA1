import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomerApp {

    private JFrame mainFrame;
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private List<Customer> customers;
    private int currentCustomerIndex = 0;

    public CustomerApp() {
        createAndShowGUI();
        populateCustomerData();
    }

    private void createAndShowGUI() {
        mainFrame = new JFrame("Customer Information");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);


        String[] columnNames = {"ID", "Last Name", "First Name", "Phone"};
        tableModel = new DefaultTableModel(columnNames, 0);
        customerTable = new JTable(tableModel);


        JScrollPane scrollPane = new JScrollPane(customerTable);


        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");


        mainFrame.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);


        previousButton.addActionListener(this::previousCustomer);
        nextButton.addActionListener(this::nextCustomer);

        mainFrame.setVisible(true);
    }

    private void populateCustomerData() {
        // Replace this with actual data retrieval logic
        customers = new ArrayList<>();
        customers.add(new Customer(1, "Chenda", "Sovisal", "092888999"));
        customers.add(new Customer(2, "Kom", "Lina", "892008999"));
        customers.add(new Customer(3, "Chan", "Seyha", "892777666"));


        for (Customer customer : customers) {
            tableModel.addRow(new Object[]{customer.getId(), customer.getLastName(), customer.getFirstName(), customer.getPhone()});
        }
    }

    private void showCustomerDetails(int index) {
        if (index < 0 || index >= customers.size()) {
            return;
        }

        Customer customer = customers.get(index);
    }

    private void previousCustomer(ActionEvent e) {
        currentCustomerIndex--;
        if (currentCustomerIndex < 0) {
            currentCustomerIndex = 0;
        }
        showCustomerDetails(currentCustomerIndex);
    }

    private void nextCustomer(ActionEvent e) {
        currentCustomerIndex++;
        if (currentCustomerIndex >= customers.size()) {
            currentCustomerIndex = customers.size() - 1;
        }
        showCustomerDetails(currentCustomerIndex);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerApp());
    }

    private static class Customer {
        private int id;
        private String lastName;
        private String firstName;
        private String phone;

    }
}

