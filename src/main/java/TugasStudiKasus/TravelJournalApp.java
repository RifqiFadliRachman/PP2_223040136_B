/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TugasStudiKasus;

/**
 *
 * @author USER
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.table.DefaultTableModel;

public class TravelJournalApp extends JFrame {
    
    public TravelJournalApp() {
        setTitle("Travel Journal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // Menus
        JMenu menuFile = new JMenu("File");
        JMenu menuEntry = new JMenu("New Entry");
        JMenu menuView = new JMenu("View Entries");

        // Menu items
        JMenuItem menuItemNewJourney = new JMenuItem("Add New Journey");
        JMenuItem menuItemNewPlace = new JMenuItem("Add New Place");
        JMenuItem menuItemNewExperience = new JMenuItem("Add Experience");
        JMenuItem menuItemViewJourneys = new JMenuItem("View Journeys");

        // Adding menu items to menus
        menuEntry.add(menuItemNewJourney);
        menuEntry.add(menuItemNewPlace);
        menuEntry.add(menuItemNewExperience);
        menuView.add(menuItemViewJourneys);

        // Adding menus to menu bar
        menuBar.add(menuFile);
        menuBar.add(menuEntry);
        menuBar.add(menuView);

        setJMenuBar(menuBar);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to Travel Journal", JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);
        add(mainPanel);

        // Event listeners
        menuItemNewJourney.addActionListener(e -> openJourneyForm());
        menuItemNewPlace.addActionListener(e -> openPlaceForm());
        menuItemNewExperience.addActionListener(e -> openExperienceForm());
        menuItemViewJourneys.addActionListener(e -> viewJourneys());
    }

    // Method to save data to a file
    private void saveToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Open form to add a new journey
    private void openJourneyForm() {
        JFrame journeyFrame = new JFrame("Add New Journey");
        journeyFrame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Destination:"));
        JTextField txtDestination = new JTextField();
        panel.add(txtDestination);

        panel.add(new JLabel("Start Date:"));
        JTextField txtStartDate = new JTextField();
        panel.add(txtStartDate);

        panel.add(new JLabel("End Date:"));
        JTextField txtEndDate = new JTextField();
        panel.add(txtEndDate);

        panel.add(new JLabel("Activities:"));
        JTextArea txtActivities = new JTextArea();
        panel.add(txtActivities);

        JButton btnSave = new JButton("Save");
        panel.add(btnSave);

        journeyFrame.add(panel);
        journeyFrame.setVisible(true);

        // Save button action
        btnSave.addActionListener(e -> {
            String data = txtDestination.getText() + "," + txtStartDate.getText() + "," +
                          txtEndDate.getText() + "," + txtActivities.getText();
            saveToFile("journeys.txt", data);
            JOptionPane.showMessageDialog(journeyFrame, "Journey Saved!");
            journeyFrame.dispose();
        });
    }

    // Open form to add a new place visited
    private void openPlaceForm() {
        JFrame placeFrame = new JFrame("Add New Place");
        placeFrame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Place Name:"));
        JTextField txtPlaceName = new JTextField();
        panel.add(txtPlaceName);

        panel.add(new JLabel("Location:"));
        JTextField txtLocation = new JTextField();
        panel.add(txtLocation);

        panel.add(new JLabel("Description:"));
        JTextArea txtDescription = new JTextArea();
        panel.add(txtDescription);

        JButton btnSave = new JButton("Save");
        panel.add(btnSave);

        placeFrame.add(panel);
        placeFrame.setVisible(true);

        // Save button action
        btnSave.addActionListener(e -> {
            String data = txtPlaceName.getText() + "," + txtLocation.getText() + "," + txtDescription.getText();
            saveToFile("places.txt", data);
            JOptionPane.showMessageDialog(placeFrame, "Place Saved!");
            placeFrame.dispose();
        });
    }

    // Open form to add a new experience
    private void openExperienceForm() {
        JFrame experienceFrame = new JFrame("Add Experience");
        experienceFrame.setSize(400, 300);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Journey Name:"));
        JTextField txtJourneyName = new JTextField();
        panel.add(txtJourneyName);

        panel.add(new JLabel("Experience Details:"));
        JTextArea txtExperienceDetails = new JTextArea();
        panel.add(txtExperienceDetails);

        JButton btnSave = new JButton("Save");
        panel.add(btnSave);

        experienceFrame.add(panel);
        experienceFrame.setVisible(true);

        // Save button action
        btnSave.addActionListener(e -> {
            String data = txtJourneyName.getText() + "," + txtExperienceDetails.getText();
            saveToFile("experiences.txt", data);
            JOptionPane.showMessageDialog(experienceFrame, "Experience Saved!");
            experienceFrame.dispose();
        });
    }

    // View saved journeys in a table
    private void viewJourneys() {
        JFrame viewFrame = new JFrame("View Journeys");
        viewFrame.setSize(500, 300);

        String[] columns = {"Destination", "Start Date", "End Date", "Activities"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        try (BufferedReader reader = new BufferedReader(new FileReader("journeys.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        viewFrame.add(scrollPane);
        viewFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TravelJournalApp().setVisible(true);
        });
    }
}

