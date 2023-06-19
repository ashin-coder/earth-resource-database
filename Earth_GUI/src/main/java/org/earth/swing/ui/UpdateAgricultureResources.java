/*UpdateAgricultureResources
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
 * This is a class that represents Update Agricultural Resources operations for Earth Resource Database
 * The class provides methods for running different update operations on tables in database
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */


@Log
public class UpdateAgricultureResources implements ActionListener {
    WorldDBUtils db_Utils;
    public void db_setup(){
        db_Utils = new WorldDBUtils();
        db_Utils.init();
    }

    JFrame frame = new JFrame();
    String[] operation_name = {"Select an operation","1) Update Agriculture Resource Name ","2) Update Agriculture Resource Amount ","3) Update Agriculture Resource Category","4) Update State ID","5) Exit from the Menu"} ;
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
            call_update_agri_res_name();
            log.info("User Selected 'Update Agriculture Resource Name'");
        }
        if(selectedOperation.equals(operation_name[2])) {
            removeComponents();
            call_update_agri_res_amount();
            log.info("User Selected 'Update Agriculture Resourc Amount'");
        }

        if(selectedOperation.equals(operation_name[3])) {
            removeComponents();
            call_update_agri_res_category();
            log.info("User Selected 'Update Agriculture Resource Category'");
        }

        if(selectedOperation.equals(operation_name[4])) {
            removeComponents();
            call_update_state_id();
            log.info("User Selected 'Update Agriculture Resource State ID'");
        }
        
        if(selectedOperation.equals(operation_name[5])) {
            UpdatePage1 U1 = new UpdatePage1();
            U1.gui_func();
            frame.dispose();
            log.info("User Selected 'Exit from the Menu'");
        }
    }


    void removeComponents() {
        for (Component component : componentsToRemove) {
            frame.getContentPane().remove(component);
        }
        componentsToRemove.clear();
    }

    // update agriculture resource name
    void call_update_agri_res_name(){

        JLabel agri_res_id_label = new JLabel("Agriculture Resource ID :");
        agri_res_id_label.setBounds(140, 200, 180, 40);
        agri_res_id_label.setForeground(Color.white);
        frame.add(agri_res_id_label);
        componentsToRemove.add(agri_res_id_label);

        JTextField agri_res_id_label_text = new JTextField();
        agri_res_id_label_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(agri_res_id_label_text);
        componentsToRemove.add(agri_res_id_label_text);

        JLabel new_agri_res_name_label = new JLabel("New Agriculture Resource Name :");
        new_agri_res_name_label.setBounds(100, 300, 200, 40);
        new_agri_res_name_label.setForeground(Color.white);
        frame.add(new_agri_res_name_label);
        componentsToRemove.add(new_agri_res_name_label);

        JTextField new_agri_res_name_text = new JTextField();
        new_agri_res_name_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_agri_res_name_text);
        componentsToRemove.add(new_agri_res_name_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int agri_resource_id = Integer.parseInt(agri_res_id_label_text.getText().toUpperCase());
                String new_agri_resource_name =new_agri_res_name_text.getText();
                new_agri_resource_name = new_agri_resource_name.substring(0, 1).toUpperCase() + new_agri_resource_name.substring(1).toLowerCase();
                // perform necessary action with the input data
                db_setup();
                db_Utils.update_agri_res_name(new_agri_resource_name,agri_resource_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nAgriculure Resource ID: " + agri_resource_id + "\n New Agriculture Resource Name : " + new_agri_resource_name + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nAgriculure Resource ID: " + agri_resource_id + "\n New Agriculture Resource Name : " + new_agri_resource_name + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();

    }

    // update agriculture resource amount
    void call_update_agri_res_amount(){

        JLabel agri_res_id_label = new JLabel("Agriculture Resource ID :");
        agri_res_id_label.setBounds(140, 200, 180, 40);
        agri_res_id_label.setForeground(Color.white);
        frame.add(agri_res_id_label);
        componentsToRemove.add(agri_res_id_label);

        JTextField agri_res_id_label_text = new JTextField();
        agri_res_id_label_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(agri_res_id_label_text);
        componentsToRemove.add(agri_res_id_label_text);

        JLabel new_agri_res_amount_label = new JLabel("New Agriculture Resource Amount :");
        new_agri_res_amount_label.setBounds(90, 300, 210, 40);
        new_agri_res_amount_label.setForeground(Color.white);
        frame.add(new_agri_res_amount_label);
        componentsToRemove.add(new_agri_res_amount_label);

        JTextField new_agri_res_amount_text = new JTextField();
        new_agri_res_amount_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_agri_res_amount_text);
        componentsToRemove.add(new_agri_res_amount_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int agri_resource_id = Integer.parseInt(agri_res_id_label_text.getText().toUpperCase());
                double new_agri_resource_amount = Double.parseDouble(new_agri_res_amount_text.getText());

                // perform necessary action with the input data
                db_setup();
                db_Utils.update_agri_res_amount(new_agri_resource_amount,agri_resource_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nAgriculure Resource ID: " + agri_resource_id + "\n New Agriculture Resource Amount : " + new_agri_resource_amount + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nAgriculure Resource ID: " + agri_resource_id + "\n New Agriculture Resource Amount : " + new_agri_resource_amount + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();

    }

    // update agriculture resource category
    void call_update_agri_res_category(){
        JLabel agri_res_id_label = new JLabel("Agriculture Resource ID :");
        agri_res_id_label.setBounds(140, 200, 180, 40);
        agri_res_id_label.setForeground(Color.white);
        frame.add(agri_res_id_label);
        componentsToRemove.add(agri_res_id_label);

        JTextField agri_res_id_label_text = new JTextField();
        agri_res_id_label_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(agri_res_id_label_text);
        componentsToRemove.add(agri_res_id_label_text);

        JLabel new_agri_res_category_label = new JLabel("New Agriculture Resource Category :");
        new_agri_res_category_label.setBounds(90, 300, 280, 40);
        new_agri_res_category_label.setForeground(Color.white);
        frame.add(new_agri_res_category_label);
        componentsToRemove.add(new_agri_res_category_label);

        JTextField new_agri_res_category_text = new JTextField();
        new_agri_res_category_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_agri_res_category_text);
        componentsToRemove.add(new_agri_res_category_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int agri_resource_id = Integer.parseInt(agri_res_id_label_text.getText().toUpperCase());
                String new_agri_resource_category =new_agri_res_category_text.getText();
                new_agri_resource_category = new_agri_resource_category.substring(0, 1).toUpperCase() + new_agri_resource_category.substring(1).toLowerCase();

                // perform necessary action with the input data
                db_setup();
                db_Utils.update_agri_res_category(new_agri_resource_category,agri_resource_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nAgriculure Resource ID: " + agri_resource_id + "\n New Agriculture Resource Category : " + new_agri_resource_category + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nAgriculure Resource ID: " + agri_resource_id + "\n New Agriculture Resource Category : " + new_agri_resource_category + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // update agriculture resource state id
    void call_update_state_id(){
        JLabel agri_res_id_label = new JLabel("Agriculture Resource ID :");
        agri_res_id_label.setBounds(140, 200, 180, 40);
        agri_res_id_label.setForeground(Color.white);
        frame.add(agri_res_id_label);
        componentsToRemove.add(agri_res_id_label);

        JTextField agri_res_id_text = new JTextField();
        agri_res_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(agri_res_id_text);
        componentsToRemove.add(agri_res_id_text);

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
                int agri_res_id = Integer.parseInt(agri_res_id_text.getText().toUpperCase());
                String new_state_id =new_state_id_text.getText().toUpperCase();

                // perform necessary action with the input data
                db_setup();
                db_Utils.update_agri_res_state_id(new_state_id,agri_res_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nAgriculture Resource ID: " + agri_res_id + "\n New State ID : " + new_state_id + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nAgriculture Resource ID: " + agri_res_id + "\n New State ID : " + new_state_id + "\n Check in Table if Data is Updated "+"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

}
