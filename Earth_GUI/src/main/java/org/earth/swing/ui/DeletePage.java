/*DeletePage
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
 * This is a class that represents Delete Page for Earth_GUI
 * The class provides methods for running different delete operations with the database data
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */

@Log
public class DeletePage implements ActionListener {
    WorldDBUtils db_Utils;
    public void db_setup(){
        db_Utils = new WorldDBUtils();
        db_Utils.init();
    }

    JFrame frame = new JFrame();
    String[] operation_name = {"Select an operation","1) Delete Row in Continents ","2) Delete Row in Countries ","3) Delete Row in States","4)  Delete Row in Agricultural Resources","5) Delete Row in Mineral Resources","6) Exit to Main Menu"} ;
    JComboBox combobox = new JComboBox(operation_name);

    ArrayList<Component> componentsToRemove = new ArrayList<>();
    ImageIcon logo = new ImageIcon("Earth_GUI/src/main/resources/earth_icon_1.png");
    public void gui_func() {

        JLabel title = new JLabel("Earth Resource Database : Enter Row ID", JLabel.CENTER);
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
            call_delete_continent_row();
            log.info(" User Selected 'Delete Row in Continents'");
        }
        if(selectedOperation.equals(operation_name[2])) {
            removeComponents();
            call_delete_country_row();
            log.info(" User Selected 'Delete Row in Countries'");
        }

        if(selectedOperation.equals(operation_name[3])) {
            removeComponents();
            call_delete_states_row();
            log.info(" User Selected 'Delete Row in States'");
        }

        if(selectedOperation.equals(operation_name[4])) {
            removeComponents();
            call_delete_agri_res_row();
            log.info(" User Selected 'Delete Row in Agriculture Resources'");
        }

        if(selectedOperation.equals(operation_name[5])) {
            removeComponents();
            call_delete_mineral_res_row();
            log.info(" User Selected 'Delete Row in Mineral Resources'");
        }

        if(selectedOperation.equals(operation_name[6])) {
            Home  h1 = new Home();
            h1.gui_func();
            frame.dispose();
            log.info(" User Selected 'Exit from the Menu'");
        }
    }


    void removeComponents() {
        for (Component component : componentsToRemove) {
            frame.getContentPane().remove(component);
        }
        componentsToRemove.clear();
    }

    // delete a row in "continents" table
    void call_delete_continent_row(){

        JLabel continent_id_label = new JLabel("Continent ID :");
        continent_id_label.setBounds(200, 200, 100, 40);
        continent_id_label.setForeground(Color.white);
        frame.add(continent_id_label);
        componentsToRemove.add(continent_id_label);

        JTextField continent_id_text = new JTextField();
        continent_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(continent_id_text);
        componentsToRemove.add(continent_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,300,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String continent_id = continent_id_text.getText().toUpperCase();

                // perform necessary action with the input data
                db_setup();
                db_Utils.delete_continent_rows(continent_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nContinent ID: " + continent_id + "\n Check in Table if Data is Deleted " , "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nContinent ID: " + continent_id + "\n Check in Table if Data is Deleted "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();

    }

    // delete a row in "countries" table
    void call_delete_country_row(){

        JLabel country_id_label = new JLabel("Country ID :");
        country_id_label.setBounds(200, 200, 100, 40);
        country_id_label.setForeground(Color.white);
        frame.add(country_id_label);
        componentsToRemove.add(country_id_label);

        JTextField country_id_text = new JTextField();
        country_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(country_id_text);
        componentsToRemove.add(country_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,300,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String country_id = country_id_text.getText().toUpperCase();

                // perform necessary action with the input data
                db_setup();
                db_Utils.delete_countries_rows(country_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nCountry ID: " + country_id + "\n Check in Table if Data is Deleted " , "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
            log.info("Data submitted: \nCountry ID: " + country_id + "\n Check in Table if Data is Deleted " + "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // delete a row in "states" table
    void call_delete_states_row(){
        JLabel state_id_label = new JLabel(" State ID :");
        state_id_label.setBounds(200, 200, 100, 40);
        state_id_label.setForeground(Color.white);
        frame.add(state_id_label);
        componentsToRemove.add(state_id_label);

        JTextField state_id_text = new JTextField();
        state_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(state_id_text);
        componentsToRemove.add(state_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,300,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String state_id = state_id_text.getText().toUpperCase();

                // perform necessary action with the input data
                db_setup();
                db_Utils.delete_states_rows(state_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nState ID: "  + state_id+ "\n Check in Table if Data is Deleted " , "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nState ID: "  + state_id+ "\n Check in Table if Data is Deleted " +"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // delete a row in "Agricultureresources" table
    void call_delete_agri_res_row() {

        JLabel agri_res_id_label = new JLabel("Agriculture Resource ID :");
        agri_res_id_label.setBounds(140, 200, 180, 40);
        agri_res_id_label.setForeground(Color.white);
        frame.add(agri_res_id_label);
        componentsToRemove.add(agri_res_id_label);

        JTextField agri_res_id_text = new JTextField();
        agri_res_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(agri_res_id_text);
        componentsToRemove.add(agri_res_id_text);
        JButton submit_button = new JButton();
        submit_button.setBounds(400, 300, 200, 40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int agri_res_id = Integer.parseInt(agri_res_id_text.getText().toUpperCase());

                // perform necessary action with the input data
                db_setup();
                db_Utils.delete_agri_res_rows(agri_res_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nAgricultural Resource ID: " + agri_res_id + "\n Check in Table if Data is Deleted ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nAgricultural Resource ID: " + agri_res_id + "\n Check in Table if Data is Deleted "+"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // delete a row in "Mineralresources" table
    private void call_delete_mineral_res_row() {
        JLabel mineral_res_id_label = new JLabel("Mineral Resource ID :");
        mineral_res_id_label.setBounds(140, 200, 180, 40);
        mineral_res_id_label.setForeground(Color.white);
        frame.add(mineral_res_id_label);
        componentsToRemove.add(mineral_res_id_label);

        JTextField mineral_res_id_label_text = new JTextField();
        mineral_res_id_label_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(mineral_res_id_label_text);
        componentsToRemove.add(mineral_res_id_label_text);

        JButton submit_button = new JButton();
        submit_button.setBounds(400, 300, 200, 40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mineral_res_id = Integer.parseInt(mineral_res_id_label_text.getText().toUpperCase());
                // perform necessary action with the input data
                db_setup();
                db_Utils.delete_mineral_res_rows(mineral_res_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nMineral Resource ID: " + mineral_res_id + "\n Check in Table if Data is Deleted ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nMineral Resource ID: " + mineral_res_id + "\n Check in Table if Data is Deleted "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

}
