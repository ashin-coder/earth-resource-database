/*UpdateContinents
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
 * This is a class that represents Update Continents operations for Earth Resource Database
 * The class provides methods for running different update operations on Continents table in database
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */


@Log
public class UpdateContinents implements ActionListener {

    WorldDBUtils db_Utils;
    public void db_setup(){
        db_Utils = new WorldDBUtils();
        db_Utils.init();
    }


    JFrame frame = new JFrame();
    String[] operation_name = {"Select an operation","1) Update Continent ID","2) Update Continent Name","2) Exit from the Menu"} ;
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
            call_update_continent_id();
            log.info(" User Selected 'Update Continent ID'");
        }
        if(selectedOperation.equals(operation_name[2])) {
            removeComponents();
            call_update_continent_name();
            log.info(" User Selected 'Update Continent Name'");
        }

        if(selectedOperation.equals(operation_name[3])) {
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

    // update continent id
    void call_update_continent_id(){

        JLabel continent_id_label = new JLabel("Continent ID :");
        continent_id_label.setBounds(200, 200, 100, 40);
        continent_id_label.setForeground(Color.white);
        frame.add(continent_id_label);
        componentsToRemove.add(continent_id_label);

        JTextField continent_id_text = new JTextField();
        continent_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(continent_id_text);
        componentsToRemove.add(continent_id_text);

        JLabel new_continent_id_label = new JLabel("New Continent ID:");
        new_continent_id_label.setBounds(190, 300, 180, 40);
        new_continent_id_label.setForeground(Color.white);
        frame.add(new_continent_id_label);
        componentsToRemove.add(new_continent_id_label);

        JTextField new_continent_id_text = new JTextField();
        new_continent_id_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_continent_id_text);
        componentsToRemove.add(new_continent_id_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String continent_id = continent_id_text.getText().toUpperCase();
                String new_continent_id =new_continent_id_text.getText().toUpperCase();

                // perform necessary action with the input data
                db_setup();
                db_Utils.update_continent_id(new_continent_id,continent_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nContinent ID: " + continent_id + "\n New Continent ID : " + new_continent_id + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nContinent ID: " + continent_id + "\n New Continent ID : " + new_continent_id + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // update continent name
    void call_update_continent_name(){

        JLabel continent_id_label = new JLabel("Continent ID :");
        continent_id_label.setBounds(200, 200, 100, 40);
        continent_id_label.setForeground(Color.white);
        frame.add(continent_id_label);
        componentsToRemove.add(continent_id_label);

        JTextField continent_id_text = new JTextField();
        continent_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(continent_id_text);
        componentsToRemove.add(continent_id_text);

        JLabel new_continent_name_label = new JLabel("New Continent Name:");
        new_continent_name_label.setBounds(170, 300, 180, 40);
        new_continent_name_label.setForeground(Color.white);
        frame.add(new_continent_name_label);
        componentsToRemove.add(new_continent_name_label);

        JTextField new_continent_name_text = new JTextField();
        new_continent_name_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_continent_name_text);
        componentsToRemove.add(new_continent_name_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String continent_id = continent_id_text.getText().toUpperCase();
                String new_continent_name =new_continent_name_text.getText();
                new_continent_name = new_continent_name.substring(0, 1).toUpperCase() + new_continent_name.substring(1).toLowerCase();
                // perform necessary action with the input data

                db_setup();
                db_Utils.update_continent_name(new_continent_name,continent_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nContinent ID: " + continent_id + "\n New Continent Name : " + new_continent_name + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nContinent ID: " + continent_id + "\n New Continent Name : " + new_continent_name + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }













}
