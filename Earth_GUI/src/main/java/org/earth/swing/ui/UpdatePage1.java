/*UpdatePage1
 *Version 1.0
 *4-04-2023
 *Copyright notice
 */

package org.earth.swing.ui;
import lombok.extern.java.Log;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a class that represents Update Page Menu for Earth_GUI
 * The class provides methods for selecting table to perform update operations from database
 * also each event and action performed by the user are logged into a external file "earth%u.log"
 * I have tried to name methods appropriately for easy understanding of its functionality while reading through the code
 */


@Log
public class UpdatePage1 implements ActionListener {

    JFrame frame = new JFrame();
    JButton continents_button =  new JButton();
    JButton country_button =  new JButton();
    JButton states_button =  new JButton();
    JButton agriculture_resource_button =  new JButton();
    JButton mineral_resource_button =  new JButton();
    JButton exit_button =  new JButton();
    ImageIcon logo = new ImageIcon("Earth_GUI/src/main/resources/earth_icon_1.png");
    public void gui_func() {

        JLabel title = new JLabel("Select The Table to Update", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.PLAIN, 20));
        title.setForeground(Color.white);
        title.setBounds(350, 25, 300, 50);
        frame.add(title);

        Image scaledImage = logo.getImage().getScaledInstance(title.getHeight(), title.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledLogo = new ImageIcon(scaledImage);
        title.setIcon(scaledLogo);

        continents_button.setBounds(400,100,200,50);
        continents_button.setText("Continents Table");
        continents_button.setFocusable(false);
        continents_button.addActionListener(this::actionPerformed);
        frame.add(continents_button);

        country_button.setBounds(400,200,200,50);
        country_button.setText("Country Table");
        country_button.setFocusable(false);
        country_button.addActionListener(this::actionPerformed);
        frame.add(country_button);

        states_button.setBounds(400,300,200,50);
        states_button.setText("State Table");
        states_button.setFocusable(false);
        states_button.addActionListener(this::actionPerformed);
        frame.add(states_button);

        agriculture_resource_button.setBounds(400,400,200,50);
        agriculture_resource_button.setText("Agriculture Resource Table");
        agriculture_resource_button.setFocusable(false);
        agriculture_resource_button.addActionListener(this::actionPerformed);
        frame.add(agriculture_resource_button);

        mineral_resource_button.setBounds(400,500,200,50);
        mineral_resource_button.setText("Mineral Resource Table");
        mineral_resource_button.setFocusable(false);
        mineral_resource_button.addActionListener(this::actionPerformed);
        frame.add(mineral_resource_button);

        exit_button.setBounds(400,600,200,50);
        exit_button.setText("Exit from the menu");
        exit_button.setFocusable(false);
        exit_button.addActionListener(this::actionPerformed);
        frame.add(exit_button);

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
        if(e.getSource()==continents_button) {

            UpdateContinents U1 = new UpdateContinents();
            U1.gui_func();
            frame.dispose();
            log.info(" User Selected to Update 'Continents Table'");
        }
        if(e.getSource()==country_button) {
            UpdateCountries U2 = new UpdateCountries();
            U2.gui_func();
            frame.dispose();
            log.info(" User Selected to Update 'Countries Table'");
        }

        if(e.getSource()==states_button) {
           UpdateStates U3 = new UpdateStates();
           U3.gui_func();
           frame.dispose();
           log.info(" User Selected to Update 'States Table'");
        }
        if(e.getSource()==agriculture_resource_button) {
            UpdateAgricultureResources U4 = new UpdateAgricultureResources();
            U4.gui_func();
            frame.dispose();
            log.info(" User Selected to Update 'Agriculture Resource Table'");
        }

        if(e.getSource()==mineral_resource_button) {
            UpdateMineralResources U5 = new UpdateMineralResources();
            U5.gui_func();
            frame.dispose();
            log.info(" User Selected to Update 'Mineral Resource Table'");
        }
        if(e.getSource()==exit_button) {
            Home  h1 = new Home();
            h1.gui_func();
            frame.dispose();
            log.info(" User Selected 'Exit from the Menu'");
        }
    }


}
