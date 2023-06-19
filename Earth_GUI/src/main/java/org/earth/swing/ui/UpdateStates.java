/*UpdateStates
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
 * This is a class that represents Update States operations for Earth Resource Database
 * The class provides methods for running different update operations on States table in database
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */

@Log
public class UpdateStates implements ActionListener {

    WorldDBUtils db_Utils;
    public void db_setup(){
        db_Utils = new WorldDBUtils();
        db_Utils.init();
    }

    JFrame frame = new JFrame();
    String[] operation_name = {"Select an operation","1) Update State ID","2) Update State Name","3) Update Country ID","4) Update State Population","5) Exit the Menu"} ;
    JComboBox combobox = new JComboBox(operation_name);

    ArrayList<Component> componentsToRemove = new ArrayList<>();
    ImageIcon logo = new ImageIcon("Earth_GUI/src/main/resources/earth_icon_1.png");
    public void gui_func() {

        JLabel title = new JLabel("Earth Resource Database : Select Column to Update", JLabel.CENTER);
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
            call_update_state_id();
            log.info(" User Selected 'Update State ID'");
        }
        if(selectedOperation.equals(operation_name[2])) {
            removeComponents();
            call_update_state_name();
            log.info(" User Selected 'Update State Name'");
        }

        if(selectedOperation.equals(operation_name[3])) {
            removeComponents();
            call_update_country_id();
            log.info(" User Selected 'Update Country Name'");
        }

        if(selectedOperation.equals(operation_name[4])) {
            removeComponents();
            call_update_state_population();
            log.info(" User Selected 'Update State Population'");
        }

        if(selectedOperation.equals(operation_name[5])) {
            UpdatePage1 U1 = new UpdatePage1();
            U1.gui_func();
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

    // update state id
    void call_update_state_id(){

        JLabel state_id_label = new JLabel("State ID :");
        state_id_label.setBounds(200, 200, 100, 40);
        state_id_label.setForeground(Color.white);
        frame.add(state_id_label);
        componentsToRemove.add(state_id_label);

        JTextField state_id_text = new JTextField();
        state_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(state_id_text);
        componentsToRemove.add(state_id_text);

        JLabel new_state_id_label = new JLabel("New State ID :");
        new_state_id_label.setBounds(190, 300, 180, 40);
        new_state_id_label.setForeground(Color.white);
        frame.add(new_state_id_label);
        componentsToRemove.add(new_state_id_label);

        JTextField new_state_id_text = new JTextField();
        new_state_id_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_state_id_text);
        componentsToRemove.add(new_state_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String state_id = state_id_text.getText().toUpperCase();
                String new_state_id =new_state_id_text.getText().toUpperCase();;

                // perform necessary action with the input data
                db_setup();
                db_Utils.update_state_id(new_state_id,state_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nState ID: " + state_id + "\n New State ID : " + new_state_id + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nState ID: " + state_id + "\n New State ID : " + new_state_id + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // update state name
    void call_update_state_name(){

        JLabel state_id_label = new JLabel("State ID :");
        state_id_label.setBounds(200, 200, 100, 40);
        state_id_label.setForeground(Color.white);
        frame.add(state_id_label);
        componentsToRemove.add(state_id_label);

        JTextField state_id_text = new JTextField();
        state_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(state_id_text);
        componentsToRemove.add(state_id_text);

        JLabel new_state_name_label = new JLabel("New State Name :");
        new_state_name_label.setBounds(190, 300, 180, 40);
        new_state_name_label.setForeground(Color.white);
        frame.add(new_state_name_label);
        componentsToRemove.add(new_state_name_label);

        JTextField new_state_name_text = new JTextField();
        new_state_name_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_state_name_text);
        componentsToRemove.add(new_state_name_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String state_id = state_id_text.getText().toUpperCase();
                String new_state_name =new_state_name_text.getText();
                new_state_name = new_state_name.substring(0, 1).toUpperCase() + new_state_name.substring(1).toLowerCase();
                db_setup();
                db_Utils.update_state_name(new_state_name,state_id);

                // perform necessary action with the input data
                JOptionPane.showMessageDialog(frame, "Data submitted: \nState ID: " + state_id + "\n New State Name : " + new_state_name + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nState ID: " + state_id + "\n New State Name : " + new_state_name + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // update country id
    void call_update_country_id(){

        JLabel state_id_label = new JLabel("State ID :");
        state_id_label.setBounds(200, 200, 100, 40);
        state_id_label.setForeground(Color.white);
        frame.add(state_id_label);
        componentsToRemove.add(state_id_label);

        JTextField state_id_text = new JTextField();
        state_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(state_id_text);
        componentsToRemove.add(state_id_text);

        JLabel new_country_id_label = new JLabel("New Country ID :");
        new_country_id_label.setBounds(190, 300, 180, 40);
        new_country_id_label.setForeground(Color.white);
        frame.add(new_country_id_label);
        componentsToRemove.add(new_country_id_label);

        JTextField new_country_id_text = new JTextField();
        new_country_id_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_country_id_text);
        componentsToRemove.add(new_country_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String state_id = state_id_text.getText().toUpperCase();
                String new_country_id =new_country_id_text.getText().toUpperCase();

                // perform necessary action with the input data
                db_setup();
                db_Utils.update_state_country_id(new_country_id,state_id);

                JOptionPane.showMessageDialog(frame, "Data submitted: \nState ID: " + state_id + "\n New Country ID : " + new_country_id + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nState ID: " + state_id + "\n New Country ID : " + new_country_id + "\n Check in Table if Data is Updated "+"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // update state population
    void call_update_state_population(){

        JLabel state_id_label = new JLabel("State ID :");
        state_id_label.setBounds(200, 200, 100, 40);
        state_id_label.setForeground(Color.white);
        frame.add(state_id_label);
        componentsToRemove.add(state_id_label);

        JTextField state_id_text = new JTextField();
        state_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(state_id_text);
        componentsToRemove.add(state_id_text);

        JLabel new_state_population_label = new JLabel("New State Population :");
        new_state_population_label.setBounds(170, 300, 190, 40);
        new_state_population_label.setForeground(Color.white);
        frame.add(new_state_population_label);
        componentsToRemove.add(new_state_population_label);

        JTextField new_state_population_text = new JTextField();
        new_state_population_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_state_population_text);
        componentsToRemove.add(new_state_population_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String state_id = state_id_text.getText().toUpperCase();
                int new_state_population = Integer.parseInt(new_state_population_text.getText());
                // perform necessary action with the input data
                db_setup();
                db_Utils.update_state_population(new_state_population,state_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nState ID: " + state_id + "\n New State Population : " + new_state_population + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nState ID: " + state_id + "\n New State Population : " + new_state_population + "\n Check in Table if Data is Updated "+"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

}
