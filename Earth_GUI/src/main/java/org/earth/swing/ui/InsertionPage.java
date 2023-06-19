/*InsertionPage
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */

package org.earth.swing.ui;
import lombok.extern.java.Log;
import org.earth.database.WorldDBUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This is a class that represents Insertion Page for Earth_GUI
 * The class provides methods for running different insertion operations with the database data
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */

@Log
public class InsertionPage implements ActionListener {
    WorldDBUtils db_Utils;
    
    public void db_setup(){
        db_Utils = new WorldDBUtils();
        db_Utils.init();
    }
    JFrame frame = new JFrame();
    String[] operation_name = {"Select an operation","1) Enter New Continent Details","2) Enter New Country Details","3) Enter New State Details"
            ,"4) Enter New Agriculture Resource Details","5) Enter New Mineral Resource Details","6) Exit from The Menu"} ;
    JComboBox combobox = new JComboBox(operation_name);

    ArrayList<Component> componentsToRemove = new ArrayList<>();
    ImageIcon logo = new ImageIcon("Earth_GUI/src/main/resources/earth_icon_1.png");
    public void gui_func() {
        JLabel title = new JLabel("Earth Resource Database : Insertion Operations", JLabel.CENTER);
        title.setFont(new Font("SansSerif",Font.PLAIN,20));
        title.setBounds(250, 25, 500, 50);
        title.setForeground(Color.white);
        frame.add(title);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedOperation = (String) combobox.getSelectedItem();
        if(selectedOperation.equals(operation_name[1])) {
            removeComponents();
            call_insertion_continent();
            log.info(" User Selected 'Enter New Continent Details' ");
        }
        if(selectedOperation.equals(operation_name[2])) {
            removeComponents();
            call_insertion_country();
            log.info(" User Selected 'Enter New Country Details' ");
        }

        if(selectedOperation.equals(operation_name[3])) {
            removeComponents();
            call_insertion_state();
            log.info(" User Selected 'Enter New State Details' ");
        }
        if(selectedOperation.equals(operation_name[4])) {
            removeComponents();
            call_agricultural_resource();
            log.info(" User Selected 'Enter New Agriculture Resource Details' ");
        }

        if(selectedOperation.equals(operation_name[5])) {
            removeComponents();
            call_mineral_resource();
            log.info(" User Selected 'Enter New Mineral Resource Details' ");
        }
        if(selectedOperation.equals(operation_name[6])) {
            Home  h1 = new Home();
            h1.gui_func();
            frame.dispose();
            log.info(" User Selected 'Exit From the Menu' ");
        }
    }


    void removeComponents() {
        for (Component component : componentsToRemove) {
            frame.getContentPane().remove(component);
        }
        componentsToRemove.clear();
    }

