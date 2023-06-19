/*UpdateMineralResources
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
 * This is a class that represents Update Mineral Resources operations for Earth Resource Database
 * The class provides methods for running different update operations on tables in database
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */

@Log
public class UpdateMineralResources implements ActionListener {
    WorldDBUtils db_Utils;
    public void db_setup(){
        db_Utils = new WorldDBUtils();
        db_Utils.init();
    }

    JFrame frame = new JFrame();
    String[] operation_name = {"Select an operation","1) Update Mineral Resource Name ","2) Update Mineral Resource Amount ","3) Update Mineral Resource Reserve","4) Update State ID","5) Exit from the Menu"} ;
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
            call_update_mineral_res_name();
            log.info("User Selected 'Update Mineral Resource Name'");
        }
        if(selectedOperation.equals(operation_name[2])) {
            removeComponents();
            call_update_mineral_res_amount();
            log.info("User Selected 'Update Mineral Resource Amount'");
        }

        if(selectedOperation.equals(operation_name[3])) {
            removeComponents();
            call_update_mineral_res_reserve();
            log.info("User Selected 'Update Mineral Resource Reserve'");
        }

        if(selectedOperation.equals(operation_name[4])) {
            removeComponents();
            call_update_state_id();
            log.info("User Selected 'Update Mineral Resource State ID'");
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

    // update mineral resource name
    void call_update_mineral_res_name(){

        JLabel mineral_res_id_label = new JLabel("Mineral Resource ID :");
        mineral_res_id_label.setBounds(140, 200, 180, 40);
        mineral_res_id_label.setForeground(Color.white);
        frame.add(mineral_res_id_label);
        componentsToRemove.add(mineral_res_id_label);

        JTextField mineral_res_id_label_text = new JTextField();
        mineral_res_id_label_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(mineral_res_id_label_text);
        componentsToRemove.add(mineral_res_id_label_text);

        JLabel new_mineral_res_name_label = new JLabel("New Mineral Resource Name :");
        new_mineral_res_name_label.setBounds(100, 300, 200, 40);
        new_mineral_res_name_label.setForeground(Color.white);
        frame.add(new_mineral_res_name_label);
        componentsToRemove.add(new_mineral_res_name_label);

        JTextField new_mineral_res_name_text = new JTextField();
        new_mineral_res_name_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_mineral_res_name_text);
        componentsToRemove.add(new_mineral_res_name_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mineral_resource_id = Integer.parseInt(mineral_res_id_label_text.getText().toUpperCase());
                String new_mineral_resource_name =new_mineral_res_name_text.getText();
                new_mineral_resource_name = new_mineral_resource_name.substring(0, 1).toUpperCase() + new_mineral_resource_name.substring(1).toLowerCase();
                
                // perform necessary action with the input data
                db_setup();
                db_Utils.update_mineral_res_name(new_mineral_resource_name,mineral_resource_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nMineral Resource ID: " + mineral_resource_id + "\n New Mineral Resource Name : " + new_mineral_resource_name + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nMineral Resource ID: " + mineral_resource_id + "\n New Mineral Resource Name : " + new_mineral_resource_name + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();

    }

    // update mineral resource amount
    void call_update_mineral_res_amount(){

        JLabel mineral_res_id_label = new JLabel("Mineral Resource ID :");
        mineral_res_id_label.setBounds(140, 200, 180, 40);
        mineral_res_id_label.setForeground(Color.white);
        frame.add(mineral_res_id_label);
        componentsToRemove.add(mineral_res_id_label);

        JTextField mineral_res_id_label_text = new JTextField();
        mineral_res_id_label_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(mineral_res_id_label_text);
        componentsToRemove.add(mineral_res_id_label_text);

        JLabel new_mineral_res_amount_label = new JLabel("New Mineral Resource Amount :");
        new_mineral_res_amount_label.setBounds(100, 300, 200, 40);
        new_mineral_res_amount_label.setForeground(Color.white);
        frame.add(new_mineral_res_amount_label);
        componentsToRemove.add(new_mineral_res_amount_label);

        JTextField new_mineral_res_amount_text = new JTextField();
        new_mineral_res_amount_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_mineral_res_amount_text);
        componentsToRemove.add(new_mineral_res_amount_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mineral_resource_id = Integer.parseInt(mineral_res_id_label_text.getText().toUpperCase());
                int new_mineral_amount = Integer.parseInt(new_mineral_res_amount_text.getText());

                // perform necessary action with the input data
                db_setup();
                db_Utils.update_mineral_res_amount(new_mineral_amount,mineral_resource_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nMineral Resource ID: " + mineral_resource_id + "\n New Mineral Resource Amount : " + new_mineral_amount + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nMineral Resource ID: " + mineral_resource_id + "\n New Mineral Resource Amount : " + new_mineral_amount + "\n Check in Table if Data is Updated "+"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // update mineral resource reserve
    void call_update_mineral_res_reserve(){
        JLabel mineral_res_id_label = new JLabel("Mineral Resource ID :");
        mineral_res_id_label.setBounds(140, 200, 180, 40);
        mineral_res_id_label.setForeground(Color.white);
        frame.add(mineral_res_id_label);
        componentsToRemove.add(mineral_res_id_label);

        JTextField mineral_res_id_label_text = new JTextField();
        mineral_res_id_label_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(mineral_res_id_label_text);
        componentsToRemove.add(mineral_res_id_label_text);

        JLabel new_mineral_res_reserve_label = new JLabel("New Mineral Resource Reserve :");
        new_mineral_res_reserve_label.setBounds(100, 300, 200, 40);
        new_mineral_res_reserve_label.setForeground(Color.white);
        frame.add(new_mineral_res_reserve_label);
        componentsToRemove.add(new_mineral_res_reserve_label);

        JTextField new_mineral_res_reserve_text = new JTextField();
        new_mineral_res_reserve_text.setBounds(300, 300, 400, 40); // set the position and size of the text field
        frame.add(new_mineral_res_reserve_text);
        componentsToRemove.add(new_mineral_res_reserve_text);

        JButton submit_button =  new JButton();
        submit_button.setBounds(400,400,200,40);
        submit_button.setText("Submit");
        submit_button.setFocusable(false);
        submit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mineral_resource_id = Integer.parseInt(mineral_res_id_label_text.getText().toUpperCase());
                int new_mineral_reserve = Integer.parseInt(new_mineral_res_reserve_text.getText());

                db_setup();
                db_Utils.update_mineral_res_reserve(new_mineral_reserve,mineral_resource_id);
                // perform necessary action with the input data
                JOptionPane.showMessageDialog(frame, "Data submitted: \nMineral Resource ID: " + mineral_resource_id + "\n New Mineral Reserve Name : " + new_mineral_reserve + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nMineral Resource ID: " + mineral_resource_id + "\n New Mineral Reserve Name : " + new_mineral_reserve + "\n Check in Table if Data is Updated "+ "\nSubmission Successful");

            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

    // update mineral resource state id
    void call_update_state_id(){

        JLabel mineral_res_id_label = new JLabel("Mineral Resource ID :");
        mineral_res_id_label .setBounds(160, 200, 200, 40);
        mineral_res_id_label .setForeground(Color.white);
        frame.add(mineral_res_id_label);
        componentsToRemove.add(mineral_res_id_label);

        JTextField mineral_res_id_text = new JTextField();
        mineral_res_id_text.setBounds(300, 200, 400, 40); // set the position and size of the text field
        frame.add(mineral_res_id_text);
        componentsToRemove.add(mineral_res_id_text);

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
                int mineral_res_id = Integer.parseInt(mineral_res_id_text.getText().toUpperCase());
                String new_state_id =new_state_id_text.getText().toUpperCase();;

                // perform necessary action with the input data
                db_setup();
                db_Utils.update_mineral_res_state_id(new_state_id,mineral_res_id);
                JOptionPane.showMessageDialog(frame, "Data submitted: \nMineral Resource ID: " + mineral_res_id + "\n New State ID : " + new_state_id + "\n Check in Table if Data is Updated ", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);
                log.info("Data submitted: \nMineral Resource ID: " + mineral_res_id + "\n New State ID : " + new_state_id + "\n Check in Table if Data is Updated "+"\nSubmission Successful");
            }
        });
        frame.add(submit_button);
        componentsToRemove.add(submit_button);
        // update the frame to show the added components
        frame.revalidate();
        frame.repaint();
    }

}
