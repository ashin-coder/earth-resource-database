/*ViewPage
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */

package org.earth.swing.ui;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.earth.database.WorldDBUtils;
import org.earth.database.WorldQueriesFunc;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that represents View Page for Earth_GUI
 * The class provides methods for running different view operations with the database data
 * also each event and action performed by the user are logged into an external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */

@Log
public class ViewPage implements ActionListener {
    WorldDBUtils dbUtils = new WorldDBUtils();
    WorldQueriesFunc w1 = new WorldQueriesFunc();
    JFrame frame = new JFrame();
    String[] operation_name = {"Select an operation","1) View the Tables present in the Earth Database","2) View Data from Tables present in Earth Database","3) View Population of a Country (Based on State Population Entered)"
            ,"4) View the number of States producing a given Agricultural Resource","5) View the number of States producing a given Mineral Resource","6) Exit from the Menu"} ;
    JComboBox combobox = new JComboBox(operation_name);
    ArrayList<Component> componentsToRemove = new ArrayList<>();

    public void gui_func() {

        JLabel title = new JLabel("Earth Resource Database : View Data Operations", JLabel.CENTER);
        title.setFont(new Font("SansSerif",Font.PLAIN,20));
        title.setBounds(250, 25, 500, 50);
        title.setForeground(Color.white);
        frame.add(title);
        ImageIcon logo = new ImageIcon("Earth_GUI/src/main/resources/earth_icon_1.png");

        frame.add(combobox);
        combobox.setBounds(300,100,400,40);
        combobox.addActionListener(this::actionPerformed);

        frame.setTitle("Earth Resource Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(1000,800);
        frame.setVisible(true);
        ImageIcon logo2 =  new ImageIcon("Earth_GUI/src/main/resources/earth_icon_2.png");
        frame.setIconImage(logo2.getImage());
        frame.getContentPane().setBackground(new Color(17, 66, 117, 234));
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e)  {
        String selectedOperation = (String) combobox.getSelectedItem();
        if(selectedOperation.equals(operation_name[1])) {
            removeComponents();
            call_view_table();
            log.info(" User Selected 'View The Tables present in the Earth Database' ");
        }
        if(selectedOperation.equals(operation_name[2])) {
            removeComponents();
            call_view_table_data();
            log.info(" User Selected 'View Data from Tables present in the Earth Database' ");
        }

        if(selectedOperation.equals(operation_name[3])) {
            removeComponents();
            call_view_population();
            log.info(" User Selected 'View Population of a Country (Based on State Population Entered)' ");
        }
        if(selectedOperation.equals(operation_name[4])) {
            removeComponents();
            call_view_count_agriculture();
            log.info(" User Selected 'View no of States producing a given Agriculture Resource' ");
        }

        if(selectedOperation.equals(operation_name[5])) {
            removeComponents();
            call_view_count_mineral();
            log.info(" User Selected 'View no of States producing a given Mineral Resource' ");

        }
        if(selectedOperation.equals(operation_name[6])) {
            Home  h1 = new Home();
            h1.gui_func();
            frame.dispose();
            log.info(" User Selected 'Exit from the Menu' ");
        }
    }
    void removeComponents() {
        for (Component component : componentsToRemove) {
            frame.getContentPane().remove(component);
        }
        componentsToRemove.clear();
    }

    // view the tables present in the database
    void call_view_table() throws SQLException, IOException {
        try {
            Object[] result = w1.show_tables();
            String[][] tableData = (String[][]) result[0];
            String[] columnNames = (String[]) result[1];
            JTable table = new JTable(tableData, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBounds(125, 250, 750, 450);
            frame.add(scrollPane);
            componentsToRemove.add(scrollPane);
            log.info(" Displays all the Tables present in the Earth Resource Database");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // view data from different tables present in the database
    void call_view_table_data(){

        JLabel table_name_label = new JLabel("Table Name :");
        table_name_label.setBounds(200, 150, 100, 40);
        table_name_label.setForeground(Color.white);
        frame.add(table_name_label);
        componentsToRemove.add(table_name_label);

        JTextField table_name_text = new JTextField();
        table_name_text.setBounds(300, 150, 400, 40); // set the position and size of the text field
        frame.add(table_name_text);
        componentsToRemove.add(table_name_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,200,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                String table_name = table_name_text.getText();
                table_name = table_name.substring(0, 1).toUpperCase() +table_name.substring(1).toLowerCase();

                // Get the table data
                TableModel tableModel = w1.show_table_data(table_name);
                int rowCount = tableModel.getRowCount();
                int columnCount = tableModel.getColumnCount();
                String[][] table_data = new String[rowCount][columnCount];
                String[] column_names = new String[columnCount];
                for (int col = 0; col < columnCount; col++) {
                    column_names[col] = tableModel.getColumnName(col);
                }
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < columnCount; col++) {
                        table_data[row][col] = tableModel.getValueAt(row, col).toString();
                    }
                }

                // Create a new table model and populate it with data
                DefaultTableModel table_model = new DefaultTableModel(table_data, column_names);

                // Create a new JTable object with the table model
                JTable table = new JTable(table_model);

                // Remove the old scroll pane (if it exists)
                Component[] components = frame.getContentPane().getComponents();
                for (Component component : components) {
                    if (component instanceof JScrollPane) {
                        frame.remove(component);
                    }
                }

                // Add the table to a new scroll pane
                JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                scrollPane.setBounds(125, 250, 750, 450);
                frame.add(scrollPane);
                componentsToRemove.add(scrollPane);

                // Perform necessary action with the input data
                JOptionPane.showMessageDialog(frame, "Data submitted: \nTable Name: " + table_name, "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nTable Name: " + table_name +"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // view the population of countries based states present in countries
    void call_view_population(){

        JLabel country_name_label = new JLabel("Country Name :");
        country_name_label.setBounds(200, 150, 100, 40);
        country_name_label.setForeground(Color.white);
        frame.add(country_name_label);
        componentsToRemove.add(country_name_label);

        JTextField country_name_text = new JTextField();
        country_name_text.setBounds(300, 150, 400, 40); // set the position and size of the text field
        frame.add(country_name_text);
        componentsToRemove.add(country_name_text);

        JLabel  population_value_label = new JLabel();
        population_value_label.setBounds(100,400,800,40);
        population_value_label.setForeground(Color.white);
        population_value_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        frame.add(population_value_label);
        componentsToRemove.add(population_value_label);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,200,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String country_name = country_name_text.getText();
                country_name = country_name.substring(0, 1).toUpperCase() + country_name.substring(1).toLowerCase();
                dbUtils.init();
                List<String> sum = dbUtils.select_population(country_name);
                // Hide the JLabel temporarily to prevent it from overlapping with the dialog
                population_value_label.setVisible(false);

                JOptionPane.showMessageDialog(frame, "Data submitted: \nCountry Name: " + country_name, "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nCountry Name: " + country_name+ "\nSubmission Successful");
                // Update JLabel and add it back to the container
                population_value_label.setText("The Total Population of "+country_name+ " based on the no of States Entered is currently : " + sum.get(1));
                componentsToRemove.add(population_value_label);
                population_value_label.setVisible(true);

                // Force the container to revalidate and repaint to ensure proper layout
                frame.revalidate();
                frame.repaint();
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();

    }

    // view no of states in a country producing specific agriculture resource
    void call_view_count_agriculture(){

        JLabel agri_resource_name_label = new JLabel("Agriculture Resource Name :");
        agri_resource_name_label.setBounds(130, 150, 200, 40);
        agri_resource_name_label.setForeground(Color.white);
        frame.add(agri_resource_name_label);
        componentsToRemove.add(agri_resource_name_label);

        JTextField agri_resource_text = new JTextField();
        agri_resource_text.setBounds(300, 150, 400, 40); // set the position and size of the text field
        frame.add(agri_resource_text);
        componentsToRemove.add(agri_resource_text);

        JLabel country_name_label = new JLabel("Country Name :");
        country_name_label.setBounds(200, 200, 180, 40);
        country_name_label.setForeground(Color.white);
        frame.add(country_name_label);
        componentsToRemove.add(country_name_label);

        JTextField country_name_text = new JTextField();
        country_name_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(country_name_text);
        componentsToRemove.add(country_name_text);


        JLabel agri_resource_count_label = new JLabel();
        agri_resource_count_label.setBounds(100,400,800,40);
        agri_resource_count_label.setForeground(Color.white);
        agri_resource_count_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        componentsToRemove.add(agri_resource_count_label);
        frame.add(agri_resource_count_label);
        componentsToRemove.add(agri_resource_count_label);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,250,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String agri_resource_name = agri_resource_text.getText();
                agri_resource_name = agri_resource_name.substring(0, 1).toUpperCase() +agri_resource_name.substring(1).toLowerCase();
                String country_name = country_name_text.getText();
                country_name = country_name.substring(0, 1).toUpperCase() + country_name.substring(1).toLowerCase();
                dbUtils.init();
                List<String> count_agri = dbUtils.count_agri_resource(agri_resource_name,country_name);
                agri_resource_count_label.setVisible(false);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nCountry Name: " + country_name, "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nCountry Name: " + country_name+ "\nSubmission Successful");
                // Update JLabel and add it back to the container
                agri_resource_count_label.setText("No of States in "+country_name+" producing "+agri_resource_name+" : "+count_agri.get(1));
                componentsToRemove.add(agri_resource_count_label);
                agri_resource_count_label.setVisible(true);
                // Force the container to revalidate and repaint to ensure proper layout
                frame.revalidate();
                frame.repaint();
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();

    }

    // view no of states in a country producing specific mineral resource
    void call_view_count_mineral(){

        JLabel mineral_resource_name_label = new JLabel("Mineral Resource Name :");
        mineral_resource_name_label.setBounds(150, 150, 200, 40);
        mineral_resource_name_label.setForeground(Color.white);
        frame.add(mineral_resource_name_label);
        componentsToRemove.add(mineral_resource_name_label);

        JTextField mineral_resource_text = new JTextField();
        mineral_resource_text.setBounds(300, 150, 400, 40); // set the position and size of the text field
        frame.add(mineral_resource_text);
        componentsToRemove.add(mineral_resource_text);

        JLabel country_name_label = new JLabel("Country Name :");
        country_name_label.setBounds(200, 200, 180, 40);
        country_name_label.setForeground(Color.white);
        frame.add(country_name_label);
        componentsToRemove.add(country_name_label);

        JTextField country_name_text = new JTextField();
        country_name_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(country_name_text);
        componentsToRemove.add(country_name_text);


        JLabel  mineral_resource_count_label = new JLabel();
        mineral_resource_count_label.setBounds(300,400,800,40);
        mineral_resource_count_label.setForeground(Color.white);
        mineral_resource_count_label.setFont(new Font("SansSerif", Font.PLAIN, 20));
        componentsToRemove.add(mineral_resource_count_label);
        frame.add(mineral_resource_count_label);
        componentsToRemove.add(mineral_resource_count_label);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,250,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mineral_resource_name = mineral_resource_text.getText();
                mineral_resource_name = mineral_resource_name.substring(0, 1).toUpperCase() +mineral_resource_name.substring(1).toLowerCase();
                String country_name = country_name_text.getText();
                country_name = country_name.substring(0, 1).toUpperCase() + country_name.substring(1).toLowerCase();
                mineral_resource_count_label.setVisible(false);
                dbUtils.init();
                List<String> count_mineral = dbUtils.count_mineral_resource(mineral_resource_name,country_name);
                // perform necessary action with the input data
                JOptionPane.showMessageDialog(frame, "Data submitted:"+"\nMineral Resource Name: "+mineral_resource_name+"\nCountry Name: " + country_name , "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted:"+"\nMineral Resource Name: "+mineral_resource_name+"\nCountry Name: " + country_name +"\nSubmission Successful");
                // Update JLabel and add it back to the container
                mineral_resource_count_label.setText("No of States in "+country_name+" producing "+mineral_resource_name+" : "+count_mineral.get(1));
                mineral_resource_count_label.setVisible(true);
                componentsToRemove.add(mineral_resource_count_label);
                // Force the container to revalidate and repaint to ensure proper layout
                frame.revalidate();
                frame.repaint();
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();

    }
}