    // insert new continent data
    void call_insertion_continent(){

        JLabel continent_id_label = new JLabel("Continent ID :");
        continent_id_label.setBounds(200, 200, 100, 40);
        continent_id_label.setForeground(Color.white);
        frame.add(continent_id_label);
        componentsToRemove.add(continent_id_label);

        JTextField continent_id_text = new JTextField();
        continent_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(continent_id_text);
        componentsToRemove.add(continent_id_text);

        JLabel continent_name_label = new JLabel("Continent Name :");
        continent_name_label.setBounds(200, 300, 100, 40);
        continent_name_label.setForeground(Color.white);
        frame.add(continent_name_label);
        componentsToRemove.add(continent_name_label);

        JTextField continent_name_text = new JTextField();
        continent_name_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(continent_name_text);
        componentsToRemove.add(continent_name_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String continent_id = continent_id_text.getText().toUpperCase();
                String continent_name = continent_name_text.getText();
                continent_name = continent_name.substring(0, 1).toUpperCase() + continent_name.substring(1).toLowerCase();
                db_setup();
                db_Utils.insertContinent01(continent_id,continent_name);

                // perform necessary action with the input data
                JOptionPane.showMessageDialog(frame, "Data submitted: \nContinent ID: " + continent_id + "\nContinent Name: " + continent_name, "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nContinent ID: " + continent_id + "\nContinent Name: " + continent_name+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();

    }

    // insert new country data
    void call_insertion_country(){

        JLabel country_id_label = new JLabel("Country ID :");
        country_id_label.setBounds(200, 200, 100, 40);
        country_id_label.setForeground(Color.white);
        frame.add(country_id_label);
        componentsToRemove.add(country_id_label);

        JTextField country_id_text = new JTextField();
        country_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(country_id_text);
        componentsToRemove.add(country_id_text);

        JLabel country_name_label = new JLabel("Country Name :");
        country_name_label.setBounds(200, 300, 100, 40);
        country_name_label.setForeground(Color.white);
        frame.add(country_name_label);
        componentsToRemove.add(country_name_label);

        JTextField country_name_text = new JTextField();
        country_name_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(country_name_text);
        componentsToRemove.add(country_name_text);

        JLabel continent_id_label = new JLabel("Continent ID :");
        continent_id_label.setBounds(200, 400, 100, 40);
        continent_id_label.setForeground(Color.white);
        frame.add(continent_id_label);
        componentsToRemove.add(continent_id_label);

        JTextField continent_id_text = new JTextField();
        continent_id_text.setBounds(300, 400, 400, 40); // set the position and size of the text field
        frame.add(continent_id_text);
        componentsToRemove.add(continent_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,500,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String country_id = country_id_text.getText().toUpperCase();
                String country_name = country_name_text.getText();
                country_name = country_name.substring(0, 1).toUpperCase() + country_name.substring(1).toLowerCase();
                String continent_id = continent_id_text.getText().toUpperCase();
                db_setup();
                db_Utils.insertCountry01(country_id,country_name,continent_id);

                JOptionPane.showMessageDialog(frame, "Data submitted: \nCountry ID: " +country_id + "\nCountry Name: " + country_name + "\nContinent id: "+continent_id, "Submission Successful", JOptionPane.INFORMATION_MESSAGE );
                log.info("Data submitted: \nCountry ID: " +country_id + "\nCountry Name: " + country_name + "\nContinent id: "+continent_id+ "\nSubmission Successful");
            }
        });

        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // insert new state data
    void call_insertion_state(){

        JLabel state_id_label = new JLabel("State ID :");
        state_id_label.setBounds(200, 200, 100, 40);
        state_id_label.setForeground(Color.white);
        frame.add(state_id_label);
        componentsToRemove.add(state_id_label);

        JTextField state_id_text = new JTextField();
        state_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(state_id_text);
        componentsToRemove.add(state_id_text);

        JLabel state_name_label = new JLabel("State Name :");
        state_name_label.setBounds(200, 300, 100, 40);
        state_name_label.setForeground(Color.white);
        frame.add(state_name_label);
        componentsToRemove.add(state_name_label);

        JTextField state_name_text = new JTextField();
        state_name_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(state_name_text);
        componentsToRemove.add(state_name_text);

        JLabel country_id_label = new JLabel("Country ID :");
        country_id_label.setBounds(200, 400, 100, 40);
        country_id_label.setForeground(Color.white);
        frame.add(country_id_label);
        componentsToRemove.add(country_id_label);

        JTextField country_id_text = new JTextField();
        country_id_text.setBounds(300, 400, 400, 40); // set the position and size of the text field
        frame.add(country_id_text);
        componentsToRemove.add(country_id_text);


        JLabel population_label = new JLabel("Population :");
        population_label.setBounds(200, 500, 100, 40);
        population_label.setForeground(Color.white);
        frame.add(population_label);
        componentsToRemove.add(population_label);


        JTextField population_text = new JTextField();
        population_text.setBounds(300, 500, 400, 40); // set the position and size of the text field
        frame.add(population_text);
        componentsToRemove.add(population_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,600,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String state_id = state_id_text.getText().toUpperCase();
                String state_name = state_name_text.getText();
                state_name = state_name.substring(0, 1).toUpperCase() + state_name.substring(1).toLowerCase();
                String country_id = country_id_text.getText().toUpperCase();
                int population = Integer.parseInt(population_text.getText());
                db_setup();
                db_Utils.insertState01(state_id,state_name,country_id,population);

                JOptionPane.showMessageDialog(frame, "Data submitted: \nState ID: " +state_id + "\nState Name: " + state_name +
                        "\nCountry id: "+country_id+ "\nPopulation: "+population,"Submission Successful", JOptionPane.INFORMATION_MESSAGE );
                log.info("Data submitted: \nState ID: " +state_id + "\nState Name: " + state_name +
                        "\nCountry id: "+country_id+ "\nPopulation: "+population+"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);

        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }


    // insert new agriculture resource data
    void call_agricultural_resource(){

        JLabel agri_resource_name_label = new JLabel("Resource Name :");
        agri_resource_name_label.setBounds(200, 200, 100, 40);
        agri_resource_name_label.setForeground(Color.white);
        frame.add(agri_resource_name_label);
        componentsToRemove.add(agri_resource_name_label);

        JTextField agri_resource_name_text = new JTextField();
        agri_resource_name_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(agri_resource_name_text);
        componentsToRemove.add(agri_resource_name_text);

        JLabel amount_label = new JLabel("Amount :");
        amount_label.setBounds(200, 300, 100, 40);
        amount_label.setForeground(Color.white);
        frame.add(amount_label);
        componentsToRemove.add(amount_label);

        JTextField amount_text = new JTextField();
        amount_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(amount_text);
        componentsToRemove.add(amount_text);

        JLabel agri_resource_category_label = new JLabel("Category :");
        agri_resource_category_label.setBounds(200, 400, 150, 40);
        agri_resource_category_label.setForeground(Color.white);
        frame.add(agri_resource_category_label);
        componentsToRemove.add(agri_resource_category_label);

        JTextField agri_resource_category_text = new JTextField();
        agri_resource_category_text.setBounds(300, 400, 400, 40); // set the position and size of the text field
        frame.add(agri_resource_category_text);
        componentsToRemove.add(agri_resource_category_text);


        JLabel state_id_label = new JLabel("State ID :");
        state_id_label.setBounds(200, 500, 100, 40);
        state_id_label.setForeground(Color.white);
        frame.add(state_id_label);
        componentsToRemove.add(state_id_label);

        JTextField state_id_text = new JTextField();
        state_id_text.setBounds(300, 500, 400, 40); // set the position and size of the text field
        frame.add(state_id_text);
        componentsToRemove.add(state_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,600,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String agri_resource_name = agri_resource_name_text.getText();
                agri_resource_name = agri_resource_name.substring(0, 1).toUpperCase() + agri_resource_name.substring(1).toLowerCase();
                double amount = Double.parseDouble(amount_text.getText());
                String agri_resource_category= agri_resource_category_text.getText();
                agri_resource_category = agri_resource_category.substring(0, 1).toUpperCase() + agri_resource_category.substring(1).toLowerCase();
                String state_id= state_id_text.getText().toUpperCase();
                db_setup();
                db_Utils.insertAgriculturalResource01(agri_resource_name,amount,agri_resource_category,state_id);

                JOptionPane.showMessageDialog(frame, "Data submitted: \nAgriculture Resource Name: " +agri_resource_name + "\nAmount: "
                        + amount+ "\nAgriculture Resource Category: "+agri_resource_category + "\nState ID: "+state_id, "Submission Successful", JOptionPane.INFORMATION_MESSAGE );
                log.info("Data submitted: \nAgriculture Resource Name: " +agri_resource_name + "\nAmount: "
                        + amount+ "\nAgriculture Resource Category: "+agri_resource_category + "\nState ID: "+state_id+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);

        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // insert new mineral resource data
    void call_mineral_resource(){

        JLabel mineral_resource_name_label = new JLabel("Resource Name :");
        mineral_resource_name_label.setBounds(200, 200, 100, 40);
        mineral_resource_name_label.setForeground(Color.white);
        frame.add(mineral_resource_name_label);
        componentsToRemove.add(mineral_resource_name_label);

        JTextField mineral_resource_name_text = new JTextField();
        mineral_resource_name_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(mineral_resource_name_text);
        componentsToRemove.add(mineral_resource_name_text);

        JLabel amount_label = new JLabel("Amount :");
        amount_label.setBounds(200, 300, 100, 40);
        amount_label.setForeground(Color.white);
        frame.add(amount_label);
        componentsToRemove.add(amount_label);

        JTextField amount_text = new JTextField();
        amount_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(amount_text);
        componentsToRemove.add(amount_text);

        JLabel mineral_resource_reserve_label = new JLabel("Reserve :");
        mineral_resource_reserve_label.setBounds(200, 400, 150, 40);
        mineral_resource_reserve_label.setForeground(Color.white);
        frame.add(mineral_resource_reserve_label);
        componentsToRemove.add(mineral_resource_reserve_label);

        JTextField mineral_resource_reserve_text = new JTextField();
        mineral_resource_reserve_text.setBounds(300, 400, 400, 40); // set the position and size of the text field
        frame.add(mineral_resource_reserve_text);
        componentsToRemove.add(mineral_resource_reserve_text);


        JLabel state_id_label = new JLabel("State ID :");
        state_id_label.setBounds(200, 500, 100, 40);
        state_id_label.setForeground(Color.white);
        frame.add(state_id_label);
        componentsToRemove.add(state_id_label);

        JTextField state_id_text = new JTextField();
        state_id_text.setBounds(300, 500, 400, 40); // set the position and size of the text field
        frame.add(state_id_text);
        componentsToRemove.add(state_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,600,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String mineral_resource_name = mineral_resource_name_text.getText();
                mineral_resource_name = mineral_resource_name.substring(0, 1).toUpperCase() + mineral_resource_name.substring(1).toLowerCase();
                double amount = Double.parseDouble(amount_text.getText());
                double reserve= Double.parseDouble(mineral_resource_reserve_text.getText());
                String state_id= state_id_text.getText().toUpperCase();
                db_setup();
                db_Utils.insertMineralResource01(mineral_resource_name,amount,reserve,state_id);

                JOptionPane.showMessageDialog(frame, "Data submitted: \nMineral Resource Name: " +mineral_resource_name + "\nAmount: "
                        + amount+ "\n Mineral Reserve: "+reserve + "\nState ID: "+state_id, "Submission Successful", JOptionPane.INFORMATION_MESSAGE  );
                log.info("Data submitted: \nMineral Resource Name: " +mineral_resource_name + "\nAmount: "
                        + amount+ "\n Mineral Reserve: "+reserve + "\nState ID: "+state_id+"\nSubmission Successful");
            }
        });

        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

}
